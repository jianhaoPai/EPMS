package com.hjh.Bean;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component(value="workingCalendar")
public class WorkingCalendar 
{
	private int id;
	private Date date;
	private int week;
	private int isWorkDay;
	private String note;
	@Override
	public String toString() {
		return "WorkingCalendar [id=" + id + ", date=" + date + ", week="
				+ week + ", isWorkDay=" + isWorkDay + ", note=" + note + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getIsWorkDay() {
		return isWorkDay;
	}
	public void setIsWorkDay(int isWorkDay) {
		this.isWorkDay = isWorkDay;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public WorkingCalendar() {
		super();
		// TODO Auto-generated constructor stub
	}
}
