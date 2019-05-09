package kr.co.oneul.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IUserService;
import kr.co.oneul.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Inject
	private IUserService userService;
	
	//로그인 관련
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@ModelAttribute("userDTO") UserDTO userDTO){
		return "user/login";
	}
	
	@RequestMapping(value = "/actLogin", method = RequestMethod.POST)
	public String actLogin(UserDTO userDTO, HttpSession session, Model model)throws Exception{
		
		UserDTO loginDTO = userService.loginUser(userDTO);
		
		//로그인 실패시
		if(loginDTO == null){
	         model.addAttribute("faildto", userDTO);
	         model.addAttribute("loginResult", "fail");
	         System.out.println("로그인 실패");
	         return "user/login";
	      }
		
		loginDTO.setUserpw(null);		
		model.addAttribute("loginDTO", loginDTO);
	
		System.out.println(loginDTO.toString());
		return "redirect:/home";
	}
	
	//아이디 체크
	@RequestMapping(value = "/actCheckid", method = RequestMethod.POST)
	public String actCheckid(UserDTO userDTO, HttpSession session, Model model) throws Exception{
		String re = userService.checkUser(userDTO);
		System.out.println(re);
		session.setAttribute("checkid", userDTO.getUserid());
		model.addAttribute("result", re);
		return "user/checkid";
	}
	
	@RequestMapping(value = "/actJoin", method = RequestMethod.POST)
	public String joinUser(UserVO userVO, HttpSession session)throws Exception{
		
		if(session.getAttribute("checkid") != null){
			logger.info("회원가입 성공이므로 넣어놨던거 제거");
			session.invalidate();
		}
		userService.joinUser(userVO);
		
		return "user/login";
	}
	
	//유저정보여부 확인
	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String goCheckUser(HttpSession session, @ModelAttribute("userDTO") UserDTO userDTO, Model model, String code) throws Exception{
		System.out.println(code);
		model.addAttribute("Code", code);
		return "user/check";
	}
	
	
	@RequestMapping(value = "/actCheck", method = RequestMethod.POST)
	public String checkUser(HttpSession session, Model model, UserDTO userDTO, UserVO userVO) throws Exception{
		
			
			UserVO chkVO = userService.confirmUser(userDTO);
			
			
			System.out.println("확인 결과");
				
			if(chkVO != null){
				System.out.println("일치하므로 정보 읽어오기");			
				return "redirect:/user/update";			
				}
					System.out.println("틀림");
				return "user/check";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String goUserInfo(HttpSession session, Model model, @ModelAttribute("userVO") UserVO userVO, UserDTO userDTO) throws Exception{
		
		UserDTO loginDTO = (UserDTO)session.getAttribute("login");
		userVO = userService.readUser(loginDTO);
		model.addAttribute("userinfo", userVO);
		
		return "user/update";
	}
	
	
	//유저정보 수정관련
	@RequestMapping(value = "/actUpdate", method = RequestMethod.POST)
	public String updateUser(HttpSession session, UserVO userVO, Model model, UserDTO updateDTO)throws Exception{
		
		UserDTO loginDTO = (UserDTO)session.getAttribute("login");
		
		int result = userService.updateUser(userVO);
		System.out.println(result);
		
		updateDTO.setUserid(userVO.getUserid());
		updateDTO.setNickname(userVO.getNickname());
		updateDTO.setAuthority(loginDTO.getAuthority());
		
		System.out.println(userVO);
		System.out.println(updateDTO);
		
		model.addAttribute("updateDTO", updateDTO);
		
		return "redirect:/home";
	}
	
	//유저 탈퇴 관련
	@RequestMapping(value = "/leave", method = RequestMethod.GET)
	public String goLeaveUser(@ModelAttribute("userDTO") UserDTO userDTO) throws Exception{
		return "user/leave";
	}
	
	@RequestMapping(value = "/last", method = RequestMethod.POST)
	public String last(UserDTO userDTO)throws Exception{
		
		UserVO chkVO = userService.confirmUser(userDTO);
		System.out.println(chkVO);
		
		if(chkVO == null){
			System.out.println("잘못 입력");
			return "redirect:/home";
		}else{
		return "user/last";
		}
	}
	
	@RequestMapping(value = "/actLeave", method = RequestMethod.POST)
	public String leaveUser(HttpSession session, UserDTO userDTO, RedirectAttributes rttr)throws Exception{
		
		userService.leaveUser(userDTO);
		int leaveOk = 1;
		UserVO check = userService.confirmUser(userDTO);
		
		if(check == null){
			
			if(session.getAttribute("login") != null){
				System.out.println("탈퇴했습니다");
				session.invalidate();
				rttr.addFlashAttribute("leaveResult", leaveOk);
			}
		}else{
		
			logger.info("탈퇴실패");
			return "home";
		}
		
		
		return "redirect:/user/login";
		
	}
	
	//유저 검색(그룹에 추가할때 확인)
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchUser(Model model, UserDTO userDTO)throws Exception{
		
		
		if(userDTO.getUserid() == null){
			return "group/main";
		}else{
		UserVO secVO = userService.searchUser(userDTO);
			if(secVO.getUserid() != null){
				System.out.println(secVO);
				
				model.addAttribute("memberVO", secVO);
				
				return "group/main";
			}
		}return "group/main";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpSession session, RedirectAttributes rttr) throws Exception{
		
		int logoutOk = 1;
		System.out.println("로그아웃");
		if(session.getAttribute("login") != null){
			logger.info("로그아웃");
			session.invalidate();
			rttr.addFlashAttribute("logoutResult", logoutOk);
		}
		return "redirect:/home";
	}
	
}
