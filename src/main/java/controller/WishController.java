package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import service.WishService;

@Controller
@RequestMapping("/wish/")
public class WishController {
	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	private final WishService wishService;
	
	@Autowired
	public WishController(WishService wishService) {
		System.out.println("wishController On");
		this.wishService = wishService;
	}
	
	@ModelAttribute
	void init(HttpServletRequest request, Model model) {
		this.request = request;
		this.model = model;
		this.session = request.getSession();
	}
	
	@ResponseBody
	@PostMapping("insert")
	public Boolean insertWish(String bu_email) {
		System.out.println("찜하기");
		String email = (String) session.getAttribute("email");
		if(email == null) return true;
		
		try {
			
			int result = wishService.insertWish(email, bu_email);
			if(result != 0) return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	@ResponseBody
	@PostMapping("delete")
	public Boolean deleteWish(String bu_email) {
		String email = (String) session.getAttribute("email");
		if(email == null) return true;
		
		try {
			
			int result = wishService.deleteWish(email, bu_email);
			if(result != 0) return false;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
