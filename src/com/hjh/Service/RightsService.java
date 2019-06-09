package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.Rights;
import com.hjh.Bean.User;

public interface RightsService {

	//查询所有权限
	public List<Rights> showRights(String menuName, int before, int after);

	//计算所有权限条数
	public int count(String menuName);
	//查询不在该权限组的员工
	public List<User> showRightsPeople(String id,String jobId,String name,String dename, int before, int after);
	//获取不在该权限组的员工的条数
	public int countRightsPeople(String id,String jobId,String name,String dename);
	//向权限组内添加员工
	public int addRightsPeople(String jobId, String id);
	//查询该权限组的权限
	public List<Rights> showGroupsRights(String id, int before, int after);
	//查询该权限组的权限的条数
	public int countGroupsRights(String id);
	//查询该权限组中没有的权限
	public List<Rights> showGroupsLackRights(String id,String menuName, int before, int after);
	//查询该权限组中没有的权限的条数
	public int countGroupsLackRights(String id,String menuName);
	//向权限组中添加权限
	public int addGroupsLackRights(String menuId, String id);
	//删除权限组中的权限
	public int deleteGroupsRights(String menuId, String id);
	//查询权限组是否已有该权限
	public Rights selectGroupsRights(String menuId, String id);

}
