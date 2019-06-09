package com.hjh.Bean;


public class Menu {
	private int id;
	private int mid;
	private String menuName;
	private String menuPath;
	private int pareMenuId;
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
}
