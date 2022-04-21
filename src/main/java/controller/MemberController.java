package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import model.Business;
import model.Member;
import model.Picture;
//import util.Naver_Sens_V2;
import service.MemberDao;


@Controller
@RequestMapping("/member/")
public class MemberController{
	
	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	@Autowired
	MemberDao md;
	
	@ModelAttribute
	void init(HttpServletRequest request, Model model) {
		this.request = request;
		this.model = model;
		this.session = request.getSession();
	}
	
	
	@RequestMapping("logout")
	public String logout() {

		session.invalidate(); // 세션과 관련된 모든 데이터 날리는 메소드
		model.addAttribute("msg", "로그아웃 하였습니다.");
		model.addAttribute("url", request.getContextPath() + "/search/main");
		return "/view/alert";
	}

	@RequestMapping("loginForm")
	public String loginForm() {
		return "/view/member/loginForm";
	}

	@RequestMapping("loginPro")
	public String loginPro() {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email = request.getParameter("email");
		String pass = request.getParameter("password");

		Member m = md.selectMemberOne(email);

		String msg = "아이디를 확인하세요";
		String url = request.getContextPath() + "/member/loginForm";

		if (m != null) {
			if (pass.equals(m.getPassword())) { // 로그인 성공
				request.getSession().setAttribute("email", email);
				msg = m.getName() + "님이 로그인 하셨습니다.";
				url = request.getContextPath() + "/search/main";
			} else { // 아이디 o / 패스워드 x
				msg = "비밀번호를 확인하세요.";
			}
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/view/alert";
	}

	@RequestMapping("signupForm")
	public String signupForm() {
		return "/view/member/signupForm";
	}

	@RequestMapping("signupPro")
	public String signupPro() {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		int num = md.insertMember(request);

		String msg = "";
		String url = "";
		if (num > 0) {
			msg = name + "님의 가입이 완료되었습니다.";
			url = request.getContextPath() + "/view/member/loginForm";
		} else {
			msg = "회원가입에 실패하였습니다.";
			url = request.getContextPath() + "/view/member/loginForm";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		request.getSession().invalidate();
		return "/view/alert";
	}

	@RequestMapping("buLoginForm")
	public String buLoginForm() {

		return "/view/member/buLoginForm";
	}

	@RequestMapping("buLoginPro")
	public String buLoginPro() {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email = request.getParameter("bu_email");
		String pass = request.getParameter("bu_password");

		Business bu = md.selectBusinessOne(email);

		String msg = "아이디를 확인하세요";
		String url = request.getContextPath() + "/member/buLoginForm";

		if (bu != null) {
			if (pass.equals(bu.getBu_password())) { // 로그인 성공
				request.getSession().setAttribute("bu_email", email);
				msg = bu.getBu_name() + "님이 로그인 하셨습니다.";
				url = request.getContextPath() + "/room/roomlist";
			} else { // 아이디 o / 패스워드 x
				msg = "비밀번호를 확인하세요.";
			}
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/view/alert";
	}

	@RequestMapping("buSignupForm")
	public String buSignupForm() {

		return "/view/member/buSignupForm";
	}

	@RequestMapping("buSignupPro")
	public String buSignupPro() {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("bu_name");
		int num = md.insertBusiness(request);

		String msg = "";
		String url = "";
		if (num > 0) {
			msg = name + "님의 가입이 완료되었습니다.";
			url = request.getContextPath() + "/view/member/buLoginForm";
		} else {
			msg = "회원가입에 실패하였습니다.";
			url = request.getContextPath() + "/view/member/buLoginForm";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/view/alert";
	}

//	@RequestMapping("phoneAuth")
//	public String phoneAuth() {
//		HttpSession session = request.getSession();
//
//		try {
//			request.setCharacterEncoding("utf-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//
//		Naver_Sens_V2 ns = new Naver_Sens_V2();
//		String tel = request.getParameter("tel");
//		Random rand = new Random();
//		String numStr = "";
//		for (int i = 0; i < 6; i++) {
//			String ran = Integer.toString(rand.nextInt(10));
//			numStr += ran;
//		}
//		System.out.println("회원가입 문자 인증 => " + numStr);
//
//		ns.send_msg(tel, numStr);
//		model.addAttribute("result", numStr);
//		session.setAttribute("rand", numStr);
//
//		return "/common/phoneAuth";
//	}

	@RequestMapping("phoneAuthOk")
	public String phoneAuthOk() {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String rand = (String) session.getAttribute("rand");
		String code = (String) request.getParameter("code");

		System.out.println(rand + " : " + code);

		if (rand.equals(code)) {
			model.addAttribute("result", false);
		} else {
			model.addAttribute("result", true);
		}

		return "/common/phoneAuth";
	}

	@RequestMapping("memberInfo")
	public String memberInfo() {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String email = (String) session.getAttribute("email");

		if (email != null) {


			Member m = md.selectMemberOne(email);

			model.addAttribute("mem", m);

		} else {
			return "/member/loginForm";
		}

		return "/view/member/memberInfo";
	}

	@RequestMapping("kakaoLogin")
	public String kakaoLogin() {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = request.getParameter("id");

		Member m = md.selectMemberOne(id);

		String msg = "아이디를 확인하세요";
		String url = request.getContextPath() + "/member/loginForm";

		if (m != null) { // 카카오로그인 성공
			session.setAttribute("email", id);
			msg = m.getName() + "님이 로그인 하셨습니다.";
			url = request.getContextPath() + "/search/main";
		} else { // id가 없을 때
			String name = request.getParameter("name");
			url = request.getContextPath() + "/member/kakaoSignup";
			msg = "회원가입 페이지로 이동합니다.";
			session.setAttribute("id", id);
			session.setAttribute("name", name);
		}
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/view/alert";
	}

	@RequestMapping("kakaoSignup")
	public String kakaoSignup() {
		return "/view/member/kakaoSignup";
	}

	@RequestMapping("readId")
	public String readId() {
		String email = request.getParameter("email");
		String bu_email = request.getParameter("bu_email");
		String chk = "true";

		System.out.println(bu_email);

		if (email != null) {
			Member m = md.selectMemberOne(email);
			chk = m == null ? "false" : "true";
			model.addAttribute("result", chk);
		}
		if (bu_email != null) {
			Business b = md.selectBusinessOne(bu_email);
			chk = b == null ? "false" : "true";
			model.addAttribute("result", chk);
		}

		return "/common/phoneAuth";
	}

	@RequestMapping("buInfo")
	public String buInfo() {

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String bu_email = (String) session.getAttribute("bu_email");
		if (bu_email != null) {


			Business b = md.selectBusinessOne(bu_email);

			model.addAttribute("mem", b);

		} else {
			return "/member/buLoginForm";
		}
		return "/view/member/buInfo";
	}

	@RequestMapping("buUpdateForm")
	public String buUpdateForm() {

		String bu_email = (String) session.getAttribute("bu_email");
		if (bu_email != null) {


			Business b = md.selectBusinessOne(bu_email);
			int pic_num = b.getPic_num();
			String location = "";
			
			List<Picture> pist =  md.selectPic(pic_num);
			for(Picture p : pist) {
				location += p.getLocation()+"\n";
			}
			model.addAttribute("pic_num", pic_num);
			model.addAttribute("location", location);
			model.addAttribute("mem", b);

		} else {
			return "/member/buLoginForm";
		}

		return "/view/member/buUpdateForm";
	}

	@RequestMapping("buUpdatePro")
	public String buUpdatePro() {

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int pic_num = Integer.parseInt(request.getParameter("pic_num"));
		String name = request.getParameter("bu_name");
		
		int num = md.updateBusiness(request);

		String msg = "";
		String url = "";
		if (num > 0) {
			msg = name+"님의 정보 수정이 완료되었습니다.";
			url = request.getContextPath() + "/room/roomlist";
		}
		else if(num == -1) {
			msg = "비밀번호가 틀렸습니다";
			url = request.getContextPath() + "/member/buUpdateForm?pic_num="+pic_num;
		}
		else {
			msg = "정보 수정에 실패하였습니다.";
			url = request.getContextPath() + "/member/buLoginForm";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		
		return "/view/alert";

	}
	
	@RequestMapping("review")
	public String review() {
		
		return "/common/review";
	}
	
	@RequestMapping("reviewPro")
	public String reviewPro() {
		
		return "/view/alert";
	}
}
