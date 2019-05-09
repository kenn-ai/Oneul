package kr.co.oneul.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.dto.ChartDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IChartService;
import kr.co.oneul.service.IUserService;

@Controller
@RequestMapping("/chart")
public class ChartController {
	
	@Inject
	private IChartService chartService;
	@Inject
	private IUserService userService;
	private static final Logger logger = LoggerFactory.getLogger(ChartController.class);
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String example(Model model, HttpSession session) throws Exception{
		UserDTO userDTO = (UserDTO) session.getAttribute("login");
		Date regdate = userService.readUser(userDTO).getRegdate();
		Date now = new Date();
		Date differ;
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(regdate);
		cal2.setTime(now);
		long dday = TimeUnit.MILLISECONDS.toDays(cal2.getTimeInMillis()-cal1.getTimeInMillis())+1;
		model.addAttribute("dday",dday);

		return "/chart/main";
	}

	@RequestMapping(value = "/weatherBar", method = RequestMethod.POST)
	@ResponseBody
	public List<ChartDTO> chartWeatherBar(Model model, @RequestParam(value ="userid") String userid) throws Exception{
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(userid);
		System.out.println(chartService.weatherChart(userDTO));
		return chartService.weatherChart(userDTO);
	}
	
	@RequestMapping(value = "/emotionBar", method = RequestMethod.POST)
	@ResponseBody
	public List<ChartDTO> chartEmotionBar(Model model, @RequestParam(value ="userid") String userid) throws Exception{
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(userid);
		return chartService.emotionChart(userDTO);
	}
	
	@RequestMapping(value = "/line", method = RequestMethod.POST)
	@ResponseBody
	public List<ChartDTO> chartLine(Model model, @RequestParam(value ="userid") String userid) throws Exception{
		UserDTO userDTO = new UserDTO();
		userDTO.setUserid(userid);
		return chartService.diaryChart(userDTO);
		
	}
}
