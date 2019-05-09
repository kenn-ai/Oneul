package kr.co.oneul.controller.mobile;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.oneul.chart.ChartCell;
import kr.co.oneul.chart.ChartCols;
import kr.co.oneul.chart.ChartObject;
import kr.co.oneul.chart.ChartRows;
import kr.co.oneul.controller.ChartController;
import kr.co.oneul.dto.ChartDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IChartService;
import kr.co.oneul.service.IUserService;

@Controller
@RequestMapping("/m/chart")
public class ChartControllerMobile {
	
	
	
	@Inject
	private IChartService chartService;
	@Inject
	private IUserService userService;
	private static final Logger logger = LoggerFactory.getLogger(ChartController.class);
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String example(Model model) {

		return "";
	}
	
	@RequestMapping(value = "/bar")
	public String chartBar(Model model, @RequestParam(value ="userid") String userid) throws Exception{
		model.addAttribute("userid", userid);
		return "/chart/mEmotion";
	}
	
	@RequestMapping(value = "/line")
	public String chartLine(Model model, @RequestParam(value ="userid") String userid) throws Exception{
		model.addAttribute("userid", userid);
		return "/chart/mDiary";
	}
	
	@RequestMapping(value = "/emotion")
	@ResponseBody
	public ChartObject chartEmotion(Model model, @RequestParam(value="userid") String userid) throws Exception {
		int emotion_H=0;
		int emotion_N=0;
		int emotion_A=0;
		int emotion_R=0;
		UserDTO userDTO = new UserDTO();
		List<ChartDTO> list = new ArrayList<ChartDTO>();
		userDTO.setUserid(userid);
		list=chartService.emotionChart(userDTO);
		
		//##########
		for (int i = 0; i < list.size(); i++) {
			switch (list.get(i).getEmotion()) {
			//희
			case "good":
				emotion_H++;
				break;
			case "happy":
				emotion_H++;
				break;
			case "deep_in_love":
				emotion_H++;
				break;
			//노
			case "annoy":
				emotion_N++;
				break;
			//애
			case "crying":
				emotion_A++;
				break;
			case "tears":
				emotion_A++;
				break;
			//락
			case "death":
				emotion_R++;
				break;
			}
		}
		
		//##########
		
		ChartObject json = new ChartObject();
		//Cols 1번 라벨
		ChartCols c1 = new ChartCols("label", "Emotion", null, "string");
		//Cols 2번 라벨		
		ChartCols c2 = new ChartCols("label", "Times", null, "number");
		//Cell Data를 만듬
		ChartCell cell1 = new ChartCell("기쁨");
		ChartCell ca = new ChartCell(""+emotion_H);
		ChartCell cell2 = new ChartCell("화남");
		ChartCell cb = new ChartCell(""+emotion_N);
		ChartCell cell3 = new ChartCell("슬픔");
		ChartCell cc = new ChartCell(""+emotion_A);
		ChartCell cell4 = new ChartCell("즐거움");
		ChartCell cd = new ChartCell(""+emotion_R);
		
		//Rows
		ChartRows r1 = new ChartRows();
		ChartRows r2 = new ChartRows();
		ChartRows r3 = new ChartRows();
		ChartRows r4 = new ChartRows();
		
		r1.getC().add(cell1);
		r1.getC().add(ca);
		r2.getC().add(cell2);
		r2.getC().add(cb);
		r3.getC().add(cell3);
		r3.getC().add(cc);
		r4.getC().add(cell4);
		r4.getC().add(cd);
		
		//Cols, Rows를 전송할 Object에 삽입.
		json.getCols().add(c1);
		json.getCols().add(c2);
		json.getRows().add(r1);
		json.getRows().add(r2);
		json.getRows().add(r3);
		json.getRows().add(r4);
		
		return json;
	}
	
	@RequestMapping(value = "/diary")
	@ResponseBody
	public ChartObject chartDiary(Model model, @RequestParam(value="userid") String userid) throws Exception {
		Calendar cal = Calendar.getInstance();
		int [] month = new int[12];
		UserDTO userDTO = new UserDTO();
		List<ChartDTO> list = new ArrayList<ChartDTO>();
		userDTO.setUserid(userid);
		list=chartService.diaryChart(userDTO);
		
		//##########
		for(int i=0; i<list.size(); i++) {
				cal.setTime(list.get(i).getRegdate());
			switch (cal.get(cal.MONTH)) {
				//여기부터 1월
				case 0:
					month[0]++;
					break;
				case 1:
					month[1]++;
					break;
				case 2:
					month[2]++;
					break;
				case 3:
					month[3]++;
					break;
				case 4:
					month[4]++;
					break;
				case 5:
					month[5]++;
					break;
				case 6:
					month[6]++;
					break;
				case 7:
					month[7]++;
					break;
				case 8:
					month[8]++;
					break;
				case 9:
					month[9]++;
					break;
				case 10:
					month[10]++;
					break;
				case 11:
					month[11]++;
					break;
			}
			
		}
		
		//##########
		
		ChartObject json = new ChartObject();
		//Cols 1번 라벨
		ChartCols c1 = new ChartCols("label", "Month", null, "string");
		//Cols 2번 라벨		
		ChartCols c2 = new ChartCols("label", "Times", null, "number");
		//Cell Data를 만듬
		ChartCell cell1 = new ChartCell("1월");
		ChartCell ca = new ChartCell(""+month[0]);
		ChartCell cell2 = new ChartCell("2월");
		ChartCell cb = new ChartCell(""+month[1]);
		ChartCell cell3 = new ChartCell("3월");
		ChartCell cc = new ChartCell(""+month[2]);
		ChartCell cell4 = new ChartCell("4월");
		ChartCell cd = new ChartCell(""+month[3]);
		ChartCell cell5 = new ChartCell("5월");
		ChartCell ce = new ChartCell(""+month[4]);
		ChartCell cell6 = new ChartCell("6월");
		ChartCell cf = new ChartCell(""+month[5]);
		ChartCell cell7 = new ChartCell("7월");
		ChartCell cg = new ChartCell(""+month[6]);
		ChartCell cell8 = new ChartCell("8월");
		ChartCell ch = new ChartCell(""+month[7]);
		ChartCell cell9 = new ChartCell("9월");
		ChartCell ci = new ChartCell(""+month[8]);
		ChartCell cell10 = new ChartCell("10월");
		ChartCell cj = new ChartCell(""+month[9]);
		ChartCell cell11 = new ChartCell("11월");
		ChartCell ck = new ChartCell(""+month[10]);
		ChartCell cell12 = new ChartCell("12월");
		ChartCell cl = new ChartCell(""+month[11]);
		
		//Rows
		ChartRows r1 = new ChartRows();
		ChartRows r2 = new ChartRows();
		ChartRows r3 = new ChartRows();
		ChartRows r4 = new ChartRows();
		ChartRows r5 = new ChartRows();
		ChartRows r6 = new ChartRows();
		ChartRows r7 = new ChartRows();
		ChartRows r8 = new ChartRows();
		ChartRows r9 = new ChartRows();
		ChartRows r10 = new ChartRows();
		ChartRows r11 = new ChartRows();
		ChartRows r12 = new ChartRows();
		
		r1.getC().add(cell1);
		r1.getC().add(ca);
		r2.getC().add(cell2);
		r2.getC().add(cb);
		r3.getC().add(cell3);
		r3.getC().add(cc);
		r4.getC().add(cell4);
		r4.getC().add(cd);
		r5.getC().add(cell5);
		r5.getC().add(ce);
		r6.getC().add(cell6);
		r6.getC().add(cf);
		r7.getC().add(cell7);
		r7.getC().add(cg);
		r8.getC().add(cell8);
		r8.getC().add(ch);
		r9.getC().add(cell9);
		r9.getC().add(ci);
		r10.getC().add(cell10);
		r10.getC().add(cj);
		r11.getC().add(cell11);
		r11.getC().add(ck);
		r12.getC().add(cell12);
		r12.getC().add(cl);
		
		//Cols, Rows를 전송할 Object에 삽입.
		json.getCols().add(c1);
		json.getCols().add(c2);
		json.getRows().add(r1);
		json.getRows().add(r2);
		json.getRows().add(r3);
		json.getRows().add(r4);
		json.getRows().add(r5);
		json.getRows().add(r6);
		json.getRows().add(r7);
		json.getRows().add(r8);
		json.getRows().add(r9);
		json.getRows().add(r10);
		json.getRows().add(r11);
		json.getRows().add(r12);
		
		return json;
	}
	
}
