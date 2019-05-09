package kr.co.oneul.dao;

import java.util.List;

import kr.co.oneul.dto.MapSearchDTO;
import kr.co.oneul.vo.MapVO;

public interface IMapDAO {

	public Integer saveMap(MapVO mapVO) throws Exception;
	public List<MapVO> todayMap(MapSearchDTO mapSearchDTO) throws Exception;
	
}
