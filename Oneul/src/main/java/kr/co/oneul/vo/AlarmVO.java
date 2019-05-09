package kr.co.oneul.vo;

import java.util.Date;

public class AlarmVO {
	
	private Integer alarm_no;
	private String reader;
	private String nickname_r;
	private String writer;
	private String nickname_w;
	private String contents;
	private Date regdate;
	private String type;
	private Integer group_no;
	public Integer getAlarm_no() {
		return alarm_no;
	}
	public void setAlarm_no(Integer alarm_no) {
		this.alarm_no = alarm_no;
	}
	public String getReader() {
		return reader;
	}
	public void setReader(String reader) {
		this.reader = reader;
	}
	public String getNickname_r() {
		return nickname_r;
	}
	public void setNickname_r(String nickname_r) {
		this.nickname_r = nickname_r;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getNickname_w() {
		return nickname_w;
	}
	public void setNickname_w(String nickname_w) {
		this.nickname_w = nickname_w;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}
	@Override
	public String toString() {
		return "AlarmVO [alarm_no=" + alarm_no + ", reader=" + reader + ", nickname_r=" + nickname_r + ", writer="
				+ writer + ", nickname_w=" + nickname_w + ", contents=" + contents + ", regdate=" + regdate + ", type="
				+ type + ", group_no=" + group_no + "]";
	}
	
}
