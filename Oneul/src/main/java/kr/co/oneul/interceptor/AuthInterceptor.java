package kr.co.oneul.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();

		if (session.getAttribute("login") == null) {
			System.out.println("로그인 안되있음요.");

			response.sendRedirect("/user/login");
			return false;
		}
		System.out.println("로그인되있으니 통과 ㅇㅇ");
		return true;
	}
	
}
