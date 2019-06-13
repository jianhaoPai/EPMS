package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.Partyinfo;
import com.epms.Bean.Personalinfo;
import com.epms.Bean.User;

@Repository
public interface PartyinfoMapper 
{
	//根据工号查询党信息
	Partyinfo selectPartyinfoById(int jobId);
}
