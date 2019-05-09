package kr.co.oneul.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.oneul.dao.ICommentDAO;
import kr.co.oneul.service.ICommentService;
import kr.co.oneul.vo.CommentVO;

@Service
public class CommentServiceImpl implements ICommentService {

	@Inject
	ICommentDAO commentDAO;
	
	@Override
	public List<CommentVO> listComment(Integer diary_no) throws Exception {	
		return commentDAO.listComment(diary_no);
	}

	@Override
	public int writeComment(CommentVO commentVO) throws Exception {	
		return commentDAO.writeComment(commentVO);
	}

	@Override
	public int updateComment(CommentVO commentVO) throws Exception {
		return commentDAO.updateComment(commentVO);
	}

	@Override
	public int deleteComment(Integer comment_no) throws Exception {	
		return commentDAO.deleteComment(comment_no);
	}

}
