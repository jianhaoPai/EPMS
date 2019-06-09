package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.TotalData;

public interface CultivateRecordService 
{
	//查询某个月每个员工的培训天数
	public List<TotalData> CountCultivate(String year,String month);
}
