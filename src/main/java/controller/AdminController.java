package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
		this.adminService=adminService;
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
			if(check) {
				msg = "관리자 모드입니다.";
				model.addAttribute("msg", msg);
				session.setAttribute("admin", email);
				return "redirect:/admin/analytics";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("msg", msg);
		return "redirect:/common/adminLogin";
	}
	
	@RequestMapping("analytics")
	public String analytics() {
		return "/admin/analytics";
	}
	
	@RequestMapping("businessApproval")
	public String businessApproval() {
		return "/admin/businessApproval";
	}
	
}
