package com.hjh.Bean;
import org.springframework.stereotype.Component;

@Component(value="attendance")
public class Attendance 
{
	private int jobId;
	private String startDate;
	private String finishDate;
	private int overTime;
	private int earlyLeave;
	private int lately;
	private String today;
	private int startTime;
	
	public int getStartTime() {
		return startTime;
	}
	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}
	public String getToday() {
		return today;
	}
	public void setToday(String today) {
		this.today = today;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public int getOverTime() {
		return overTime;
	}
	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}
	
	public int getEarlyLeave() {
		return earlyLeave;
	}
	public void setEarlyLeave(int earlyLeave) {
		this.earlyLeave = earlyLeave;
	}
	public int getLately() {
		return lately;
	}
	public void setLately(int lately) {
		this.lately = lately;
	}
	
	@Override
	public String toString() {
		return "Attendance [jobId=" + jobId + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", overTime=" + overTime
				+ ", earlyLeave=" + earlyLeave + ", lately=" + lately
				+ ", today=" + today + ", startTime=" + startTime + "]";
	}
	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

}
