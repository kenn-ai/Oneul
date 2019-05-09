package kr.co.oneul.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.oneul.dao.IDiaryDAO;
import kr.co.oneul.dto.SearchDTO;
import kr.co.oneul.dto.UserDTO;
import kr.co.oneul.service.IDiaryService;
import kr.co.oneul.util.MediaUtils;
import kr.co.oneul.vo.DiaryCriteria;
import kr.co.oneul.vo.DiaryVO;
import kr.co.oneul.vo.Material;
import oracle.net.aso.d;

@Service
public class DiaryServiceImpl implements IDiaryService {

	@Inject
	IDiaryDAO diaryDAO;

	@Override
	public int checkDiaryInPriv(String userid, String date) throws Exception {
		return diaryDAO.checkDiaryInPriv(userid, date);
	}
	
	@Override
	public int checkDiaryInGroup(String userid, String date, Integer group_no) throws Exception {
		return diaryDAO.checkDiaryInGroup(userid, date, group_no);
	}
	
	@Override
	public DiaryVO readDiary(Integer diary_no) throws Exception {
		return diaryDAO.readDiary(diary_no);
	}

	@Transactional
	@Override
	public int deleteDiary(Integer diary_no) throws Exception {
		diaryDAO.deleteAttach(diary_no);
		int deleteResult = diaryDAO.deleteDiary(diary_no);
		return deleteResult;
	}

	// InPriv
	@Transactional
	@Override
	public List<DiaryVO> listDiaryInPriv(UserDTO userDTO, DiaryCriteria diaryCri) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userDTO", userDTO);
		map.put("diaryCri", diaryCri);

		List<DiaryVO> diaryList = diaryDAO.listDiaryInPriv(map);

		if(diaryList != null) {
			for(DiaryVO diaryVO : diaryList) {
				List<String> backgroundImgList = diaryDAO.getAttach(diaryVO.getDiary_no());
				if(!backgroundImgList.isEmpty()) {
					for(int i = 0; i < backgroundImgList.size(); i++) {
						if(MediaUtils.checkFileType(backgroundImgList.get(i))) {
							diaryVO.setBackgroundImg(backgroundImgList.get(i));
							break;
						}
					}
				}
			}
		}
		return diaryList;
	}

	@Transactional
	@Override
	public int writeDiaryInPriv(DiaryVO diaryVO) throws Exception {

		int writeResult = diaryDAO.writeDiaryInPriv(diaryVO);

		String[] files = diaryVO.getFiles();

		if(files != null) {
			for(String fileName : files) {
				diaryDAO.addAttach(fileName, diaryVO.getUserid());
			}
		}

		return writeResult;
	}

	@Transactional
	@Override
	public int updateDiaryInPriv(DiaryVO diaryVO) throws Exception {

		int updateResult = diaryDAO.updateDiaryInPriv(diaryVO);

		Integer diary_no = diaryVO.getDiary_no();

		diaryDAO.deleteAttach(diary_no);

		String[] files = diaryVO.getFiles();

		if(files != null) {
			for(String fileName : files) {
				diaryDAO.replaceAttach(fileName, diary_no, diaryVO.getUserid());
			}
		}

		return updateResult;
	}

	@Override
	public DiaryVO randomDiary() throws Exception {
		return diaryDAO.randomDiary();
	}

	@Transactional
	@Override
	public List<DiaryVO> searchDiaryInPriv(SearchDTO searchDTO) throws Exception {
		List<DiaryVO> diaryList = diaryDAO.searchDiaryInPriv(searchDTO);

		if(diaryList != null) {
			for(DiaryVO diaryVO : diaryList) {
				List<String> backgroundImgList = diaryDAO.getAttach(diaryVO.getDiary_no());
				if(!backgroundImgList.isEmpty()) {
					for(int i = 0; i < backgroundImgList.size(); i++) {
						if(MediaUtils.checkFileType(backgroundImgList.get(i))) {
							diaryVO.setBackgroundImg(backgroundImgList.get(i));
							break;
						}
					}
				}
			}
		}

		return diaryList;
	}

	// InGroup

	@Transactional
	@Override
	public List<DiaryVO> listDiaryInGroup(UserDTO userDTO, DiaryCriteria diaryCri) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userDTO", userDTO);
		map.put("diaryCri", diaryCri);

		List<DiaryVO> diaryList = diaryDAO.listDiaryInGroup(map);

		if(diaryList != null) {
			for(DiaryVO diaryVO : diaryList) {
				List<String> backgroundImgList = diaryDAO.getAttach(diaryVO.getDiary_no());
				if(!backgroundImgList.isEmpty()) {
					for(int i = 0; i < backgroundImgList.size(); i++) {
						if(MediaUtils.checkFileType(backgroundImgList.get(i))) {
							diaryVO.setBackgroundImg(backgroundImgList.get(i));
							break;
						}
					}
				}
			}
		}

		return diaryList;
	}

	@Transactional
	@Override
	public int writeDiaryInGroup(DiaryVO diaryVO) throws Exception {

		int writeResult = diaryDAO.writeDiaryInGroup(diaryVO);

		String[] files = diaryVO.getFiles();

		if(files != null) {
			for(String fileName : files) {
				diaryDAO.addAttach(fileName, diaryVO.getUserid());
			}
		}

		return writeResult;
	}

	@Transactional
	@Override
	public int updateDiaryInGroup(DiaryVO diaryVO) throws Exception {

		int updateResult = diaryDAO.updateDiaryInGroup(diaryVO);

		Integer diary_no = diaryVO.getDiary_no();

		diaryDAO.deleteAttach(diary_no);

		String[] files = diaryVO.getFiles();

		if(files != null) {
			for(String fileName : files) {
				diaryDAO.replaceAttach(fileName, diary_no, diaryVO.getUserid());
			}
		}

		return updateResult;
	}

	@Transactional
	@Override
	public List<DiaryVO> searchDiaryInGroup(SearchDTO searchDTO) throws Exception {
		List<DiaryVO> diaryList = diaryDAO.searchDiaryInGroup(searchDTO);

		if(diaryList != null) {
			for(DiaryVO diaryVO : diaryList) {
				List<String> backgroundImgList = diaryDAO.getAttach(diaryVO.getDiary_no());
				if(!backgroundImgList.isEmpty()) {
					for(int i = 0; i < backgroundImgList.size(); i++) {
						if(MediaUtils.checkFileType(backgroundImgList.get(i))) {
							diaryVO.setBackgroundImg(backgroundImgList.get(i));
							break;
						}
					}
				}
			}
		}

		return diaryList;
	}

	// paging
	public int countPagingInPriv(UserDTO userDTO, DiaryCriteria diaryCri) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userDTO", userDTO);
		map.put("diaryCri", diaryCri);
		return diaryDAO.countPagingInPriv(map);
	}

	public int countPagingInGroup(UserDTO userDTO, DiaryCriteria diaryCri) throws Exception {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userDTO", userDTO);
		map.put("diaryCri", diaryCri);
		return diaryDAO.countPagingInGroup(map);
	}

	// Mobile	
	@Transactional
	@Override
	public List<DiaryVO> listDiaryInPrivForM(UserDTO userDTO) throws Exception {
		
		List<DiaryVO> diaryList = diaryDAO.listDiaryInPrivForM(userDTO);

		if(diaryList != null) {
			for(DiaryVO diaryVO : diaryList) {
				List<String> backgroundImgList = diaryDAO.getAttach(diaryVO.getDiary_no());
				if(!backgroundImgList.isEmpty()) {
					for(int i = 0; i < backgroundImgList.size(); i++) {
						if(MediaUtils.checkFileType(backgroundImgList.get(i))) {
							diaryVO.setBackgroundImg(backgroundImgList.get(i));
							break;
						}
					}
				}
			}
		}

		return diaryList;
	}
	
	@Transactional
	@Override
	public List<DiaryVO> listDiaryInGroupForM(UserDTO userDTO) throws Exception {

		List<DiaryVO> diaryList = diaryDAO.listDiaryInGroupForM(userDTO);

		if(diaryList != null) {
			for(DiaryVO diaryVO : diaryList) {
				List<String> backgroundImgList = diaryDAO.getAttach(diaryVO.getDiary_no());
				if(!backgroundImgList.isEmpty()) {
					for(int i = 0; i < backgroundImgList.size(); i++) {
						if(MediaUtils.checkFileType(backgroundImgList.get(i))) {
							diaryVO.setBackgroundImg(backgroundImgList.get(i));
							break;
						}
					}
				}
			}
		}

		return diaryList;
	}
	
	@Transactional
	@Override
	public List<DiaryVO> searchDiaryInPrivForM(SearchDTO searchDTO) throws Exception {
		List<DiaryVO> diaryList = diaryDAO.searchDiaryInPrivForM(searchDTO);

		if(diaryList != null) {
			for(DiaryVO diaryVO : diaryList) {
				List<String> backgroundImgList = diaryDAO.getAttach(diaryVO.getDiary_no());
				if(!backgroundImgList.isEmpty()) {
					for(int i = 0; i < backgroundImgList.size(); i++) {
						if(MediaUtils.checkFileType(backgroundImgList.get(i))) {
							diaryVO.setBackgroundImg(backgroundImgList.get(i));
							break;
						}
					}
				}
			}
		}

		return diaryList;
	}

	@Transactional
	@Override
	public List<DiaryVO> searchDiaryInGroupForM(SearchDTO searchDTO) throws Exception {
		List<DiaryVO> diaryList = diaryDAO.searchDiaryInGroupForM(searchDTO);

		if(diaryList != null) {
			for(DiaryVO diaryVO : diaryList) {
				List<String> backgroundImgList = diaryDAO.getAttach(diaryVO.getDiary_no());
				if(!backgroundImgList.isEmpty()) {
					for(int i = 0; i < backgroundImgList.size(); i++) {
						if(MediaUtils.checkFileType(backgroundImgList.get(i))) {
							diaryVO.setBackgroundImg(backgroundImgList.get(i));
							break;
						}
					}
				}
			}
		}

		return diaryList;
	}

	@Override
	public List<String> getAttach(Integer diary_no) throws Exception {
		return diaryDAO.getAttach(diary_no);
	}

	@Override
	public Material material() throws Exception {
		return diaryDAO.material();
	}

	@Transactional
	@Override
	public int updateDiaryInPrivForM(DiaryVO diaryVO) throws Exception {

		int updateResult = diaryDAO.updateDiaryInPriv(diaryVO);

		Integer diary_no = diaryVO.getDiary_no();

		String[] files = diaryVO.getFiles();

		if(files != null) {
			for(String fileName : files) {
				diaryDAO.addAttachForM(diary_no, fileName, diaryVO.getUserid());
			}
		}

		return updateResult;
	}

	@Transactional
	@Override
	public int updateDiaryInGroupForM(DiaryVO diaryVO) throws Exception {

		int updateResult = diaryDAO.updateDiaryInGroup(diaryVO);

		Integer diary_no = diaryVO.getDiary_no();

		String[] files = diaryVO.getFiles();

		if(files != null) {
			for(String fileName : files) {
				diaryDAO.addAttachForM(diary_no, fileName, diaryVO.getUserid());
			}
		}

		return updateResult;
	}

}
