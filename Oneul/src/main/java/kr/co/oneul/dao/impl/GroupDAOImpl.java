package kr.co.oneul.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.IGroupDAO;
import kr.co.oneul.dto.GroupDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.vo.GroupVO;

@Repository
public class GroupDAOImpl implements IGroupDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.groupMapper";
	
	@Override
	public List<GroupVO> listGroup(UserDTO userDTO) throws Exception {		
		return sqlSession.selectList(namespace+".listGroup", userDTO);
	}

	@Override
	public int makeGroup(GroupDTO groupDTO) throws Exception {
		return sqlSession.insert(namespace+".makeGroup", groupDTO);
	}

	@Override
	public int leaveGroup(UserDTO userDTO) throws Exception {
		return sqlSession.delete(namespace+".leaveGroup", userDTO);
	}

	@Override
	public GroupVO infoGroup(UserDTO userDTO) throws Exception {
		return sqlSession.selectOne(namespace+".infoGroup", userDTO);
	}

}
