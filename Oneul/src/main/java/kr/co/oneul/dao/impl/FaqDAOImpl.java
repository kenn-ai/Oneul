package kr.co.oneul.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.IFaqDAO;
import kr.co.oneul.vo.FaqVO;

@Repository
public class FaqDAOImpl implements IFaqDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.faqMapper";
	
	@Override
	public List<FaqVO> listFaq() throws Exception {
		return sqlSession.selectList(namespace+".listFaq");
	}

}
