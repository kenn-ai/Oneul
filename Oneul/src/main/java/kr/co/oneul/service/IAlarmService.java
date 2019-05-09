package kr.co.oneul.service;

import java.util.List;

import kr.co.oneul.dto.AlarmDTO;
import kr.co.oneul.vo.AlarmVO;

public interface IAlarmService {

	public int inviteAlarm(AlarmVO alarmVO) throws Exception;
	public int rejectAlarm(AlarmDTO alarmDTO) throws Exception;
	public List<AlarmVO> countAlarm(AlarmVO alarmVO) throws Exception;
}
