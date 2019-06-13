package com.epms.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Partyinfo;
import com.epms.Bean.Personalinfo;
import com.epms.Conventer.DateConverter;
import com.epms.Mapper.PartyinfoMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Service.PartyinfoService;
import com.epms.Service.PersonalinfoService;

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
