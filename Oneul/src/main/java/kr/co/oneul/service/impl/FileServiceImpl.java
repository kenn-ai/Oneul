package kr.co.oneul.service.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import kr.co.oneul.dao.IFileDAO;
import kr.co.oneul.service.IFileService;

@Service
public class FileServiceImpl implements IFileService {

	@Inject
	IFileDAO fileDAO;

	@Override
	public void photoUpload(Map<String, Object> map, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
