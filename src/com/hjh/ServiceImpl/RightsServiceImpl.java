package com.hjh.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.Rights;
import com.hjh.Bean.User;
import com.hjh.Mapper.RightsMapper;
import com.hjh.Service.RightsService;
@Service("rightsService")
public class RightsServiceImpl implements RightsService{

	@Autowired
	private RightsMapper rightsMapper;

	//查询所有权限
	public List<Rights> showRights(String menuName, int before, int after) {
		return rightsMapper.showRights(menuName,before,after);
	}

	//计算所有权限条数
	public int count(String menuName) {
		return rightsMapper.count(menuName);
	}

	//查询不在该权限组的员工
	public List<User> showRightsPeople(String id,String jobId,String name,String dename, int before, int after) {
		return rightsMapper.showRightsPeople(id,jobId,name,dename,before,after);
	}

	//获取不在该权限组的员工的条数
	public int countRightsPeople(String id,String jobId,String name,String dename) {
		return rightsMapper.countRightsPeople(id,jobId,name,dename);
	}

	//向权限组内添加员工
	public int addRightsPeople(String jobId, String id) {
		return rightsMapper.addRightsPeople(jobId,id);
	}

	//查询该权限组的权限
	public List<Rights> showGroupsRights(String id, int before, int after) {
		return rightsMapper.showGroupsRights(id,before,after);
	}

	//查询该权限组的权限的条数
	public int countGroupsRights(String id) {
		return rightsMapper.countGroupsRights(id);
	}

	//查询该权限组中没有的权限
	public List<Rights> showGroupsLackRights(String id,String menuName, int before, int after) {
		return rightsMapper.showGroupsLackRights(id,menuName,before,after);
	}

	//查询该权限组中没有的权限的条数
	public int countGroupsLackRights(String id,String menuName) {
		return rightsMapper.countGroupsLackRights(id,menuName);
	}

	//向权限组中添加权限
	public int addGroupsLackRights(String menuId, String id) {
		return rightsMapper.addGroupsLackRights(menuId,id);
	}

	//删除权限组中的权限
	public int deleteGroupsRights(String menuId, String id) {
		return rightsMapper.deleteGroupsRights(menuId,id);
	}

	//查询权限组是否已有该权限
	public Rights selectGroupsRights(String menuId, String roleId) {
		return rightsMapper.selectGroupsRights(menuId,roleId);
	}



	
}
