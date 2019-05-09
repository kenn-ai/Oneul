package kr.co.oneul.vo;

import java.util.List;

public class DiaryListVO {

	List<DiaryVO> diaryList;
	List<MemberVO> memberList;
	GroupVO groupVO;
	
	public List<DiaryVO> getDiaryList() {
		return diaryList;
	}
	public void setDiaryList(List<DiaryVO> diaryList) {
		this.diaryList = diaryList;
	}
	public List<MemberVO> getMemberList() {
		return memberList;
	}
	public void setMemberList(List<MemberVO> memberList) {
		this.memberList = memberList;
	}
	public GroupVO getGroupVO() {
		return groupVO;
	}
	public void setGroupVO(GroupVO groupVO) {
		this.groupVO = groupVO;
	}
	
	@Override
	public String toString() {
		return "DiaryListVO [diaryList=" + diaryList + ", memberList=" + memberList + ", groupVO=" + groupVO + "]";
	}
	
}
