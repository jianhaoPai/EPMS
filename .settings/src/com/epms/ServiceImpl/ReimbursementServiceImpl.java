package com.epms.ServiceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Reimbursement;
import com.epms.Mapper.ReimbursementMapper;
import com.epms.Service.ReimbursementService;
@Service("reimbursementService")
public class ReimbursementServiceImpl implements ReimbursementService{

	@Autowired
	private ReimbursementMapper reimbursementMapper;
	
	//查询个人报销记录
	public List<Reimbursement> showReimbursementByJobId(int jobId, int before,int after) {
		return reimbursementMapper.showReimbursementByJobId(jobId,before,after);
	}
	
	//查询个人报销记录条数
	public int countByJobId(int jobId) {
		return reimbursementMapper.countByJobId(jobId);
	}

	//通过类型和类型查询报销记录
	public Reimbursement findByJobIdTypeContent(int jobId,String type, String content) {
		return reimbursementMapper.findByJobIdTypeContent(jobId,type,content);
	}

	//添加报销记录
	public int addReimburse(int jobId,String type, String content,String time, double amount) {
		return reimbursementMapper.addReimburse(jobId,type,content,time,amount);
	}

	//查询待审核的报销费用
	public List<Reimbursement> showVerifyReimburse(int before, int after) {
		return reimbursementMapper.showVerifyReimburse(before,after);
	}

	//查询待报销的报销费用条数
	public int countVerifyReimburse() {
		return reimbursementMapper.countVerifyReimburse();
	}

	//审核报销通过
	public int verifyPerformYes(int jobId, String time, int verifyId,String verifyDate) {
		return reimbursementMapper.verifyPerformYes(jobId,time,verifyId,verifyDate);
	}

	//审核报销不通过
	public int verifyPerformNo(int jobId, String time, int verifyId,String verifyDate) {
		return reimbursementMapper.verifyPerformNo(jobId,time,verifyId,verifyDate);
	}

	//查询审核报销记录
	public List<Reimbursement> showVerifyReimburseRecord(int jobId, int before,int after) {
		return reimbursementMapper.showVerifyReimburseRecord(jobId,before,after);
	}

	//计算审核报销记录条数
	public int countVerifyReimburseRecord(int jobId) {
		return reimbursementMapper.countVerifyReimburseRecord(jobId);
	}

}
