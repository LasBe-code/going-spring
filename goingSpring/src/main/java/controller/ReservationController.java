package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Booking;
import model.Business;
import model.Member;
import model.Picture;
import model.Reserved;
import model.Room;
import service.MemberDao;
import service.ReservationDao;
import service.ReserveDao;
import service.RoomDao;
import service.SearchDao;

public class ReservationController extends MskimRequestMapping {
	ReservationDao dao = new ReservationDao();
	
	@RequestMapping("reservationList")
	public String reservationList(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(); //session을 불러옴. 
		String email = (String)session.getAttribute("email");
		List<Booking> bookingList = dao.getBookingSelectList(email);
		System.out.println(bookingList);
		Map<Integer, String> picMap = new HashMap<Integer, String>();
		String picLocation = null;
		
		for(Booking booking : bookingList) {
			picLocation = dao.bookingPictureList(booking.getRo_num()).get(0).getLocation();
			System.out.println(picLocation);
			picMap.put(booking.getRo_num(), picLocation);
		}
		
		request.setAttribute("picMap", picMap);
		request.setAttribute("bookingList", bookingList);
		
		return "/view/reservationList/reservationList.jsp";
	}
	
	@RequestMapping("reservationDetail")
    public String reservationDetail (HttpServletRequest request, HttpServletResponse response) {
        String flag = request.getParameter("flag");
        String bo_num = request.getParameter("bo_num");
        String email = (String) request.getSession().getAttribute("email");
        System.out.println(bo_num);
        //bo_num이 getParameter로 넘어왔기때문에 반환형이 String이다/ 그런데 테이블에선 int이므로 String으로 형변환을 해줘야하는데 Integer.parseInt이렇게 써줘야 한다.

        boolean f = Boolean.valueOf(flag);
        //flag 값이 true => 예약 상세내역 정보. false => 취소 내역 정보.
        if (f == false){
            //상태값 변경 1=> 2
            dao.updateBookingStatus(bo_num);
        }

        //bo_num으로 select 
        Booking bookingDetail = dao.getBookingSelectDetail(bo_num);
        request.setAttribute("bookingDetail", bookingDetail);

        MemberDao md = new MemberDao();
        Member m = md.selectMemberOne(email);
        request.setAttribute("member", m);

        return "/view/reservationList/reservationDetail.jsp";
    }
	
	/*
	@RequestMapping("reservationCancelDetail")
	public String reservationCencelDetail(HttpServletRequest request, HttpServletResponse response) {
		return "/view/reservationList/reservationCancelDetail.jsp";
	}
	*/
	
	@RequestMapping("detail")
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		ReserveDao reserveDao = new ReserveDao();
		MemberDao md = new MemberDao();
		RoomDao rd = new RoomDao();
		SearchDao sd = new SearchDao();
		
		String bu_email = request.getParameter("bu_email");
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		String ro_count = request.getParameter("ro_count");
		
		/*
		 * String bu_email = "test@naver.com"; String checkin = "20220304"; String
		 * checkout = "20220307"; String ro_count = "1";
		 */
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("bu_email", bu_email); map.put("ro_count", ro_count);
		
		List<Room> roomList = reserveDao.roomList(map); // 룸 정보 받아오기
		Business bu = md.selectBusinessOne(bu_email); // 사업자, 숙소 정보 받아오기
		List<Picture> buPicList = sd.sbPicList(bu.getPic_num());
		
		Map<Integer, Boolean> roomMap = new HashMap<>();
		for(Room r : roomList) {
			map.put("ro_num", ""+r.getRo_num()); map.put("checkin", checkin); map.put("checkout", String.valueOf(Integer.parseInt(checkout)-1));
			// 방마다 예약 체크
			List<Reserved> list = reserveDao.reserveCheck(map);
			System.out.println(r.getRo_num()); System.out.println(list);
			if(list.isEmpty()) { // 예약된 정보가 없으면 false
				roomMap.put(r.getRo_num(), false);
			} else { // 예약이 되어 있으면 true
				roomMap.put(r.getRo_num(), true); 
			}
			
		}
		
		Map<Integer, Object> roomPicMap = new HashMap<>();
		List<Picture> picList = new ArrayList<Picture>();
		
		for(Room room : roomList) {
			picList = rd.selectPic(room.getPic_num());
			System.out.println(picList);
			roomPicMap.put(room.getRo_num(), picList.get(0).getLocation().trim());
			System.out.println(picList.get(0).getLocation().trim());
		}
		
		System.out.println("business : "+bu);
		System.out.println("roomList : "+roomList);
		System.out.println("roomMap : "+roomMap);
		System.out.println("buPicList : "+buPicList);
		
		request.setAttribute("buPicList", buPicList);		
		request.setAttribute("roomPicMap", roomPicMap);
		request.setAttribute("roomList", roomList);
		request.setAttribute("roomMap", roomMap);
		request.setAttribute("bu", bu);
		request.setAttribute("ro_count", ro_count);
		request.setAttribute("checkin", checkin);
		request.setAttribute("checkout", checkout);
		
		return "/view/reserve/detail.jsp";
	}
	
	@RequestMapping("reserve")
	public String reserve(HttpServletRequest request, HttpServletResponse response) {
		String email = (String) request.getSession().getAttribute("email");
		if(email == null) {
			request.setAttribute("url", request.getContextPath()+"/member/loginForm");
			request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
			return "/view/alert.jsp";
		}
		
		Booking bo = new Booking();
		ReserveDao rd = new ReserveDao();
		MemberDao md = new MemberDao();
		
		// =========== 현재 시간 ==============
		LocalDate now = LocalDate.now();
		// 포맷 정의
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		// 포맷 적용
		String nowDay = now.format(formatter);

		
		bo.setEmail((String) request.getSession().getAttribute("email"));
		bo.setCheckin(request.getParameter("checkin"));
		bo.setCheckout(request.getParameter("checkout"));
		bo.setPrice(request.getParameter("price"));
		bo.setBu_title(request.getParameter("bu_title"));
		bo.setRo_name(request.getParameter("ro_name"));
		bo.setRo_num(Integer.parseInt(request.getParameter("ro_num")));
		bo.setReg_date(nowDay);
		bo.setStatus(1);
		
		Member m = md.selectMemberOne(bo.getEmail());
		
		request.getSession().setAttribute("booking", bo);
		request.setAttribute("member", m);
		
		return "/view/reserve/reserve.jsp";
	}
	
	@RequestMapping("reservePro")
	public String reservePro(HttpServletRequest request, HttpServletResponse response) throws java.text.ParseException {
		String bo_num = request.getParameter("bo_num");
		String payment = request.getParameter("payment");
		System.out.println(bo_num+", "+payment);
		
		Booking bo = (Booking) request.getSession().getAttribute("booking");
		
		bo.setBo_num(bo_num);		bo.setPayment(payment);
		
		ReserveDao rd = new ReserveDao();
		
		int result = rd.insertBooking(bo);
		
		System.out.println(bo);
		System.out.println(result);
		
		Reserved r = new Reserved();
		
		// 날짜 계산을 위한 기능
		// String -> Date -> Calendar
		SimpleDateFormat dtFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance();
		Date checkin = dtFormat.parse(bo.getCheckin());
		Date checkout = dtFormat.parse(bo.getCheckout());
		
		// 날짜 차이 계산
		long diff = (checkout.getTime()-checkin.getTime()) / (24*60*60*1000);
		cal.setTime(checkin);
		
		String date;
		
		// 중복 테이블에 예약 내역 insert
		// 체크인 날짜 ~ 체크아웃 날짜 -1
		for(int i=0; i<diff ;i++) {
			date = dtFormat.format(cal.getTime());
			r = new Reserved(bo.getRo_num(), date);
			System.out.println(r);
			rd.insertReserved(r);
			cal.add(Calendar.DATE, 1); // 하루 증가
		}
		
		return "/view/reservationList/reservationList.jsp";
	}
	
	@RequestMapping("roomDetail")
	public String roomDetail(HttpServletRequest request, HttpServletResponse response) {
		
		RoomDao rd = new RoomDao();
		int ro_num = Integer.parseInt(request.getParameter("ro_num"));
		Room room = rd.selectRoom(ro_num);
		
		List<Picture> picList = rd.selectPic(room.getPic_num());
		List<String> p_list = new ArrayList<String>();
		for(int i=0; i<picList.size();i++) {
			p_list.add(picList.get(i).getLocation());
		}
		
		System.out.println(p_list);
		
		// 선택한 객실의 정보를 가져와서 저장
		
		String info = room.getRo_info().replace("\r\n", "<br/>");
		
		request.setAttribute("p_list", p_list);
		request.setAttribute("room", room);
		request.setAttribute("ro_num", ro_num);
		request.setAttribute("pic_num", room.getPic_num());
		request.setAttribute("info", info);
		
		return "/common/roomDetail.jsp";
	}
	
	@RequestMapping("cancel")
	public String cancel(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		
		if(request.getSession().getAttribute("email") == null) {
			request.setAttribute("url", request.getContextPath()+"/member/loginForm");
			request.setAttribute("msg", "로그인이 필요한 서비스입니다.");
			return "/view/alert.jsp";
		}
		
		ReservationDao rd = new ReservationDao();
		ReserveDao reserveDao = new ReserveDao();
		String bo_num = request.getParameter("bo_num");
		rd.updateBookingStatus(bo_num); // 예약 상태 변경
		
		// 예약 중복 내역 삭제
		Booking b = rd.getBookingSelectDetail(bo_num);			System.out.println(b.getCheckout());
		int checkout = Integer.parseInt(b.getCheckout())-1; 	
		b.setCheckout(""+checkout);								System.out.println(b.getCheckout());
		int result = reserveDao.cancelReserveList(b);
		
		String url = request.getContextPath()+"/reservation/reservationList";
		String msg = "예약 취소를 실패했습니다.";
		
		if(result != 0) {
			msg = "예약이 취소되었습니다.";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			return "/view/alert.jsp";
		}
		
		
		return "/view/alert.jsp";
	}
}
