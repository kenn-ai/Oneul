package kr.co.oneul.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.oneul.dao.INoticeDAO;
import kr.co.oneul.service.INoticeService;
import kr.co.oneul.vo.Criteria;
import kr.co.oneul.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements INoticeService {

	@Inject
	INoticeDAO noticeDAO;
	
	@Override
	public List<NoticeVO> listNotice() throws Exception {
		
		return noticeDAO.listNotice();
	}

	@Override
	public NoticeVO readNotice(Integer notice_no) throws Exception {
		
		return noticeDAO.readNotice(notice_no);
	}

	@Override
	public int deleteNotice(Integer notice_no) throws Exception {
		
		return noticeDAO.deleteNotice(notice_no);
	}

	@Override
	public int updateNotice(NoticeVO noticeVO) throws Exception {

		return noticeDAO.updateNotice(noticeVO);
	}

	@Override
	public int writeNotice(NoticeVO noticeVO) throws Exception {
		
		return noticeDAO.writeNotice(noticeVO);
	}

	@Override
	public int hitUpdateNotice(Integer notice_no) throws Exception {
	
		return noticeDAO.hitUpdateNotice(notice_no);
	}

	@Override
	public List<NoticeVO> listCriteria(Criteria cri) throws Exception {
		return noticeDAO.listCriteria(cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return noticeDAO.countPaging(cri);
	}

}
