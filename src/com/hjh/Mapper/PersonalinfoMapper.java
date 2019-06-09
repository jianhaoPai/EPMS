package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Personalinfo;

@Repository
public interface PersonalinfoMapper 
{
	//根据工号查询个人信息(不查学历，排除还没填学历，然后就返回空)
	Personalinfo selectPersonalByIdNotEducation(int jobId);
	
	//根据工号查询个人信息
	Personalinfo selectPersonalById(int jobId);
	
	//根据姓名查询个人信息(不查学历，排除还没填学历，然后就返回空)
	Personalinfo selectPersonalByNameNotEducation(String name);
	        
	//根据姓名查询个人信息
    Personalinfo selectPersonalByName(String name);
    
    //根据姓名或id查询个人信息
    List<Personalinfo> selectPersonByIdOrName(Personalinfo personalinfo);
       
    //根据部门id查询个人信息
    List<Personalinfo> selectPersonalByDepartmentId (Personalinfo personal);
    
    //修改个人信息
    int updatePersonal (Personalinfo personal);
    
    //查询所有人员信息
    List<Personalinfo> selectAllPersonal (@Param("occupation_id")String occupation_id,
    		   @Param("job_id")String job_id,@Param("name")String name,
    		   @Param("department_id")String department_id,@Param("before") int before,
    		   @Param("after") int after);
    int count (@Param("occupation_id")String occupation_id,@Param("job_id")String job_id,
    		   @Param("name")String name,@Param("department_id")String department_id);
    
    
    //部门经理查询所有人员信息
    List<Personalinfo> selectAllPersonalToManager (@Param("occupation_id")String occupation_id,
    		   @Param("job_id")String job_id,@Param("name")String name,
    		   @Param("department_id")String department_id,@Param("before") int before,
    		   @Param("after") int after,@Param("managerDepartmentId") int managerDepartmentId);
    int countToManager (@Param("occupation_id")String occupation_id,@Param("job_id")String job_id,
    		   @Param("name")String name,@Param("department_id")String department_id,
    		   @Param("managerDepartmentId") int managerDepartmentId);
    
    //修改个人信息的部门和职位
    int updatePersonalByOccupationAdjust(@Param("to_occupationid")int to_occupationid,
    		                             @Param("to_departmentid")int to_departmentid,
    		                             @Param("jobId")int jobId);
    
    //实习生转正
    int updatePersonalToEmployee(@Param("jobId")int jobId);
    
}
