package kr.co.oneul.chart;

import java.util.ArrayList;
import java.util.List;

public class ChartObject {
	
	private List<ChartCols> cols = new ArrayList<ChartCols>();
	private List<ChartRows> rows = new ArrayList<ChartRows>();
	
	public List<ChartCols> getCols() {
		return cols;
	}
	public void setCols(List<ChartCols> cols) {
		this.cols = cols;
	}
	public List<ChartRows> getRows() {
		return rows;
	}
	public void setRows(List<ChartRows> rows) {
		this.rows = rows;
	}
	
	@Override
	public String toString() {
		return "ChartObject [cols=" + cols + ", rows=" + rows + "]";
	}

}
