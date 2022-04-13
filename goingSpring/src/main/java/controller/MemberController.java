package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Business;
import model.Member;
import model.Picture;
import service.MemberDao;
import util.Naver_Sens_V2;

public class MemberController extends MskimRequestMapping {
	@RequestMapping("logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate(); // 세션과 관련된 모든 데이터 날리는 메소드
		request.setAttribute("msg", "로그아웃 하였습니다.");
		request.setAttribute("url", request.getContextPath() + "/search/main");

		return "/view/alert.jsp";
	}

	@RequestMapping("loginForm")
	public String loginForm(HttpServletRequest request, HttpServletResponse response) {
		return "/view/member/loginForm.jsp";
	}

	@RequestMapping("loginPro")
	public String loginPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		MemberDao md = new MemberDao();

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
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/view/alert.jsp";
	}

	@RequestMapping("signupForm")
	public String signupForm(HttpServletRequest request, HttpServletResponse response) {
		return "/view/member/signupForm.jsp";
	}

	@RequestMapping("signupPro")
	public String signupPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		MemberDao md = new MemberDao();
		String name = request.getParameter("name");
		int num = md.insertMember(request);

		String msg = "";
		String url = "";
		if (num > 0) {
			msg = name + "님의 가입이 완료되었습니다.";
			url = request.getContextPath() + "/view/member/loginForm.jsp";
		} else {
			msg = "회원가입에 실패하였습니다.";
			url = request.getContextPath() + "/view/member/loginForm.jsp";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.getSession().invalidate();
		return "/view/alert.jsp";
	}

	@RequestMapping("buLoginForm")
	public String buLoginForm(HttpServletRequest request, HttpServletResponse response) {

		return "/view/member/buLoginForm.jsp";
	}

	@RequestMapping("buLoginPro")
	public String buLoginPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String email = request.getParameter("bu_email");
		String pass = request.getParameter("bu_password");
		MemberDao md = new MemberDao();

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
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/view/alert.jsp";
	}

	@RequestMapping("buSignupForm")
	public String buSignupForm(HttpServletRequest request, HttpServletResponse response) {

		return "/view/member/buSignupForm.jsp";
	}

	@RequestMapping("buSignupPro")
	public String buSignupPro(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		MemberDao md = new MemberDao();
		String name = request.getParameter("bu_name");
		int num = md.insertBusiness(request);

		String msg = "";
		String url = "";
		if (num > 0) {
			msg = name + "님의 가입이 완료되었습니다.";
			url = request.getContextPath() + "/view/member/buLoginForm.jsp";
		} else {
			msg = "회원가입에 실패하였습니다.";
			url = request.getContextPath() + "/view/member/buLoginForm.jsp";
		}

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/view/alert.jsp";
	}

	@RequestMapping("phoneAuth")
	public String phoneAuth(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		Naver_Sens_V2 ns = new Naver_Sens_V2();
		String tel = request.getParameter("tel");
		Random rand = new Random();
		String numStr = "";
		for (int i = 0; i < 6; i++) {
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}
		System.out.println("회원가입 문자 인증 => " + numStr);

		ns.send_msg(tel, numStr);
		request.setAttribute("result", numStr);
		session.setAttribute("rand", numStr);

		return "/common/phoneAuth.jsp";
	}

	@RequestMapping("phoneAuthOk")
	public String phoneAuthOk(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String rand = (String) session.getAttribute("rand");
		String code = (String) request.getParameter("code");

		System.out.println(rand + " : " + code);

		if (rand.equals(code)) {
			request.setAttribute("result", false);
		} else {
			request.setAttribute("result", true);
		}

		return "/common/phoneAuth.jsp";
	}

	@RequestMapping("memberInfo")
	public String memberInfo(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String email = (String) session.getAttribute("email");

		if (email != null) {

			MemberDao md = new MemberDao();

			Member m = md.selectMemberOne(email);

			request.setAttribute("mem", m);

		} else {
			return "/member/loginForm";
		}

		return "/view/member/memberInfo.jsp";
	}

	@RequestMapping("kakaoLogin")
	public String kakaoLogin(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String id = request.getParameter("id");
		MemberDao md = new MemberDao();

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
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		return "/view/alert.jsp";
	}

	@RequestMapping("kakaoSignup")
	public String kakaoSignup(HttpServletRequest request, HttpServletResponse response) {
		return "/view/member/kakaoSignup.jsp";
	}

	@RequestMapping("readId")
	public String readId(HttpServletRequest request, HttpServletResponse response) {
		String email = request.getParameter("email");
		String bu_email = request.getParameter("bu_email");
		String chk = "true";
		MemberDao md = new MemberDao();

		System.out.println(bu_email);

		if (email != null) {
			Member m = md.selectMemberOne(email);
			chk = m == null ? "false" : "true";
			request.setAttribute("result", chk);
		}
		if (bu_email != null) {
			Business b = md.selectBusinessOne(bu_email);
			chk = b == null ? "false" : "true";
			request.setAttribute("result", chk);
		}

		return "/common/phoneAuth.jsp";
	}

	@RequestMapping("buInfo")
	public String buInfo(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		String bu_email = (String) session.getAttribute("bu_email");
		if (bu_email != null) {

			MemberDao md = new MemberDao();

			Business b = md.selectBusinessOne(bu_email);

			request.setAttribute("mem", b);

		} else {
			return "/member/buLoginForm";
		}
		return "/view/member/buInfo.jsp";
	}

	@RequestMapping("buUpdateForm")
	public String buUpdateForm(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		String bu_email = (String) session.getAttribute("bu_email");
		if (bu_email != null) {

			MemberDao md = new MemberDao();

			Business b = md.selectBusinessOne(bu_email);
			int pic_num = b.getPic_num();
			String location = "";
			
			List<Picture> pist =  md.selectPic(pic_num);
			for(Picture p : pist) {
				location += p.getLocation()+"\n";
			}
			request.setAttribute("pic_num", pic_num);
			request.setAttribute("location", location);
			request.setAttribute("mem", b);

		} else {
			return "/member/buLoginForm";
		}

		return "/view/member/buUpdateForm.jsp";
	}

	@RequestMapping("buUpdatePro")
	public String buUpdatePro(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		int pic_num = Integer.parseInt(request.getParameter("pic_num"));
		MemberDao md = new MemberDao();
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

		request.setAttribute("msg", msg);
		request.setAttribute("url", url);

		
		return "/view/alert.jsp";

	}

}
