package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.FeedBack;

@Repository
public interface FeedBackMapper 
{
   //插入反馈信息
   int insertFeedbackInfo(FeedBack feedback);
   
   //根据部门查询反馈信息
   List<FeedBack> selectFeedbackByDepartmentId(@Param("state")String state,@Param("department_id")String department_id,
		                                       @Param("feedback_type")String feedback_type,@Param("before") int before,
		                                       @Param("after") int after,@Param("departmentId")int departmentId,
		                                       @Param("jobId")int jobId);
   int countByDepartmentId(@Param("state")String state,@Param("department_id")String department_id,
                           @Param("feedback_type")String feedback_type,@Param("departmentId")int departmentId);
   
   //查询全部部门经理的反馈信息
   List<FeedBack> selectFeedbackOnlyManager(@Param("state")String state,@Param("department_id")String department_id,
                                            @Param("feedback_type")String feedback_type,@Param("before") int before,
                                            @Param("after") int after);
   int countOnlyManager(@Param("state")String state,@Param("department_id")String department_id,
                        @Param("feedback_type")String feedback_type);
   
   //查询全部总经理的反馈信息
   List<FeedBack> selectFeedbackOnlyTotalManager(@Param("state")String state,@Param("department_id")String department_id,
                                                 @Param("feedback_type")String feedback_type,@Param("before") int before,
                                                 @Param("after") int after);
   int countOnlyTotalManager(@Param("state")String state,@Param("department_id")String department_id,
                             @Param("feedback_type")String feedback_type);
   
   //审批反馈信息
   int updateFeedback(FeedBack feedback);
   
   //根据事项id查询反馈信息
   FeedBack selectFeedbackById(int id);
   
   //根据工号查询全部反馈信息
   List<FeedBack> selectAllFeedBackByJobId(@Param("state")String state,@Param("feedbackType")String feedbackType,@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
   int countByJobId(@Param("state")String state,@Param("feedbackType")String feedbackType,@Param("jobId")int jobId);
   
   //查询是否重复提交
   int checkIfRepeat(FeedBack feedback);
   
}
