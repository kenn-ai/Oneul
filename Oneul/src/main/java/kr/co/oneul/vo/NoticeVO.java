package kr.co.oneul.vo;

import java.util.Date;

public class NoticeVO {

	private Integer notice_no;
	private String userid;
	private String title;
	private String contents;
	private Integer hit;
	private Date regdate;
	
	public Integer getNotice_no() {
		return notice_no;
	}
	public void setNotice_no(Integer notice_no) {
		this.notice_no = notice_no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Integer getHit() {
		return hit;
	}
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "NoticeVO [notice_no=" + notice_no + ", userid=" + userid + ", title=" + title + ", contents=" + contents
				+ ", hit=" + hit + ", regdate=" + regdate + "]";
	}
	
}
