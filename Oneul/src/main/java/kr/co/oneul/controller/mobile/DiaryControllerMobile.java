package kr.co.oneul.controller.mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.dto.MapSearchDTO;
import kr.co.oneul.dto.SearchDTO;
import kr.co.oneul.dto.SearchMemberIdDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.ICommentService;
import kr.co.oneul.service.IDiaryService;
import kr.co.oneul.service.IGroupService;
import kr.co.oneul.service.IMapService;
import kr.co.oneul.service.IMemberService;
import kr.co.oneul.util.MediaUtils;
import kr.co.oneul.vo.AttachVO;
import kr.co.oneul.vo.DiaryCriteria;
import kr.co.oneul.vo.DiaryListVO;
import kr.co.oneul.vo.DiaryPageMaker;
import kr.co.oneul.vo.DiaryVO;
import kr.co.oneul.vo.GroupVO;
import kr.co.oneul.vo.MapVO;
import kr.co.oneul.vo.MaterialVO;
import kr.co.oneul.vo.MemberVO;
import kr.co.oneul.vo.ReadDiaryVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/m/diary")
public class DiaryControllerMobile {

	@Inject
	private IDiaryService diaryService;

	@Inject
	private IGroupService groupService;

	@Inject
	private IMemberService memberService;

	@Inject
	private ICommentService commentService;

	@Inject
	private IMapService mapService;

	private static final Logger logger = LoggerFactory.getLogger(DiaryControllerMobile.class);

	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public @ResponseBody Integer checkDiary(Model model, HttpSession session, String userid, Integer group_no) throws Exception {
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
	
	@RequestMapping(value = "/list")
	public @ResponseBody DiaryListVO diaryList(DiaryListVO diaryListVO, UserDTO userDTO, String userid,
			Integer group_no) throws Exception { // UserVO, userVO
		List<DiaryVO> diaryList;

		if ((userDTO.getGroup_no()) == 0) {
			diaryList = diaryService.listDiaryInPrivForM(userDTO);
		} else {
			diaryList = diaryService.listDiaryInGroupForM(userDTO);

			List<MemberVO> memberList = memberService.listMember(userDTO);
			diaryListVO.setMemberList(memberList);
			GroupVO groupVO = groupService.infoGroup(userDTO);
			diaryListVO.setGroupVO(groupVO);
		}

		diaryListVO.setDiaryList(diaryList);

		return diaryListVO;
	}

	@RequestMapping(value = "/addList")
	public String addListDiary(Model model, HttpSession session, DiaryCriteria diaryCri, Integer group_no,
			@Param("page") int page) throws Exception { // UserVO, userVO

		UserDTO userDTO = (UserDTO) session.getAttribute("login");
		userDTO.setGroup_no(group_no);
		session.setAttribute("login", userDTO);

		if (page != 1) {
			diaryCri.setPerPageNum(18);
		}

		List<DiaryVO> listDiary;
		if ((userDTO.getGroup_no()) == null) {
			diaryCri.setTotalNum(diaryService.countPagingInPriv(userDTO, diaryCri));
			listDiary = diaryService.listDiaryInPriv(userDTO, diaryCri);
		} else {
			diaryCri.setTotalNum(diaryService.countPagingInGroup(userDTO, diaryCri));
			listDiary = diaryService.listDiaryInGroup(userDTO, diaryCri);
		}

		DiaryPageMaker diaryPageMaker = new DiaryPageMaker();
		diaryPageMaker.setCri(diaryCri);
		diaryPageMaker.setTotalCount(diaryCri.getTotalNum());
		model.addAttribute("diaryPageMaker", diaryPageMaker);
		model.addAttribute("listDiary", listDiary);
		return "/diary/addDiary";
	}

	@RequestMapping(value = "/read")
	public @ResponseBody ReadDiaryVO readDiary(Integer diary_no) throws Exception {
		ReadDiaryVO readDiaryVO = new ReadDiaryVO();
		List<AttachVO> attachList = new ArrayList<>();
		List<String> attaches = diaryService.getAttach(diary_no);
			if(!attaches.isEmpty()) {
				for(int i = 0; i < attaches.size(); i++) {
					if(MediaUtils.checkFileType(attaches.get(i))) {
						AttachVO attachVO = new AttachVO();
						attachVO.setAttach(attaches.get(i));
						attachList.add(attachVO);
					}
				}
			}
		
		readDiaryVO.setDiaryVO(diaryService.readDiary(diary_no));
		readDiaryVO.setAttachList(attachList);
		readDiaryVO.setCommentList(commentService.listComment(diary_no));
		
		return readDiaryVO;
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public @ResponseBody Integer actWriteDiary(HttpServletRequest request, DiaryVO diaryVO) throws Exception {
		
		int writeResult;
		String json = readJSONStringFromRequestBody(request);
		JSONObject jsonObject = JSONObject.fromObject(json);
		List<String> savedNames = new ArrayList<>();
		
		Integer group_no = jsonObject.getInt("group_no");
		String priv = jsonObject.getString("priv");

		diaryVO.setUserid(jsonObject.getString("userid"));
		diaryVO.setNickname(jsonObject.getString("nickname"));
		diaryVO.setPriv(priv);
		diaryVO.setGroup_no(group_no);
		diaryVO.setWeather(jsonObject.getString("weather"));
		diaryVO.setEmotion(jsonObject.getString("emotion"));
		diaryVO.setLocation(jsonObject.getString("location"));
		diaryVO.setTitle(jsonObject.getString("title"));
		diaryVO.setContents(jsonObject.getString("contents"));
		diaryVO.setLink(jsonObject.getString("link"));
		diaryVO.setTag(jsonObject.getString("tag"));
		
		JSONArray savedNamesArray = jsonObject.getJSONArray("savedNamesArray");
		if(savedNamesArray != null) {
			for(int i=0; i<savedNamesArray.size(); i++) {
				JSONObject obj = savedNamesArray.getJSONObject(i);
				savedNames.add(obj.getString("savedName"));
			}
	        String[] files = savedNames.toArray(new String[savedNames.size()]);
	         
	        for(String s : savedNames){
	            System.out.println(s);
	        }
			System.out.println(files.toString());
			diaryVO.setFiles(files);
		}

		System.out.println(diaryVO.toString());

		if (group_no == 0) {
			if (priv.equals("비공개")) {
				diaryVO.setPriv("T");
			} else if (priv.equals("공개")) {
				diaryVO.setPriv("F");
			}
		}

		if ((group_no == 0)) {
			writeResult = diaryService.writeDiaryInPriv(diaryVO);
		} else {
			writeResult = diaryService.writeDiaryInGroup(diaryVO);
		}

		return writeResult;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Integer actUpdateDiary(HttpServletRequest request, DiaryVO diaryVO) throws Exception {
		int updateResult;

		String json = readJSONStringFromRequestBody(request);
		JSONObject jsonObject = JSONObject.fromObject(json);
		List<String> savedNames = new ArrayList<>();
		
		Integer group_no = Integer.valueOf(jsonObject.getString("group_no"));
		Integer diary_no = Integer.valueOf(jsonObject.getString("diary_no"));
		String priv = jsonObject.getString("priv");

		diaryVO.setGroup_no(group_no);
		
		diaryVO.setPriv(priv);
		diaryVO.setDiary_no(diary_no);
		diaryVO.setUserid(jsonObject.getString("userid"));
		diaryVO.setNickname(jsonObject.getString("nickname"));
		diaryVO.setWeather(jsonObject.getString("weather"));
		diaryVO.setEmotion(jsonObject.getString("emotion"));
		diaryVO.setLocation(jsonObject.getString("location"));
		diaryVO.setTitle(jsonObject.getString("title"));
		diaryVO.setContents(jsonObject.getString("contents"));
		diaryVO.setLink(jsonObject.getString("link"));
		diaryVO.setTag(jsonObject.getString("tag"));
		
		JSONArray savedNamesArray = jsonObject.getJSONArray("savedNamesArray");
		if(savedNamesArray != null) {
			for(int i=0; i<savedNamesArray.size(); i++) {
				JSONObject obj = savedNamesArray.getJSONObject(i);
				savedNames.add(obj.getString("savedName"));
			}
	        String[] files = savedNames.toArray(new String[savedNames.size()]);
	         
	        for(String file : files){
	            System.out.println("file"+file.toString());
	        }
			System.out.println(files.toString());
			diaryVO.setFiles(files);
		}

		System.out.println(diaryVO.toString());

		if (diaryVO.getGroup_no() == null) {
			if (diaryVO.getPriv().equals("비공개")) {
				diaryVO.setPriv("T");
			} else if (diaryVO.getPriv().equals("공개")) {
				diaryVO.setPriv("F");
			}
		}

		if ((diaryVO.getGroup_no() == null)) {
			updateResult = diaryService.updateDiaryInPrivForM(diaryVO);
		} else {
			updateResult = diaryService.updateDiaryInGroupForM(diaryVO);
		}

		return updateResult;
	}

	@RequestMapping(value = "/delete")
	public @ResponseBody Integer deleteDiary(Integer diary_no) throws Exception {
		int deleteResult = diaryService.deleteDiary(diary_no);
		return deleteResult;
	}

	@RequestMapping(value = "/random", method = RequestMethod.POST)
	public @ResponseBody DiaryVO randomDiary() throws Exception {
		DiaryVO diaryVO = diaryService.randomDiary();
		return diaryVO;
	}

	// 검색\
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody DiaryListVO searchDiary(DiaryListVO diaryListVO, UserDTO userDTO, String userid,
			SearchDTO searchDTO, SearchMemberIdDTO searchMemberIdDTO, Integer group_no) throws Exception {
		userDTO.setUserid(userid);
		userDTO.setGroup_no(group_no);

		if (searchDTO.getYear().equals("년") || searchDTO.getYear().equals("")) {
			searchDTO.setYear("____");
		}
		if (searchDTO.getMonth().equals("월") || searchDTO.getMonth().equals("")) {
			searchDTO.setMonth("__");
		}
		if (searchDTO.getDay().equals("일") || searchDTO.getDay().equals("")) {
			searchDTO.setDay("__");
		}
		searchDTO.setSearchRegdate(searchDTO.getYear() + searchDTO.getMonth() + searchDTO.getDay());

		List<DiaryVO> diaryList;
		if ((userDTO.getGroup_no()) == 0) {

			System.out.println("searchDTO : " + searchDTO.toString());
			System.out.println("userDTO : " + userDTO.toString());

			diaryList = diaryService.searchDiaryInPriv(searchDTO);
		} else {
			if (searchDTO.getNickname().equals("멤버")) {
				searchDTO.setUserid("");
			} else {
				searchDTO.setUserid(memberService.searchMemberId(searchMemberIdDTO));
			}
			diaryList = diaryService.searchDiaryInGroup(searchDTO);

			GroupVO groupVO = groupService.infoGroup(userDTO);
			diaryListVO.setGroupVO(groupVO);
			List<MemberVO> memberList = memberService.listMember(userDTO);
			diaryListVO.setMemberList(memberList);
		}

		diaryListVO.setDiaryList(diaryList);

		return diaryListVO;
	}

	@RequestMapping(value = "material", method = RequestMethod.POST)
	public @ResponseBody MaterialVO material(MapSearchDTO mapSearchDTO, String userid, String regdate)
			throws Exception {
		Document doc = Jsoup.connect("https://www.youtube.com/feed/trending").get();
		Elements youtubeList = doc
				.select(".expanded-shelf-content-list.has-multiple-items .expanded-shelf-content-item-wrapper");

		int randomNumber = (int) (Math.random() * youtubeList.size());

		Element movieItem = youtubeList.get(randomNumber);

		Elements a = movieItem.getElementsByAttribute("data-context-item-id");

		String movieId = a.attr("data-context-item-id");
		
		System.out.println("동영상 id : " + movieId);
		
		
		
		MaterialVO materialVO = new MaterialVO();
		mapSearchDTO.setUserid(userid);
		mapSearchDTO.setRegdate(regdate);
	
		materialVO.setWriteMaterial(diaryService.material());
		materialVO.getWriteMaterial().setMaterial03(movieId);
		
		List<MapVO> marker = mapService.todayMap(mapSearchDTO);
	      System.out.println(marker.toString());
	      materialVO.setMapMaterial(marker);
	      if(!marker.isEmpty()){
	         
	         HashSet<MapVO> markerSet = new HashSet<MapVO>(marker);
	         ArrayList<MapVO> uniMarker = new ArrayList<MapVO>(markerSet);
	         System.out.println("diaryMap" + mapSearchDTO.toString());
	         System.out.println("diaryMap" + uniMarker.toString());
	         materialVO.setMapMaterial(uniMarker);
	      }
	      
	      return materialVO;
		
	}

	private String readJSONStringFromRequestBody(HttpServletRequest request) {
		StringBuffer json = new StringBuffer();
		String line= null;
	
		try {
			BufferedReader reader = request.getReader();
			while((line = reader.readLine()) != null) {
				json.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json.toString();
	}
	
}
