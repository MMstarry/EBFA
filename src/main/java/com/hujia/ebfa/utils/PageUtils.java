package com.hujia.ebfa.Utils;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 分页类
 * @Author
 */
public class PageUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	private int total;
	private List<?> rows;

	private Object object;

	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
}
