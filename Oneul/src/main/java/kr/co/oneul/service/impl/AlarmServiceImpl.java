package kr.co.oneul.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.oneul.dao.IAlarmDAO;
import kr.co.oneul.dto.AlarmDTO;
import kr.co.oneul.service.IAlarmService;
import kr.co.oneul.vo.AlarmVO;

@Service
public class AlarmServiceImpl implements IAlarmService {

	@Inject
	IAlarmDAO alarmDAO;
	
	@Override
	public int inviteAlarm(AlarmVO alarmVO) throws Exception {
		return alarmDAO.inviteAlarm(alarmVO);
	}

	@Override
	public int rejectAlarm(AlarmDTO alarmDTO) throws Exception {
		return alarmDAO.rejectAlarm(alarmDTO);
	}

	@Override
	public List<AlarmVO> countAlarm(AlarmVO alarmVO) throws Exception {
		return alarmDAO.countAlarm(alarmVO);
	}

}
