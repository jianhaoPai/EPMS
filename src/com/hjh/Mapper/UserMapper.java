package com.hjh.Mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.User;

@Repository
public interface UserMapper {

   /*
    * 分页操作
    */
	public List<User> findAllUser(@Param("jobId")String jobId,@Param("name")String name,@Param("deName")String departmentName,@Param("before") int before,@Param("after") int after);
	public int count(@Param("jobId")String jobId,@Param("name")String name,@Param("deName")String departmentName);
	
	//查询登录的用户是否存在
	public User findByJobId(int jobId);
	//查询用户是否存在
	public User findAccountByJobId(int jobId);
	//获取用户的姓名
	public String findNameByJobId(int jobId);
	//修改账号密码就职情况
	public int editAccount(@Param("jobId")int jobId,@Param("password")String password,@Param("state")String state);
	
	//创建新账号
	public int addUser(@Param("jobId")int jobId,@Param("password")String password); 
	//添加新个人信息
	public int addPersonalInfo(@Param("jobId")int jobId,@Param("name") String name,@Param("denameId") int denameId);
	//取出总经理工号
	public User findGeneralManager();
	//取出部门经理工号
	public List<User> findManager();
	//取出该部门经理内的员工工号
	public List<User> findStaff(@Param("MjobId")String MjobId);
	//取出该员工的所有同事的工号
	public List<User> findColleague(@Param("SjobId")String SjobId);
	//通过邮箱获取密码
	public User getUserByEmail(@Param("email")String email);
	//通过工号修改部门
	public int updatePersonalinfo(@Param("jobId")int jobId,@Param("denameId") String denameId);


	int updateState(@Param("jobId")int jobId,@Param("state")String state);

}
