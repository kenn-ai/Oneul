package kr.co.oneul.vo;

public class DiaryCriteria {
	
	private int page;
	private int perPageNum;
	private int totalNum;
	
	public DiaryCriteria() {
		this.page = 1;
		this.perPageNum = 23;
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
		return this.perPageNum = 18;
	}
	public void setPerPageNum(int perPageNum) {
		
		if (perPageNum <=0 || perPageNum > 100) {
			this.perPageNum = 18;
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
