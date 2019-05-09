package kr.co.oneul.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.oneul.dto.MapSearchDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IMapService;
import kr.co.oneul.vo.MapVO;

@Controller
@RequestMapping("/")
public class HomeController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Inject
	private IMapService mapService;
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(HttpSession session, MapSearchDTO mapSearchDTO, Model model) throws Exception{
		
		UserDTO loginDTO = (UserDTO)session.getAttribute("login");
        String userid = loginDTO.getUserid();
		
		long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat webNow = new SimpleDateFormat("yyyyMMdd");
        String strWebNow = webNow.format(date);       
        
        mapSearchDTO.setUserid(userid);
        mapSearchDTO.setRegdate(strWebNow);
        
		List<MapVO> marker = mapService.todayMap(mapSearchDTO);
		System.out.println(marker.toString());
		model.addAttribute("marker", marker);
		if(marker != null){
			
			HashSet<MapVO> markerSet = new HashSet<MapVO>(marker);
			ArrayList<MapVO> uniMarker = new ArrayList<MapVO>(markerSet);
									
			System.out.println("homeMap" + uniMarker.toString());
			model.addAttribute("marker", uniMarker);
		}
		
		
		return "home";
	}

}
