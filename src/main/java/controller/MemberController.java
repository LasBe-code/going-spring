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
import model.Member;
import model.Picture;
import service.MemberService;


@Controller
@RequestMapping("/member/")
public class MemberController{
	
	HttpServletRequest request;
	Model model;
	HttpSession session;
	
	private final MemberService memberService;
	
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService=memberService;
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
		Member m = null;
		
		try {
			m = memberService.getMemberOne(email);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
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

	@RequestMapping("buLoginForm")
	public String buLoginForm() {
		return "/view/member/buLoginForm";
	}

	@RequestMapping("buLoginPro")
	public String buLoginPro(String bu_email, String bu_password) {
		String msg = "아이디를 확인하세요";
		String url = request.getContextPath() + "/member/loginForm";
		Business bu = null;
		
		try {
			bu = memberService.getBusinessOne(bu_email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (bu != null) {
			if (bu_password.equals(bu.getBu_password())) { // 로그인 성공
				session.setAttribute("bu_email", bu_email);
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
	
	@RequestMapping("signupForm")
	public String signupForm() {
		return "/view/member/signupForm";
	}

	@RequestMapping("signupPro")
	public String signupPro(Member member) {
		int num = 0;
		
		try {
			num = memberService.signupMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String msg = "회원가입에 실패하였습니다.";
		String url = request.getContextPath() + "/member/signupForm";
		if (num > 0) {
			msg = member.getName() + "님의 가입이 완료되었습니다.";
			url = request.getContextPath() + "/search/main";
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);
		request.getSession().invalidate();
		return "/view/alert";
	}

	@RequestMapping("buSignupForm")
	public String buSignupForm() {
		return "/view/member/buSignupForm";
	}

	@RequestMapping("buSignupPro")
	public String buSignupPro(Business business, String picLocation) {
		String msg = "회원가입에 실패하였습니다.";
		String url = request.getContextPath() + "/view/member/buLoginForm";
		int num = 0;
		
		try {
			num = memberService.signupBusiness(business, picLocation);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (num > 0) {
			msg = business.getBu_name() + "님의 가입이 완료되었습니다.";
			url = request.getContextPath() + "/view/member/buLoginForm";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/view/alert";
	}

	@PostMapping("phoneAuth")
	@ResponseBody
	public Boolean phoneAuth(String tel) {
		
		try { // 이미 가입된 전화번호가 있으면
			if(memberService.memberTelCount(tel) > 0) 
				return true; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String code = memberService.sendRandomMessage(tel);
		session.setAttribute("rand", code);
		return false;
	}
	
	@PostMapping("buPhoneAuth")
	@ResponseBody
	public Boolean buPhoneAuth(String tel) {
		
		try { // 이미 가입된 전화번호가 있으면
			if(memberService.businessTelCount(tel) > 0) 
				return true; 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String code = memberService.sendRandomMessage(tel);
		session.setAttribute("rand", code);
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
	
	// 로그인 검증 처리
	@RequestMapping("memberInfo")
	public String memberInfo() {
		String email = (String) session.getAttribute("email");

		try {
			Member m = memberService.getMemberOne(email);
			model.addAttribute("mem", m);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "/view/member/memberInfo";
	}

	@RequestMapping("kakaoLogin")
	public String kakaoLogin(String id, String name) {
		Member m = null;
		
		try {
			m = memberService.getMemberOne(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (m != null) { // 카카오로그인 성공
			session.setAttribute("email", id);
		} else { // id가 없을 때
			model.addAttribute("id", id);
			model.addAttribute("name", name);
			return "/member/kakaoSignup";
		}
		model.addAttribute("msg", m.getName() + "님이 로그인 하셨습니다.");
		model.addAttribute("url", request.getContextPath() + "/search/main");
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
			try {
				Member m = memberService.getMemberOne(email);
				chk = m == null ? false : true;
				return chk;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (bu_email != null) {
			try {
				Business b = memberService.getBusinessOne(bu_email);
				chk = b == null ? false : true;
				return chk;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return chk;
	}
	
	@RequestMapping("buInfo")
	public String buInfo() {
		String bu_email = (String) session.getAttribute("bu_email");
		
			try {
				Business b = memberService.getBusinessOne(bu_email);
				model.addAttribute("mem", b);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return "/view/member/buInfo";
	}

	@RequestMapping("buUpdateForm")
	public String buUpdateForm() {
		String bu_email = (String) session.getAttribute("bu_email");

			try {
				Business b = memberService.getBusinessOne(bu_email);
				int pic_num = b.getPic_num();
				String location = "";
				
				List<Picture> pist =  memberService.getPicList(pic_num);
				for(Picture p : pist) {
					location += p.getLocation()+"\n";
				}
				model.addAttribute("location", location);
				model.addAttribute("mem", b);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		return "/view/member/buUpdateForm";
	}

	@RequestMapping("buUpdatePro")
	public String buUpdatePro(Business business, String picLocation) {
		String msg = "";
		String url = request.getContextPath() + "/member/buUpdateForm";
		
		try {
			int result = memberService.modifyBusiness(business, picLocation);
			
			switch (result) {
			case -1:
				msg = "비밀번호가 틀렸습니다";
				break;
			case 1:
				msg = business.getBu_name()+"님의 정보 수정이 완료되었습니다.";
				url = request.getContextPath() + "/room/roomlist";
				break;
			default:
				msg = "정보 수정에 실패하였습니다.";
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("msg", msg);
		model.addAttribute("url", url);

		return "/view/alert";
	}
}
