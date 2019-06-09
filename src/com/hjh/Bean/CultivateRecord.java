package com.hjh.Bean;

import org.springframework.stereotype.Component;

@Component(value="cultivateRecord")
public class CultivateRecord {
	
	private int participatorId;
	private String harvest;
	private double grade;
	private String courseEvaluation;
	private String teacherEvaluation;
	private CultivateApply cultivateApply;
	public int getParticipatorId() {
		return participatorId;
	}
	public void setParticipatorId(int participatorId) {
		this.participatorId = participatorId;
	}
	public String getHarvest() {
		return harvest;
	}
	public void setHarvest(String harvest) {
		this.harvest = harvest;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public String getCourseEvaluation() {
		return courseEvaluation;
	}
	public void setCourseEvaluation(String courseEvaluation) {
		this.courseEvaluation = courseEvaluation;
	}
	public String getTeacherEvaluation() {
		return teacherEvaluation;
	}
	public void setTeacherEvaluation(String teacherEvaluation) {
		this.teacherEvaluation = teacherEvaluation;
	}
	public CultivateApply getCultivateApply() {
		return cultivateApply;
	}
	public void setCultivateApply(CultivateApply cultivateApply) {
		this.cultivateApply = cultivateApply;
	}
	@Override
	public String toString() {
		return "CultivateRecord [participatorId=" + participatorId
				+ ", harvest=" + harvest + ", grade=" + grade
				+ ", courseEvaluation=" + courseEvaluation
				+ ", teacherEvaluation=" + teacherEvaluation
				+ ", cultivateApply=" + cultivateApply + "]";
	}
	public CultivateRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}