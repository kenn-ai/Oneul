package kr.co.oneul.chart;

import java.util.ArrayList;
import java.util.List;

public class ChartRows {
	List<ChartCell> c = new ArrayList<ChartCell>();

	public List<ChartCell> getC() {
		return c;
	}

	public void setC(List<ChartCell> c) {
		this.c = c;
	}

	@Override
	public String toString() {
		return "ChartRows [c=" + c + "]";
	}
	
	
}
