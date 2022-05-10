package controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Business;
import model.Member;
import model.Review;
import model.Room;
import service.ReserveService;
import service.RoomService;

@Controller
@RequestMapping("/room/")
public class RoomController{
	
	private static Map<Object, Object> controllerMap = new HashMap<Object, Object>();

	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	private final RoomService roomService;
	private final ReserveService reserveService;
	@Autowired
	public RoomController(RoomService roomService, ReserveService reserveService) {
		super();
		this.roomService = roomService;
		this.reserveService = reserveService;
	}

	@ModelAttribute
	void init(HttpServletRequest request, Model model) {
		this.request = request;
		this.model = model;
		this.session = request.getSession();
	}

	
	// 객실정보 페이지
	
	@RequestMapping("roomlist")
	public String List() {
		List<Room> list = new ArrayList<Room>();
		Map<Object, Object> map = new HashMap<>();
		String bu_email =(String)session.getAttribute("bu_email");
		
		try {
			controllerMap.clear();
			controllerMap = roomService.List(bu_email);
			
			list = (List<Room>) controllerMap.get("list");
			map = (Map<Object, Object>) controllerMap.get("map");
			
			model.addAttribute("picMap", map);
			model.addAttribute("list", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/view/entrepreneur/roomlist";
	}
	
	
	// 객실등록 페이지
	@RequestMapping("roomInsert")
	public String roomInsert() {
		return "/view/entrepreneur/roomInsert";
	}
	
	@RequestMapping("roomInsertPro")
	public String roomInsertPro(Room room) {
		String bu_email = (String)session.getAttribute("bu_email");
		String msg = "객실 등록시 오류가 발생했습니다.";
		String url = request.getContextPath() + "/room/roomInsert?bu_email="+bu_email;
		
		try {
			controllerMap.clear();
			controllerMap = roomService.roomInsertPro(room, bu_email);
			int rnum = (int) controllerMap.get("rnum");
			int rowCnt = (int) controllerMap.get("rowCnt");
			if(rnum > 0 && rowCnt > 0) {
				msg = "객실 등록이 완료되었습니다.";
				url = request.getContextPath() + "/room/roomlist?bu_email="+bu_email;
			}
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
		
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/view/alert";
		
	}
	
	
	@RequestMapping("roominfo")
	public String roominfo(Integer ro_num) {
		
		try {
			controllerMap.clear();
			controllerMap = roomService.roominfo(ro_num);
			
			model.addAttribute("p_list", controllerMap.get("p_list"));
			model.addAttribute("room", controllerMap.get("room"));
			model.addAttribute("ro_num", controllerMap.get("ro_num"));
			model.addAttribute("pic_num", controllerMap.get("pic_num"));
			model.addAttribute("info", controllerMap.get("info"));
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/view/entrepreneur/roominfo";
	}
	
	
	@RequestMapping("roomUpdate")
	public String roomUpdate(Integer ro_num, Integer pic_num) {

		try {
			controllerMap.clear();
			controllerMap = roomService.roomUpdate(ro_num, pic_num);
			
			model.addAttribute("pic_num", pic_num);
			model.addAttribute("room", controllerMap.get("room"));
			model.addAttribute("ro_num", ro_num);
			model.addAttribute("pic", controllerMap.get("pic"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/view/entrepreneur/roomUpdate";
	}
	
	
	@RequestMapping("roomUpdatePro")
	public String roomUpdatePro(Room room) {
		
		String bu_email =(String)session.getAttribute("bu_email");
		room.setBu_email(bu_email);
		try {
			controllerMap.clear();
			controllerMap = roomService.roomUpdatePro(room);
			int rnum = (int) controllerMap.get("rnum");
			int pic = (int) controllerMap.get("pic");
			
			String msg = "객실 수정시 오류가 발생했습니다.";
			String url = request.getContextPath() + "/room/roomUpdate?ro_num="+room.getRo_num()+"&pic_num="+room.getPic_num();
			
			if(rnum > 0 && pic > 0) {
				msg = "객실 수정이 완료되었습니다.";
				url = request.getContextPath() + "/room/roomlist?bu_email="+bu_email;
			}
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/view/alert";
	}
	
	
	@RequestMapping("roomDelete")
	public String roomDelete(Room room) {
		
		int ro_num = room.getRo_num();
		model.addAttribute("ro_num", ro_num);
		
		return "/view/entrepreneur/roomDelete";
	}
	
	
	@RequestMapping("roomDeletePro")
	public String roomDeletePro(Room r, Business bu) {
		
		String bu_email =(String)session.getAttribute("bu_email");
		controllerMap.clear();
		try {
			int room = roomService.roomDeltePro(r, bu, bu_email);
			String msg = "객실 삭제시 오류가 발생했습니다.";
			String url = request.getContextPath() + "/room/roomDelete?ro_num="+r.getRo_num();
			
			if(room > 0) {
				msg = "객실 삭제가 완료되었습니다.";
				url = request.getContextPath() + "/room/roomlist?bu_email="+bu_email;
			}
			else {
				msg = (String) controllerMap.get("msg");
			}
			model.addAttribute("msg", msg);
			model.addAttribute("url", url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "/view/alert";
	}
	
	@RequestMapping("reservation")
	public String reservation(String pageNum, String searchName, String search) {
		
		try {
			String bu_email = (String) session.getAttribute("bu_email");
			// 페이지 번호
			int pageInt;
			// 한페이지에 출력할 게시글 갯수
			int limit = 10;
			
			
			if(pageNum == null)
				pageNum = "1";
					
			pageInt = Integer.parseInt(pageNum);
					
//			한페이지에 출력할 게시글 rownum의 번호 
//			1페이지면 startPage = 1 endPage = 10 이며 db에서 rownum 1번 부터 10번까지 데이터를 가져온다
//			2페이지면 startPage = 11 endPage = 20 이며 db에서 rownum 11번 부터 20번까지 데이터를 가져온다
			int startPage = (pageInt-1)*limit + 1;
			int endPage = (pageInt-1)*limit + limit;
//			게시글 갯수
			
			controllerMap.clear();
			controllerMap = roomService.reservation(search, searchName, request, bu_email, startPage,endPage);
			
			String msg = (String) controllerMap.get("msg");
			if(msg != null) {
				model.addAttribute("msg", msg);
				model.addAttribute("url",controllerMap.get("url"));
				return "/view/alert";
			}
			
			int count = (int) controllerMap.get("count");
			
			// -----------------------------------------------------------------------------
			// 게시글 갯수를 확인하는 메서드
			int boaroomServiceNum = count - (pageInt - 1) *limit;
			
			int bottomLine = 3;
			int startNum = (pageInt - 1) / bottomLine * bottomLine + 1;
			int endNum = startNum + bottomLine - 1;
			
			int maxNum = (count / limit) + (count % limit == 0 ? 0 : 1);
			if(endNum >= maxNum){
				endNum = maxNum;
			}
			
			model.addAttribute("search", search);
			model.addAttribute("searchName", searchName);
			model.addAttribute("bk", controllerMap.get("bk"));
			model.addAttribute("boaroomServiceNum", boaroomServiceNum);
			model.addAttribute("bottomLine", bottomLine);
			model.addAttribute("startNum", startNum);
			model.addAttribute("endNum", endNum);
			model.addAttribute("maxNum", maxNum);
			model.addAttribute("pageInt", pageInt);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/view/entrepreneur/reservation";
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping("sales")
	public String sales() {

		try {
			String bu_email =(String)session.getAttribute("bu_email");
			
			controllerMap.clear();
			controllerMap = roomService.sales(bu_email);
			
			model.addAttribute("result", controllerMap.get("result"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "/view/entrepreneur/sales";
	}
	
	@RequestMapping("todayCheckin")
	public String todayCheckin() {
		
		try {
			String bu_email =(String)session.getAttribute("bu_email");
			
			controllerMap.clear();
			controllerMap = roomService.todayCheckin(bu_email);
			
			model.addAttribute("notCheckin", controllerMap.get("notCheckin"));
			model.addAttribute("checkinOk", controllerMap.get("checkinOk"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/view/entrepreneur/todayCheckin";
	}
	
	
	@RequestMapping("updateTodayCheckin")
	public String updateTodayCheckin(String bo_num) {
		
		String bu_email =(String)session.getAttribute("bu_email");
		try {
			roomService.updateTodayCheckin(bo_num, bu_email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/room/todayCheckin";
	}
	
	
	@RequestMapping("todayCheckOut")
	public String todayCheckOut() {
		
		String bu_email =(String)session.getAttribute("bu_email");
		try {
			controllerMap.clear();
			controllerMap = roomService.todayCheckOut(bu_email);
			
			System.out.println("controllerMap.get(\"checkOutOk\") = "+ controllerMap.get("checkOutOk"));
			model.addAttribute("notCheckOut", controllerMap.get("notCheckOut"));
			model.addAttribute("checkOutOk", controllerMap.get("checkOutOk"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/view/entrepreneur/todayCheckOut";
	}
	
	
	@RequestMapping("updateTodayCheckOut")
	public String updateTodayCheckOut(String bo_num) {
		String bu_email =(String)session.getAttribute("bu_email");
		
		try {
			roomService.updateTodayCheckOut(bu_email, bo_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/room/todayCheckOut";
	}
	
	@RequestMapping("roomReview")
	public String roomReview(Review re) {
		String bu_email = (String)session.getAttribute("bu_email");
		int reply = 0;
		try {
			if(re.getContent_reply() == null || "".equals(re.getContent_reply()))
				re.setContent_reply("");
			else {
				reply = roomService.updateReply(re.getRev_num(),re.getContent_reply());
			}
			
			List<Review> reviewList = reserveService.businessReviewList(bu_email);
			System.out.println("reviewList = " + reviewList);
			model.addAttribute("reviewList", reviewList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/view/entrepreneur/roomReview";
	}
	
	
	@RequestMapping("roomReviewDelete")
	public String roomReviewDelete(Integer rev_num) {
		int delete = 0;
		
		try {
			delete = roomService.deleteReply(rev_num);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/room/roomReview";
	}
	
	@RequestMapping("reviewApproval")
	public String reviewApproval(Integer rev_num) {
		try {
			int reviewApproval = roomService.reviewApproval(rev_num);
			System.out.println("reviewApproval = " +reviewApproval);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/room/roomReview";
	}
	
	@RequestMapping("reportCancle")
	public String reportCancle(Integer rev_num) {
		System.out.println("rev_num = "+rev_num);
		try {
			int reportCancle = roomService.reportCancle(rev_num);
			System.out.println("reportCancle = " +reportCancle);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/room/roomReview";
	}
	
	
	@PostMapping("roomCheck")
	@ResponseBody
	public Boolean readId(String ro_name) {
		String bu_email = (String)session.getAttribute("bu_email");
		boolean chk = true;
		if (ro_name != null) {
			try {
				Room room = roomService.getRo_name(ro_name,bu_email);
				chk = room == null ? false : true;
				return chk;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return chk;
	}
}


