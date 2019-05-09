package kr.co.oneul.dao.impl;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.IUserDAO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.vo.UserVO;

@Repository
public class UserDAOImpl implements IUserDAO {

	@Inject
	private SqlSession sqlSession;
	
	private static final String namespace = "kr.co.oneul.userMapper";
	
	@Override
	public UserDTO loginUser(UserDTO userDTO) throws Exception {
		return sqlSession.selectOne(namespace + ".loginUser", userDTO);
	}

	@Override
	public Integer joinUser(UserVO userVO) throws Exception {
		return sqlSession.insert(namespace + ".joinUser", userVO);
	}
	
	@Override
	public UserVO readUser(UserDTO userDTO) throws Exception {
		
		return sqlSession.selectOne(namespace + ".readUser", userDTO);
	}

	@Override
	public Integer updateUser(UserVO userVO) throws Exception {
		
		return sqlSession.update(namespace + ".updateUser", userVO);
	}

	@Override
	public void leaveUser(UserDTO userDTO) throws Exception {
		
		sqlSession.delete(namespace + ".leaveUser", userDTO);
	}

	@Override
	public UserVO confirmUser(UserDTO userDTO) throws Exception {
		return sqlSession.selectOne(namespace + ".confirmUser", userDTO);
	}

	@Override
	public UserVO searchUser(UserDTO userDTO) throws Exception {
		return sqlSession.selectOne(namespace + ".searchUser", userDTO);
	}

	@Override
	public String checkUser(UserDTO userDTO) throws Exception {
		return sqlSession.selectOne(namespace + ".checkUser", userDTO);
	}



}
