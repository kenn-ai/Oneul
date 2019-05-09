package kr.co.oneul.dto;

public class SearchDTO {

	private String userid = "";
	private String nickname = "";
	private Integer group_no = null;
	private String searchRegdate = "";
	private String searchWord = "";
	private String year = "";
	private String month = "";
	private String day = "";
	
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
	public Integer getGroup_no() {
		return group_no;
	}
	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}
	public String getSearchRegdate() {
		return searchRegdate;
	}
	public void setSearchRegdate(String searchRegdate) {
		this.searchRegdate = searchRegdate;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	
	@Override
	public String toString() {
		return "SearchDTO [userid=" + userid + ", nickname=" + nickname + ", group_no=" + group_no + ", searchRegdate="
				+ searchRegdate + ", searchWord=" + searchWord + ", year=" + year + ", month=" + month + ", day=" + day + "]";
	}
	
}
