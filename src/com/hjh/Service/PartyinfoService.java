package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.Partyinfo;
import com.hjh.Bean.Personalinfo;

public interface PartyinfoService
{
	//根据工号查询党信息
    public Partyinfo selectPartyinfoById(int jobId);
}
