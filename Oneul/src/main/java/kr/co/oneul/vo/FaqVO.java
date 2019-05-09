package kr.co.oneul.vo;

import java.util.Date;

public class FaqVO {

	private Integer faq_no;
	private String userid;
	private String title;
	private String contents;
	private Date regdate;
	
	public Integer getFaq_no() {
		return faq_no;
	}
	public void setFaq_no(Integer faq_no) {
		this.faq_no = faq_no;
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
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "FaqVO [faq_no=" + faq_no + ", userid=" + userid + ", title=" + title + ", contents=" + contents
				+ ", regdate=" + regdate + "]";
	}
	
}
