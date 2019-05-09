package kr.co.oneul.dto;

import java.util.Date;

public class ChartDTO {
	
	private String userid;
	private String weather;
	private String emotion;
	private Date regdate;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getWeather() {
		return weather;
	}
	public void setWeather(String weather) {
		this.weather = weather;
	}
	public String getEmotion() {
		return emotion;
	}
	public void setEmotion(String emotion) {
		this.emotion = emotion;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "ChartDTO [userid=" + userid + ", weather=" + weather + ", emotion=" + emotion + ", regdate=" + regdate + "]";
	}

}
