package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Role;

@Repository
public interface RoleMapper {

	//查询用户权限组
	public List<Role> showRightsGroups(@Param("before")int before,@Param("after")int after);
	
	//计算总条数
	public int count();

	//通过权限组名找权限组
	public int findGroupsByName(String name);

	//添加权限组
	public int addRightsGroups(@Param("name")String name,@Param("content") String content,@Param("createId") int createId,@Param("createDate")String createDate);

	//更新权限组
	public int editRightsGroups(@Param("name")String name,@Param("content") String content,@Param("oldName")String oldName);

	//查询权限组内人员
	public List<String> showRightsGroupsPeople(@Param("id")String id,@Param("before") int before,@Param("after")int after);

	//计算权限组内人员总数
	public int countPeople(String id);

	//删除权限组中的人员
	public int deleteRightsGroupsPeople(@Param("jobId")int jobId,@Param("rName")String rName);
	//为新建用户添加默认权限组
	public int addUserToRole(@Param("jobId")int jobId);
	//删除权限组表中权限组
	public int deleteRole(String rName);
	//删除权限组-权限中权限组
	public int deleteRoleMenu(String rName);
	//删除权限组-用户中权限组
	public int deleteUserRole(String rName);
}
