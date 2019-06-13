package com.epms.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Role;
import com.epms.Mapper.RoleMapper;
import com.epms.Service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleMapper roleMapper;
	//查询用户权限组
	public List<Role> showRightsGroups(int before,int after){
		return roleMapper.showRightsGroups(before,after);
	}
	
	//计算总条数
	public int count(){
		return roleMapper.count();
	}

	//通过权限组名找权限组
	public int findGroupsByName(String name) {

		return roleMapper.findGroupsByName(name);
	}

	//添加权限组
	public int addRightsGroups(String name, String content, int createId,String createDate) {
		return roleMapper.addRightsGroups(name,content,createId,createDate);
	}

	//更新权限组
	public int editRightsGroups(String name, String content,String oldName) {
		return roleMapper.editRightsGroups(name,content,oldName);
	}
	
	//查询权限组内人员
	public List<String> showRightsGroupsPeople(String id,int before,int after){
		return roleMapper.showRightsGroupsPeople(id,before,after);
	}

	//计算权限组内人员总数
	public int countPeople(String id) {
		return roleMapper.countPeople(id);
	}

	//删除权限组中的人员
	public int deleteRightsGroupsPeople(int jobId,String rName) {
		return roleMapper.deleteRightsGroupsPeople(jobId,rName);
	}
}
