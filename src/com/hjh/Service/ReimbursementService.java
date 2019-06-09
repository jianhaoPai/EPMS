package com.hjh.Service;

import java.util.Date;
import java.util.List;

import com.hjh.Bean.Reimbursement;

public interface ReimbursementService {

	//查询个人报销记录
	public List<Reimbursement> showReimbursementByJobId(int jobId, int before, int after);
	//查询个人报销记录条数
	public int countByJobId(int jobId);
	//通过类型和类型查询报销记录
	public Reimbursement findByJobIdTypeContent(int jobId,String type,String content);
	//添加报销记录
	public int addReimburse(int jobId, String type, String content, String date, double amount);
	//查询待审核的报销费用
	public List<Reimbursement> showVerifyReimburse(int before, int after);
	//查询待审核的报销费用条数
	public int countVerifyReimburse();
	//审核报销通过
	public int verifyPerformYes(int jobId, String time, int verifyId,String verifyDate);
	//审核报销不通过
	public int verifyPerformNo(int jobId, String time, int verifyId,String verifyDate);
	//查询审核报销记录
	public List<Reimbursement> showVerifyReimburseRecord(int jobId, int before,int after);
	//计算审核报销记录条数
	public int countVerifyReimburseRecord(int jobId);
	
}
