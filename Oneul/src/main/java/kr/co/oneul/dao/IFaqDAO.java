package kr.co.oneul.dao;

import java.util.List;

import kr.co.oneul.vo.FaqVO;

public interface IFaqDAO {
	
	public List<FaqVO> listFaq() throws Exception;

}
