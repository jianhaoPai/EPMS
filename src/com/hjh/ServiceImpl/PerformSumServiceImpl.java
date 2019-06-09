package com.hjh.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.PerformSum;
import com.hjh.Mapper.PerformScoreMapper;
import com.hjh.Mapper.PerformSumMapper;
import com.hjh.Service.PerformSumService;
@Service("performSumService")
public class PerformSumServiceImpl implements PerformSumService{

	@Autowired
	private PerformSumMapper performSumMapper;
	@Autowired
	private PerformScoreMapper performScoreMapper;


	//查询本人的绩效评价的记录
	public List<PerformSum> showAllPerform(int jobId, int before, int after) {
		return performSumMapper.showAllPerform(jobId,before,after);
	}

	//计算总条数
	public int count(int jobId) {
		return performSumMapper.count(jobId);
	}
	
	//显示经理中需要审核的绩效评价记录
	public List<PerformSum> showVerifyPerform(int jobId,int before,int after){
		return performSumMapper.showVerifyPerform(jobId,before,after);
	}
	
	//获取经理的部门中需要审核的绩效评价记录条数
	public int countStaffVerifyPerform(int jobId){
		return performSumMapper.countStaffVerifyPerform(jobId);
	}
	
	//审核绩效评价记录
	public int updateVerifyPerform(int jobId,String assessDate,int approverId){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String nowTime=df.format(new Date());// new Date()为获取当前系统时间
		return performSumMapper.updateVerifyPerform(jobId,nowTime, assessDate, approverId);
	}
	
	//获取总经理需要审核的绩效评价记录
	public List<PerformSum> showGeneralManagerVerifyPerform(int jobId,int before,int after){
		return performSumMapper.showGeneralManagerVerifyPerform(jobId,before,after);
	}
	
    //获取总经理中需要审核的绩效评价记录条数
	public int countManagerVerifyPerform(int jobId){
		return performSumMapper.countManagerVerifyPerform(jobId);
	}
	
	//获取董事需要审核的绩效评价记录
	public List<PerformSum> showDirectorVerifyPerform(int jobId,int before,int after){
		return performSumMapper.showDirectorVerifyPerform(jobId,before,after);
	}
	
	//获取董事需要审核的绩效评价记录条数
	public int countDirectorVerifyPerform(int jobId){
		return performSumMapper.countDirectorVerifyPerform(jobId);
	}
	
	//通过工号获取身份（总经理，部门经理，董事）
	public String findIdentityByJobId(int jobId){
		return performSumMapper.findIdentityByJobId(jobId);
	}
	
	//绩效评价不通过
	public int updateVerifyPerformNo(int jobId, String assessDate){
		//删除总绩效评价的记录
		int d=performSumMapper.deletePerformSum(jobId, assessDate);
		//将各员工对该员工的绩效的state改为待评价
		int u=performScoreMapper.updatePerformScoreState(jobId, assessDate);
		//应被评数
		int peopleNum=0;
		if(performScoreMapper.checkStaff(jobId)==0){//查询被评价人是否为普通职员
			//应被评数=同事数+部门经理
			peopleNum=performScoreMapper.checkPeopleNum(jobId)-1;
			
		}else if(performScoreMapper.checkManager(jobId)==1){//查询被评价人是否为部门经理
			//应被评数=部门内人数+总经理
			peopleNum=performScoreMapper.checkPeopleNum(jobId);
			
		}else if(performScoreMapper.checkGeneralManager(jobId).equals("总经理")){//查询被评价人是否为总经理
			//应被评数=部门经理
			peopleNum=performScoreMapper.checkManagerNum()-1;

		}
		System.out.println("d:"+d+" u:"+u+" peopleNum"+peopleNum+" sum:"+(d+u-peopleNum));
		System.out.println("执行no");
		return d+u-peopleNum;
	}
	
	//获取董事查询员工绩效评价记录
	public List<PerformSum> showDirectorStaffPerform(int jobId,int before,int after){
		return performSumMapper.showDirectorStaffPerform(jobId,before,after);
	}
	//获取董事查询员工绩效评价记录条数
	public int countDirectorStaffPerform(int jobId){
		return performSumMapper.countDirectorStaffPerform(jobId);
	}
	//获取总经理查询员工绩效评价记录
	public List<PerformSum> showGeneralManagerStaffPerform(int jobId,int before,int after){
		return performSumMapper.showGeneralManagerStaffPerform(jobId,before,after);
	}
	//获取总经理查询员工绩效评价记录条数
	public int countGeneralManagerStaffPerform(int jobId){
		return performSumMapper.countGeneralManagerStaffPerform(jobId);
	}
	//获取部门经理查询员工绩效评价记录
	public List<PerformSum> showManagerStaffPerform(int jobId,int before,int after){
		return performSumMapper.showManagerStaffPerform(jobId,before,after);
	}
	//获取部门经理查询员工绩效评价记录条数
	public int countManagerStaffPerform(int jobId){
		return performSumMapper.countManagerStaffPerform(jobId);
	}
}
