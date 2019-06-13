package com.epms.Bean;

public class Wage {

	private int id;
	
	private int jobId;
	
	private String wageTime;
	
	private double basicWage;
	
	private double overtimePay;
	
	private double liveAllowance;
	
	private double holidayAllowance;
	
	private double performAllowance;
	
	private double absence;
	
	private double socialSecurity;
	
	private double borrow;
	
	private double other;
	
	private double wageTax;
	
	private double taxAmount;
	
	private double sum;
	
	private String verifyId;
	
	private String verifyDate;

	public String getVerifyId() {
		return verifyId;
	}

	public void setVerifyId(String verifyId) {
		this.verifyId = verifyId;
	}

	public String getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
	}

	private String wageState;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public String getWageTime() {
		return wageTime;
	}

	public void setWageTime(String wageTime) {
		this.wageTime = wageTime;
	}

	public double getBasicWage() {
		return basicWage;
	}

	public void setBasicWage(double basicWage) {
		this.basicWage = basicWage;
	}

	public double getOvertimePay() {
		return overtimePay;
	}

	public void setOvertimePay(double overtimePay) {
		this.overtimePay = overtimePay;
	}

	public double getHolidayAllowance() {
		return holidayAllowance;
	}

	public void setHolidayAllowance(double holidayAllowance) {
		this.holidayAllowance = holidayAllowance;
	}



	public double getLiveAllowance() {
		return liveAllowance;
	}

	public void setLiveAllowance(double liveAllowance) {
		this.liveAllowance = liveAllowance;
	}

	public double getPerformAllowance() {
		return performAllowance;
	}

	public void setPerformAllowance(double performAllowance) {
		this.performAllowance = performAllowance;
	}

	public double getAbsence() {
		return absence;
	}

	public void setAbsence(double absence) {
		this.absence = absence;
	}

	public double getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(double socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public double getBorrow() {
		return borrow;
	}

	public void setBorrow(double borrow) {
		this.borrow = borrow;
	}

	public double getOther() {
		return other;
	}

	public void setOther(double other) {
		this.other = other;
	}

	public double getWageTax() {
		return wageTax;
	}

	public void setWageTax(double wageTax) {
		this.wageTax = wageTax;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public String getWageState() {
		return wageState;
	}

	public void setWageState(String wageState) {
		this.wageState = wageState;
	}

}
