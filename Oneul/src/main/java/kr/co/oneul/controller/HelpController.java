package kr.co.oneul.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.oneul.service.IFaqService;

@Controller
@RequestMapping("/help")
public class HelpController {
	
	// faq 포함
	@Inject
	private IFaqService faqService;
	
	private static final Logger logger = LoggerFactory.getLogger(HelpController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String example(Model model) {

		return "";
	}
	
	@RequestMapping(value = "/faq", method = RequestMethod.GET)
	public void faqListHelp(Model model) throws Exception{
		
		model.addAttribute("faqList", faqService.listFaq());
	}
	
	@RequestMapping(value = "/qna", method = RequestMethod.GET)
	public void qnaHelp(Model model) throws Exception{
		
	}
	
}
