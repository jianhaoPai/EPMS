package com.hjh.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.SalaryAdjust;

@Repository
public interface SalaryAdjustMapper 
{
	//提交薪资调动表
	public int insertSalaryAdjust(SalaryAdjust salaryAdjust);
	
	//根据填表人id查询薪资调动申请信息
	List<SalaryAdjust> selectAllSalaryAdjustByWriteId(@Param("department_id") String department_id,@Param("state") String state,
			@Param("salaryadjust_type") String salaryadjust_type,@Param("before") int before,@Param("after") int after,@Param("jobId") int jobId);
	int countByWriteId(@Param("department_id") String department_id,@Param("state") String state,
			@Param("salaryadjust_type") String salaryadjust_type,@Param("jobId") int jobId);
	
	//通过事项id查询相关信息
	SalaryAdjust selectSalaryAdjustByApplyId(int applyId);
	
	//检查是否有重复
	int selectIfRepeat(SalaryAdjust salaryAdjust);
	
	//上级查询下属提交的薪资调动申请
	List<SalaryAdjust> selectSalaryAdjustToLeader(@Param("department_id") String department_id,
			                                      @Param("state") String state,
			                                      @Param("salaryadjust_type") String salaryadjust_type,
			                                      @Param("before") int before,@Param("after") int after,
			                                      @Param("writeOccupationId") int writeOccupationId);
	int countToLeader(@Param("department_id") String department_id,@Param("state") String state,
			          @Param("salaryadjust_type") String salaryadjust_type,
			          @Param("writeOccupationId") int writeOccupationId);
	
	//根据工号查询相关的薪资调动记录
	List<SalaryAdjust> selectAllSalaryAdjustByJobId(@Param("state") String state,@Param("before") int before,
			           @Param("after") int after,@Param("jobId") int jobId);
	int countByJobId(@Param("state") String state,@Param("jobId") int jobId);
	
}
