package com.epms.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
//招聘计划发布
@Repository
public interface OccupationMapper 
{
	//根据名字查询id
	public int selectIdByName(@Param("occupationName")String occupationName);
	
}
