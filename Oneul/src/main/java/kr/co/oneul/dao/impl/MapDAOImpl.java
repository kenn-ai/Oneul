package kr.co.oneul.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.IMapDAO;
import kr.co.oneul.dto.MapSearchDTO;
import kr.co.oneul.vo.MapVO;

@Repository
public class MapDAOImpl implements IMapDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.mapMapper";
	
	@Override
	public Integer saveMap(MapVO mapVO) throws Exception {
		
		return sqlSession.insert(namespace + ".savemap", mapVO);
		
	}
	@Override
	public List<MapVO> todayMap(MapSearchDTO mapSearchDTO) throws Exception {
	
		return sqlSession.selectList(namespace + ".todaymap", mapSearchDTO);
		
	}
	
}
