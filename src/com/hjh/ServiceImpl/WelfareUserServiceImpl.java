package com.hjh.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.WelfareUser;
import com.hjh.Mapper.WelfareUserMapper;
import com.hjh.Service.WelfareUserService;
@Service("welfareUserService")
public class WelfareUserServiceImpl implements WelfareUserService{

	@Autowired
	private WelfareUserMapper welfareUserMapper;
	
	//查询个人福利
	public List<WelfareUser> showWelfare(int jobId,int before, int after) {
		return welfareUserMapper.showWelfare(jobId,before,after);
	}

	//查询个人福利条数
	public int count(int jobId) {
		return welfareUserMapper.count(jobId);
	}

}
