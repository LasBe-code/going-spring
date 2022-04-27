package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Booking;
import model.Business;
import model.Picture;
import model.Room;
import repository.RoomDao;

@Service
public class RoomService {
	private Map<Object, Object> mainMap = new HashMap<Object, Object>();
	
	private final RoomDao rd;
	
	@Autowired
	public RoomService(RoomDao rd) {
		this.rd = rd;
	}

	
	public Map<Object, Object> List(String bu_email) throws Exception{
		List<Room> list = new ArrayList<Room>();
		Map<Object, Object> map = new HashMap<>();
		List<Picture> picList = new ArrayList<Picture>();
		
//		business 메일을 사용하는 사업자의 객실 리스트 저장
		list = rd.roomList(bu_email);
		
//		각 객실번호에 해당하는 사진가져오기
		for(Room room : list) {
			picList = rd.selectPic(room.getPic_num());
			map.put(room.getRo_num(), picList.get(0).getLocation().trim());
		}
		mainMap.clear();
		mainMap.put("list", list);
		mainMap.put("map", map);
		
		return mainMap;
	}
	
	public Map<Object, Object> roomInsertPro(Room room, String bu_email) throws Exception {
		
		Picture p = new Picture();
		int rowCnt = 0;
		
		int roomNum = rd.nextRoNum();
		int picNum = rd.nextPicNum();
		
		room.setRo_num(roomNum);
		room.setBu_email(bu_email);
		room.setPic_num(picNum);
		
		String[] picList = room.getLocation().split("\\n");
		
		for (String pic : picList) {
//			사진 링크를 picture table에 저장
			p = new Picture(picNum, pic.trim());
			rowCnt = rd.insertPicture(p);
		}
		
//		room객체에 저장된 값을 room table에 저장
		int rnum = rd.insertRoom(room);
		mainMap.clear();
		mainMap.put("rnum", rnum);
		mainMap.put("rowCnt", rowCnt);
		
		return mainMap;
	}
	

	public Map<Object, Object> roominfo(Integer ro_num) throws Exception{

		List<String> p_list = new ArrayList<String>();
		
		Room room = rd.selectRoom(ro_num);
		List<Picture> picList = rd.selectPic(room.getPic_num());
		for(int i=0; i<picList.size();i++) {
			p_list.add(picList.get(i).getLocation());
		}
//		선택한 객실의 정보를 가져와서 저장
		String info = room.getRo_info().replace("\r\n", "<br/>");
		mainMap.clear();
		mainMap.put("p_list", p_list);
		mainMap.put("room", room);
		mainMap.put("ro_num", ro_num);
		mainMap.put("pic_num", room.getPic_num());
		mainMap.put("info", info);
		
		return mainMap;
	}
	
	public Map<Object, Object> roomUpdate(Integer ro_num, Integer pic_num) throws Exception {

		String pic = "";
		
		Room room = rd.selectRoom(ro_num);
		List<Picture> piclist = rd.selectPic(pic_num);
		
		for(Picture p : piclist) {
			pic += p.getLocation()+"\n";
		}
		mainMap.clear();
		mainMap.put("room", room);
		mainMap.put("pic", pic);
		return mainMap;
	}
	
	
	public Map<Object, Object> roomUpdatePro(Room room) throws Exception {
		Picture picLocation = null;
		
		int p = rd.deleteLocation(room.getPic_num());
		
		String[] picList = room.getLocation().split("\n");
		int pic = 0;	
		for(String lo : picList) {
			picLocation = new Picture(room.getPic_num(),lo);
			pic = rd.insertPicture(picLocation);
		}
		
		// room객체에 저장된 값을 room table에 저장
		int rnum = rd.updateRoom(room);
		
		mainMap.clear();
		mainMap.put("rnum", rnum);
		mainMap.put("pic", pic);
		return mainMap;
	}
	
	public int roomDeltePro(Room r, Business bu, String bu_email) throws Exception{
//		객실을 삭제하기 위해 사업자 비밀번호와 입력한 비밀번호 비교
		String pwd = bu.getBu_password();
		int ro_num = r.getRo_num();
		int room = 0;
		// 사업자 비밀번호 찾기
		Business business = rd.selectBu(bu_email);
		
		mainMap.clear();
		if(pwd == null || "".equals(pwd) || !pwd.equals(business.getBu_password())) {
			mainMap.put("msg", "비밀번호가 틀렸습니다.");
		}else {
			// 비밀번호가 일치하면 객실 삭제
			room = rd.deleteRoom(bu_email, ro_num);
		}
		
		return room;
	}
	
	public List<Room> getList(String bu_email) throws Exception {
		return rd.roomList(bu_email);
	}


	public List<Picture> selectPic(int pic_num) throws Exception {
		return rd.selectPic(pic_num);
	}


	public int nextRoNum() throws Exception{
		return rd.nextRoNum();
	}


	public int nextPicNum() throws Exception{
		return rd.nextPicNum();
	}


	public int insertPicture(Picture p) throws Exception  {
		return rd.insertPicture(p);
	}


	public int insertRoom(Room room)  throws Exception {
		return rd.insertRoom(room);
	}


	public Room selectRoom(Integer ro_num) throws Exception  {
		return rd.selectRoom(ro_num);
	}


	public int deleteLocation(int pic_num) throws Exception  {
		return rd.deleteLocation(pic_num);
	}


	public int updateRoom(Room room)  throws Exception {
		return rd.updateRoom(room);
	}


	public Business selectBu(String bu_email) throws Exception  {
		return rd.selectBu(bu_email);
	}


	public int deleteRoom(String bu_email, int ro_num) throws Exception  {
		return rd.deleteRoom(bu_email, ro_num);
	}


	public List<Booking> selectBkList(Map<String, Object> map) throws Exception  {
		return rd.selectBkList(map);
	}


	public int countBoard(Map<String, Object> map) throws Exception  {
		return rd.countBoard(map);
	}


	public List<Booking> searchStatus(Map<String, Object> map) throws Exception  {
		return rd.searchStatus(map);
	}


	public int countBoardStatus(Map<String, Object> map) throws Exception  {
		return rd.countBoardStatus(map);
	}


	public List<Booking> searchName(Map<String, Object> map) throws Exception  {
		return rd.searchName(map);
	}


	public int countBoardSearchName(Map<String, Object> map)  throws Exception {
		return rd.countBoardSearchName(map);
	}


	public Booking selectSales(Map<String, Object> map) throws Exception  {
		return rd.selectSales(map);
	}


	public Booking selectAreaSales(Map<String, Object> map)  throws Exception {
		return rd.selectAreaSales(map);
	}


	public List<Booking> selectNotCheckin(Map<String, Object> map) throws Exception  {
		return rd.selectNotCheckin(map);
	}


	public List<Booking> selectcheckinOk(Map<String, Object> map)  throws Exception {
		return rd.selectcheckinOk(map);
	}


	public int updateTodayCheckin(Map<String, Object> map)  throws Exception {
		return rd.updateTodayCheckin(map);
	}


	public List<Booking> selectNotCheckOut(Map<String, Object> map)  throws Exception {
		return rd.selectNotCheckOut(map);
	}


	public List<Booking> selectcheckOutOk(Map<String, Object> map) throws Exception  {
		return rd.selectcheckOutOk(map);
	}


	public int updateAndDeleteTodayCheckOut(Map<String, Object> map) throws Exception  {
		return rd.updateAndDeleteTodayCheckOut(map);
	}

}
