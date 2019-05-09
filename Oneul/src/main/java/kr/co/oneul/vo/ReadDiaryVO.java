package kr.co.oneul.vo;

import java.util.List;

public class ReadDiaryVO {

	DiaryVO diaryVO;
	List<AttachVO> attachList;
	List<CommentVO> commentList;
	
	public DiaryVO getDiaryVO() {
		return diaryVO;
	}
	public void setDiaryVO(DiaryVO diaryVO) {
		this.diaryVO = diaryVO;
	}	
	public List<AttachVO> getAttachList() {
		return attachList;
	}
	public void setAttachList(List<AttachVO> attachList) {
		this.attachList = attachList;
	}
	public List<CommentVO> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentVO> commentList) {
		this.commentList = commentList;
	}
	
	@Override
	public String toString() {
		return "ReadDiaryVO [diaryVO=" + diaryVO + ", attachList=" + attachList + ", commentList=" + commentList + "]";
	}

}