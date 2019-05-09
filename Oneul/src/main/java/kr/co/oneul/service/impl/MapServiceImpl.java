package kr.co.oneul.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.oneul.dao.IMapDAO;
import kr.co.oneul.dto.MapSearchDTO;
import kr.co.oneul.service.IMapService;
import kr.co.oneul.vo.MapVO;

@Service
public class MapServiceImpl implements IMapService {

	@Inject
	IMapDAO mapDAO;

	@Override
	public Integer saveMap(MapVO mapVO) throws Exception {
		
		return mapDAO.saveMap(mapVO);
		
	}

	@Override
	public List<MapVO> todayMap(MapSearchDTO mapSearchDTO) throws Exception {

		return mapDAO.todayMap(mapSearchDTO);
		
	}
	
}
