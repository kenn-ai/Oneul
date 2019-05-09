package kr.co.oneul.controller.mobile;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.service.INoticeService;
import kr.co.oneul.vo.Criteria;
import kr.co.oneul.vo.NoticeVO;
import kr.co.oneul.vo.PageMaker;

@Controller
@RequestMapping("/m/notice")
public class NoticeControllerMobile {

	@Inject
	private INoticeService noticeService;

	private static final Logger logger = LoggerFactory.getLogger(NoticeControllerMobile.class);

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody List<NoticeVO> listNotice() throws Exception {
		List<NoticeVO> noticeList = noticeService.listNotice();
		return noticeList;
	}
	
	/*//페이지네이션 적용한 리스트
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void listCriteria(Model model, Criteria cri) throws Exception {
		cri.setTotalNum(noticeService.countPaging(cri));
		model.addAttribute("noticeList", noticeService.listCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(cri.getTotalNum());
		model.addAttribute("pageMaker", pageMaker);
	}*/
	
	//페이지네이션 적용한 읽기페이지
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public @ResponseBody NoticeVO readNotice(Integer notice_no) throws Exception {
		noticeService.hitUpdateNotice(notice_no);
		NoticeVO noticeVO = noticeService.readNotice(notice_no);
		
		return noticeVO;
	}

	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeNotice() {
		return "/notice/write";
	}

	@RequestMapping(value = "/actWrite", method = RequestMethod.POST)
	public String actWriteNotice(NoticeVO noticeVO, Model model) throws Exception {
		noticeService.writeNotice(noticeVO);
		// result 스프링 p.114 쪽 플래시 사용해서 임시로 값저장하기
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
	public String actUpdateNotice(NoticeVO noticeVO, Model model) throws Exception {
		int updateResult = noticeService.updateNotice(noticeVO);
		return "redirect:/notice/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteNotice(NoticeVO noticeVO) throws Exception {
		int daleteResult = noticeService.deleteNotice(noticeVO.getNotice_no());
		return "redirect:/notice/list";
	}

}
