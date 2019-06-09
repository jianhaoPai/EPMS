package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Apply;

@Repository
public interface ApplyMapper 
{
	//根据部门经理提交事项申请表
	int insertApply(Apply apply);
	
	//查询最大applyId
	int selectApplyMaxId();
	
	//总经理查看部门提交的全部申请事项信息
	List<Apply> selectApplyToTotalManager(@Param("department_id")String department_id,@Param("state")String state,
		                                  @Param("applytype_name")String applytype_name,@Param("before") int before,
		                                  @Param("after") int after);
	int countToTotalManager(@Param("department_id")String department_id,@Param("state")String state,
	                        @Param("applytype_name")String applytype_name);
	
	//董事查看总经理提交的全部申请事项信息
	List<Apply> selectApplyToBoard(@Param("department_id")String department_id,@Param("state")String state,
		                           @Param("applytype_name")String applytype_name,@Param("before") int before,
		                           @Param("after") int after);
	int countToBoard(@Param("department_id")String department_id,@Param("state")String state,
	                 @Param("applytype_name")String applytype_name);
	
	//审批申请事项
	int updateApply(Apply apply);
	
	//根据事项id查询申请事项信息
	Apply selectApplyById(int id);
	
	//根据填表人id查询申请事项信息
	List<Apply> selectAllApplyByWriteId(@Param("department_id")String department_id,@Param("state")String state,
			    @Param("applytype_name")String applytype_name,@Param("before") int before,@Param("after") int after,
			    @Param("jobId") int jobId);
	int countByWriteId(@Param("department_id")String department_id,@Param("state")String state,
			           @Param("applytype_name")String applytype_name,@Param("jobId") int jobId);
	
	
	//删除id最大的记录
	int delectApplyMaxId();
	
	//判断是否重复提交
	int checkIfRepeat(Apply apply);
}
