package kr.co.oneul.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.ICommentDAO;
import kr.co.oneul.vo.CommentVO;

@Repository
public class CommentDAOImpl implements ICommentDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.commentMapper";
	
	@Override
	public List<CommentVO> listComment(Integer diary_no) throws Exception {
		return sqlSession.selectList(namespace+".listComment",diary_no);
	}

	@Override
	public int writeComment(CommentVO commentVO) throws Exception {		
		return sqlSession.insert(namespace+".writeComment", commentVO);
	}

	@Override
	public int updateComment(CommentVO commentVO) throws Exception {	
		return sqlSession.update(namespace+".updateComment", commentVO);
	}

	@Override
	public int deleteComment(Integer comment_no) throws Exception {
		return sqlSession.delete(namespace+".deleteComment", comment_no);
	}

}
