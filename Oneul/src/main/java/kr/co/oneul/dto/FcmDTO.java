package kr.co.oneul.dto;

public class FcmDTO {
	
	private String token;
	private String userid;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	@Override
	public String toString() {
		return "FcmDTO [token=" + token + ", userid=" + userid + "]";
	}
	
}
