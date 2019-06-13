package com.epms.Service;

import java.util.List;

import com.epms.Bean.TotalData;
import com.epms.Bean.Vacate;


public interface VacateService 
{
	//插入请假信息
	String insertVacate(Vacate vacate);
	
	//通过id查询全部请假信息
	List<Vacate> selectAllVacateApplyByJobId(String state,String type,int before,int after,int jobId);	
	int count (String state,String type,int jobId);
	
	//查询全部直接下级提交的请假申请事项(上级查询下属的)
	List<Vacate> selectAllVacateApply (String department_id,String state,String type,int before,int after,int jobId);
	int countToLeader (String department_id,String state,String type,int jobId);
	
	//审批请假申请信息
  	String updateVacate(Vacate vacate);
  	
    //根据工号查询已通过的全部请假记录
  	public List<Vacate> selectPassVacateByJobId (int before,int after,int jobId);
  	int countPassVacateByJobId (int jobId);
  	
	//审批请假申请信息
  	String cancelVacateApply(Vacate vacate);
  	
    //查询全部人员当月的请假天数
  	List<TotalData> selectAllVacateSum(String year,String month);
  	
  	
}
