package kr.co.oneul.dto;

public class MapSearchDTO {
	
	private String userid = "";
	private String regdate = "";
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "MapSearchDTO [userid=" + userid + ", regdate=" + regdate + "]";
	}

}
