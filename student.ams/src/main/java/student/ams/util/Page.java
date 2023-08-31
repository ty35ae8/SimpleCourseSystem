package student.ams.util;

import java.util.List;

//分頁工具類
public class Page<T> {
	private int total;  //總條數
	private int page;  //當前頁
	private int size; //每頁數
	
	private List<T> rows; //結果集

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
	
}
