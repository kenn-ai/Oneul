package kr.co.oneul.vo;

import java.util.Date;

public class UserVO {

	private Integer user_no;
	private String userid;
	private String userpw;
	private String nickname;
	private String phone;
	private Date regdate;
	private String authority;
	
	public Integer getUser_no() {
		return user_no;
	}
	public void setUser_no(Integer user_no) {
		this.user_no = user_no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String toString() {
		return "UserVO [user_no=" + user_no + ", userid=" + userid + ", userpw=" + userpw + ", nickname=" + nickname
				+ ", phone=" + phone + ", regdate=" + regdate + ", authority=" + authority + "]";
	}
	
}
