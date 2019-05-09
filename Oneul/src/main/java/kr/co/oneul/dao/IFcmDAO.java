package kr.co.oneul.dao;

import java.util.List;

import kr.co.oneul.dto.FcmDTO;

public interface IFcmDAO {
	public int registFcm(FcmDTO fcmDTO) throws Exception;
	public List<FcmDTO> checkToken(FcmDTO fcmDTO) throws Exception;
	public FcmDTO checkToken2(FcmDTO fcmDTO) throws Exception;
	public FcmDTO getToken(FcmDTO fcmDTO) throws Exception;
	public int updateFcm(FcmDTO fcmDTO) throws Exception;
}
