package com.epms.Service;

import java.util.List;

import com.epms.Bean.Partyinfo;
import com.epms.Bean.Personalinfo;

public interface PartyinfoService
{
	//根据工号查询党信息
    public Partyinfo selectPartyinfoById(int jobId);
}
