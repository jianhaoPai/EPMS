package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;



import com.epms.Bean.Personalinfo;
import com.epms.Bean.RewardPunish;

@Repository
public interface RewardPunishMapper {

	//获取本人的奖惩记录
	List<RewardPunish> showRewardPunish(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);

	//获取本人的奖惩记录条数
	int countshowRewardPunish(int jobId);
	
	//查询工号、姓名是否正确且在该申请人部门内
	Personalinfo selectUserById(@Param("jobId")int jobId,@Param("name") String name,@Param("applyId") int applyId);
	
	//添加员工的奖惩
	int addStaffRewardPunish(@Param("jobId")int jobId,@Param("type") String type,@Param("content") String content,@Param("reason")String reason,@Param("setDate") String setDate,@Param("applyId") int applyId);

	//查询需要审核的申请员工奖惩
	List<RewardPunish> showVerifyRewardPunish(@Param("before")int before,@Param("after") int after);

	//获取需要审核的申请员工奖惩的条数
	int countVerifyRewardPunish();

	//审核申请员工奖惩通过
	int verifyRewardPunishYes(@Param("jobId")int jobId,@Param("reason") String reason,@Param("setDate") String setDate,@Param("approveId")int approveId,@Param("approveDate") String approveDate);

	//审核申请员工奖惩未通过
	int verifyRewardPunishNo(@Param("jobId")int jobId,@Param("reason") String reason,@Param("setDate") String setDate,@Param("approveId")int approveId,@Param("approveDate") String approveDate);

	//查询申请员工奖惩的记录
	List<RewardPunish> showApplyRewardPunishRecord(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);

	//查询申请员工奖惩的记录 条数
	int countApplyRewardPunishRecord(int jobId);

	//查询审核员工奖惩的记录
	List<RewardPunish> showVerifyRewardPunishRecord(@Param("jobId")int jobId,@Param("before") int before,@Param("after")int after);

	//查询审核员工奖惩的记录条数
	int countVerifyRewardPunishRecord(int jobId);

}
