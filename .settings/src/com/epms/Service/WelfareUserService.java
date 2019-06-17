package com.epms.Service;

import java.util.List;

import com.epms.Bean.WelfareUser;

public interface WelfareUserService {

	//查询个人福利
	List<WelfareUser> showWelfare(int jobId,int before, int after);

	//计算个人福利条数
	int count(int jobId);

}
