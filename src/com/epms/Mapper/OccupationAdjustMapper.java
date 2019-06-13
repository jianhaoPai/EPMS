package com.epms.Mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.OccupationAdjust;
import com.epms.Bean.SalaryAdjust;

@Repository
public interface OccupationAdjustMapper 
{
	//提交职务调动表
	public int insertOccupationAdjust(OccupationAdjust occupationAdjust);
	
	//检查是否有重复
	int selectIfRepeat(OccupationAdjust occupationAdjust);
	
	//根据填表人id查询职位调动申请信息
	List<OccupationAdjust> selectAllOccupationAdjustByWriteId(@Param("pre_departmentid") String pre_departmentid,
			               @Param("state") String state,@Param("type")String type,@Param("before") int before,
			               @Param("after") int after,@Param("jobId") int jobId);
	int countByWriteId(@Param("pre_departmentid") String pre_departmentid,@Param("state") String state,
			           @Param("type")String type,@Param("jobId") int jobId);
	
	//通过事项id查询相关信息
	OccupationAdjust selectOccupationAdjustByApplyId(int applyId);
	
	//上级查询下属提交的职位调动申请
	List<OccupationAdjust> selectOccupationAdjustToLeader(@Param("pre_departmentid") String pre_departmentid,
			                                      @Param("state") String state,@Param("type") String type,
			                                      @Param("before") int before,@Param("after") int after,
			                                      @Param("writeOccupationId") int writeOccupationId);
	int countToLeader(@Param("pre_departmentid") String pre_departmentid,
			          @Param("state") String state,@Param("type") String type,
			          @Param("writeOccupationId") int writeOccupationId);
	
	//根据工号查询相关的职位调动记录
	List<OccupationAdjust> selectAllOccupationAdjustByJobId(@Param("state") String state,
			               @Param("type")String type,@Param("before") int before,
                           @Param("after") int after,@Param("jobId") int jobId);
    int countByJobId(@Param("state") String state,@Param("type")String type,@Param("jobId") int jobId);
	
}
