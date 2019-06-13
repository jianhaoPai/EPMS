package com.epms.Service;

import java.util.List;

import com.epms.Bean.Communication;

public interface CommunicationService 
{
	//查询全部通讯录
    public List<Communication> selectAllCommunication (String departmentId,String jobId,String name,int before,int after);
    public int count (String departmentId,String jobId,String name);
  	
    //修改个人通讯信息
    String updateCommunication(Communication communication);
    
    //根据工号查询通讯信息
  	Communication selectByJobId(int jobId);
}
