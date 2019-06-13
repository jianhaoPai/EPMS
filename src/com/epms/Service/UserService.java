package com.epms.Service;

import java.util.List;


















import com.epms.Bean.User;

public interface UserService {

	
	//查询所有用户
	public List<User> findAllUser(String jobId,String name,String departmentName,int before,int after);
	//计算总条数
	public int count(String jobId,String name,String departmentName);
	//查询登录的用户是否存在
	public User checkLogin(int jobId, String password);
	//获取用户的姓名
	public String findNameByJobId(int jobId);
	//添加新用户
	public int addAccount(int jobId,String name,String denameId,String ocnameId,String password);
	//查询用户是否存在
	public User findAccountByJobId(int jobId);
	//修改账号密码就职情况
	public int editAccount(int jobid, String password,String state,String denameId,String ocnameId);
	
	//测试事务
	public void test(int jobId,String password) ;
	//通过邮箱获取密码
	public User getUserByEmail(String email);
	//通过部门号获取最后的工号
	public int selectLastJobId(String denameId);

	
	
}
