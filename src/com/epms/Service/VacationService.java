package com.epms.Service;

import java.util.List;

import com.epms.Bean.Vacate;
import com.epms.Bean.Vacation;


public interface VacationService 
{
	//查询剩余所有的假期情况
	List<Vacation> selectVacationRemain(String type,int before,int after,int jobId);	
    int count (String type,int jobId);
	
	//根据id和类型查询剩余天数
    Vacation selectRemainByVacate(Vacate vacate);
    Vacation selectRemainByVacation(Vacation vacation);
  	
}
