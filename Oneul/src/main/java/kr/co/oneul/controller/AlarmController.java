package kr.co.oneul.controller;

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

import kr.co.oneul.service.IAlarmService;
import kr.co.oneul.vo.AlarmVO;

@Controller
@RequestMapping("/alarm")
public class AlarmController {
	
	private static final Logger logger = LoggerFactory.getLogger(AlarmController.class);
	
	@Inject
	IAlarmService alarmService;
	
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
//		return alarmService.countAlarm(alarmVO).size();
//		List<AlarmVO> list = new ArrayList<AlarmVO>();
//		list = alarmService.countAlarm(alarmVO);
		return alarmService.countAlarm(alarmVO);
	}
	
}


