package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.SocialSecurity;
import com.epms.Bean.TotalData;

@Repository
public interface TotalDataMapper 
{
	//插入请假天数信息
	int insertTotalDataByTotalVacate(@Param("jobId") int jobId,@Param("totalVacate") int totalVacate);
	
	//修改请假天数信息
	int updateTotalDataByTotalVacate(@Param("jobId") int jobId,@Param("totalVacate") int totalVacate);

	//根据工号查询当月的请假天数、迟到次数、早退次数、加班时长(以半小时为单位)
	TotalData selectByJobId(@Param("jobId") int jobId);
	
	//查询全部人员的当月的请假天数、迟到次数、早退次数、加班时长(以半小时为单位)
	List<TotalData> selectAllDataSum();
	
	//删除表数据
	int deleteAll();
	
	
	//插入早退、迟到、加班(工作日加班、节假日加班)、缺勤信息
	int insertTotalDataByLately(@Param("jobId") int jobId,@Param("totalLately") int totalLately);
	int insertTotalDataByEarlyLeave(@Param("jobId") int jobId,@Param("totalEarlyleave") int totalEarlyleave);
	int insertTotalDataByWorkOverTime(@Param("jobId") int jobId,@Param("totalWorkOverTime") int totalWorkOverTime);
	int insertTotalDataByNotWorkOverTime(@Param("jobId") int jobId,@Param("totalNotWorkOverTime") int totalNotWorkOverTime);
	int insertTotalDataByAbsence(@Param("jobId") int jobId,@Param("totalAbsence") int totalAbsence);	
	
	//修改早退、迟到、加班(工作日加班、节假日加班)、缺勤、培训信息 
	int updateTotalDataByLately(@Param("jobId") int jobId,@Param("totalLately") int totalLately);
	int updateTotalDataByEarlyLeave(@Param("jobId") int jobId,@Param("totalEarlyleave") int totalEarlyleave);
	int updateTotalDataByWorkOverTime(@Param("jobId") int jobId,@Param("totalWorkOverTime") int totalWorkOverTime);
	int updateTotalDataByNotWorkOverTime(@Param("jobId") int jobId,@Param("totalNotWorkOverTime") int totalNotWorkOverTime);
	int updateTotalDataByAbsence(@Param("jobId") int jobId,@Param("totalAbsence") int totalAbsence);
	int updateTotalDataByCaltivateday(@Param("jobId") int jobId,@Param("totalCaltivateDay") int totalCaltivateDay);
	
	//分页查询表中数据
	public List<TotalData> selectDataSum (@Param("before") int before,@Param("limit") int limit);
	public int countDataSum ();
	
	
}
