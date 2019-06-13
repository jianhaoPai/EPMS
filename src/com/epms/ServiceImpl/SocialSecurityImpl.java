package com.epms.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.SocialSecurity;
import com.epms.Mapper.SocialSecurityMapper;
import com.epms.Service.SocialSecurityService;

@Service("socialSecurityService")
public class SocialSecurityImpl implements SocialSecurityService
{
	@Autowired
	private SocialSecurityMapper socialSecurityMapper;

	//根据工号查询保险信息
	@Override
	public List<SocialSecurity> selectSocialSecurityById(String securityType,int before,int after, int jobId) 
	{
		return socialSecurityMapper.selectSocialSecurityById(securityType, before, after, jobId);
	}

	@Override
	public int count(String securityType,int jobId) 
	{
		return socialSecurityMapper.count(securityType, jobId);
	}
	

	//根据工号和保险类型查询保险信息
	@Override
	public List<SocialSecurity> selectSocialSecurityByTypeAndId(SocialSecurity socialSecurity) 
	{
		return socialSecurityMapper.selectSocialSecurityByTypeAndId(socialSecurity);
	}

	

	
}
