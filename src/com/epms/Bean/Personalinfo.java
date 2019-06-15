package com.epms.Bean;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
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
	
	
	@Pattern(regexp ="0\\d{5}",message="工号必须为6位，且以0开头")
	private Integer jobId;
	
	@NotBlank(message="姓名不能为空！")
	private String name;
	
	@NotNull(message="年龄不能为空！")
	@Range(min=1,max=120,message="年龄必须在1到120之间！")
	private int age;
	
	@NotBlank(message="性别不能为空！")
	private String sex;
	
	@Past(message="请输入一个过去的时间！")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String birthday ;
	
	@Pattern(regexp ="1[3|8][3|6|7|9]\\d{8}",message="手机号码必须为11位，且以1开头，第二位为3/8,第三位为3/6/9")
	private String phone;
	
	@Pattern(regexp ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",message="请输入一个合法的邮箱")
	private String email;
	
	@NotBlank(message="地址不能为空！")
	private String address;
	
	@NotBlank(message="婚姻情况不能为空！")
	private String marital;
	
	@Length(min=18,max=18,message="身份证需为18位！")
	private String identityCard;
	
	@NotBlank(message="籍贯不能为空！")
	private String census;
	
	@Past(message="请输入一个过去的时间！")
	@Pattern(regexp ="(((0[1-9]|[12][0-9]|3[01])/((0[13578]|1[02]))|((0[1-9]|[12][0-9]|30)/(0[469]|11))| (0[1-9]|[1][0-9]|2[0-8])/(02))/([0-9]{3}[1- 9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9] {2}|[1-9][0-9]{3}))|(29/02/(([0-9]{2})(0 [48]|[2468][048]|[13579][26])|((0[48]| [2468][048]|[3579][26])00)))",message="格式错误请重新输入!")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String entryDate;
	
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
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