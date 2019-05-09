package kr.co.oneul.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import kr.co.oneul.dao.IDiaryDAO;
import kr.co.oneul.dto.SearchDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.vo.DiaryVO;
import kr.co.oneul.vo.Material;

@Repository
public class DiaryDAOImpl implements IDiaryDAO {

	@Inject
	private SqlSession sqlSession;
	private static final String namespace = "kr.co.oneul.diaryMapper";

	@Override
	public int checkDiaryInPriv(String userid, String date) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("date", date);
		return sqlSession.selectOne(namespace+".checkDiaryInPriv", paramMap);
	}
	
	@Override
	public int checkDiaryInGroup(String userid, String date, Integer group_no) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userid", userid);
		paramMap.put("date", date);
		paramMap.put("group_no", group_no);
		return sqlSession.selectOne(namespace+".checkDiaryInGroup", paramMap);
	}
	
	// 공통
	@Override
	public DiaryVO readDiary(Integer diary_no) throws Exception {
		return sqlSession.selectOne(namespace + ".readDiary", diary_no);
	}
	
	@Override
	public int deleteDiary(Integer diary_no) throws Exception {
		return sqlSession.delete(namespace + ".deleteDiary", diary_no);
	}
	
	// InPriv

	@Override
	public List<DiaryVO> listDiaryInPriv(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".listDiaryInPriv", map);
	}

	@Override
	public int writeDiaryInPriv(DiaryVO diaryVO) throws Exception {
		return sqlSession.insert(namespace + ".writeDiaryInPriv", diaryVO);
	}

	@Override
	public int updateDiaryInPriv(DiaryVO diaryVO) throws Exception {
		return sqlSession.update(namespace + ".updateDiaryInPriv", diaryVO);
	}

	@Override
	public DiaryVO randomDiary() throws Exception {
		return sqlSession.selectOne(namespace + ".randomDiary");
	}

	@Override
	public List<DiaryVO> searchDiaryInPriv(SearchDTO searchDTO) throws Exception {
		return sqlSession.selectList(namespace + ".searchDiaryInPriv", searchDTO);
	}

	// InGroup

	@Override
	public List<DiaryVO> listDiaryInGroup(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectList(namespace + ".listDiaryInGroup", map);
	}

	@Override
	public int writeDiaryInGroup(DiaryVO diaryVO) throws Exception {
		return sqlSession.insert(namespace + ".writeDiaryInGroup", diaryVO);
	}

	@Override
	public int updateDiaryInGroup(DiaryVO diaryVO) throws Exception {
		return sqlSession.update(namespace + ".updateDiaryInGroup", diaryVO);
	}

	@Override
	public List<DiaryVO> searchDiaryInGroup(SearchDTO searchDTO) throws Exception {
		return sqlSession.selectList(namespace + ".searchDiaryInGroup", searchDTO);
	}

	// paging
	
	@Override
	public int countPagingInPriv(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace+".countPagingInPriv", map);
	}
	
	@Override
	public int countPagingInGroup(HashMap<String, Object> map) throws Exception {
		return sqlSession.selectOne(namespace+".countPagingInGroup", map);
	}

	// Mobile
	
	@Override
	public List<DiaryVO> listDiaryInPrivForM(UserDTO userDTO) throws Exception {
		return sqlSession.selectList(namespace+".listDiaryInPrivForM", userDTO);
	}

	@Override
	public List<DiaryVO> listDiaryInGroupForM(UserDTO userDTO) throws Exception {
		return sqlSession.selectList(namespace+".listDiaryInGroupForM", userDTO);
	}

	@Override
	public List<DiaryVO> searchDiaryInPrivForM(SearchDTO searchDTO) throws Exception {
		return sqlSession.selectList(namespace+".searchDiaryInPrivForM", searchDTO);
	}

	@Override
	public List<DiaryVO> searchDiaryInGroupForM(SearchDTO searchDTO) throws Exception {
		return sqlSession.selectList(namespace+".searchDiaryInGroupForM", searchDTO);
	}
	
	@Override
	public void addAttach(String fullName, String userid) throws Exception { 
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fullName", fullName);
		paramMap.put("userid", userid);
		sqlSession.insert(namespace+".addAttach", paramMap);    
	}
	
	@Override
	public List<String> getAttach(Integer diary_no) throws Exception {
		return sqlSession.selectList(namespace +".getAttach", diary_no);
	}
	
	@Override
	public void deleteAttach(Integer diary_no) throws Exception {
		sqlSession.delete(namespace+".deleteAttach", diary_no);
	}

	@Override
	public void replaceAttach(String fullName, Integer diary_no, String userid) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("diary_no", diary_no);
		paramMap.put("fullName", fullName);
		paramMap.put("userid", userid);
		sqlSession.insert(namespace+".replaceAttach", paramMap);
	}
	
	@Override
	public void addAttachForM(Integer diary_no, String fullName, String userid) throws Exception {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("diary_no", diary_no);
		paramMap.put("fullName", fullName);
		paramMap.put("userid", userid);
		sqlSession.insert(namespace+".replaceAttach", paramMap);
	}
	
	@Override
	public Material material() throws Exception {
		return sqlSession.selectOne(namespace + ".material");
	}

}
