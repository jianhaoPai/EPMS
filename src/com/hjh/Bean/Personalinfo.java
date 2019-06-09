package com.hjh.Bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

//个人信息表
@Component(value="personalinfo")
public class Personalinfo 
{
	private Department department;
	private Occupation occupation;
	private Education education;
	
	private int jobId;
	
	private String name;
	
	private int age;
	private String sex;
	
	private String birthday ;
	
	@Pattern(regexp ="1[3|8][3|6|7|9]\\d{8}",message="手机号码必须为11位，且以1开头，第二位为3/8,第三位为3/6/9")
	private String phone;
	
	private String email;
	
	private String address;
	
	private String marital;
	
	private String identityCard;
	
	private String census;
	
	private String entryDate;
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getMarital() {
		return marital;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Occupation getOccupation() {
		return occupation;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	public void setMarital(String marital) {
		this.marital = marital;
	}
	public String getIdentityCard() {
		return identityCard;
	}
	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
	public String getCensus() {
		return census;
	}
	public void setCensus(String census) {
		this.census = census;
	}
	public String getEntryDate() {
		return entryDate;
	}
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	public Personalinfo() {
		super();
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Education getEducation() {
		return education;
	}
	public void setEducation(Education education) {
		this.education = education;
	}
	@Override
	public String toString() {
		return "Personalinfo [department=" + department + ", occupation="
				+ occupation + ", education=" + education + ", jobId=" + jobId
				+ ", name=" + name + ", age=" + age + ", sex=" + sex
				+ ", birthday=" + birthday + ", phone=" + phone + ", email="
				+ email + ", address=" + address + ", marital=" + marital
				+ ", identityCard=" + identityCard + ", census=" + census
				+ ", entryDate=" + entryDate + "]";
	}
	
}