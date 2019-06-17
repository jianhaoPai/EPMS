package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Vacate;
import com.epms.Bean.Vacation;

@Repository
public interface VacationMapper 
{
	//查询所有假期剩余情况
	List<Vacation> selectAllRemainById (@Param("type") String type,@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
	int count (@Param("type") String type,@Param("jobId")int jobId);
	
	//根据id和类型查询剩余情况
	Vacation selectRemainByVacate(Vacate vacate);
	Vacation selectRemainByVacation(Vacation vacation);
	
	//根据id和类型修改虚拟剩余情况
	int updateVirtualUse(Vacation vacation);
	
	//根据id和类型修改虚拟剩余情况和真实剩余情况
	int updateVirtualAll(Vacation vacation);
	
	////根据id和类型修改虚拟剩余情况和已经使用情况
	int updateVirtualExceptRemain(Vacation vacation);
}
