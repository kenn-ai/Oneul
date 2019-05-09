package kr.co.oneul.vo;

import java.util.Date;

public class CommentVO {

	private Integer comment_no;
	private Integer diary_no;
	private String userid;
	private String nickname;
	private String contents;
	private Date regdate;
	
	public Integer getComment_no() {
		return comment_no;
	}
	public void setComment_no(Integer comment_no) {
		this.comment_no = comment_no;
	}
	public Integer getDiary_no() {
		return diary_no;
	}
	public void setDiary_no(Integer diary_no) {
		this.diary_no = diary_no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "CommentVO [comment_no=" + comment_no + ", diary_no=" + diary_no + ", userid=" + userid + ", nickname="
				+ nickname + ", contents=" + contents + ", regdate=" + regdate + "]";
	}
	
}
