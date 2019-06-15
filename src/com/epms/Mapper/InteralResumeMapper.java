package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.InteralResume;

//内部人员简历表
@Repository
public interface InteralResumeMapper
{
   int insertInteralResume(InteralResume interalResume);
    
    //通过工号查询提交的简历信息
    public List<InteralResume> selectInteralResumeByJobId(@Param("departmentId")String departmentId,
    		@Param("occupationId")String occupationId,@Param("status")String status,@Param("before") int before,@Param("after") int after,
    		@Param("jobId")int jobId);
	public int countByJobId(@Param("departmentId")String departmentId,@Param("occupationId")String occupationId,
			@Param("status")String status,@Param("jobId")int jobId);
	
	//检查是否重复提交
	int checkIfRepect(InteralResume interalResume);
	
	//部门经理查询内部人员简历
	public List<InteralResume> selectAllInteralResumeToManager(@Param("before") int before,@Param("after") int after,
			                                            @Param("jobId") int jobId,
			                                            @Param("managerDepartmentId") int managerDepartmentId);
	public int countAllInteralResumeToManager(@Param("jobId")int jobId,@Param("managerDepartmentId") int managerDepartmentId);

	//总经理查询全部内部人员简历
	public List<InteralResume> selectAllInteralResume(@Param("before") int before,@Param("after") int after);
	public int countAllInteralResume();
	


}


