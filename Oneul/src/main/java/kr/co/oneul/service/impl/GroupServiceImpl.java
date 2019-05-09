package kr.co.oneul.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.oneul.dao.IGroupDAO;
import kr.co.oneul.dto.GroupDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IGroupService;
import kr.co.oneul.vo.GroupVO;

@Service
public class GroupServiceImpl implements IGroupService {

	@Inject
	private IGroupDAO groupDAO;
	
	@Override
	public List<GroupVO> listGroup(UserDTO userDTO) throws Exception {
		return groupDAO.listGroup(userDTO);
	}

	@Override
	public int makeGroup(GroupDTO groupDTO) throws Exception {
		return groupDAO.makeGroup(groupDTO);
	}

	@Override
	public int leaveGroup(UserDTO userDTO) throws Exception {
		return groupDAO.leaveGroup(userDTO);
	}

	@Override
	public GroupVO infoGroup(UserDTO userDTO) throws Exception {
		return groupDAO.infoGroup(userDTO);
	}

}
