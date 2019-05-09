package kr.co.oneul.controller.mobile;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.dto.FcmDTO;
import kr.co.oneul.service.IFcmService;

@Controller
@RequestMapping("/m/fcm")
public class FCMControllerMobile {
	
	private static final Logger logger = LoggerFactory.getLogger(FCMControllerMobile.class);
	@Inject
	IFcmService fcmService;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registFcm(Model model, @RequestParam("token") String token, @RequestParam("userid") String userid) throws Exception{
		System.out.println("token값은 : " +token);
		System.out.println("userid값은 : " +userid);
		
		FcmDTO fcmDTO = new FcmDTO();
		fcmDTO.setToken(token);
		fcmDTO.setUserid(userid);
				
		if( (fcmService.checkToken(fcmDTO).size() != 0) && ( fcmService.checkToken2(fcmDTO) !=null ) ) {
			System.out.println("업데이트 토큰");
			fcmService.updateFcm(fcmDTO);
		} else {
			System.out.println("새로등록 토큰");
			fcmService.registFcm(fcmDTO);
		}
		
		return "/fcm/register";
	}
	
	@RequestMapping(value = "/getToken", method = RequestMethod.POST)
	@ResponseBody
	public FcmDTO getToken(Model model, @RequestParam("userid") String userid) throws Exception{
		FcmDTO fcmDTO = new FcmDTO();
		fcmDTO.setUserid(userid);
		System.out.println("토큰얻으려는 userid 는"+userid);
		FcmDTO fcmtest = fcmService.getToken(fcmDTO);
		System.out.println(userid+"의 토큰값은 : "+fcmtest.getToken());
//		return fcmService.getToken(fcmDTO);
		return fcmtest;
	}
	
}
