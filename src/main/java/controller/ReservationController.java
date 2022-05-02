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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import service.BookingService;
import service.MemberService;
import service.ReserveService;
import util.DateParse;

@Controller
@RequestMapping("/reservation/")
public class ReservationController{
	
	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	private final ReserveService reserveService;
	private final MemberService memberService;
	
	@Autowired
	public ReservationController(
								ReserveService reserveService,
								MemberService memberService) {
		this.reserveService=reserveService;
		this.memberService=memberService;
	}
	
	@ModelAttribute
	void init(HttpServletRequest request, Model model) {
		this.request = request;
		this.model = model;
		this.session = request.getSession();
	}
	
	@RequestMapping("reservationList")
	public String reservationList() {
		String email = (String)session.getAttribute("email");
		
		try {
			// Booking(*) + Picture(location) + Review(rev_num)
			model.addAttribute("bookingList", reserveService.getMemberBookingList(email));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/view/reservationList/reservationList";
	}
	
	@RequestMapping("reservationDetail")
    public String reservationDetail (String bo_num) {
        String email = (String) request.getSession().getAttribute("email");
        
		try {
			model.addAttribute("member", memberService.getMemberOne(email));
			model.addAttribute("bookingDetail", reserveService.getBooking(bo_num));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return "/common/bookingDetail";
    }
	
	@RequestMapping("detail")
	public String detail(String bu_email, 
			@RequestParam(defaultValue = "2") String ro_count, 
			String checkin, String checkout) {
		String today = DateParse.getTodayPlus(0);			
		String tomorrow = DateParse.getTodayPlus(1);
		
		try {
			
			// 숙소 정보 + 별점 평균 + 리뷰 개수
			Business bu = reserveService.getReviewAvgCountBusiness(bu_email); 
			
			// 숙소 사진
			List<Picture> buPicList = reserveService.getPicList(bu.getPic_num()); 
			
			// Room(*) + Reserved(예약 일자 중복 정보) + Picture(방의 첫번째 사진)
			List<Room> roomList = reserveService.getOverlapRoomList(
					bu_email, 
					ro_count, 
					DateParse.dateToStr(checkin), 
					DateParse.dateToStr(checkout));
			
			// 숙소에 대한 리뷰 리스트
			List<Review> reviewList = reserveService.businessReviewList(bu_email);
			
			model.addAttribute("buPicList", buPicList);		
			model.addAttribute("roomList", roomList);
			model.addAttribute("bu", bu);
			model.addAttribute("ro_count", ro_count);
			model.addAttribute("checkin", checkin);
			model.addAttribute("checkout", checkout);
			model.addAttribute("today", DateParse.strToDate(today));
			model.addAttribute("tomorrow", DateParse.strToDate(tomorrow));
			model.addAttribute("reviewList", reviewList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/view/reserve/detail";
	}
	
	@RequestMapping("reserve")
	public String reserve(Booking booking) {
		String email = (String) request.getSession().getAttribute("email");
		Member m=null;
		
		try {
			m = memberService.getMemberOne(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int night = DateParse.dateDif(booking.getCheckin(), booking.getCheckout()); // (checkout-checkin)
		booking.setPrice((Integer.parseInt(booking.getPrice()) * night)+"");
		booking.setEmail(email);
		
		session.setAttribute("booking", booking);
		model.addAttribute("member", m);
		
		return "/view/reserve/reserve";
	}
	
	@ResponseBody
	@PostMapping(value = "reservePro", produces = "application/text; charset=UTF-8")
	public String reservePro(String bo_num, String payment) {
		String msg = "예약을 실패했습니다.";
		
		Booking booking = (Booking) session.getAttribute("booking");
		booking.setBo_num(bo_num);		
		booking.setPayment(payment);
		booking.setReg_date(DateParse.getTodayPlus(0));
		booking.setStatus(1);
		booking.setCheckin(DateParse.dateToStr(booking.getCheckin()));
		booking.setCheckout(DateParse.dateToStr(booking.getCheckout()));
		
		try {
			int result = reserveService.insertBooking(booking);
			System.out.println(result);
			if(result != 0) {
				msg = booking.getBu_title()+"/"+booking.getRo_name()+"에 예약하였습니다.";
				session.removeAttribute("booking");
				System.out.println(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("msg", msg);
		return "?msg="+msg;
	}
	
	@RequestMapping("roomDetail")
	public String roomDetail(int ro_num) {
		
		try {
			
			// 객실 정보와 사진 가져오기
			Room room = reserveService.getRoom(ro_num);
			List<Picture> picList = reserveService.getPicList(room.getPic_num());
			
			// 선택한 객실의 정보를 가져와서 파싱 후 저장
			String info = room.getRo_info().replace("\r\n", "<br/>");
			
			model.addAttribute("picList", picList);
			model.addAttribute("room", room);
			model.addAttribute("pic_num", room.getPic_num());
			model.addAttribute("info", info);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/common/roomDetail";
	}
	
	@RequestMapping("cancel")
	public String cancel(String bo_num) throws IOException{
		String msg = "예약 취소를 실패했습니다.";
		model.addAttribute("msg", msg);
		
		try {
			int result = reserveService.reserveCancel(bo_num);
			if(result != 0) msg = "예약이 취소되었습니다.";
			model.addAttribute("msg", msg);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/reservation/reservationList";
		}
		return "redirect:/reservation/reservationList";
	}
	
	@RequestMapping("review")
	public String review(String bo_num){
		try {
			model.addAttribute("booking", reserveService.getBooking(bo_num));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/common/review";
	}
	
	@RequestMapping("reviewPro")
	public String reviewPro(Review review){
		String msg = "리뷰 등록을 실패했습니다.";
		review.setEmail((String) request.getSession().getAttribute("email"));
		review.setReview_date(DateParse.getTodayPlus(0));
		
		try {
			int result = reserveService.writeRevire(review);
			if(result != 0) {
				msg = "리뷰를 등록했습니다.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", msg);
			return "/common/reviewResult";
		}
		
		model.addAttribute("msg", msg);
		return "/common/reviewResult";
	}
}
