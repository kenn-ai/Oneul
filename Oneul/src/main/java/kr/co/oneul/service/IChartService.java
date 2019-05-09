package kr.co.oneul.service;

import java.util.List;

import kr.co.oneul.dto.ChartDTO;
import kr.co.oneul.dto.UserDTO;

public interface IChartService {
	
	public List<ChartDTO> weatherChart(UserDTO userDTO) throws Exception;
	public List<ChartDTO> emotionChart(UserDTO userDTO) throws Exception;
	public List<ChartDTO> diaryChart(UserDTO userDTO) throws Exception;
	
}
