package com.epms.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.epms.Bean.CultivateApply;
import com.epms.Bean.CultivateRecord;
import com.epms.Bean.TotalData;


public interface CultivateRecordService 
{
	//报名培训
	String insertCultivateRecord(int cultivateId,int participatorId);
	
	//员工查询自己报名的培训课程
	List<CultivateRecord> selectCultivateRecordByJobId(int before,int after,int jobId);
	int countSelectCultivateRecordByJobId(int jobId);
	//查询某个月每个员工的培训天数
		public List<TotalData> CountCultivate(String year,String month);
}
