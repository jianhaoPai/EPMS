package com.hjh.ServiceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hjh.Bean.Wage;
import com.hjh.Mapper.WageMapper;
import com.hjh.Service.WageService;
@Service("wageService")
public class WageServiceImpl implements WageService{

	@Autowired
	private WageMapper wageMapper;
	
	//查看本人工资
	public List<Wage> showWageByJobId(int jobId, int before, int after) {
		return wageMapper.showWageByJobId(jobId,before,after);
	}

	//查看个人工资条数
	public int countByJobId(int jobId) {
		return wageMapper.countByJobId(jobId);
	}

}
