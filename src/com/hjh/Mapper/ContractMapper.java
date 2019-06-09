package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Attendance;
import com.hjh.Bean.Contract;

@Repository
public interface ContractMapper 
{
	//根据工号查询劳动合同信息
	List<Contract> selectContractById (@Param("startDate") String startDate,@Param("contractType") String contractType,@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
	int count (@Param("startDate") String startDate,@Param("contractType") String contractType,@Param("jobId")int jobId);
	
	//根据合同开始时间查询劳动合同信息
	List<Contract> selectContractByDate(Contract contract);
	
	
	
}
