package com.epms.Service;

import java.util.List;

import com.epms.Bean.Personalinfo;

public interface PersonalinfoService
{
	//根据工号查询个人信息(不查学历，排除还没填学历，然后就返回空)
	public Personalinfo selectPersonalByIdNotEducation(int jobId);
	
	//根据工号查询个人信息
	public Personalinfo selectPersonalById(int jobId);
	
	//根据姓名查询个人信息(不查学历，排除还没填学历，然后就返回空)
	public Personalinfo selectPersonalByNameNotEducation(String name);
	
	//根据姓名查询个人信息
    public Personalinfo selectPersonalByName(String name);
    
    //根据id或姓名查询个人信息
    public List<Personalinfo> selectPersonByIdOrName (Personalinfo personal);
    
    //根据部门id查询个人信息
    public List<Personalinfo> selectPersonalByDepartmentId (Personalinfo personal);
    
    //修改个人信息
    String updatePersonal (Personalinfo personal);
    
    //查询所有人员信息
    public List<Personalinfo> selectAllPersonal (String occupation_id,String job_id,String name,
    		String department_id,int before,int after,int jobId);
    public int count (String occupation_id,String job_id,String name,String department_id,int jobId);
}
