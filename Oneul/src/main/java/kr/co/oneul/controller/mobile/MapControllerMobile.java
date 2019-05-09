package kr.co.oneul.controller.mobile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.controller.MapController;
import kr.co.oneul.dto.MapSearchDTO;
import kr.co.oneul.dto.SearchDTO;
import kr.co.oneul.service.IMapService;
import kr.co.oneul.vo.MapVO;

@Controller
@RequestMapping("/m/map")
public class MapControllerMobile {
	
	private static final Logger logger = LoggerFactory.getLogger(MapController.class);
	
	@Inject
	private IMapService mapService;
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody Integer saveMap(String userid, String latitude, String longitude, String address) throws Exception {
		MapVO mapVO = new MapVO();
		
		double x = Double.valueOf(latitude);
		double y = Double.valueOf(longitude);
		
		mapVO.setUserid(userid);
		mapVO.setX(x);
		mapVO.setY(y);
		mapVO.setAddress(address);
       
		return  mapService.saveMap(mapVO);
       
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody List<MapVO> search(SearchDTO searchDTO, MapSearchDTO mapSearchDTO) throws Exception {

		if (searchDTO.getYear().equals("년")) {
			searchDTO.setYear("____");
		}
		if (searchDTO.getMonth().equals("월")) {
			searchDTO.setMonth("__");
		}
		if (searchDTO.getDay().equals("일")) {
			searchDTO.setDay("__");
		}
		mapSearchDTO.setRegdate(searchDTO.getYear() + searchDTO.getMonth() + searchDTO.getDay());
		
		if(mapSearchDTO.getRegdate().equals("________")){
			List<MapVO> marker = new ArrayList<>();
			System.out.println("todaymarker ser" + marker.toString());
			return marker;
		} else {
		System.out.println("search1" + mapSearchDTO.toString());
		List<MapVO> marker = mapService.todayMap(mapSearchDTO);
		
		if(marker != null){
			
			HashSet<MapVO> markerSet = new HashSet<MapVO>(marker);
			ArrayList<MapVO> uniMarker = new ArrayList<MapVO>(markerSet);
									
			System.out.println("search2" + uniMarker.toString());
			return uniMarker;
		} else {
			List<MapVO> nullMarker = new ArrayList<>();
			return nullMarker;
		}
		}
	}
}
