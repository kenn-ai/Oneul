package kr.co.oneul.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.dto.AlarmDTO;
import kr.co.oneul.dto.GroupDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IAlarmService;
import kr.co.oneul.service.IGroupService;
import kr.co.oneul.service.IMemberService;
import kr.co.oneul.service.IUserService;
import kr.co.oneul.vo.AlarmVO;
import kr.co.oneul.vo.MemberVO;

@Controller
@RequestMapping("/group")
public class GroupController {
	
	// group+member
	
//	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Inject
	private IGroupService groupService;
	@Inject
	private IMemberService memberService;
	@Inject
	private IAlarmService alarmService;
	@Inject
	private IUserService userService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void mainGroup(Model model, HttpSession session) throws Exception{
		
		UserDTO userDTO = (UserDTO)session.getAttribute("login");
		
		model.addAttribute("groupList", groupService.listGroup(userDTO));
		model.addAttribute("userDTO", userDTO);
	}
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public void infoGroup(Model model, Integer group_no) throws Exception {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setGroup_no(group_no);
		List<MemberVO> memberList = memberService.listMember(userDTO);
		model.addAttribute("memberList", memberList);
		model.addAttribute("group_no", group_no);
		
	}
	
	@RequestMapping(value = "/invite", method = RequestMethod.POST)
	@ResponseBody
	public String inviteGroup(@RequestParam(value="receiver") String receiver, 
			@RequestParam(value="group_no") Integer group_no, HttpSession session) throws Exception {
		UserDTO userDTO = (UserDTO)session.getAttribute("login");
		System.out.println("invite에 들어왔다.");
		UserDTO receiverDTO = new UserDTO();
		receiverDTO.setUserid(receiver);
		String nickname_r = userService.searchUser(receiverDTO).getNickname();
		
		AlarmVO alarmVO = new AlarmVO();
		alarmVO.setWriter(userDTO.getUserid());
		alarmVO.setNickname_w(userDTO.getNickname());
		alarmVO.setReader(receiver);
		alarmVO.setNickname_r(nickname_r);
		alarmVO.setContents("님으로 부터 그룹 초대가 왔습니다.");
		alarmVO.setType("INVITE");
		alarmVO.setGroup_no(group_no);
		alarmService.inviteAlarm(alarmVO);
		return "Success";
	}
	
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	@ResponseBody
	public String inviteResult(	@RequestParam(value ="result") String result,
			@RequestParam(value ="alarm_no") Integer alarm_no,
			@RequestParam(value ="reader") String reader,
			@RequestParam(value ="group_no") Integer group_no) throws Exception {
			
			UserDTO userDTO = new UserDTO();
			userDTO.setUserid(reader);
			
			AlarmDTO alarmDTO = new AlarmDTO();
			alarmDTO.setAlarm_no(alarm_no);
			alarmDTO.setGroup_no(group_no);
			alarmDTO.setNickname(userService.searchUser(userDTO).getNickname());
			alarmDTO.setUserid(reader);
			
			System.out.println(alarmDTO.toString());
			
			System.out.println("result의 값은"+result);
			
			if(result.equals("accept")) {
				System.out.println("if문 안에 Accept로 들어옴");
				memberService.addMember(alarmDTO);
				return "accept";
			} else {
				System.out.println("if문 안에 Reject로 들어옴");
				alarmService.rejectAlarm(alarmDTO);
				return "reject";
			}
	}
	
	@RequestMapping(value = "/make", method = RequestMethod.GET)
	public String makeGroup(@ModelAttribute("makeDTO") GroupDTO makeDTO) throws Exception{
		return "/group/make";
	}
	
	@RequestMapping(value = "/actMake", method = RequestMethod.POST)
	public String actMakeGroup(GroupDTO groupDTO, HttpSession session) throws Exception{
		UserDTO userDTO = (UserDTO)session.getAttribute("login");
		groupDTO.setUserid(userDTO.getUserid());
		
		groupService.makeGroup(groupDTO);
		
		return "redirect:/group/list";
	}
	
	@RequestMapping(value = "/leave", method = RequestMethod.GET)
	public String leaveGroup(UserDTO userDTO, HttpSession session) throws Exception{
		System.out.println(userDTO);
		groupService.leaveGroup(userDTO);
		
		return "redirect:/group/list";
	}
	
}
