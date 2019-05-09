package kr.co.oneul.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.INoticeDAO;
import kr.co.oneul.vo.Criteria;
import kr.co.oneul.vo.NoticeVO;

@Repository
public class NoticeDAOImpl implements INoticeDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.noticeMapper";
	
	@Override
	public List<NoticeVO> listNotice() throws Exception {
		
		return sqlSession.selectList(namespace+".listNotice");
	}

	@Override
	public NoticeVO readNotice(Integer notice_no) throws Exception {
	
		return sqlSession.selectOne(namespace+".readNotice", notice_no);
	}

	@Override
	public int deleteNotice(Integer notice_no) throws Exception {
		
		return sqlSession.delete(namespace+".deleteNotice", notice_no);
	}

	@Override
	public int updateNotice(NoticeVO noticeVO) throws Exception {
		
		return sqlSession.update(namespace+".updateNotice", noticeVO);
	}

	@Override
	public int writeNotice(NoticeVO noticeVO) throws Exception {
	
		return sqlSession.insert(namespace+".writeNotice", noticeVO);
	}

	@Override
	public int hitUpdateNotice(Integer notice_no) throws Exception {
		
		return sqlSession.update(namespace+".hitUpdateNotice", notice_no);
	}

	@Override
	public List<NoticeVO> listCriteria(Criteria cri) throws Exception {
		return sqlSession.selectList(namespace+".listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return sqlSession.selectOne(namespace+".countPaging", cri);
	}



}
