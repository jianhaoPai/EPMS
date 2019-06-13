package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Rights;
import com.epms.Bean.User;

@Repository
public interface RightsMapper {

	//查询所有权限
	public List<Rights> showRights(@Param("menuName")String menuName,@Param("before") int before,@Param("after") int after);
	
	//计算所有权限条数
	public int count(@Param("menuName")String menuName);

	//查询不在该权限组的员工
	public List<User> showRightsPeople(@Param("id")String roleId,@Param("jobId")String jobId,@Param("name")String name,@Param("deName")String departmentName,@Param("before") int before,@Param("after") int after);

	//获取不在该权限组的员工的条数
	public int countRightsPeople(@Param("id")String id,@Param("jobId")String jobId,@Param("name")String name,@Param("deName")String departmentName);

	//向权限组内添加员工
	public int addRightsPeople(@Param("jobId")String jobId,@Param("id") String id);

	//查询该权限组的权限
	public List<Rights> showGroupsRights(@Param("id")String id,@Param("before") int before,@Param("after") int after);

	//查询该权限组的权限的条数
	public int countGroupsRights(@Param("id")String id);

	//查询该权限组的权限
	public List<Rights> showGroupsRights(@Param("id")String id,@Param("menuName") String menuName,@Param("before")int before,@Param("after") int after);
	
	//查询该权限组的权限的条数
	public int countGroupsRights(@Param("id")String id,@Param("menuName") String menuName);

	//查询该权限组中没有的权限
	public List<Rights> showGroupsLackRights(@Param("id")String id,@Param("menuName") String menuName,@Param("before")int before,@Param("after") int after);

	//查询该权限组中没有的权限的条数
	public int countGroupsLackRights(@Param("id")String id,@Param("menuName") String menuName);

	//向权限组中添加权限
	public int addGroupsLackRights(@Param("menuId")String menuId,@Param("id") String id);
	
	//删除权限组中的权限
	public int deleteGroupsRights(@Param("menuId")String menuId,@Param("id") String id);

	//查询权限组是否已有该权限
	public Rights selectGroupsRights(@Param("menuId")String menuId,@Param("roleId") String roleId);
}
