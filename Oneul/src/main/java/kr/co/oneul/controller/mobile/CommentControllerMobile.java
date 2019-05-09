package kr.co.oneul.controller.mobile;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.service.ICommentService;
import kr.co.oneul.vo.CommentVO;

@Controller
@RequestMapping("/m/comment")
public class CommentControllerMobile {
	
	@Inject
	private ICommentService commentService;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentControllerMobile.class);
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public @ResponseBody List<CommentVO> commentList(Integer diary_no) throws Exception{
		List<CommentVO> commentList = commentService.listComment(diary_no);
		return commentList;
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public @ResponseBody Integer  writeComment(CommentVO commentVO, Integer diary_no, String nickname, String userid, String contents) throws Exception {
		commentVO.setDiary_no(diary_no);
		commentVO.setContents(contents);
		commentVO.setNickname(nickname);
		commentVO.setUserid(userid);
		int commentWriteResult = commentService.writeComment(commentVO);
		return commentWriteResult;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public @ResponseBody Integer deleteComment(Integer comment_no) throws Exception{
		int commentDeleteResult =commentService.deleteComment(comment_no);
		return commentDeleteResult;
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public @ResponseBody Integer updateComment(CommentVO commentVO, Integer comment_no, String contents) throws Exception{
		commentVO.setComment_no(comment_no);
		commentVO.setContents(contents);
		int commentUpdateResult =commentService.updateComment(commentVO);
		return commentUpdateResult;
	}

} 
