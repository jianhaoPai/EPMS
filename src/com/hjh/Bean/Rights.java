package com.hjh.Bean;

public class Rights {

	private int id;
	
	private int mid;
	
	private String menuName;
	
	private String menuPath;
	
	private int pareMenuId;
	
	private String mark;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuPath() {
		return menuPath;
	}

	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}

	public int getPareMenuId() {
		return pareMenuId;
	}

	public void setPareMenuId(int pareMenuId) {
		this.pareMenuId = pareMenuId;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}
}
