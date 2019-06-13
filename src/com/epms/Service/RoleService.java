package com.epms.Service;

import java.util.List;

import com.epms.Bean.Role;

public interface RoleService {

	//查询用户权限组
	public List<Role> showRightsGroups(int before,int after);
	//计算总条数
	public int count();
	//通过权限组名找权限组
	public int findGroupsByName(String name);
	//添加权限组
	public int addRightsGroups(String name, String content, int createId,String nowTime);
	//更新权限组
	public int editRightsGroups(String name, String content,String oldName);
	//查询权限组内人员
	public List<String> showRightsGroupsPeople(String id, int before, int after);
	//计算权限组内人员总数
	public int countPeople(String id);
	//删除权限组中的人员
	public int deleteRightsGroupsPeople(int jobId,String rName);
	
}
