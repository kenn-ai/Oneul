package kr.co.oneul.dao;

import java.util.List;

import kr.co.oneul.vo.CommentVO;

public interface ICommentDAO {

	public List<CommentVO> listComment(Integer diary_no) throws Exception;
	public int writeComment(CommentVO commentVO) throws Exception;
	public int updateComment(CommentVO commentVO) throws Exception;
	public int deleteComment(Integer comment_no) throws Exception;
	
}
