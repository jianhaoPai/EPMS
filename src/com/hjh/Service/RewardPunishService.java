package com.hjh.Service;

import java.util.List;
import com.hjh.Bean.Personalinfo;
import com.hjh.Bean.RewardPunish;

public interface RewardPunishService {
	//获取本人的奖惩记录
	public List<RewardPunish> showRewardPunish(int jobId,int before, int after);
	//获取本人的奖惩记录条数
	public int countshowRewardPunish(int jobId);
	//查询工号、姓名是否正确且在该申请人部门内
	public Personalinfo selectUserById(int jobId, String name, int applyId);
	//添加员工的奖惩
	public int addStaffRewardPunish(int jobId, String type, String content,String reason, String setDate, int applyId);
	//查询需要审核的申请员工奖惩
	public List<RewardPunish> showVerifyRewardPunish(int before, int after);
	//获取需要审核的申请员工奖惩的条数
	public int countVerifyRewardPunish();
	//审核申请员工奖惩通过
	public int verifyRewardPunishYes(int jobId, String reason, String setDate,int approveId, String approveDate);
	//审核申请员工奖惩未通过
	public int verifyRewardPunishNo(int jobId, String reason, String setDate,int approveId, String approveDate);
	//查询申请员工奖惩的记录
	public List<RewardPunish> showApplyRewardPunishRecord(int jobId,int before, int after);
	//查询申请员工奖惩的记录 条数
	public int countApplyRewardPunishRecord(int jobId);
	//查询审核员工奖惩的记录
	public List<RewardPunish> showVerifyRewardPunishRecord(int jobId,int before, int after);
	//查询审核员工奖惩的记录条数
	public int countVerifyRewardPunishRecord(int jobId);

}
