package kr.co.oneul.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.oneul.dao.IMemberDAO;
import kr.co.oneul.dto.AlarmDTO;
import kr.co.oneul.dto.SearchMemberIdDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IMemberService;
import kr.co.oneul.vo.MemberVO;

@Service
public class MemberServiceImpl implements IMemberService {

	@Inject
	IMemberDAO memberDAO;
	
	@Override
	public List<MemberVO> listMember(UserDTO userDTO) throws Exception {
		return memberDAO.listMember(userDTO);
	}

	@Override
	public int addMember(AlarmDTO alarmDTO) throws Exception {
		return memberDAO.addMember(alarmDTO);
	}

	@Override
	public int minMember(MemberVO memberVO) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String searchMemberId(SearchMemberIdDTO searchMemberIdDTO) throws Exception {
		return memberDAO.searchMemberId(searchMemberIdDTO);
	}

}
