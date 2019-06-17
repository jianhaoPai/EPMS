package com.epms.Service;

import java.util.List;

import com.epms.Bean.CultivateRecord;
import com.epms.Bean.TotalData;


public interface CultivateRecordService 
{
	//报名培训
	String insertCultivateRecord(int cultivateId,int participatorId);
	
	//员工查询自己报名的培训课程
	List<CultivateRecord> selectCultivateRecordByJobId(String cultivateId,String status,int before,int after,int jobId);
	int countSelectCultivateRecordByJobId(String cultivateId,String status,int jobId);
	
	//上级查询下属所报名的全部培训记录
	List<CultivateRecord> selectAllCultivateRecord(int before,int after,int leaderId);
	int countselectAllCultivateRecord(int leaderId);
	
	//审核培训报名
	String updateCultivateRecordStatus(int recordId,String recordStatus,String status,int cultivateId);

	//查询某个月每个员工的培训天数
	public List<TotalData> CountCultivate(String year,String month);
	
}
