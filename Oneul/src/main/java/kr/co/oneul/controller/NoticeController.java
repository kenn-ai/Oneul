package kr.co.oneul.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.oneul.service.INoticeService;
import kr.co.oneul.vo.Criteria;
import kr.co.oneul.vo.NoticeVO;
import kr.co.oneul.vo.PageMaker;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Inject
	private INoticeService noticeService;

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	public String listNotice(Model model) throws Exception {
//		List<NoticeVO> noticeList = noticeService.listNotice();
//		model.addAttribute("noticeList", noticeList);
//		return "/notice/list";
//	}
	
	//페이지네이션 적용한 리스트
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void listCriteria(Model model, Criteria cri) throws Exception {
		cri.setTotalNum(noticeService.countPaging(cri));
		model.addAttribute("noticeList", noticeService.listCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(cri.getTotalNum());
		model.addAttribute("pageMaker", pageMaker);
	}
	
	//페이지네이션 적용한 읽기페이지
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readNotice(@RequestParam("notice_no") Integer notice_no, Model model, @ModelAttribute("cri") Criteria cri) throws Exception {
		noticeService.hitUpdateNotice(notice_no);
		NoticeVO noticeVO = noticeService.readNotice(notice_no);
		model.addAttribute("noticeVO", noticeVO);
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeNotice() {
		return "/notice/write";
	}

	@RequestMapping(value = "/actWrite", method = RequestMethod.POST)
	public String actWriteNotice(NoticeVO noticeVO, RedirectAttributes rttr) throws Exception {
		int noticeWriteResult = noticeService.writeNotice(noticeVO);
		rttr.addFlashAttribute("noticeWriteResult", noticeWriteResult);
		return "redirect:/notice/list";
	}

	@RequestMapping(value = "/update")
	public String updateNotice(Model model, Integer notice_no) throws Exception {
		System.out.println("???");
		NoticeVO noticeVO = noticeService.readNotice(notice_no);
		model.addAttribute("noticeVO", noticeVO);
		return "/notice/update";
	}

	@RequestMapping(value = "/actUpdate", method = RequestMethod.POST)
	public String actUpdateNotice(NoticeVO noticeVO, RedirectAttributes rttr) throws Exception {
		int noticeUpdateResult = noticeService.updateNotice(noticeVO);
		rttr.addFlashAttribute("noticeWriteResult", noticeUpdateResult);
		return "redirect:/notice/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteNotice(NoticeVO noticeVO, RedirectAttributes rttr) throws Exception {
		int noticeDeleteResult = noticeService.deleteNotice(noticeVO.getNotice_no());
		rttr.addFlashAttribute("noticeDeleteResult", noticeDeleteResult);
		return "redirect:/notice/list";
	}

}
