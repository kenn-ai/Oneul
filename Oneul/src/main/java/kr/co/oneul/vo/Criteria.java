package kr.co.oneul.vo;

public class Criteria {
	
	private int page;
	private int perPageNum;
	private int totalNum;
	
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		
		if (page <=0 ) {
			this.page = 1;
			return;
		}
		
		this.page = page;
	}
	public int getPerPageNum() {
		return this.perPageNum;
	}
	public void setPerPageNum(int perPageNum) {
		
		if (perPageNum <=0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}
		this.perPageNum = perPageNum;
	}
	
	
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getFirst() {
		return (totalNum-(this.page-1)*perPageNum);
	}
	public int getLast() {
		return (totalNum-(this.page) * perPageNum);
	}
	public int getPageStart() {
		return (this.page - 1) * perPageNum;
	}
	
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
	
	
}
