package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Menu;

@Repository
public interface MenuMapper {

	/*
	 * 通过权限组查询菜单
	 */
	List<Menu> findMenuByRole(int jobId);

	//验证权限
	int findMenuByJobId(@Param("jobId")int jobId,@Param("path") String path);
}
