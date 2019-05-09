package kr.co.oneul.chart;

public class ChartCell {
	private String v;
	private String f;
	
	
	
	public ChartCell(String v) {
		super();
		this.v = v;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getF() {
		return f;
	}
	public void setF(String f) {
		this.f = f;
	}
	@Override
	public String toString() {
		return "ChartCell [v=" + v + ", f=" + f + "]";
	}
	
}
