package kr.co.oneul.controller.mobile;

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

import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IUserService;
import kr.co.oneul.vo.UserVO;

@Controller
@RequestMapping("/m/user")
public class UserControllerMobile {

	private static final Logger logger = LoggerFactory.getLogger(UserControllerMobile.class);

	@Inject
	private IUserService userService;

	// 로그인 관련
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody UserDTO mlogin(UserDTO userDTO) throws Exception {
		UserDTO loginDTO = userService.loginUser(userDTO);
		loginDTO.setUserpw(null);
		System.out.println("모바일에서 로그인함");
		return loginDTO;
	}
	
	// 회원가입 관련
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public @ResponseBody Integer mjoin(UserVO userVO) throws Exception {
		int result = userService.joinUser(userVO);
		return result;
	}

	// 유저정보여부 확인
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public @ResponseBody UserVO mCheck(UserDTO userDTO) throws Exception{
		return userService.confirmUser(userDTO);
	}
	
	@RequestMapping(value = "/readinfo", method = RequestMethod.POST)
	public @ResponseBody UserVO mReadinfo(UserDTO userDTO) throws Exception{
		UserVO result = userService.readUser(userDTO);
		result.setUserpw(null);
		return result;
	}

	// 유저정보 수정관련
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Integer mUpdate(UserVO userVO) throws Exception{
		int result = userService.updateUser(userVO);
		return result;
	}

	// 유저 탈퇴 관련
	@RequestMapping(value = "/leave", method = RequestMethod.POST)
	public @ResponseBody Integer mLeave(UserDTO userDTO) throws Exception{
		userService.leaveUser(userDTO);
		int ok = 1;
		int no = 0;
		if(userService.confirmUser(userDTO) == null){
			return ok;
		}
		return no;
	}
	
	// 유저 검색(그룹에 추가할때 확인)
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody UserVO searchUser(UserDTO userDTO) throws Exception {
		return userService.searchUser(userDTO);
	}

}
