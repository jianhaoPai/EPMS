package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.SocialSecurity;

@Repository
public interface SocialSecurityMapper 
{
	//根据工号查询保险信息
	public List<SocialSecurity> selectSocialSecurityById (@Param("securityType") String securityType,@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId);
	public int count (@Param("securityType") String securityType,@Param("jobId")int jobId);
	
	//根据工号查询保险信息
	List<SocialSecurity> selectSocialSecurityByTypeAndId(SocialSecurity socialSecurity);
}
