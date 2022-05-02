package interceptor;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MemberLoginInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		
		if(email == null) {
			String msg = URLEncoder.encode("로그인이 필요합니다.", "UTF-8");
			response.sendRedirect(request.getContextPath()+"/member/loginForm?msg="+msg);
			return false;
		}
		return true;
	}
}
