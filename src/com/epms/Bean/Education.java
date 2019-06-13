package com.epms.Bean;
import org.springframework.stereotype.Component;


@Component(value="education")
public class Education 
{
	private int jobId;
	private String schoolName;
	private String outDate;
	private String inDate;
	private String education;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getOutDate() {
		return outDate;
	}
	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	@Override
	public String toString() {
		return "Education [jobId=" + jobId + ", schoolName=" + schoolName
				+ ", outDate=" + outDate + ", inDate=" + inDate
				+ ", education=" + education + "]";
	}
	public Education() {
		super();
		// TODO Auto-generated constructor stub
	}

}
