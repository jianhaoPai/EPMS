package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Communication;
import com.epms.Bean.Personalinfo;

@Repository
public interface CommunicationMapper 
{
	 //查询全部通讯录
	 List<Communication> selectAll(@Param("departmentId") String departmentId,@Param("jobId") String jobId,@Param("name") String name,
			 @Param("before") int before,@Param("after") int after);
	 int count (@Param("departmentId") String departmentId,@Param("jobId") String jobId,@Param("name") String name);
	 
	 //根据工号查询通讯信息
	 Communication selectByJobId(@Param("jobId")int jobId);
	 
	 //修改个人通讯信息
	 int updateCommunication(Communication communication);
}
