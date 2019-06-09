package com.hjh.Mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

//招聘计划发布
@Repository
public interface CultivateRecordMapper 
{
	//根据工号查询报名的所有培训id
	Integer[] selectIdByJobId(@Param("jobId") int jobId);
}
