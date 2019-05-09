package kr.co.oneul.dao;

import java.util.List;

import kr.co.oneul.dto.AlarmDTO;
import kr.co.oneul.vo.AlarmVO;

public interface IAlarmDAO {

	public int inviteAlarm(AlarmVO alarmVO) throws Exception;
	public int rejectAlarm(AlarmDTO alarmDTO) throws Exception;
	public List<AlarmVO> countAlarm(AlarmVO alarmVO) throws Exception;
	
}
