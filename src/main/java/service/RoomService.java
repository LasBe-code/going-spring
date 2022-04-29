package service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Booking;
import model.Business;
import model.Picture;
import model.Room;
import model.SearchDTO;
import repository.RoomDao;
import util.DateParse;

@Service
public class RoomService {
	private Map<Object, Object> serviceMap = new HashMap<Object, Object>();
	private Map<String, Object> map = new HashMap<String, Object>();
	private final RoomDao roomDao;
	
	@Autowired
	public RoomService(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	
	public Map<Object, Object> List(String bu_email) throws Exception{
		List<Room> list = new ArrayList<Room>();
		Map<Object, Object> map = new HashMap<>();
		List<Picture> picList = new ArrayList<Picture>();
		
//		business 메일을 사용하는 사업자의 객실 리스트 저장
		list = roomDao.roomList(bu_email);
		
//		각 객실번호에 해당하는 사진가져오기
		for(Room room : list) {
			picList = roomDao.selectPic(room.getPic_num());
			map.put(room.getRo_num(), picList.get(0).getLocation().trim());
		}
		serviceMap.clear();
		serviceMap.put("list", list);
		serviceMap.put("map", map);
		
		return serviceMap;
	}
	
	public Map<Object, Object> roomInsertPro(Room room, String bu_email) throws Exception {
		
		Picture p = new Picture();
		int rowCnt = 0;
		
		int roomNum = roomDao.nextRoNum();
		int picNum = roomDao.nextPicNum();
		
		room.setRo_num(roomNum);
		room.setBu_email(bu_email);
		room.setPic_num(picNum);
		
		String[] picList = room.getLocation().split("\\n");
		
		for (String pic : picList) {
//			사진 링크를 picture table에 저장
			p = new Picture(picNum, pic.trim());
			rowCnt = roomDao.insertPicture(p);
		}
		
//		room객체에 저장된 값을 room table에 저장
		int rnum = roomDao.insertRoom(room);
		serviceMap.clear();
		serviceMap.put("rnum", rnum);
		serviceMap.put("rowCnt", rowCnt);
		
		return serviceMap;
	}
	

	public Map<Object, Object> roominfo(Integer ro_num) throws Exception{

		List<String> p_list = new ArrayList<String>();
		
		Room room = roomDao.selectRoom(ro_num);
		List<Picture> picList = roomDao.selectPic(room.getPic_num());
		for(int i=0; i<picList.size();i++) {
			p_list.add(picList.get(i).getLocation());
		}
//		선택한 객실의 정보를 가져와서 저장
		String info = room.getRo_info().replace("\r\n", "<br/>");
		serviceMap.clear();
		serviceMap.put("p_list", p_list);
		serviceMap.put("room", room);
		serviceMap.put("ro_num", ro_num);
		serviceMap.put("pic_num", room.getPic_num());
		serviceMap.put("info", info);
		
		return serviceMap;
	}
	
	public Map<Object, Object> roomUpdate(Integer ro_num, Integer pic_num) throws Exception {

		String pic = "";
		
		Room room = roomDao.selectRoom(ro_num);
		List<Picture> piclist = roomDao.selectPic(pic_num);
		
		for(Picture p : piclist) {
			pic += p.getLocation()+"\n";
		}
		serviceMap.clear();
		serviceMap.put("room", room);
		serviceMap.put("pic", pic);
		return serviceMap;
	}
	
	
	public Map<Object, Object> roomUpdatePro(Room room) throws Exception {
		Picture picLocation = null;
		
		int p = roomDao.deleteLocation(room.getPic_num());
		
		String[] picList = room.getLocation().split("\n");
		int pic = 0;	
		for(String lo : picList) {
			picLocation = new Picture(room.getPic_num(),lo);
			pic = roomDao.insertPicture(picLocation);
		}
		
		// room객체에 저장된 값을 room table에 저장
		int rnum = roomDao.updateRoom(room);
		
		serviceMap.clear();
		serviceMap.put("rnum", rnum);
		serviceMap.put("pic", pic);
		return serviceMap;
	}
	
	public int roomDeltePro(Room r, Business bu, String bu_email) throws Exception{
//		객실을 삭제하기 위해 사업자 비밀번호와 입력한 비밀번호 비교
		String pwd = bu.getBu_password();
		int ro_num = r.getRo_num();
		int room = 0;
		// 사업자 비밀번호 찾기
		Business business = roomDao.selectBu(bu_email);
		
		serviceMap.clear();
		if(pwd == null || "".equals(pwd) || !pwd.equals(business.getBu_password())) {
			serviceMap.put("msg", "비밀번호가 틀렸습니다.");
		}else {
			// 비밀번호가 일치하면 객실 삭제
			room = roomDao.deleteRoom(bu_email, ro_num);
		}
		
		return room;
	}
	
	public Map<Object, Object> reservation(String search, String searchName, HttpServletRequest request, 
			String bu_email, int startPage, int endPage)throws Exception {
		
		serviceMap.clear();
		map.put("bu_email", bu_email);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		// =========== 현재 시간 ==============
		LocalDate now = LocalDate.now();
		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		// 포맷 적용
		String nowDay = now.format(formatter);
		
//		예약 내역 찾기
		List<Booking> bk = new ArrayList<Booking>();
//		검색할 컬럼이름
		map.put("searchName", searchName);
//		검색할 컬럼 값
		map.put("search", search);
		int count = 0;
		
		if("".equals(searchName) || searchName == null || search == null || "".equals(search)) {
			bk = roomDao.selectBkList(map);
			count = roomDao.countBoard(map);
		}
		else if("status".equals(searchName)) {
			if("예약완료".equals(search)) {
				map.put("status", "1");
			}
			else if("결제취소".equals(search)) {
				map.put("status", "2");
			}
			else if("이용완료".equals(search)) {
				map.put("status", "3");
			}
			else if("입실완료".equals(search)) {
				map.put("status", "4");
			}
			else {
				String msg = "예약완료, 결제취소, 이용완료 , 입실완료중 하나를 입력하세요.";
				String url = request.getContextPath()+"/room/reservation";
				serviceMap.put("msg", msg);
				serviceMap.put("url", url);
				
				return serviceMap;
			}
			bk = roomDao.searchStatus(map);
			count = roomDao.countBoardStatus(map);
		}
		else {
			bk = roomDao.searchName(map);
			count = roomDao.countBoardSearchName(map);
		}
		serviceMap.put("bk", bk);
		serviceMap.put("count", count);
		
		return serviceMap;
	}
	
	@SuppressWarnings("unchecked")
	public Map<Object, Object> sales(String bu_email) throws Exception {
		
		String[] month = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
		map.clear();
		map.put("bu_email", bu_email);
		
		String result = "";
		for(String mon : month) {
			map.put("mon", mon);
			Booking bo = roomDao.selectSales(map);
			if(result!="") { 
				result += ","; 
			}

			if(bo == null) {
				result += "['"+mon+"월', "+"0"+"]";

			}
			else {
				result += "['"+mon+"월', "+bo.getPrice()+"]";
			}
		}
		serviceMap.clear();
		serviceMap.put("result", result);
		return serviceMap;
	}
	
	
	public Map<Object, Object> areaSales(String month) throws Exception {
		String[] areas = {"서울", "경기", "강원", "부산"};
		
		if(month == null) {
			LocalDate now = LocalDate.now();
			int month1 = now.getMonthValue();
			month = "0"+month1;
		}
		String result = "";
		map.put("month", month);
		for(String area : areas) {
			map.put("area", area);
//			지역별 월매출
			Booking bo = roomDao.selectAreaSales(map);
			if(result!="") { 
				result += ","; 
			}
			if(bo == null) {
				result += "['"+area+"', "+"0"+"]";
			}
			else {
				result += "['"+area+"', "+bo.getPrice()+"]";
			}
		}
		serviceMap.clear();
		serviceMap.put("result", result);
		serviceMap.put("month", month);
		return serviceMap;
	}
	
	
	public Map<Object, Object> todayCheckin(String bu_email) throws Exception {
		
		String checkin = DateParse.getTodayPlus(0);
		
		map.clear();
		map.put("bu_email", bu_email);
		map.put("checkin", checkin);
//		아직 체크인 안한 객실내역
		List<Booking> notCheckin = roomDao.selectNotCheckin(map);
//		체크인 완료한 객실내역
		List<Booking> checkinOk = roomDao.selectcheckinOk(map);
		
		serviceMap.clear();
		serviceMap.put("notCheckin", notCheckin);
		serviceMap.put("checkinOk", checkinOk);
		return serviceMap;
	}
	
	
	public void updateTodayCheckin(String bo_num, String bu_email) throws Exception {
		String checkin = DateParse.getTodayPlus(0);
		
		map.clear();
		map.put("bu_email", bu_email);
		map.put("bo_num", bo_num);
		map.put("checkin", checkin);
		
		int rowCnt = roomDao.updateTodayCheckin(map);
	}
	
	public Map<Object, Object> todayCheckOut(String bu_email) throws Exception {
		
		String checkout = DateParse.getTodayPlus(0);
		
		map.clear();
		map.put("bu_email", bu_email);
		map.put("checkout", checkout);
//		아직 체크아웃 안한 객실 내역
		List<Booking> notCheckOut = roomDao.selectNotCheckOut(map);
//		체크아웃하고 나간 객실 내역
		List<Booking> checkOutOk = roomDao.selectcheckOutOk(map);
		
		serviceMap.clear();
		serviceMap.put("notCheckOut", notCheckOut);
		serviceMap.put("checkOutOk", checkOutOk);
		return serviceMap;
	}
	
	public void updateTodayCheckOut(String bu_email, String bo_num) throws Exception {
		
		String checkout = DateParse.getTodayPlus(0);
		
		map.clear();
		map.put("bu_email", bu_email);
		map.put("bo_num", bo_num);
		map.put("checkout", checkout);
		
		int rowCnt = roomDao.updateAndDeleteTodayCheckOut(map);
	}


	@SuppressWarnings("unchecked")
	public Map<Object, Object> addressList(SearchDTO searchDTO) throws Exception {
		
		String bu_id = searchDTO.getBu_id();
		String ro_count = searchDTO.getRo_count();
		String checkin = searchDTO.getCheckin();
		String checkout = searchDTO.getCheckout();
		String bu_address = searchDTO.getBu_address();
		
		if(bu_address == null  || bu_id == null || checkin == null ||  checkout == null) {
			bu_address = "서울";
			bu_id = "1";
			checkin = DateParse.strToDate(DateParse.getTodayPlus(0));
			checkout = DateParse.strToDate(DateParse.getTodayPlus(1));
		}
		System.out.println("in = "+checkin);
		System.out.println("out = "+checkout);
		
		map.put("bu_address", bu_address);
		map.put("bu_id", bu_id);
		
		List<Business> addressList = new ArrayList<Business>();
		addressList = roomDao.addressList(map);
		
		String resultAddress = "[";
		String roomTitle = "[";
		String roomPic = "[";
		String bu_email = "[";
		
			for(int i = 0 ; i < addressList.size(); i++) {
			if(i == addressList.size()-1) {
				resultAddress += "'"+addressList.get(i).getBu_address().trim()+"'";
				roomTitle += "'"+addressList.get(i).getBu_title().trim()+"'";
				roomPic += "'"+addressList.get(i).getLocation().trim()+"'";
				bu_email += "'"+addressList.get(i).getBu_email().trim()+"'";
			}
			else {
				resultAddress += "'"+addressList.get(i).getBu_address().trim()+"', ";
				roomTitle += "'"+addressList.get(i).getBu_title().trim()+"', ";
				roomPic += "'"+addressList.get(i).getLocation().trim()+"', ";
				bu_email += "'"+addressList.get(i).getBu_email().trim()+"', ";
			}
		}
		resultAddress += "]";
		roomTitle += "]";
		roomPic += "]";
		bu_email += "]";
		
		serviceMap.put("resultAddress", resultAddress);
		serviceMap.put("roomTitle", roomTitle);
		serviceMap.put("roomPic", roomPic);
		serviceMap.put("bu_email", bu_email);
		serviceMap.put("bu_id", bu_id);
		serviceMap.put("ro_count", ro_count);
		serviceMap.put("checkin", checkin);
		serviceMap.put("checkout", checkout);
		serviceMap.put("bu_address", bu_address);
		
		return serviceMap;
	}


	public Room getRo_name(String ro_name, String bu_email) {
		map.clear();
		map.put("ro_name", ro_name);
		map.put("bu_email", bu_email);
		return roomDao.getRo_name(map);
	}

}
