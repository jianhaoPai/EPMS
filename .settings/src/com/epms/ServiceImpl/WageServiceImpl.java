package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.PerformSum;
import com.epms.Bean.StaffWage;
import com.epms.Bean.TotalData;
import com.epms.Bean.Wage;
import com.epms.Mapper.PerformSumMapper;
import com.epms.Mapper.WageMapper;
import com.epms.Service.WageService;
@Service("wageService")
public class WageServiceImpl implements WageService{

	@Autowired
	private WageMapper wageMapper;
	
	@Autowired
	private PerformSumMapper performsumMapper;
	
	//查看本人工资
	public List<Wage> showWageByJobId(int jobId, int before, int after) {
		return wageMapper.showWageByJobId(jobId,before,after);
	}

	//查看个人工资条数
	public int countByJobId(int jobId) {
		return wageMapper.countByJobId(jobId);
	}

	//计算工资
	public void countStaffWage(List<TotalData> totalData) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
        String wageTime=df.format(new Date());// new Date()为获取当前系统时间
        SimpleDateFormat df3 = new SimpleDateFormat("yyyy");//设置日期格式
        String year=df3.format(new Date());
        SimpleDateFormat df2 = new SimpleDateFormat("M");//设置日期格式
        String nowMonth=df2.format(new Date());
        int lastMonth=Integer.parseInt(nowMonth)-1;
        String asseaaDate="";
        if(lastMonth<10){
        	asseaaDate=year+"0"+String.valueOf(lastMonth);
        }else{
        	asseaaDate=year+String.valueOf(lastMonth);
        }
		for(int i = 0;i<totalData.size();i++){
			int jobId = totalData.get(i).getJobId();//获取工号
			
			Wage wage=wageMapper.selectWage(jobId,wageTime);//查询是否有工资记录jobId,wageTime
			if(wage!=null){
				StaffWage staffWage = wageMapper.selectWageByJobId(jobId);//查询是否有基本工资
				if(staffWage!=null){
					double basicWage=staffWage.getWage();//获取基本工资
					double halHourfWage=(basicWage/21.75)/16;//半小时工资
					double taxAmount=basicWage-5000;
					double wageTax=0;
					if(basicWage<5000){
						wageTax=0;
						taxAmount=0;
					}else if(basicWage>5000&&basicWage<8000){
						wageTax=taxAmount*0.03;
					}else if(basicWage>8001&&basicWage<17000){
						wageTax=taxAmount*0.1;
					}else if(basicWage>17001&&basicWage<30000){
						wageTax=taxAmount*0.2;
					}else if(basicWage>30001&&basicWage<40000){
						wageTax=taxAmount*0.25;
					}else if(basicWage>40001&&basicWage<60000){
						wageTax=taxAmount*0.3;
					}else if(basicWage>60001&&basicWage<85000){
						wageTax=taxAmount*0.35;
					}else if(basicWage>85001){
						wageTax=taxAmount*0.45;
					}
					if(totalData.get(i).getTotalLately()>3||totalData.get(i).getTotalEarlyleave()>3){//迟到早退多于3次
						double LatelyEarly=(totalData.get(i).getTotalLately()+totalData.get(i).getTotalEarlyleave())*50;//迟到早退
						double vacate=(basicWage/21.75)*totalData.get(i).getTotalVacate();//请假
						double workOT = halHourfWage*totalData.get(i).getTotalWorkOverTime();//平时加班费
						double notworkOT=halHourfWage*totalData.get(i).getTotalWorkOverTime()*3;//节假日加班费
						double absence=(basicWage/21.75)*totalData.get(i).getTotalAbsence();//缺勤
						absence=absence-LatelyEarly-vacate;//缺勤
						double overTimePay=workOT+notworkOT;//加班费
						double socialSecurity=basicWage*0.097;//社保
						double housingFund=basicWage*0.08;//住房公积金
						double sum=basicWage+overTimePay-absence-socialSecurity-housingFund-wageTax;//实际工资
						wageMapper.addWage(jobId,wageTime,basicWage,overTimePay,0,0,0,absence,socialSecurity,housingFund,0,0,wageTax,taxAmount,sum);
					}else{
						double LatelyEarly=(totalData.get(i).getTotalLately()+totalData.get(i).getTotalEarlyleave())*50;//迟到早退
						double vacate=(basicWage/21.75)*totalData.get(i).getTotalVacate();//请假
						double workOT = halHourfWage*totalData.get(i).getTotalWorkOverTime();//平时加班费
						double notworkOT=halHourfWage*totalData.get(i).getTotalWorkOverTime()*3;//节假日加班费
						double absence=(basicWage/21.75)*totalData.get(i).getTotalAbsence();//缺勤
						absence=absence-LatelyEarly-vacate;//缺勤
						double overTimePay=workOT+notworkOT;//加班费
						double socialSecurity=basicWage*0.097;//社保
						double housingFund=basicWage*0.08;//住房公积金
						double sum=basicWage+overTimePay-absence-socialSecurity-housingFund-wageTax;//实际工资
						PerformSum performSum=performsumMapper.selectPerform(jobId,"月度评价",asseaaDate);
						double performAllowance=basicWage*0.2*performSum.getScore()/100;
						wageMapper.addWage(jobId,wageTime,basicWage,overTimePay,0,0,performAllowance,absence,socialSecurity,housingFund,0,0,wageTax,taxAmount,sum);
					}
				}else{
					System.out.println(jobId+"没有基本工资");
				}
			}else{
				System.out.println(jobId+"工资记录已存在");
			}
			
		}
	}

	//查看需要工资审核的记录
	public List<Wage> showVerifyWage(int before, int limit) {
		return wageMapper.showVerifyWage(before,limit);
	}

	//查看需要工资审核的记录条数
	public int countVerifyWage() {
		return wageMapper.countVerifyWage();
	}

	
	
}
