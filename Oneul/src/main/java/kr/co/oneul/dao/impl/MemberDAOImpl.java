package kr.co.oneul.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.IMemberDAO;
import kr.co.oneul.dto.AlarmDTO;
import kr.co.oneul.dto.SearchMemberIdDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.vo.MemberVO;

@Repository
public class MemberDAOImpl implements IMemberDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.memberMapper";
	
	@Override
	public List<MemberVO> listMember(UserDTO userDTO) throws Exception {
		return sqlSession.selectList(namespace+".listMember", userDTO);
	}

	@Override
	public int addMember(AlarmDTO alarmDTO) throws Exception {
		return sqlSession.insert(namespace+".addMember", alarmDTO);
	}

	@Override
	public int minMember(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String searchMemberId(SearchMemberIdDTO searchMemberIdDTO) throws Exception {
		return sqlSession.selectOne(namespace+".searchMemberId", searchMemberIdDTO);
	}

}
