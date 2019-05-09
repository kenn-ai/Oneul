package kr.co.oneul.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.oneul.dto.SearchDTO;
import kr.co.oneul.dto.SearchMemberIdDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IDiaryService;
import kr.co.oneul.service.IGroupService;
import kr.co.oneul.service.IMemberService;
import kr.co.oneul.vo.DiaryCriteria;
import kr.co.oneul.vo.DiaryPageMaker;
import kr.co.oneul.vo.DiaryVO;
import kr.co.oneul.vo.GroupVO;
import kr.co.oneul.vo.MemberVO;

@Controller
@RequestMapping("/diary")
public class DiaryController {
	
	@Inject
	private IDiaryService diaryService;
	
	@Inject
	private IGroupService groupService;
	
	@Inject
	private IMemberService memberService;
	
	private static final Logger logger = LoggerFactory.getLogger(DiaryController.class);
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public @ResponseBody Integer checkDiary(Model model, HttpSession session, Integer group_no) throws Exception {
		UserDTO userDTO = (UserDTO) session.getAttribute("login");
		String userid = userDTO.getUserid();
        String date = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		int checkResult;
		if(group_no == 0) {
			System.out.println("글갯수" + diaryService.checkDiaryInPriv(userid, date));
			checkResult = diaryService.checkDiaryInPriv(userid, date);
			return checkResult;
		} else {
			checkResult = diaryService.checkDiaryInGroup(userid, date, group_no);
			return checkResult;
		}
	}
	
	@RequestMapping(value = "/select")
	public String selectDiary(Model model, HttpSession session) throws Exception {
		UserDTO userDTO = (UserDTO) session.getAttribute("login");
		model.addAttribute("groupList", groupService.listGroup(userDTO));
		return "/diary/select";
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String diaryList(Model model, HttpSession session, DiaryCriteria diaryCri, Integer group_no) throws Exception {
				
		UserDTO userDTO = (UserDTO) session.getAttribute("login");
		userDTO.setGroup_no(group_no);
		session.setAttribute("login", userDTO);
		
		List<DiaryVO> diaryList;
		if((userDTO.getGroup_no()) == null) {
			diaryCri.setTotalNum(diaryService.countPagingInPriv(userDTO, diaryCri));
			diaryList = diaryService.listDiaryInPriv(userDTO, diaryCri);
		} else {
			diaryCri.setTotalNum(diaryService.countPagingInGroup(userDTO, diaryCri));
			diaryList = diaryService.listDiaryInGroup(userDTO, diaryCri);
			
			GroupVO groupVO = groupService.infoGroup(userDTO);
			model.addAttribute("groupVO", groupVO);
			List<MemberVO> memberList = memberService.listMember(userDTO);
			model.addAttribute("memberList", memberList);
		}
		
		DiaryPageMaker diaryPageMaker = new DiaryPageMaker();
		diaryPageMaker.setCri(diaryCri);
		diaryPageMaker.setTotalCount(diaryCri.getTotalNum());
		model.addAttribute("diaryPageMaker", diaryPageMaker);
		model.addAttribute("diaryList", diaryList);
		
		return "/diary/list";
	}	
	
	@RequestMapping(value = "/addList")
	public String addListDiary(Model model, HttpSession session, DiaryCriteria diaryCri, Integer group_no, @Param("page") int page) throws Exception {	// UserVO, userVO
				
		UserDTO userDTO = (UserDTO) session.getAttribute("login");
		userDTO.setGroup_no(group_no);
		session.setAttribute("login", userDTO);
		
		if(page != 1) {
			diaryCri.setPerPageNum(18);
		}
		
		List<DiaryVO> diaryList;
		if((userDTO.getGroup_no()) == null) {
			diaryCri.setTotalNum(diaryService.countPagingInPriv(userDTO, diaryCri));
			diaryList = diaryService.listDiaryInPriv(userDTO, diaryCri);
		} else {
			diaryCri.setTotalNum(diaryService.countPagingInGroup(userDTO, diaryCri));
			diaryList = diaryService.listDiaryInGroup(userDTO, diaryCri);
		}	
		
		DiaryPageMaker diaryPageMaker = new DiaryPageMaker();
		diaryPageMaker.setCri(diaryCri);
		diaryPageMaker.setTotalCount(diaryCri.getTotalNum());
		model.addAttribute("diaryPageMaker", diaryPageMaker);		
		model.addAttribute("diaryList", diaryList);

		return "/diary/addDiary";
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.POST)
	public String readDiary(Model model, Integer diary_no) throws Exception {
		DiaryVO diaryVO = diaryService.readDiary(diary_no);
		model.addAttribute("diaryVO", diaryVO);
		
		return "/diary/read";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String writeDiary(@ModelAttribute("diaryVO") DiaryVO diaryVO, Model model) throws Exception {
		
		Document doc = Jsoup.connect("https://www.youtube.com/feed/trending").get();
		Elements youtubeList = doc
				.select(".expanded-shelf-content-list.has-multiple-items .expanded-shelf-content-item-wrapper");

		int randomNumber = (int) (Math.random() * youtubeList.size());

		Element movieItem = youtubeList.get(randomNumber);

		Elements a = movieItem.getElementsByAttribute("data-context-item-id");

		String movieId = a.attr("data-context-item-id");
		System.out.println("동영상 id : " + movieId);

		model.addAttribute("movieId", movieId);
		
		return "/diary/write";
	}
	
	@RequestMapping(value = "/actWrite", method = RequestMethod.POST)
	public String actWriteDiary(Model model, RedirectAttributes rttr, @ModelAttribute("diaryVO") DiaryVO diaryVO) throws Exception {
		int writeResult;
		String dest = "";
				
		if(diaryVO.getGroup_no() == null) {
			if(diaryVO.getPriv().equals("비공개")) {
				diaryVO.setPriv("T");
			} else if(diaryVO.getPriv().equals("공개")) {
				diaryVO.setPriv("F");
			}
		}
		
		if((diaryVO.getGroup_no() == null)) {
			writeResult = diaryService.writeDiaryInPriv(diaryVO);
		} else {
			writeResult = diaryService.writeDiaryInGroup(diaryVO);
			dest = "?group_no="+diaryVO.getGroup_no();
		}
		
		model.addAttribute("writeResult", writeResult);
		model.addAttribute("group_no", diaryVO.getGroup_no());
		
		return "forward:/diary/list";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateDiary(Model model, Integer diary_no) throws Exception {
		DiaryVO diaryVO = diaryService.readDiary(diary_no);
		model.addAttribute("diaryVO", diaryVO);
		return "/diary/update";
	}
	
	@RequestMapping(value = "/actUpdate", method=RequestMethod.POST)
	public String actUpdateDiary(Model model, RedirectAttributes rttr, @ModelAttribute("diaryVO") DiaryVO diaryVO) throws Exception {
		int updateResult;
		String dest = "";
		
		if(diaryVO.getGroup_no() == null) {
			if(diaryVO.getPriv().equals("비공개")) {
				diaryVO.setPriv("T");
			} else if(diaryVO.getPriv().equals("공개")) {
				diaryVO.setPriv("F");
			}
		}
		
		if((diaryVO.getGroup_no() == null)) {
			updateResult = diaryService.updateDiaryInPriv(diaryVO);
		} else {
			updateResult = diaryService.updateDiaryInGroup(diaryVO);
			dest = "?group_no="+diaryVO.getGroup_no();
		}
		
		model.addAttribute("updateResult", updateResult);
		model.addAttribute("diary_no", diaryVO.getDiary_no());
		return "forward:/diary/read";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteDiary(Model model, HttpSession session, RedirectAttributes rttr, String userid, Integer diary_no, Integer group_no) throws Exception {
		int deleteResult = diaryService.deleteDiary(diary_no);
		String dest = "";
		UserDTO userDTO = (UserDTO) session.getAttribute("login");
		if(userDTO.getGroup_no() != null) {
			dest = "?group_no="+userDTO.getGroup_no();
		}
		
		model.addAttribute("deleteResult", deleteResult);
		model.addAttribute("group_no", group_no);

		return "forward:/diary/list";
	}
	
	@RequestMapping(value = "/random")
	public String randomDiary(Model model) throws Exception {
		DiaryVO diaryVO = diaryService.randomDiary();
		model.addAttribute("diaryVO", diaryVO);
		return "/diary/random";
	}
	
	// 검색\
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchDiary(Model model, HttpSession session, SearchDTO searchDTO, SearchMemberIdDTO searchMemberIdDTO, Integer group_no) throws Exception {
		
		UserDTO userDTO = (UserDTO) session.getAttribute("login");
		userDTO.setGroup_no(group_no);
		session.setAttribute("login", userDTO);
		
		if(searchDTO.getYear().equals("년")) {
			searchDTO.setYear("____");
		}
		if(searchDTO.getMonth().equals("월")) {
			searchDTO.setMonth("__");
		}
		if(searchDTO.getDay().equals("일")) {
			searchDTO.setDay("__");
		}
		searchDTO.setSearchRegdate(searchDTO.getYear()+searchDTO.getMonth()+searchDTO.getDay());
		
		List<DiaryVO> diaryList;	
		if((userDTO.getGroup_no()) == null) {			
			diaryList = diaryService.searchDiaryInPriv(searchDTO);
		} else {
			if(searchDTO.getNickname().equals("멤버")) {
				searchDTO.setUserid("");
			} else {
				searchDTO.setUserid(memberService.searchMemberId(searchMemberIdDTO));
			}		
			diaryList = diaryService.searchDiaryInGroup(searchDTO);
			
			GroupVO groupVO = groupService.infoGroup(userDTO);
			model.addAttribute("groupVO", groupVO);
			List<MemberVO> memberList = memberService.listMember(userDTO);
			model.addAttribute("memberList", memberList);
		}
		
		model.addAttribute("diaryList", diaryList);
		
		return "/diary/list";
	}
	
	@ResponseBody
	@RequestMapping("/getAttach/{diary_no}")
	public List<String> getFiles(@PathVariable("diary_no") Integer diary_no) throws Exception {
		return diaryService.getAttach(diary_no);
	}
	
}


