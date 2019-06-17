package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.CultivateApply;
//招聘计划发布
@Repository
public interface CultivateApplyMapper {
	
	//提交培训计划
	int insertCultivateApply(CultivateApply cultivateApply);
	int checkIfRepect(CultivateApply cultivateApply);
	
	//下级查看自己提交给上级的培训计划
	List<CultivateApply> selectAllCultivateApplyByWriteId(@Param("cultivateId") String cultivateId,@Param("status") String status,
			@Param("before") int before,@Param("after") int after,@Param("writeId") int writeId);
	int countSelectAllCultivateApplyByWriteId(@Param("cultivateId") String cultivateId,
			@Param("status") String status,@Param("writeId") int writeId);
	
	//上级查看下级提交的培训计划
	List<CultivateApply> selectCultivateApplyToTotalManager(@Param("before") int before,@Param("after") int after);
	int countToTotalManager();
		
	List<CultivateApply> selectCultivateApplyToBoard(@Param("before") int before,@Param("after") int after);
	int countToBoard();
	
	//审批培训计划
	public int updateCultivateApplyStatus(CultivateApply cultivateApply);
	
	//通过事项id，查询事项信息
	public CultivateApply selectCultivateApplyById(int id);
	
	//员工查询培训计划
	List<CultivateApply> selectAllCultivateApplyToEmployee(@Param("before") int before,@Param("after") int after);
	int countSelectAllCultivateApplyToEmployee();
	
	//修改报名人数
	public int updatealreadyPerson(@Param("alreadyPerson")int alreadyPerson,@Param("id")int id);

	//通过培训id查询培训信息
	public CultivateApply selectById(@Param("id")int id);
	
	//根据id查询对应的培训开始和结束时间
	List<CultivateApply> selectTheSameDateInId(@Param("ids") Integer[]ids,
			@Param("year") String year,@Param("month") String month);
	
	List<CultivateApply> selectSameStartDateInId(@Param("ids") Integer[]ids,
			@Param("year") String year,@Param("month") String month);
	
	List<CultivateApply> selectSameFinishDateInId(@Param("ids") Integer[]ids,
			@Param("year") String year,@Param("month") String month);
}
