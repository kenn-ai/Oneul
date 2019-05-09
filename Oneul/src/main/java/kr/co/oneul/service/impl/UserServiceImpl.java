package kr.co.oneul.service.impl;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.oneul.dao.IUserDAO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IUserService;
import kr.co.oneul.vo.UserVO;

@Service
public class UserServiceImpl implements IUserService {

	@Inject
	private IUserDAO userDAO;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDTO loginUser(UserDTO userDTO) throws Exception {
		// String encoderpw = passwordEncoder.encode(userDTO.getUserpw());
		// System.out.println(encoderpw);
		// userDTO.setUserpw(encoderpw);

		// userDAO.loginUser(userDTO);

		UserDTO login = userDAO.loginUser(userDTO);

		try {
			boolean isValidPassword = BCrypt.checkpw(userDTO.getUserpw(), login.getUserpw());

			if (isValidPassword == true) {
				return login;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println("로그인 에러");
			return null;
		}

	}

	@Override
	public Integer joinUser(UserVO userVO) throws Exception {
		try {
			String encoderpw = passwordEncoder.encode(userVO.getUserpw());
			System.out.println(encoderpw);
			userVO.setUserpw(encoderpw);
		} catch (Exception e) {
			System.out.println("회원가입 에러");
		}
		return userDAO.joinUser(userVO);
	}

	@Override
	public UserVO readUser(UserDTO userDTO) throws Exception {
		try {
			return userDAO.readUser(userDTO);
		} catch (Exception e) {
			System.out.println("유저정보 읽어오기 에러");
			return null;
		}
	}

	@Override
	public Integer updateUser(UserVO userVO) throws Exception {
		try {
			String encoderpw = passwordEncoder.encode(userVO.getUserpw());
			System.out.println(encoderpw);
			userVO.setUserpw(encoderpw);

		} catch (Exception e) {
			System.out.println("회원정보 수정 에러");
			return null;
		}
		return userDAO.updateUser(userVO);
	}

	@Override
	public void leaveUser(UserDTO userDTO) throws Exception {
		try {
			// return
			userDAO.leaveUser(userDTO);
		} catch (Exception e) {
			System.out.println("회원 탈퇴 에러");
			// return null;
		}
	}

	@Override
	public UserVO confirmUser(UserDTO userDTO) throws Exception {

		try {
			UserVO contirm = userDAO.confirmUser(userDTO);
			System.out.println(contirm.toString());

			boolean isValidPassword = BCrypt.checkpw(userDTO.getUserpw(), contirm.getUserpw());

			if (isValidPassword == true) {
				return contirm;
			} else {
				return null;
			}

		} catch (Exception e) {
			System.out.println("회원 확인 에러");
			return null;
		}
	}

	@Override
	public UserVO searchUser(UserDTO userDTO) throws Exception {
		try {
			return userDAO.searchUser(userDTO);
		} catch (Exception e) {
			System.out.println("회원 검색 에러");
			return null;
		}
	}

	@Override
	public String checkUser(UserDTO userDTO) throws Exception {

		return userDAO.checkUser(userDTO);
	}

}
