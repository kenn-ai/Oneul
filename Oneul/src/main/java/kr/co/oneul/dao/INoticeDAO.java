package kr.co.oneul.dao;

import java.util.List;

import kr.co.oneul.vo.Criteria;
import kr.co.oneul.vo.NoticeVO;

public interface INoticeDAO {

	public List<NoticeVO> listNotice() throws Exception;
	public NoticeVO readNotice(Integer notice_no) throws Exception;
	public int updateNotice(NoticeVO noticeVO) throws Exception;
	public int deleteNotice(Integer notice_no) throws Exception;
	public int writeNotice(NoticeVO noticeVO)throws Exception;
	public int hitUpdateNotice(Integer notice_no)throws Exception;
	public List<NoticeVO> listCriteria(Criteria cri) throws Exception;
	public int countPaging(Criteria cri) throws Exception;
}
