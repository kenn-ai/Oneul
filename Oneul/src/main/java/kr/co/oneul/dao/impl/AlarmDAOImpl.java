package kr.co.oneul.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.IAlarmDAO;
import kr.co.oneul.dto.AlarmDTO;
import kr.co.oneul.vo.AlarmVO;

@Repository
public class AlarmDAOImpl implements IAlarmDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.alarmMapper";
	
	@Override
	public int inviteAlarm(AlarmVO alarmVO) throws Exception {
		return sqlSession.insert(namespace+".inviteAlarm", alarmVO);
	}

	@Override
	public int rejectAlarm(AlarmDTO alarmDTO) throws Exception {
		return sqlSession.delete(namespace+".deleteAlarm", alarmDTO);
	}

	@Override
	public List<AlarmVO> countAlarm(AlarmVO alarmVO) throws Exception {
		return sqlSession.selectList(namespace+".countAlarm", alarmVO);
	}

}
