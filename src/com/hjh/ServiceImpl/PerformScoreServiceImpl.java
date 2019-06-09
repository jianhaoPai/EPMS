package com.hjh.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjh.Bean.PerformScore;
import com.hjh.Bean.User;
import com.hjh.Mapper.PerformScoreMapper;
import com.hjh.Mapper.PerformSumMapper;
import com.hjh.Mapper.UserMapper;
import com.hjh.Service.PerformScoreService;
@Service("performScoreService")
public class PerformScoreServiceImpl implements PerformScoreService{
	@Autowired
	private PerformScoreMapper performScoreMapper;
	@Autowired
	private PerformSumMapper performSumMapper;
	@Autowired
	private UserMapper userMapper;
	
	/*
	 * 查询未评价的记录
	 */
	public List<PerformScore> showAllEvaluate(int jobId,int before, int after) {
		return performScoreMapper.showAllEvaluate(jobId,before,after);
	}

	/*
	 * 计算总条数
	 */
	public int count(int jobId) {
		return performScoreMapper.count(jobId);
	}

	/*
	 * 添加绩效评价
 	 */
	public int addPerform(int jobId,int evaluateId,int score, String date, int as1, int as2, int as3,
			int as4, int as5, int as6, int as7, int as8, int as9, int as10,
			int as11, int as12, int as13, int as14, int as15, int as16,
			int as17, int as18, int as19, int as20) {

		return performScoreMapper.addPerform(jobId,evaluateId,score,date,as1,as2,as3,
			as4,as5,as6,as7,as8,as9,as10,
			as11,as12,as13,as14,as15,as16,
			as17,as18,as19,as20);
	}

	
	//查询是否最后的评价，是则计算总绩效评价
	public int checkEndPerform(int evaluateId, String assessDate,String type) {
		long s=System.currentTimeMillis();//获取开始时间
		if(performScoreMapper.checkStaff(evaluateId)==0){//查询被评价人是否为普通职员
			long s2=System.currentTimeMillis();//获取开始时间
			//普通职员被评价数
			int performNum=performScoreMapper.checkPerformNum(evaluateId,assessDate);
			//应被评数=同事数+部门经理
			int peopleNum=performScoreMapper.checkPeopleNum(evaluateId)-1;
			System.out.println("performNum:"+performNum+" peopleNum"+peopleNum);
			if(performNum==peopleNum){
				int sumScore=performScoreMapper.countPerformScore(evaluateId,assessDate);
				double score=sumScore/peopleNum;
				//添加总绩效成绩
				performSumMapper.addPerformSum(evaluateId,score,type,assessDate);
				System.out.println("添加总成绩");
			}
			long e2=System.currentTimeMillis();//获取结束时间
			System.out.println("普通职员的运行时间："+(e2-s2)+"ms");//输出程序运行时间

		}else if(performScoreMapper.checkManager(evaluateId)==1){//查询被评价人是否为部门经理
			long s1=System.currentTimeMillis();//获取开始时间
			//部门经理的被评数
			int performNum=performScoreMapper.checkPerformNum(evaluateId,assessDate);
			//应被评数=部门内人数+总经理
			int peopleNum=performScoreMapper.checkPeopleNum(evaluateId);
			if(performNum==peopleNum){
				int sumScore=performScoreMapper.countPerformScore(evaluateId,assessDate);
				double score=sumScore/peopleNum;
				//添加总绩效成绩
				performSumMapper.addPerformSum(evaluateId,score,type,assessDate);
				System.out.println("添加总成绩");
			}
			System.out.println("performNum:"+performNum+" peopleNum:"+peopleNum);
			long e1=System.currentTimeMillis();//获取结束时间
			System.out.println("部门经理运行时间："+(e1-s1)+"ms");//输出程序运行时间
		}else if(performScoreMapper.checkGeneralManager(evaluateId).equals("总经理")){//查询被评价人是否为总经理
			long s3=System.currentTimeMillis();//获取开始时间
			//经理的被评数
			int managerNum=performScoreMapper.checkManagerNum()-1;
			//应被评数=经理人数
			int performNum=performScoreMapper.checkPerformNum(evaluateId,assessDate);
			if(managerNum==performNum){
				int sumScore=performScoreMapper.countPerformScore(evaluateId,assessDate);
				double score=sumScore/managerNum;
				//添加总绩效成绩
				performSumMapper.addPerformSum(evaluateId,score,type,assessDate);
				System.out.println("添加总成绩");
			}
			long e3=System.currentTimeMillis();//获取结束时间
			System.out.println("总经理的运行时间："+(e3-s3)+"ms");//输出程序运行时间
		}
		
		long e=System.currentTimeMillis();//获取结束时间
		System.out.println("impl判断是否最后评价的运行时间："+(e-s)+"ms");//输出程序运行时间
		return 0;
	}
	
	//添加绩效评价初始数据
	@Transactional
	public void addInitPerform(){
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");//设置日期格式
	        String nowTime=df.format(new Date());// new Date()为获取当前系统时间
			//获取总经理工号
			User GeneralManager = userMapper.findGeneralManager();
			int GMjobId = GeneralManager.getJobId();
			//获取部门经理的工号
			List<User> ManagerList = userMapper.findManager();
			
			//初始化总经理绩效评价
			int i = performScoreMapper.initGeneralManagerEvaluate(GMjobId,ManagerList,nowTime);
			System.out.println("初始化总经理绩效评价数据："+i+"条");
			
			//初始化部门经理绩效评价
			int j = performScoreMapper.addMToGMEvaluate(ManagerList,GMjobId,nowTime);
			System.out.println("初始化部门经理对总经理绩效评价数据："+j+"条");
			
			//初始化部门经理对部门员工绩效评价
			for(int x=0;x<ManagerList.size();x++){
				//取出部门经理工号
				String MjobId =String.valueOf(ManagerList.get(x).getJobId());
				//取出该经理内的员工工号
				List<User> StaffList = userMapper.findStaff(MjobId);
				//添加部门经理对员工的绩效评价
				int k = performScoreMapper.addMToStaffEvaluate(MjobId,StaffList,nowTime);
				System.out.println("初始化"+MjobId+"部门经理对员工绩效评价数据："+k+"条");
				//添加员工对部门经理的绩效评价
				int l = performScoreMapper.addStaffToMEvaluate(StaffList,MjobId,nowTime);
				System.out.println("初始化"+MjobId+"部门内员工对部门经理的绩效评价数据："+l+"条");
				for(int y=0;y<StaffList.size();y++){
					//获取员工工号
					String SjobId=String.valueOf(StaffList.get(y).getJobId());
					//获取该员工的同事工号
					List<User> colleagueList = userMapper.findColleague(SjobId);
					//添加员工对同事的绩效评价
					int m = performScoreMapper.addStaffToStaff(SjobId,colleagueList,nowTime);
					System.out.println("初始化"+MjobId+"部门内员工对员工的绩效评价数据："+m+"条");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("数据库插入错误");
			throw new RuntimeSqlException();
		}
		
	}

}
