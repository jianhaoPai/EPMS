package com.hjh.Bean;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;


//¼ÙÆÚÉêÇë¼ÇÂ¼±í
@Component(value="vacate")
public class Vacate
{
	private int id;
	private int jobId;
	private String startDate;
	private String finishDate;
	private String type;
	
	private int daySum;
	private String cancelDate;
	private String state;
	private String approvalDate;
	private String submitDate;
	private String reason;
	private int discountDay;
	
	public int getDiscountDay() {
		return discountDay;
	}
	public void setDiscountDay(int discountDay) {
		this.discountDay = discountDay;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getDaySum() {
		return daySum;
	}
	public void setDaySum(int daySum) {
		this.daySum = daySum;
	}
	public String getCancalDate() {
		return cancelDate;
	}
	public void setCancalDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "Vacate [id=" + id + ", jobId=" + jobId + ", startDate="
				+ startDate + ", finishDate=" + finishDate + ", type=" + type
				+ ", daySum=" + daySum + ", cancelDate=" + cancelDate
				+ ", state=" + state + ", approvalDate=" + approvalDate
				+ ", submitDate=" + submitDate + ", reason=" + reason
				+ ", discountDay=" + discountDay + "]";
	}
	public Vacate() {
		super();
	}
	

}
