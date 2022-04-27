package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Business;
import model.Member;
import model.Picture;
import repository.MemberDao;
import util.Naver_Sens_V2;


@Controller
@RequestMapping("/member/")
public class MemberController{
	
	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	private final MemberDao md;
	
	@Autowired
	public MemberController(MemberDao md) {
		this.md=md;
	}
	
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
	public String loginPro(String email, String password) {
		String msg = "아이디를 확인하세요";
		String url = request.getContextPath() + "/member/loginForm";

		Member m = md.selectMemberOne(email);

		if (m != null) {
			if (password.equals(m.getPassword())) { // 로그인 성공
				session.removeAttribute("bu_email");
				session.setAttribute("email", email);
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
	public String buLoginPro(String bu_email, String bu_password) {
		Business bu = md.selectBusinessOne(bu_email);

		String msg = "아이디를 확인하세요";
		String url = request.getContextPath() + "/member/loginForm";

		if (bu != null) {
			if (bu_password.equals(bu.getBu_password())) { // 로그인 성공
				request.getSession().setAttribute("bu_email", bu_email);
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
	public String buSignupPro(String bu_name) {
		int num = md.insertBusiness(request);

		String msg = "";
		String url = "";
		if (num > 0) {
			msg = bu_name + "님의 가입이 완료되었습니다.";
			url = request.getContextPath() + "/view/member/buLoginForm";
		} else {
			msg = "회원가입에 실패하였습니다.";
			url = request.getContextPath() + "/view/member/buLoginForm";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/view/alert";
	}

	@PostMapping("phoneAuth")
	@ResponseBody
	public Boolean phoneAuth(String tel) {
		int check = md.memberTelCount(tel);
		if(check>0) return true; 
		
		Naver_Sens_V2 message = new Naver_Sens_V2();
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		System.out.println("회원가입 문자 인증 => " + numStr);

//		message.send_msg(tel, numStr);
		model.addAttribute("result", numStr);
		session.setAttribute("rand", numStr);

		return false;
	}
	
	@PostMapping("buPhoneAuth")
	@ResponseBody
	public Boolean buPhoneAuth(String tel) {
		int check = md.businessTelCount(tel);
		System.out.println(tel+" : "+check);
		if(check>0) return true; 
		
		Naver_Sens_V2 message = new Naver_Sens_V2();
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		System.out.println("회원가입 문자 인증 => " + numStr);

//		message.send_msg(tel, numStr);
		model.addAttribute("result", numStr);
		session.setAttribute("rand", numStr);

		return false;
	}

	@PostMapping("phoneAuthOk")
	@ResponseBody
	public Boolean phoneAuthOk() {

		String rand = (String) session.getAttribute("rand");
		String code = (String) request.getParameter("code");

		System.out.println(rand + " : " + code);

		if (rand.equals(code)) {
			session.removeAttribute("rand");
			return false;
		} 

		return true;
	}

	@RequestMapping("memberInfo")
	public String memberInfo() {
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

	@PostMapping("readId")
	@ResponseBody
	public Boolean readId(String email, String bu_email) {
		boolean chk = true;

		if (email != null) {
			Member m = md.selectMemberOne(email);
			chk = m == null ? false : true;
			return chk;
		}
		if (bu_email != null) {
			Business b = md.selectBusinessOne(bu_email);
			chk = b == null ? false : true;
			return chk;
		}

		return chk;
	}

	@RequestMapping("buInfo")
	public String buInfo() {
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
}
