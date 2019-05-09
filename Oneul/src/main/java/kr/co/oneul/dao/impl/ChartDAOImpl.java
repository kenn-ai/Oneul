package kr.co.oneul.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.IChartDAO;
import kr.co.oneul.dto.ChartDTO;
import kr.co.oneul.dto.UserDTO;

@Repository
public class ChartDAOImpl implements IChartDAO{
	
	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.chartMapper";

	@Override
	public List<ChartDTO> weatherChart(UserDTO userDTO) throws Exception {
		return sqlSession.selectList(namespace+".weatherChart", userDTO);
	}
	
	@Override
	public List<ChartDTO> emotionChart(UserDTO userDTO) throws Exception {
		return sqlSession.selectList(namespace+".emotionChart", userDTO);
	}

	@Override
	public List<ChartDTO> diaryChart(UserDTO userDTO) throws Exception {
		return sqlSession.selectList(namespace+".diaryChart", userDTO);
	}
	
}
