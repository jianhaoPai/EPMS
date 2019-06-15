package com.epms.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.ExternalResume;
//外部人员简历表
@Repository
public interface ExternalResumeMapper
{
  //提交简历 
  int insertExternalResume(ExternalResume externalResume);
  
  //根据事项id查询简历
  ExternalResume selectMyResume(int resumeId);
  
  //检查是否重复提交
  int checkIfRepect(ExternalResume externalResume);
  
  //查询全部外部人员简历
  public List<ExternalResume> selectAllExternalResume(@Param("before") int before,@Param("after") int after);
  public int countSelectAllExternalResume();
 
  //部门经理查询外部人员简历
  public List<ExternalResume> selectAllExternalResumeToManager(@Param("before") int before,@Param("after") int after,
		                                                       @Param("managerDepartmentId") int managerDepartmentId);
  public int countSelectAllExternalResumeToManager(@Param("managerDepartmentId") int managerDepartmentId);
  
 /* //通过名字查询出所有的简历
  List<ExternalResume> selectExternalResumeByName(String name);
  public List<ExternalResume> selectExternalResumeAll(@Param("before") int before,@Param("after") int after);
  public int count(String name);*/
}
