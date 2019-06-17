package com.epms.Bean;

import org.springframework.stereotype.Component;

@Component(value = "totalData")
public class TotalData {
	private int jobId;
	private int totalVacate;//请假
	private int totalLately;//迟到
	private int totalEarlyleave;//早退
	private int totalWorkOverTime;//平时加班
	private int totalNotWorkOverTime;//节假日加班
	private int totalAbsence;//缺勤
	private int totalCaltivateDay;//培训天数

	public int getTotalCaltivateDay() {
		return totalCaltivateDay;
	}
	public void setTotalCaltivateDay(int totalCaltivateDay) {
		this.totalCaltivateDay = totalCaltivateDay;
	}



	@Override
	public String toString() {
		return "TotalData [jobId=" + jobId + ", totalVacate=" + totalVacate
				+ ", totalLately=" + totalLately + ", totalEarlyleave="
				+ totalEarlyleave + ", totalWorkOverTime=" + totalWorkOverTime
				+ ", totalNotWorkOverTime=" + totalNotWorkOverTime
				+ ", totalAbsence=" + totalAbsence + ", totalCaltivateDay="
				+ totalCaltivateDay + "]";
	}



	public int getJobId() {
		return jobId;
	}



	public void setJobId(int jobId) {
		this.jobId = jobId;
	}



	public int getTotalVacate() {
		return totalVacate;
	}



	public void setTotalVacate(int totalVacate) {
		this.totalVacate = totalVacate;
	}



	public int getTotalLately() {
		return totalLately;
	}



	public void setTotalLately(int totalLately) {
		this.totalLately = totalLately;
	}



	public int getTotalEarlyleave() {
		return totalEarlyleave;
	}



	public void setTotalEarlyleave(int totalEarlyleave) {
		this.totalEarlyleave = totalEarlyleave;
	}



	public int getTotalWorkOverTime() {
		return totalWorkOverTime;
	}



	public void setTotalWorkOverTime(int totalWorkOverTime) {
		this.totalWorkOverTime = totalWorkOverTime;
	}



	public int getTotalNotWorkOverTime() {
		return totalNotWorkOverTime;
	}



	public void setTotalNotWorkOverTime(int totalNotWorkOverTime) {
		this.totalNotWorkOverTime = totalNotWorkOverTime;
	}



	public int getTotalAbsence() {
		return totalAbsence;
	}



	public void setTotalAbsence(int totalAbsence) {
		this.totalAbsence = totalAbsence;
	}



	public TotalData() {
		super();
		// TODO Auto-generated constructor stub
	}
}
