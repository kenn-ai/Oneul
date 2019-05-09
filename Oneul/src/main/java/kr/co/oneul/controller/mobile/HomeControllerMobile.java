package kr.co.oneul.controller.mobile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/m")
public class HomeControllerMobile {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeControllerMobile.class);
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {

		return "home";
	}
	
}
