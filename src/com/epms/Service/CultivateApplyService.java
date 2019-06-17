package com.epms.Service;

import java.util.List;

import com.epms.Bean.CultivateApply;


public interface CultivateApplyService {
	//提交培训申请
	public String insertCultivateApply(CultivateApply cultivateApply,int jobId);

	//查询自己提交的培训计划
	List<CultivateApply> selectAllCultivateApplyByWriteId(String cultivateId,String status,
			int before,int after, int writeId);
	int countSelectAllCultivateApplyByWriteId(String cultivateId,String status,int writeId);
	
	//上级查询下级提交的全部培训信息
	public List<CultivateApply> selectAllCultivateApply(int before,int after,int jobId);
	public int countAllCultivateApply(int jobId);
	//审核培训计划
	public String updateCultivateApplyStatus(CultivateApply cultviateApply);	
	//员工查询培训计划
	List<CultivateApply> selectAllCultivateApplyToEmployee(int before,int after);
	int countSelectAllCultivateApplyToEmployee();
	
	
	
}
