package com.hjh.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.Partyinfo;
import com.hjh.Bean.Personalinfo;
import com.hjh.Conventer.DateConverter;
import com.hjh.Mapper.PartyinfoMapper;
import com.hjh.Mapper.PersonalinfoMapper;
import com.hjh.Service.PartyinfoService;
import com.hjh.Service.PersonalinfoService;

@Service("partyinfoService")
public class PartyinfoServiceImpl implements PartyinfoService
{
	@Autowired
	private Partyinfo partyinfo;
	
	@Autowired
	private PartyinfoMapper partyinfoMapper;
	
	//根据工号查询党信息
	@Override
	public Partyinfo selectPartyinfoById(int jobId) 
	{
		partyinfo=partyinfoMapper.selectPartyinfoById(jobId);
		if(partyinfo==null)
		{
			partyinfo=new Partyinfo();
			partyinfo.setPartyIdentity("无");
			partyinfo.setPartyOccupation("无");
			partyinfo.setJobId(jobId);
		}
		return partyinfo;
	}
}
