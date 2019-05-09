package kr.co.oneul.dto;

public class SearchMemberIdDTO {

	String nickname = "";
	Integer group_no = null;
	
	public SearchMemberIdDTO() {}
	
	public SearchMemberIdDTO(String nickname, Integer group_no) {
		super();
		this.nickname = nickname;
		this.group_no = group_no;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Integer getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}
	
	@Override
	public String toString() {
		return "SearchMemberIdDTO [nickname=" + nickname + ", group_no=" + group_no + "]";
	}
	
}
