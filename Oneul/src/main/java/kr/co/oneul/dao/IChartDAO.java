package kr.co.oneul.dao;

import java.util.List;

import kr.co.oneul.dto.ChartDTO;
import kr.co.oneul.dto.UserDTO;

public interface IChartDAO {
	
	public List<ChartDTO> weatherChart(UserDTO userDTO) throws Exception;
	public List<ChartDTO> emotionChart(UserDTO userDTO) throws Exception;
	public List<ChartDTO> diaryChart(UserDTO userDTO) throws Exception;
	
}
