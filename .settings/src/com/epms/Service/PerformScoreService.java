package com.epms.Service;

import java.util.List;

import com.epms.Bean.PerformScore;

public interface PerformScoreService {

	//查询未评价的记录
	public List<PerformScore> showAllEvaluate(int jobId,int before,int after);
	//计算总条数
	public int count(int jobId);
	//添加绩效评价
	public int addPerform(int jobId,int evaluateId,int score, String date, int as1, int as2, int as3, int as4, int as5, int as6, int as7, int as8, int as9, int as10, int as11, int as12, int as13, int as14, int as15, int as16, int as17, int as18, int as19, int as20);
	//查询是否最后的评价，是则计算总绩效评价
	public int checkEndPerform(int evaluateId,String assessDate,String type);
	//添加绩效评价初始数据
	public void addInitPerform();
	
}
