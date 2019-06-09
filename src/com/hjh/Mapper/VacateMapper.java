package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Vacate;

@Repository
public interface VacateMapper 
{
	//插入请假信息
	public int insertVacate(Vacate vacate); 
	
	//根据工号查询全部请假记录
	public List<Vacate> selectAllVacateApplyByJobId (@Param("state") String state,@Param("type")String type,@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
	int count (@Param("state") String state,@Param("type")String type,@Param("jobId")int jobId);
	
	//查询是否有重复的请假信息
	public int selectIfRepeat (Vacate vacate);
	
	//部门经理查询全部下属提交的请假申请
	List<Vacate> selectVacateToManager(@Param("department_id")String department_id,@Param("state")String state,
			                           @Param("type")String type,@Param("before") int before,
			                           @Param("after") int after,@Param("jobId")int jobId);
	int countToManager(@Param("department_id")String department_id,@Param("state")String state,
                       @Param("type")String type,@Param("jobId")int jobId);
	
	//总经理查询全部部门经理提交的请假申请
	List<Vacate> selectVacateToTotalManager(@Param("department_id")String department_id,@Param("state")String state,
                                            @Param("type")String type,@Param("before") int before,
                                            @Param("after") int after);
	int countToTotalManager(@Param("department_id")String department_id,@Param("state")String state,
                            @Param("type")String type);
	
	//董事查询全部总经理提交的请假申请
	List<Vacate> selectVacateToBoard(@Param("department_id")String department_id,@Param("state")String state,
                                     @Param("type")String type,@Param("before") int before,
                                     @Param("after") int after);
	int countToBoard(@Param("department_id")String department_id,@Param("state")String state,
                     @Param("type")String type);
	
	//审批请假申请
	int updateVacate(Vacate vacate);
	
	//通过事项id查询信息
	Vacate selectVacateById(int id);
	
	//根据工号查询已通过的全部请假记录
	public List<Vacate> selectPassVacateByJobId (@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
	int countPassVacateByJobId (int jobId);
	
	//销假处理
	int cancelVacateApply(Vacate vacate);
	
	//查询全部员工的当月请假天数（请假开始时间和请假结束时间都为本月时）
	List<Vacate> countVacateSumBySameDate(@Param("year") String year,@Param("month") String month);
	
	//查询全部员工的当月请假天数（请假开始时间为本月,但结束时间不为本月）
	List<Vacate> countVacateSumByStartIsMonth(@Param("year") String year,@Param("month") String month);
	
	//查询全部员工的当月请假天数（请假结束时间为本月,但开始时间不为本月）
	List<Vacate> countVacateSumByFinishIsMonth(@Param("year") String year,@Param("month") String month);
	
	//根据工号查询全部同意的请假申请的开始时间和结束时间
	public List<Vacate> selectVacateStartAndFinishByJobId (@Param("jobId")int jobId);
	
	
	
}
