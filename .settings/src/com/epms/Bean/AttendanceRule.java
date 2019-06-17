package com.epms.Bean;

import org.springframework.stereotype.Component;

@Component(value="attendanceRule")
public class AttendanceRule
{
	private int id;
	private String setStart;
	private String setFinish;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSetStart() {
		return setStart;
	}
	public void setSetStart(String setStart) {
		this.setStart = setStart;
	}
	public String getSetFinish() {
		return setFinish;
	}
	public void setSetFinish(String setFinish) {
		this.setFinish = setFinish;
	}
	public AttendanceRule() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "AttendanceRule [id=" + id + ", setStart=" + setStart
				+ ", setFinish=" + setFinish + "]";
	}

}
