package kr.co.oneul.dto;

public class UserDTO {

	private String userid;
	private String userpw;
	private Integer group_no;
	private String authority;
	private String nickname;
	
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
	public Integer getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + ", userpw=" + userpw + ", group_no=" + group_no + ", authority="
				+ authority + ", nickname=" + nickname + "]";
	}

}
