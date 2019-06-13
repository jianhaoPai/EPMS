package com.epms.ServiceImpl;

import java.util.List;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.epms.Bean.User;
import com.epms.Mapper.RoleMapper;
import com.epms.Mapper.UserMapper;
import com.epms.Service.UserService;

@Service("userService")

public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	
	/*
	 * 查询所有用户
	 */
	public List<User> findAllUser(String jobId,String name,String departmentName,int before,int after){
		return userMapper.findAllUser(jobId,name,departmentName,before,after);
	}

	/*
	 * 查询条数
	 */
	public int count(String jobId,String name,String departmentName) {
		return userMapper.count(jobId,name,departmentName);
	}
	
	/*
	 * 查询登录的用户是否存在（检验工号、密码）
	 */
	@Override
	public User checkLogin(int jobId,String password) {
		User user=userMapper.findByJobId(jobId);
		if(user!=null&&user.getPassword().equals(password)){
			return user;
		}
		return null;
	}
	
	/*
	 * 获取用户的姓名
	 */
	public String findNameByJobId(int jobId){
		return userMapper.findNameByJobId(jobId);
	}
	
	/*
	 * 查询用户是否存在（只查jobId）
	 */
	public User findAccountByJobId(int jobId){
		return userMapper.findAccountByJobId(jobId);
	}
	/*
	 * 添加新用户
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public int addAccount(int jobId,String name,String denameId,String ocnameId, String password){
		int i=0,j=0,k=0;
		try{
			i=userMapper.addUser(jobId,password);
			j=userMapper.addPersonalInfo(jobId,name,denameId,ocnameId);
			k=roleMapper.addUserToRole(jobId);
			if(i==0||j==0||k==0){
				throw new Exception();
			}
		}catch(Exception e){
			System.out.println("数据操作错误");
		}
		return 1;
	}
	
	/*
	 * 修改账号密码就职情况
	 */
	@Transactional
	public int editAccount(int jobId,String password,String state,String denameId,String ocnameId){
		try{
			userMapper.updatePersonalinfo(jobId,denameId,ocnameId);
			userMapper.editAccount(jobId,password,state);
			return 1;
		}catch(Exception e){
			System.out.println("数据操作错误");
			throw new RuntimeSqlException();
		}
	}

	//测试事务
	@Transactional(rollbackFor=Exception.class)
	public void test(int jobId,String password){
		try{
			userMapper.addUser(jobId, password);
			throw new RuntimeSqlException();
		}catch(Exception e){
			//TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			System.out.println("数据操作错误");
			throw new RuntimeSqlException();
		}
		
		
	}

	//通过邮箱获取密码
	public User getUserByEmail(String email) {
		return userMapper.getUserByEmail(email);
	}
	
	//通过部门号获取最后的工号
	public int selectLastJobId(String denameId){
		Integer i=userMapper.selectLastJobId(denameId);
		if(i!=null){
			return i;
		}else{
			String jobId=denameId+"0000";
			int j=Integer.parseInt(jobId);
			return j;
		}
		
	}
	
}
