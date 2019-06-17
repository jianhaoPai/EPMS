package com.epms.Mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Reimbursement;
@Repository
public interface ReimbursementMapper {

	//查询个人报销记录
	public List<Reimbursement> showReimbursementByJobId(@Param("jobId")int jobId,@Param("before") int before,@Param("after")int after);

	//查询个人报销记录条数
	public int countByJobId(int jobId);

	//通过工号、类型、内容查询报销记录
	public Reimbursement findByJobIdTypeContent(@Param("jobId")int jobId,@Param("type") String type,@Param("content")String content);

	//添加报销
	public int addReimburse(@Param("jobId")int jobId,@Param("type") String type,@Param("content") String content,@Param("time")String time,@Param("amount") double amount);

	//查询待审核的报销费用
	public List<Reimbursement> showVerifyReimburse(@Param("before")int before,@Param("after") int after);

	//查询待审核的报销费用条数
	public int countVerifyReimburse();

	//审核报销通过
	public int verifyPerformYes(@Param("jobId")int jobId,@Param("time") String time,@Param("verifyId") int verifyId,@Param("verifyDate")String verifyDate);

	//审核报销不通过
	public int verifyPerformNo(@Param("jobId")int jobId,@Param("time") String time,@Param("verifyId") int verifyId,@Param("verifyDate")String verifyDate);

	//查询审核报销记录
	public List<Reimbursement> showVerifyReimburseRecord(@Param("jobId")int jobId,@Param("before") int before,@Param("after")int after);

	//计算审核报销记录条数
	public int countVerifyReimburseRecord(int jobId);

	

}
