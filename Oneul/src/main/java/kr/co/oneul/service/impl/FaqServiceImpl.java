package kr.co.oneul.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.oneul.dao.IFaqDAO;
import kr.co.oneul.service.IFaqService;
import kr.co.oneul.vo.FaqVO;

@Service
public class FaqServiceImpl implements IFaqService {

	@Inject
	IFaqDAO faqDAO;
	
	@Override
	public List<FaqVO> listFaq() throws Exception {
		
		return faqDAO.listFaq();
	}

}
