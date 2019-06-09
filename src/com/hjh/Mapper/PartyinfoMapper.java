package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Partyinfo;
import com.hjh.Bean.Personalinfo;
import com.hjh.Bean.User;

@Repository
public interface PartyinfoMapper 
{
	//根据工号查询党信息
	Partyinfo selectPartyinfoById(int jobId);
}
