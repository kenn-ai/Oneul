package kr.co.oneul.controller.mobile;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.service.IFaqService;
import kr.co.oneul.vo.FaqVO;

@Controller
@RequestMapping("/m/help")
public class HelpControllerMobile {

	// faq 포함
	@Inject
	private IFaqService faqService;

	private static final Logger logger = LoggerFactory.getLogger(HelpControllerMobile.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String example(Model model) {

		return "";
	}

	@RequestMapping(value = "/faq", method = RequestMethod.POST)
	public @ResponseBody List<FaqVO> faqListHelp(Model model) throws Exception {
		List<FaqVO> faqList = faqService.listFaq();
		return faqList;
	}

	@RequestMapping(value = "/qna", method = RequestMethod.GET)
	public void qnaHelp(Model model) throws Exception {
		// 미구현
	}

}
