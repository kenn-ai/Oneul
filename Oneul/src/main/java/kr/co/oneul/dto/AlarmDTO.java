package kr.co.oneul.dto;

public class AlarmDTO {
	
	private Integer alarm_no;
	private Integer group_no;
	private String userid;
	private String nickname;
	public Integer getAlarm_no() {
		return alarm_no;
	}
	public void setAlarm_no(Integer alarm_no) {
		this.alarm_no = alarm_no;
	}
	public Integer getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
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
	
	@Override
	public String toString() {
		return "AlarmDTO [alarm_no=" + alarm_no + ", group_no=" + group_no + ", userid=" + userid + ", nickname="
				+ nickname + "]";
	}

}
