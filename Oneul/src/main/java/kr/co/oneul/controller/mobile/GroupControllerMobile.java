package kr.co.oneul.controller.mobile;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
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
import kr.co.oneul.vo.GroupVO;

@Controller
@RequestMapping("/m/group")
public class GroupControllerMobile {
	
	// group+member
	
//	private static final Logger logger = LoggerFactory.getLogger(GroupController.class);
	
	@Inject
	private IGroupService groupService;

	@Inject
	private IUserService userService;
	
	@Inject 
	private IMemberService memberService;
	
	@Inject
	private IAlarmService alarmService;
	
	@RequestMapping(value= "/list")
	public @ResponseBody List<GroupVO> listGroup(UserDTO userDTO, @RequestParam("userid") String userid) throws Exception{
		userDTO.setUserid(userid);
		List<GroupVO> list = groupService.listGroup(userDTO);
		System.out.println(list);
		return groupService.listGroup(userDTO);
	}
	
	@RequestMapping(value= "/make")
	public void makeGroup(GroupDTO groupDTO, @RequestParam("userid") String userid, @RequestParam("name") String name) throws Exception{
		groupDTO.setUserid(userid);
		groupDTO.setName(name);
		groupService.makeGroup(groupDTO);
	}
	
	@RequestMapping(value = "/invite", method = RequestMethod.POST)
	@ResponseBody
	public String inviteGroup(@RequestParam(value="receiver") String receiver, 
			@RequestParam(value="group_no") Integer group_no, @RequestParam(value="userid") String userid, @RequestParam(value="nickname") String nickname) throws Exception {
		System.out.println("Mobile invite에 들어왔다.");
		System.out.println("전달받은 값 : "+"receiver : "+receiver+"@ group_no :"+group_no+"@ userid :"+userid+"@ nickname : "+nickname);
		UserDTO receiverDTO = new UserDTO();
		receiverDTO.setUserid(receiver);
		String nickname_r = userService.searchUser(receiverDTO).getNickname();
		
		AlarmVO alarmVO = new AlarmVO();
		alarmVO.setWriter(userid);
		alarmVO.setNickname_w(nickname);
		alarmVO.setReader(receiver);
		alarmVO.setNickname_r(nickname_r);
		alarmVO.setContents("님으로 부터 그룹 초대가 왔습니다.");
		alarmVO.setType("INVITE");
		alarmVO.setGroup_no(group_no);
		alarmService.inviteAlarm(alarmVO);
		return "Success";
	}
	
	@RequestMapping(value="/leave")
	public void leaveGroup(@RequestParam("userid") String userid, @RequestParam("group_no") Integer group_no) throws Exception{
		System.out.println("들어오고있냐 리브");
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(userid);
		userDTO.setGroup_no(group_no);
		groupService.leaveGroup(userDTO);
	}
	
	@RequestMapping(value = "/accept", method = RequestMethod.POST)
	@ResponseBody
	public String inviteResult(	@RequestParam(value ="result") String result, @RequestParam(value ="reader") String reader,
			@RequestParam(value="alarm_no") String alarm_no, @RequestParam(value="group_no") String group_no) throws Exception {
			
		System.out.println("result 는 : " +result + "reader는 : "+reader);
		System.out.println("alarm_no 는 : " +alarm_no + "group_no는 : "+group_no);
			UserDTO userDTO = new UserDTO();
			userDTO.setUserid(reader);
			
			AlarmDTO alarmDTO = new AlarmDTO();
			alarmDTO.setAlarm_no(Integer.parseInt(alarm_no));
			alarmDTO.setGroup_no(Integer.parseInt(group_no));
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

}
