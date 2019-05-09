package kr.co.oneul.controller.mobile;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IAlarmService;
import kr.co.oneul.service.IUserService;
import kr.co.oneul.vo.AlarmVO;

@Controller
@RequestMapping("/m/alarm")
public class AlarmControllerMobile {
	
	private static final Logger logger = LoggerFactory.getLogger(AlarmControllerMobile.class);
	
	@Inject
	IAlarmService alarmService;
	@Inject
	IUserService userService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String example(Model model) {

		return "";
	}
	
	@RequestMapping(value = "/count")
	@ResponseBody 
	public List<AlarmVO> countAlarm(@RequestParam(value ="userid") String userid) throws Exception {
		System.out.println("userid 는 이것이다 : "+userid);
		AlarmVO alarmVO = new AlarmVO();
		alarmVO.setReader(userid);
		return alarmService.countAlarm(alarmVO);
	}
	
}
