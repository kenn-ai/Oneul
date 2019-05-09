package kr.co.oneul.dao;

import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.vo.UserVO;

public interface IUserDAO {

	public UserDTO loginUser(UserDTO userDTO) throws Exception;
	public Integer joinUser(UserVO userVO) throws Exception;
	public UserVO readUser(UserDTO userDTO) throws Exception;  //추가함
	public Integer updateUser(UserVO userVO) throws Exception;
	public void leaveUser(UserDTO userDTO) throws Exception;
	
	// 신원확인
	public UserVO confirmUser(UserDTO userDTO) throws Exception;
	
	// 아이디 중복체크
	public String checkUser(UserDTO userDTO) throws Exception;
	
	// 유저검색(그룹에추가할 멤버검색)
	public UserVO searchUser(UserDTO userDTO) throws Exception;
	
}
