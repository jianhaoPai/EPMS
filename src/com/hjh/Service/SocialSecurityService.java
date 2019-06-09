package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.SocialSecurity;

public interface SocialSecurityService
{
	//根据工号查询保险信息
	public List<SocialSecurity> selectSocialSecurityById(String securityType,int before,int after,int jobId);	
    public int count (String securityType,int jobId);

	//根据工号查询保险信息
	public List<SocialSecurity> selectSocialSecurityByTypeAndId(SocialSecurity socialSecurity);
}
