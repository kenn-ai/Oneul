package kr.co.oneul.service;

import java.util.List;

import kr.co.oneul.dto.AlarmDTO;
import kr.co.oneul.dto.SearchMemberIdDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.vo.MemberVO;

public interface IMemberService {

	public List<MemberVO> listMember(UserDTO userDTO) throws Exception;
	
	// 멤버추가
	public int addMember(AlarmDTO alarmDTO) throws Exception;
	// 멤버삭제
	public int minMember(MemberVO memberVO) throws Exception;
	
	public String searchMemberId(SearchMemberIdDTO searchMemberIdDTO) throws Exception;

}
