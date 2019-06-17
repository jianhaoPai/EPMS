package com.epms.Bean;


import org.springframework.stereotype.Component;

//内部人员简历表
@Component(value="interalResume")
public class InteralResume {
	private int resumeId;
	private int jobId;
	private Resume resume;
	public int getResumeId() {
		return resumeId;
	}
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public Resume getResume() {
		return resume;
	}
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	@Override
	public String toString() {
		return "InteralResume [resumeId=" + resumeId + ", jobId=" + jobId
				+ ", resume=" + resume + "]";
	}
	public InteralResume() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
