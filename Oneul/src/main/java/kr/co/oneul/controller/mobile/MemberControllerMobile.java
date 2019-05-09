package kr.co.oneul.controller.mobile;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IMemberService;
import kr.co.oneul.vo.MemberVO;

@Controller
@RequestMapping("/m/member")
public class MemberControllerMobile {

	@Inject
	private IMemberService memberService;

	@RequestMapping(value= "/list")
	public @ResponseBody List<MemberVO> listMember(@RequestParam("group_no") Integer group_no) throws Exception{
		UserDTO userDTO = new UserDTO();
		userDTO.setGroup_no(group_no);
		return memberService.listMember(userDTO);
		
	}
}
