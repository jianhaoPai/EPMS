package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.CultivateApply;
//招聘计划发布
@Repository
public interface CultivateApplyMapper 
{
	//根据id查询对应的培训开始和结束时间
	List<CultivateApply> selectTheSameDateInId(@Param("ids") Integer[]ids,
			@Param("year") String year,@Param("month") String month);
	
	List<CultivateApply> selectSameStartDateInId(@Param("ids") Integer[]ids,
			@Param("year") String year,@Param("month") String month);
	
	List<CultivateApply> selectSameFinishDateInId(@Param("ids") Integer[]ids,
			@Param("year") String year,@Param("month") String month);
	
}
