package kr.co.oneul.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface IFileService {

	// upload, download
	
	public void photoUpload(Map<String, Object> map, HttpServletRequest request) throws Exception;
	
}
