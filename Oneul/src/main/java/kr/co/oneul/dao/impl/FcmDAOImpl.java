package kr.co.oneul.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.IFcmDAO;
import kr.co.oneul.dto.FcmDTO;

@Repository
public class FcmDAOImpl implements IFcmDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.fcmMapper";
	
	@Override
	public int registFcm(FcmDTO fcmDTO) throws Exception {
		return sqlSession.insert(namespace+".regist", fcmDTO);
	}

	@Override
	public FcmDTO getToken(FcmDTO fcmDTO) throws Exception {
		return sqlSession.selectOne(namespace+".getToken", fcmDTO);
	}

	@Override
	public int updateFcm(FcmDTO fcmDTO) throws Exception {
		return sqlSession.update(namespace+".update", fcmDTO);
	}

	@Override
	public List<FcmDTO> checkToken(FcmDTO fcmDTO) throws Exception {
		return sqlSession.selectList(namespace+".checkToken", fcmDTO);
	}

	@Override
	public FcmDTO checkToken2(FcmDTO fcmDTO) throws Exception {
		return sqlSession.selectOne(namespace+".checkToken2", fcmDTO);
	}

}
