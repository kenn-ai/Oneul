package kr.co.oneul.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//세션받아오기
		HttpSession session = request.getSession();
		
		//저장되어 있는 model 들의 map 가져오기
		ModelMap modelMap = modelAndView.getModelMap();
		
		//저장된 model 중 memberVO model attribute 가져오기
		Object loginDTO = modelMap.get("loginDTO");
		
		if(loginDTO != null){
			System.out.println("세션등록 성공!!");
			//세션등록
			session.setAttribute("login", loginDTO);
			//아무대도 안감 그 페이지 그대로 쓰겠다! response.sendRedirect("/");
			
//			Object dest = session.getAttribute("dest");
//			System.out.println((String)dest);
//			response.sendRedirect(dest != null ? (String)dest : "/board/boardListAll");
		}
	}
	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		
//		HttpSession session = request.getSession();
//
//		if(session.getAttribute("login") != null){
//			logger.info("이전 로그인 정보 삭제");
//			session.removeAttribute("login");
//		}
//		
//		return true;
//	}

}
