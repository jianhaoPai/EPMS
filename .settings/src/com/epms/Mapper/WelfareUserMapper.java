package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.WelfareUser;

@Repository
public interface WelfareUserMapper {

	//查询个人福利
	public List<WelfareUser> showWelfare(@Param("jobId")int jobId,@Param("before")int before,@Param("after") int after);

	//查询个人福利条数
	public int count(int jobId);

}
