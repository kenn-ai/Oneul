package kr.co.oneul.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.oneul.dto.MapSearchDTO;
import kr.co.oneul.dto.SearchDTO;
import kr.co.oneul.service.IMapService;
import kr.co.oneul.vo.MapVO;

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Inject
	private IMapService mapService;

	private static final Logger logger = LoggerFactory.getLogger(MapController.class);

	@RequestMapping(value = "/todaymarker", method = RequestMethod.GET)
	public String todayMarker(Model model) throws Exception {

		List<MapVO> marker = new ArrayList<>();
		System.out.println("todaymarker" + marker.toString());
		model.addAttribute("marker", marker);

		return "/chart/map";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(SearchDTO searchDTO, MapSearchDTO mapSearchDTO, Model model) throws Exception {

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
			model.addAttribute("marker", marker);
		} else {
		
		System.out.println("search1" + mapSearchDTO.toString());
		List<MapVO> marker = mapService.todayMap(mapSearchDTO);
		
			if(marker != null){
				
				HashSet<MapVO> markerSet = new HashSet<MapVO>(marker);
				ArrayList<MapVO> uniMarker = new ArrayList<MapVO>(markerSet);
										
				System.out.println("search2" + uniMarker.toString());
				model.addAttribute("marker", uniMarker);
			}
		}
		return "/chart/map";
	}
}
