package kr.co.oneul.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.oneul.service.ICommentService;
import kr.co.oneul.vo.CommentVO;

@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Inject
	private ICommentService commentService;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String commentList(Model model, @ModelAttribute("diary_no") Integer diary_no) throws Exception{
		List<CommentVO> commentList = commentService.listComment(diary_no);
		model.addAttribute("commentList", commentList);
		model.addAttribute("diary_no", diary_no);
		return "/diary/addComment";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writeComment(CommentVO commentVO, Model model, @ModelAttribute("diary_no") Integer diary_no) throws Exception {
		int commentWirteResult = commentService.writeComment(commentVO);
		model.addAttribute("commentWirteResult", commentWirteResult);
		return "forward:/diary/read";
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String updateComment(CommentVO commentVO,Model model, @ModelAttribute("diary_no") Integer diary_no)throws Exception{
		System.out.println(commentVO.toString());
		int commentUpdateResult =commentService.updateComment(commentVO);
		model.addAttribute("commentUpdateResult", commentUpdateResult);
		return "forward:/diary/read";
	}

	@RequestMapping(value="/delete",method=RequestMethod.POST)
	public String deleteComment(CommentVO commentVO, Model model, @ModelAttribute("diary_no") Integer diary_no) throws Exception{
		int commentDeleteResult = commentService.deleteComment(commentVO.getComment_no());
		model.addAttribute("commentDeleteResult", commentDeleteResult);
		return "forward:/diary/read";
	}
	
} 
