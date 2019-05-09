package kr.co.oneul.dao;

import java.util.HashMap;
import java.util.List;

import kr.co.oneul.dto.SearchDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.vo.DiaryVO;
import kr.co.oneul.vo.Material;

public interface IDiaryDAO {
	
	public int checkDiaryInPriv(String userid, String date) throws Exception;
	public int checkDiaryInGroup(String userid, String date, Integer group_no) throws Exception;
	
	// 공통
	public DiaryVO readDiary(Integer diary_no) throws Exception;
	public int deleteDiary(Integer diary_no) throws Exception;
	
	// <개인> 다이어리
	public List<DiaryVO> listDiaryInPriv(HashMap<String, Object> map) throws Exception;
	public int writeDiaryInPriv(DiaryVO diaryVO) throws Exception;
	public int updateDiaryInPriv(DiaryVO diaryVO) throws Exception;
	
	public DiaryVO randomDiary() throws Exception;
	
	// <개인> 검색기능
	public List<DiaryVO> searchDiaryInPriv(SearchDTO searchDTO) throws Exception;
	
	// <그룹> 다이어리
	public List<DiaryVO> listDiaryInGroup(HashMap<String, Object> map) throws Exception;
	public int writeDiaryInGroup(DiaryVO diaryVO) throws Exception;
	public int updateDiaryInGroup(DiaryVO diaryVO) throws Exception;

	// <그룹> 검색기능
	public List<DiaryVO> searchDiaryInGroup(SearchDTO searchDTO) throws Exception;

	public int countPagingInPriv(HashMap<String, Object> map) throws Exception;
	public int countPagingInGroup(HashMap<String, Object> map) throws Exception;
	
	// Mobile
	public List<DiaryVO> listDiaryInPrivForM(UserDTO userDTO)throws Exception;
	public List<DiaryVO> listDiaryInGroupForM(UserDTO userDTO)throws Exception;
	
	public List<DiaryVO> searchDiaryInPrivForM(SearchDTO searchDTO) throws Exception;
	public List<DiaryVO> searchDiaryInGroupForM(SearchDTO searchDTO) throws Exception;

	// upload
	public void addAttach(String fullName, String userid) throws Exception;
	public void addAttachForM(Integer diary_no, String fullName, String userid) throws Exception;
	public List<String> getAttach(Integer diary_no) throws Exception;
	public void deleteAttach(Integer diary_no) throws Exception;
	public void replaceAttach(String fullName, Integer diary_no, String userid) throws Exception;
	
	//글감
	public Material material() throws Exception;

}