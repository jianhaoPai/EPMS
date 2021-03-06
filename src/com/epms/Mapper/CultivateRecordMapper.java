package com.epms.Mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.CultivateRecord;

//招聘计划发布
@Repository
public interface CultivateRecordMapper 
{
	//报名培训
		int insertCultivateRecord(@Param("cultivateId") int cultivateId,@Param("participatorId") int participatorId,
				@Param("status") String status);
		
		//查询是否重复报名
		int checkIfRepeat(@Param("cultivateId") int cultivateId,@Param("participatorId") int participatorId);
		
		//删除最新语句
		int deleteMaxId();
		
		//员工查询自己报名的培训课程
		List<CultivateRecord> selectCultivateRecordByJobId(@Param("cultivateId") String cultivateId,@Param("status") String status,@Param("before") int before,@Param("after") int after,@Param("jobId") int jobId);
		int countSelectCultivateRecordByJobId(@Param("cultivateId") String cultivateId,@Param("status") String status,@Param("jobId") int jobId);
		
		//根据年月和工号查询是否已有报名培训
		int countSumByDate(@Param("jobId") int jobId,@Param("year") String year,@Param("month") String month);
		
		//上级查询直接下级的已报名培训记录
		List<CultivateRecord> selectCultivateRecordToManager(@Param("before") int before,@Param("after") int after,@Param("managerId") int managerId);
		int countToManager(@Param("managerId") int managerId);
		
		List<CultivateRecord> selectCultivateRecordToTotalManager(@Param("before") int before,@Param("after") int after);
		int countToTotalManager();
	 	
		//审核报名培训
		int updateCultivateRecordStatus(@Param("recordId") int recordId,@Param("status") String status);
		
		//根据工号查询报名的所有培训id
		Integer[] selectIdByJobId(@Param("jobId") int jobId);
}
