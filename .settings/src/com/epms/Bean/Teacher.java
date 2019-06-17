package com.epms.Bean;

import org.springframework.stereotype.Component;

@Component(value="teacher")
public class Teacher {
	
	private int id;
	private String teacherName;
	private String education;
	private String experience;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	@Override
	public String toString() {
		return "Teacher [id=" + id + ", teacherName=" + teacherName
				+ ", education=" + education + ", experience=" + experience
				+ "]";
	}
	public Teacher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
