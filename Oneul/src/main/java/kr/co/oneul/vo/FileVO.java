package kr.co.oneul.vo;

import java.util.Date;

public class FileVO {

	private Integer diary_no;
	private Integer file_no;
	private String userid;
	private String filepath;
	private String filename;
	private double filesize;
	private Date regdate;
	
	public Integer getDiary_no() {
		return diary_no;
	}
	public void setDiary_no(Integer diary_no) {
		this.diary_no = diary_no;
	}
	public Integer getFile_no() {
		return file_no;
	}
	public void setFile_no(Integer file_no) {
		this.file_no = file_no;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public double getFilesize() {
		return filesize;
	}
	public void setFilesize(double filesize) {
		this.filesize = filesize;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "FileVO [diary_no=" + diary_no + ", file_no=" + file_no + ", userid=" + userid + ", filepath=" + filepath
				+ ", filename=" + filename + ", filesize=" + filesize + ", regdate=" + regdate + "]";
	}
	
}
