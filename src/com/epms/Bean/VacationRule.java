package com.epms.Bean;

import org.springframework.stereotype.Component;

@Component("vacationRule")
public class VacationRule 
{
	private int id;
	private String vacationType;
	private int day;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getVacationType() {
		return vacationType;
	}
	public void setVacationType(String vacationType) {
		this.vacationType = vacationType;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	@Override
	public String toString() {
		return "VacationRule [id=" + id + ", vacationType=" + vacationType
				+ ", day=" + day + "]";
	}
	
}
