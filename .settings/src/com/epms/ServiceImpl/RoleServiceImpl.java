package com.epms.ServiceImpl;

import java.util.List;

import org.apache.ibatis.jdbc.RuntimeSqlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	//删除权限组
	@Transactional
	public int deleteRightsGroups(String rName) {
		try{
			//删除权限组-权限中权限组
			int j=roleMapper.deleteRoleMenu(rName);
			//删除权限组-用户中权限组
			int k=roleMapper.deleteUserRole(rName);
			//删除权限组表中权限组
			int i=roleMapper.deleteRole(rName);
			System.out.println("i:"+i+" j"+j+" k"+k);
			if(i!=0||j!=0||k!=0){
				return 1;
			}else{
				return 0;
			}
			
		}catch(Exception e){
			throw new RuntimeSqlException();
		}
		
	}
}
