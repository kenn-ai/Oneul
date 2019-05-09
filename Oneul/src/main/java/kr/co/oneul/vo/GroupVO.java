package kr.co.oneul.vo;

import java.util.Date;

public class GroupVO {

	private int group_no;
	private String name;
	private Date regdate;
	
	public int getGroup_no() {
		return group_no;
	}
	public void setGroup_no(int group_no) {
		this.group_no = group_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "GroupVO [group_no=" + group_no + ", name=" + name + ", regdate=" + regdate + "]";
	}

}
