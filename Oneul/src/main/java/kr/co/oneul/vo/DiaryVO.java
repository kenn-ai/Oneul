package kr.co.oneul.vo;

import java.util.Arrays;
import java.util.Date;

public class DiaryVO {

	private Integer diary_no;
	private Integer group_no;
	private String priv;
	private String userid;
	private String nickname;
	private String weather;
	private String emotion;
	private String location;
	private String title;
	private String contents;
	private String link;
	private String tag;
	private Date regdate;
	private String backgroundImg;
	
	private String[] files;

	public Integer getDiary_no() {
		return diary_no;
	}

	public void setDiary_no(Integer diary_no) {
		this.diary_no = diary_no;
	}

	public Integer getGroup_no() {
		return group_no;
	}

	public void setGroup_no(Integer group_no) {
		this.group_no = group_no;
	}

	public String getPriv() {
		return priv;
	}

	public void setPriv(String priv) {
		this.priv = priv;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getBackgroundImg() {
		return backgroundImg;
	}

	public void setBackgroundImg(String backgroundImg) {
		this.backgroundImg = backgroundImg;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "DiaryVO [diary_no=" + diary_no + ", group_no=" + group_no + ", priv=" + priv + ", userid=" + userid
				+ ", nickname=" + nickname + ", weather=" + weather + ", emotion=" + emotion + ", location=" + location
				+ ", title=" + title + ", contents=" + contents + ", link=" + link + ", tag=" + tag + ", regdate="
				+ regdate + ", backgroundImg=" + backgroundImg + ", files=" + Arrays.toString(files) + "]";
	}
	
}
