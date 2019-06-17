package com.epms.Service;

import java.util.List;

import com.epms.Bean.Attendance;
import com.epms.Bean.Contract;
import com.epms.Bean.Partyinfo;
import com.epms.Bean.Personalinfo;

public interface ContractService
{
	//根据工号查询劳动合同信息
	public List<Contract> selectContractById(String startDate,String contractType,int before,int after,int jobId);	
    public int count (String startDate,String contractType,int jobId);
	
	//根据合同开始时间查询劳动合同信息
	public List<Contract> selectContractByDate(Contract contract);
	
}
