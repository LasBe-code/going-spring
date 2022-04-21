package controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Booking;
import model.Business;
import model.Member;
import model.Picture;
import model.Reserved;
import model.Review;
import model.Room;
import service.MemberDao;
import service.ReservationDao;
import service.ReserveDao;
import service.RoomDao;
import service.SearchDao;
import util.DateParse;

@Controller
@RequestMapping("/reservation/")
public class ReservationController{
	
	
	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	
	@Autowired
	ReservationDao dao;
	@Autowired
	ReserveDao reserveDao;
	@Autowired
	MemberDao md;
	@Autowired
	SearchDao sd;
	@Autowired
	RoomDao rd;
	
	@ModelAttribute
	void init(HttpServletRequest request, Model model) {
		this.request = request;
		this.model = model;
		this.session = request.getSession();
	}
	
	@RequestMapping("reservationList")
	public String reservationList() {
		HttpSession session = request.getSession(); //session을 불러옴. 
		String email = (String)session.getAttribute("email");
		
		// Booking(*) + Picture(location) + Review(rev_num)
		List<Booking> bookingList = dao.selectBookingPicRevList(email);
		model.addAttribute("bookingList", bookingList);
		System.out.println(bookingList);
		return "/view/reservationList/reservationList";
	}
	
	@RequestMapping("reservationDetail")
    public String reservationDetail () {
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
        Member m = md.selectMemberOne(email);
        
        model.addAttribute("member", m);
        model.addAttribute("bookingDetail", bookingDetail);

        return "/common/bookingDetail";
    }
	
	@RequestMapping("detail")
	public String detail() throws java.text.ParseException {
		DateParse dateParse = new DateParse();
		
		String bu_email = request.getParameter("bu_email");	String ro_count = request.getParameter("ro_count");
		String checkin = request.getParameter("checkin");	String checkout = request.getParameter("checkout");
		String today = dateParse.getTodayPlus(0);			String tomorrow = dateParse.getTodayPlus(1);
		
		Business bu = reserveDao.reviewAvgCountBusinessOne(bu_email); // 숙소 정보 + 별점 평균 + 리뷰 개수
		List<Picture> buPicList = sd.sbPicList(bu.getPic_num()); // 숙소 사진
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("bu_email", bu_email); 	map.put("ro_count", ro_count);
		map.put("checkin", dateParse.dateToStr(checkin)); 	
		map.put("checkout", dateParse.dateToStr(checkout));
		
		// Room(*) + Reserved(예약 일자 중복 정보) + Picture(방의 첫번째 사진)
		List<Room> roomList = reserveDao.overlapRoomList(map); 
		
		// 숙소에 대한 리뷰 리스트
		List<Review> reviewList = reserveDao.businessReviewList(bu_email);
		
		model.addAttribute("buPicList", buPicList);		
		model.addAttribute("roomList", roomList);
		model.addAttribute("bu", bu);
		model.addAttribute("ro_count", ro_count);
		model.addAttribute("checkin", checkin);
		model.addAttribute("checkout", checkout);
		model.addAttribute("today", dateParse.strToDate(today));
		model.addAttribute("tomorrow", dateParse.strToDate(tomorrow));
		model.addAttribute("reviewList", reviewList);
		return "/view/reserve/detail";
	}
	
	@RequestMapping("reserve")
	public String reserve() {
		String email = (String) request.getSession().getAttribute("email");
		if(email == null) {
			model.addAttribute("url", request.getContextPath()+"/member/loginForm");
			model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
			return "/view/alert";
		}
		
		Booking bo = new Booking();
		DateParse dateParse = new DateParse();
		String checkin = request.getParameter("checkin");
		String checkout = request.getParameter("checkout");
		int night = dateParse.dateDif(checkin, checkout); // (checkout-checkin)
		String price = (Integer.parseInt(request.getParameter("price")) * night)+"" ;
		System.out.println(price);
		
		bo.setEmail((String) request.getSession().getAttribute("email"));
		bo.setCheckin(checkin);
		bo.setCheckout(checkout);
		bo.setPrice(price);
		bo.setBu_title(request.getParameter("bu_title"));
		bo.setRo_name(request.getParameter("ro_name"));
		bo.setRo_num(Integer.parseInt(request.getParameter("ro_num")));
		bo.setReg_date(dateParse.getTodayPlus(0));
		bo.setStatus(1);
		
		Member m = md.selectMemberOne(bo.getEmail());
		
		request.getSession().setAttribute("booking", bo);
		model.addAttribute("member", m);
		
		return "/view/reserve/reserve";
	}
	
	@RequestMapping("reservePro")
	public String reservePro() {
		DateParse dateParse = new DateParse();
		Booking bo = (Booking) request.getSession().getAttribute("booking");
		String bo_num = request.getParameter("bo_num");
		String payment = request.getParameter("payment");
		Reserved r = new Reserved();
		bo.setBo_num(bo_num);		
		bo.setPayment(payment);
		int result = reserveDao.insertBooking(bo);
		System.out.println(result == 0 ? "예약 실패" : "예약 성공");
		request.getSession().removeAttribute("booking");
		
		// 날짜 차이 계산
		int dif = dateParse.dateDif(bo.getCheckin(), bo.getCheckout());
		
		// 체크인 날짜 ~ 체크아웃 날짜 -1
		for(int i=0; i<dif ;i++) {
			r = new Reserved(bo.getRo_num(), dateParse.datePlus(bo.getCheckin(), i));
			System.out.println(r);
			reserveDao.insertReserved(r);
		}
		
		return "/view/reservationList/reservationList";
	}
	
	@RequestMapping("roomDetail")
	public String roomDetail() {
		
		int ro_num = Integer.parseInt(request.getParameter("ro_num"));
		Room room = rd.selectRoom(ro_num);
		
		List<Picture> picList = rd.selectPic(room.getPic_num());
		List<String> p_list = new ArrayList<String>();
		for(int i=0; i<picList.size();i++) {
			p_list.add(picList.get(i).getLocation());
		}
		
		System.out.println(p_list);
		
		// 선택한 객실의 정보를 가져와서 파싱 후 저장
		String info = room.getRo_info().replace("\r\n", "<br/>");
		
		model.addAttribute("p_list", p_list);
		model.addAttribute("room", room);
		model.addAttribute("ro_num", ro_num);
		model.addAttribute("pic_num", room.getPic_num());
		model.addAttribute("info", info);
		
		return "/common/roomDetail";
	}
	
	@RequestMapping("cancel")
	public String cancel() throws IOException{
		
		if(request.getSession().getAttribute("email") == null) {
			model.addAttribute("url", request.getContextPath()+"/member/loginForm");
			model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
			return "/view/alert";
		}
		
		ReservationDao rd = new ReservationDao();
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
			System.out.println(msg);
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			return "/view/alert";
		}
		
		
		return "/view/alert";
	}
	
	@RequestMapping("review")
	public String review(){
		
		if(request.getSession().getAttribute("email") == null) {
			model.addAttribute("url", request.getContextPath()+"/member/loginForm");
			model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
			return "/view/alert";
		}
		
		ReservationDao rd = new ReservationDao();
		//
		String bo_num = request.getParameter("bo_num");
		Booking booking = rd.getBookingSelectDetail(bo_num);
		
		model.addAttribute("booking", booking);
		
		return "/common/review";
	}
	
	@RequestMapping("reviewPro")
	public String reviewPro(){
		if(request.getSession().getAttribute("email") == null) {
			model.addAttribute("url", request.getContextPath()+"/member/loginForm");
			model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
			return "/view/alert";
		}
		try {request.setCharacterEncoding("utf-8");} 
		catch (UnsupportedEncodingException e) {e.printStackTrace();}
		
		ReservationDao rd = new ReservationDao();
		DateParse dateParse = new DateParse();
		
		Review review = new Review(
				rd.nextRevNum(),
				Integer.parseInt(request.getParameter("score")),
				request.getParameter("bo_num"),
				(String) request.getSession().getAttribute("email"),
				request.getParameter("content"),
				dateParse.getTodayPlus(0)
				);
		
		int result= rd.insertReview(review);
		String msg = "리뷰 등록을 실패했습니다.";
		
		if(result != 0) {
			msg = "리뷰를 등록했습니다.";
			model.addAttribute("msg", msg);
			return "/common/reviewResult";
		}
		model.addAttribute("msg", msg);
		return "/common/reviewResult";
	}
}
