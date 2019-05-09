package kr.co.oneul.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.oneul.dao.IFcmDAO;
import kr.co.oneul.dto.FcmDTO;
import kr.co.oneul.service.IFcmService;

@Service
public class FcmServiceImpl implements IFcmService {

	@Inject
	IFcmDAO fcmDao;
	
	@Override
	public int registFcm(FcmDTO fcmDTO) throws Exception {
		return fcmDao.registFcm(fcmDTO);
	}

	@Override
	public FcmDTO getToken(FcmDTO fcmDTO) throws Exception {
		return fcmDao.getToken(fcmDTO);
	}

	@Override
	public int updateFcm(FcmDTO fcmDTO) throws Exception {
		return fcmDao.updateFcm(fcmDTO);
	}

	@Override
	public List<FcmDTO> checkToken(FcmDTO fcmDTO) throws Exception {
		return fcmDao.checkToken(fcmDTO);
	}

	@Override
	public FcmDTO checkToken2(FcmDTO fcmDTO) throws Exception {
		return fcmDao.checkToken2(fcmDTO);
	}

}
