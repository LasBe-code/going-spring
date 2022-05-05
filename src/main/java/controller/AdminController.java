package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Business;
import service.AdminService;

@Controller
@RequestMapping("/admin/")
public class AdminController {

	HttpServletRequest request;
	Model model;
	HttpSession session;

	private final AdminService adminService;

	@Autowired
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@ModelAttribute
	void init(HttpServletRequest request, Model model) {
		this.request = request;
		this.model = model;
		this.session = request.getSession();
	}

	@RequestMapping("adminLogin")
	public String adminLogin() {
		return "/common/adminLogin";
	}

	@RequestMapping("adminLoginPro")
	public String adminLoginPro(String email, String password) {
		String msg = "등록된 관리자 계정이 아닙니다.";

		try {
			Boolean check = adminService.adminLogin(email, password);
			if (check) {
				msg = "관리자 모드입니다.";
				model.addAttribute("msg", msg);
				session.setAttribute("admin", email);
				return "redirect:/admin/monthlySales";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("msg", msg);
		return "redirect:/admin/adminLogin";
	}

	@RequestMapping("monthlySales")
	public String monthlySales() {
		return "/admin/monthlySales";
	}

	@RequestMapping("areaSales")
	public String areaSales() {
		return "/admin/areaSales";
	}

	@RequestMapping("categorySales")
	public String categorySales() {
		return "/admin/categorySales";
	}

	@RequestMapping("businessApproval")
	public String businessApproval(String pageNum) {
		
		try {
			// 페이징
			if (pageNum == null) pageNum = "1";
			int pageInt = Integer.parseInt(pageNum);
			int limit = 10;
			int bottomLine = 3;
			int startNum = (pageInt - 1) / bottomLine * bottomLine + 1;
			int endNum = startNum + bottomLine - 1;
			int count = adminService.notApprovalBuCount(); // 게시물 총 개수
			int maxNum = (count / limit) + (count % limit == 0 ? 0 : 1);
			if(endNum >= maxNum){
				endNum = maxNum;
			}
			
			//	ROWNUM
			int startPage = (pageInt - 1) * limit + 1;
			int endPage = (pageInt - 1) * limit + limit;
			List<Business> businessList = adminService.notApprovalBuList(startPage,endPage);
			
			model.addAttribute("bottomLine", bottomLine);
			model.addAttribute("startNum", startNum);
			model.addAttribute("endNum", endNum);
			model.addAttribute("maxNum", maxNum);
			model.addAttribute("pageInt", pageInt);
			model.addAttribute("businessList", businessList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "/admin/businessApproval";
	}
	
	@ResponseBody
	@PostMapping("approval")
	public boolean approval(String bu_email) {
		try {
			int result = adminService.businessApproval(bu_email);
			if(result != 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@ResponseBody
	@PostMapping("approvalCancel")
	public boolean cancel(String bu_email) {
		try {
			int result = adminService.businessApprovalCancel(bu_email);
			if(result != 0) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@RequestMapping("reviewReport")
	public String reviewReport() {
		return "/admin/reviewReport";
	}

}
