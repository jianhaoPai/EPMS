package com.hjh.Service;

import java.util.List;

















import com.hjh.Bean.User;

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
	public int addAccount(int jobId,String name,int denameId,String password);
	//查询用户是否存在
	public User findAccountByJobId(int jobId);
	//修改账号密码就职情况
	public int editAccount(int jobid, String password,String state,String denameId);
	
	//测试事务
	public void test(int jobId,String password) ;
	//通过邮箱获取密码
	public User getUserByEmail(String email);


	
	
}
