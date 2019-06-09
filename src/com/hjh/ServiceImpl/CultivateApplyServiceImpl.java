package com.hjh.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.CultivateApply;
import com.hjh.Bean.CultivateRecord;
import com.hjh.Bean.Department;
import com.hjh.Bean.Personalinfo;
import com.hjh.Mapper.CultivateApplyMapper;
import com.hjh.Mapper.PersonalinfoMapper;
import com.hjh.Service.CultivateApplyService;

@Service(value="cultivateApplyService")
public class CultivateApplyServiceImpl implements CultivateApplyService{
	@Autowired
	private CultivateApplyMapper cultivateApplyMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
    

}
