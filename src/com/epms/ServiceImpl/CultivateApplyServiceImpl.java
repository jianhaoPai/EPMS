package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.CultivateApply;
import com.epms.Bean.CultivateRecord;
import com.epms.Bean.Department;
import com.epms.Bean.Personalinfo;
import com.epms.Mapper.CultivateApplyMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Service.CultivateApplyService;

@Service(value="cultivateApplyService")
public class CultivateApplyServiceImpl implements CultivateApplyService{
	@Autowired
	private CultivateApplyMapper cultivateApplyMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
    

}
