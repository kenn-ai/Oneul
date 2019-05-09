package kr.co.oneul.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.oneul.dao.IChartDAO;
import kr.co.oneul.dto.ChartDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IChartService;

@Service
public class ChartServiceImpl implements IChartService{

	@Inject
	private IChartDAO chartDAO;
	
	@Override
	public List<ChartDTO> weatherChart(UserDTO userDTO) throws Exception {
		return chartDAO.weatherChart(userDTO);
	}
	
	@Override
	public List<ChartDTO> emotionChart(UserDTO userDTO) throws Exception {
		return chartDAO.emotionChart(userDTO);
	}

	@Override
	public List<ChartDTO> diaryChart(UserDTO userDTO) throws Exception {
		return chartDAO.diaryChart(userDTO);
	}

}
