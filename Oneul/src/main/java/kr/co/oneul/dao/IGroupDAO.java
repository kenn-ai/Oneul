package kr.co.oneul.dao;

import java.util.List;

import kr.co.oneul.dto.GroupDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.vo.GroupVO;

public interface IGroupDAO {

	public List<GroupVO> listGroup(UserDTO userDTO) throws Exception;
	public int makeGroup(GroupDTO groupDTO) throws Exception;
	public int leaveGroup(UserDTO userDTO) throws Exception;
	public GroupVO infoGroup(UserDTO userDTO) throws Exception;
	// make : 그룹생성
	// leave : 그룹탈퇴
	// ??? : 그룹명변경 만들건지?
}
