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
import repository.MemberDao;
import repository.BookingDao;
import repository.ReserveDao;
import repository.RoomDao;
import repository.SearchDao;
import util.DateParse;

@Controller
@RequestMapping("/reservation/")
public class ReservationController{
	DateParse dateParse = new DateParse();
	
	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	
	@Autowired
	BookingDao bookingDao;
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
		String email = (String)session.getAttribute("email");
		
		// Booking(*) + Picture(location) + Review(rev_num)
		List<Booking> bookingList = bookingDao.selectBookingPicRevList(email);
		model.addAttribute("bookingList", bookingList);
		return "/view/reservationList/reservationList";
	}
	
	@RequestMapping("reservationDetail")
    public String reservationDetail () {
        String bo_num = request.getParameter("bo_num");
        String email = (String) request.getSession().getAttribute("email");

        Booking bookingDetail = bookingDao.getBookingSelectDetail(bo_num);
        Member m = md.selectMemberOne(email);
        
        model.addAttribute("member", m);
        model.addAttribute("bookingDetail", bookingDetail);

        return "/common/bookingDetail";
    }
	
	@RequestMapping("detail")
	public String detail(String bu_email, String ro_count, String checkin, String checkout) {
		String today = dateParse.getTodayPlus(0);			String tomorrow = dateParse.getTodayPlus(1);
		
		Business bu = reserveDao.reviewAvgCountBusinessOne(bu_email); // 숙소 정보 + 별점 평균 + 리뷰 개수
		List<Picture> buPicList = sd.sbPicList(bu.getPic_num()); // 숙소 사진
		
		System.out.println(bu);
		
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
	public String reserve(String checkin, String checkout) {
		String email = (String) request.getSession().getAttribute("email");
		if(email == null) {
			model.addAttribute("url", request.getContextPath()+"/member/loginForm");
			model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
			return "/view/alert";
		}
		
		Booking bo = new Booking();
		
		int night = dateParse.dateDif(checkin, checkout); // (checkout-checkin)
		String price = (Integer.parseInt(request.getParameter("price")) * night)+"" ;
		System.out.println(price);
		
		bo.setEmail((String) request.getSession().getAttribute("email"));
		bo.setCheckin(dateParse.dateToStr(checkin));
		bo.setCheckout(dateParse.dateToStr(checkout));
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
	public String reservePro(String bo_num, String payment) {
		String url = request.getContextPath()+"/reservation/reservationList";
		String msg = "예약을 실패했습니다.";
		
		Booking bo = (Booking) request.getSession().getAttribute("booking");
		Reserved r = new Reserved();
		bo.setBo_num(bo_num);		
		bo.setPayment(payment);
		
		int result = reserveDao.insertBooking(bo);
		
		if(result != 0) {
			request.getSession().removeAttribute("booking");
			
			// 날짜 차이 계산
			int dif = dateParse.dateDif(bo.getCheckin(), bo.getCheckout());
			
			// 체크인 날짜 ~ 체크아웃 날짜 -1
			for(int i=0; i<dif ;i++) {
				r = new Reserved(bo.getRo_num(), dateParse.datePlus(bo.getCheckin(), i));
				System.out.println(r);
				reserveDao.insertReserved(r);
			}
			msg = "예약을 성공했습니다.";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		return "/view/alert";
	}
	
	@RequestMapping("roomDetail")
	public String roomDetail(int ro_num) {
		
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
	public String cancel(String bo_num) throws IOException{
		String email = (String) request.getSession().getAttribute("email");
		
		BookingDao rd = new BookingDao();
		Booking b = rd.getBookingSelectDetail(bo_num);	
		
		// 본인이 예약한 예약내역만 취소 가능
		if(!email.equals(b.getEmail())) {
			model.addAttribute("url", request.getContextPath()+"/member/loginForm");
			model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
			return "/view/alert";
		}
		
		// 예약 중복 내역 삭제
		int checkout = Integer.parseInt(b.getCheckout())-1; 	
		b.setCheckout(""+checkout);							
		int result = reserveDao.cancelReserveList(b);
		
		rd.updateBookingStatus(bo_num); // 예약 상태 변경
		
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
	public String review(String bo_num){
		if(request.getSession().getAttribute("email") == null) {
			model.addAttribute("url", request.getContextPath()+"/member/loginForm");
			model.addAttribute("msg", "로그인이 필요한 서비스입니다.");
			return "/view/alert";
		}
		
		Booking booking = bookingDao.getBookingSelectDetail(bo_num);
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
		
		Review review = new Review(
				bookingDao.nextRevNum(),
				Integer.parseInt(request.getParameter("score")),
				request.getParameter("bo_num"),
				(String) request.getSession().getAttribute("email"),
				request.getParameter("content"),
				dateParse.getTodayPlus(0)
				);
		
		int result= bookingDao.insertReview(review);
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
