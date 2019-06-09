package com.hjh.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.Vacate;
import com.hjh.Bean.Vacation;
import com.hjh.Mapper.VacationMapper;
import com.hjh.Service.VacationService;

@Service("vacationService")
public class VacationServiceImpl implements VacationService
{
	@Autowired
	private VacationMapper vacationMapper;
	
	@Autowired
	private Vacation vacation;

	//查询全部假期剩余
	@Override
	public List<Vacation> selectVacationRemain(String type,int before, int after, int jobId) 
	{
		return vacationMapper.selectAllRemainById(type, before, after, jobId);
	}

	@Override
	public int count(String type,int jobId) 
	{
		return vacationMapper.count(type, jobId);
	}	
	

	//根据id和type查询假期剩余
	@Override
	public Vacation selectRemainByVacate(Vacate vacate) 
	{
		return vacationMapper.selectRemainByVacate(vacate);
	}
	
	//修改假期
	/*@Override
	public int updateVacation(Vacate vacate) 
	{
		int preRemain=selectRemainByIdAndType(vacate);
		vacation.setJobId(vacate.getJobId());
		vacation.setType(vacate.getType());
		vacation.setRemain(preRemain-vacate.getDaySum());
		return vacationMapper.updateVacation(vacation);
	}*/

	@Override
	public Vacation selectRemainByVacation(Vacation vacation) 
	{
		return vacationMapper.selectRemainByVacation(vacation);
	}
	
}
