package com.hjh.ServiceImpl;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.Menu;
import com.hjh.Mapper.MenuMapper;
import com.hjh.Service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuMapper menuMapper;
	
	/*
	 * 通过权限组查找菜单
	 */
	public List<Menu> findMenuByRole(int jobId){
		return menuMapper.findMenuByRole(jobId);
	}
}
