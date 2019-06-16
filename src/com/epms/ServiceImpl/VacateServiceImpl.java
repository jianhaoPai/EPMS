package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Personalinfo;
import com.epms.Bean.TotalData;
import com.epms.Bean.Vacate;
import com.epms.Bean.Vacation;
import com.epms.Bean.WorkingCalendar;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.TotalDataMapper;
import com.epms.Mapper.VacateMapper;
import com.epms.Mapper.VacationMapper;
import com.epms.Mapper.WorkingCalendarMapper;
import com.epms.Service.VacateService;
import com.epms.Utils.CalculateDaySum;

@Service("vacateService")
public class VacateServiceImpl implements VacateService
{
	@Autowired
	private Vacation vacation;
	
	@Autowired
	private TotalData totalData;
	
	@Autowired
	private VacateMapper vacateMapper;
	
	@Autowired
	private VacationMapper vacationMapper;
	
	@Autowired
	private TotalDataMapper totalDataMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	@Autowired
	private WorkingCalendarMapper workingCalendarMapper;
	
	//提交请假申请
	@Override
	public String insertVacate(Vacate vacate) 
	{
		JSONObject result = new JSONObject();
		vacate.setState("待审核");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		vacate.setSubmitDate(dateFormat.format(new Date()));

		if(CheckErrorMess(vacate)==null)//检查插入的数据是否存在错误
		{
			// 计算请假天数
			vacate = calculateVacateDay(vacate);
			
			// 提交假期申请表
			vacateMapper.insertVacate(vacate);

			// 根据提交的申请表，修改相应类型的假期剩余信息
			/*if (vacate.getType().equals("年假") || vacate.getType().equals("产假")) 
			{
				Vacation vacation = vacationMapper.selectRemainByVacate(vacate);
				int virtualUse = vacation.getVirtualUse();// 获得此时对应类型的假期虚拟使用天数
				vacation.setJobId(vacate.getJobId());
				vacation.setType(vacate.getType());
				vacation.setVirtualUse(vacate.getDaySum() + virtualUse);
				vacationMapper.updateVirtualUse(vacation);
			}*/
			Vacation vacation = vacationMapper.selectRemainByVacate(vacate);
			int virtualUse = vacation.getVirtualUse();// 获得此时对应类型的假期虚拟使用天数
			vacation.setJobId(vacate.getJobId());
			vacation.setType(vacate.getType());
			vacation.setVirtualUse(vacate.getDaySum() + virtualUse);
			vacationMapper.updateVirtualUse(vacation);
			result.put("status", true);
			result.put("message", "提交成功！");
		} else {
			return CheckErrorMess(vacate);
		}
		return result.toString();
	}
	
	//检查提交的请假是否合理
	public String CheckErrorMess(Vacate vacate)
	{
		int startId=workingCalendarMapper.selectIdByDate(vacate.getStartDate());
		int nowId=workingCalendarMapper.selectIdByDate(vacate.getSubmitDate());
		JSONObject result = new JSONObject();
		
		if(vacate.getType().equals(""))//查询输入的日期是否为未来的日期
		{
			result.put("status", false);
			result.put("message", "提交失败，还未选择请假类型！");
		}
		else if(calculateVacateDay(vacate)==null)//计算请假天数并判断输入的开始时间和结束时间是否合理
		{
			result.put("status", false);
			result.put("message", "提交失败，假期结束日期必须在假期开始日期之后！");
		}
		else if(startId<nowId)//查询输入的日期是否为未来的日期
		{
			result.put("status", false);
			result.put("message", "提交失败，请输入一个未来的请假日期！");
		}
		else if(vacateMapper.selectIfRepeat(vacate)>0)//判断是否重复提交
		{
			result.put("status", false);
			result.put("message", "提交失败，请勿重复提交！");
		}
		else if(vacate.getDiscountDay()==0)//查询请假的日期是否存在非假日天数
		{
			result.put("status", false);
			result.put("message", "提交失败，所提交的请假时间为法定节假日，无需请假！");
		}
		else if(!checkSexIfHavePregnancy(vacate))//当请假为产假时，检查性别是否为男
		{
			result.put("status", false);
			result.put("message", "提交失败，男生没有产假！");
		}
		else if(!checkRemain(vacate))//判断假期是否充足
		{
			result.put("status", false);
			result.put("message", "提交失败，假期不足！");
		}
		else if(!checkVacateDateIfRepeat(vacate))
		{
			result.put("status", false);
			result.put("message", "提交失败，提交的请假日期与已提交(已同意或待审核)的请假申请时间冲突！");
		}
		else
		{
			return null;
		}
		return result.toString();
	}
	
	//查询是否有足够剩余请假天数
	public Boolean checkRemain(Vacate vacate)
	{
		//获得此时对应的假期剩余天数信息(仅年假和产假需查询)
		if(vacate.getType().equals("年假")||vacate.getType().equals("年假"))
		{
			Vacation vacation=vacationMapper.selectRemainByVacate(vacate);
			int virtualUse=vacation.getVirtualUse();
			int remain=vacation.getRemain();
			if((remain-virtualUse)>=vacate.getDaySum())
			{
				return true;
			}
			return false;
		}
		else
		{
			return true;
		}
	}
	
	//通过性别判断是否有产假
	public Boolean checkSexIfHavePregnancy(Vacate vacate)
	{
		if(vacate.getType().equals("产假"))
		{
			Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(vacate.getJobId());
			if(personalinfo.getSex().equals("男"))
			{ return false; }
			else {
				return true;}
		} else{
			return true;
		}
	}
	
	//计算请假天数和计算扣工资的天数
	public Vacate calculateVacateDay(Vacate vacate)
	{
		//计算请假天数(包括其中包括的法定节假日天数)
		CalculateDaySum cds=new CalculateDaySum();
		vacate.setDaySum(cds.calculate(vacate.getStartDate(), vacate.getFinishDate()));
		if(vacate.getDaySum()<=0)
		{ return null; }
		else{
			//计算实际扣工资的天数(即减去法定放假天数)
			int startId=workingCalendarMapper.selectIdByDate(vacate.getStartDate());
			int finishId=workingCalendarMapper.selectIdByDate(vacate.getFinishDate());
			int notWorkDay=workingCalendarMapper.selectNotWorkBetweenDate(startId,finishId);
			vacate.setDiscountDay(vacate.getDaySum()-notWorkDay);
			return vacate;
		}
	}
	
	//计算提交的请假日期是否在之前已经请过假了，避免请假时间冲突
	public boolean checkVacateDateIfRepeat(Vacate vacate)
	{
		//先计算出在数据库里，此员工已通过的请假时间
		List<Vacate> vacateList=vacateMapper.selectVacateStartAndFinishByJobId(vacate.getJobId());
		int vacateStartId=workingCalendarMapper.selectIdByDate(vacate.getStartDate());
		int vacateFinishId=workingCalendarMapper.selectIdByDate(vacate.getFinishDate());
		
		List dateIdlist = new ArrayList();
		for(int i=0;i<vacateList.size();i++)
		{
			int startId=workingCalendarMapper.selectIdByDate(vacateList.get(i).getStartDate());
			int finishId=workingCalendarMapper.selectIdByDate(vacateList.get(i).getFinishDate());
			List<WorkingCalendar> intList=workingCalendarMapper.selectIdBetweenDate(startId, finishId);
			for(int j=0;j<intList.size();j++)
			{
				dateIdlist.add(intList.get(j).getId());
			}
		}
		for(int i=0;i<dateIdlist.size();i++)
		{
			if(Integer.parseInt(dateIdlist.get(i).toString())==vacateStartId
					||Integer.parseInt(dateIdlist.get(i).toString())==vacateFinishId)
			{
				return false;
			}
		}
		return true;
	}
	
	//查询根据工号查询全部请假信息
	@Override
	public List<Vacate> selectAllVacateApplyByJobId(String state,String type,int before, int after, int jobId) {
		return vacateMapper.selectAllVacateApplyByJobId(state, type, before, after, jobId);
	}

	@Override
	public int count(String state,String type,int jobId) {
		return vacateMapper.count(state, type, jobId);
	}

	//查询全部直接下级提交的请假申请事项(上级查询下属的)
	@Override
	public List<Vacate> selectAllVacateApply(String department_id,String state,String type,
			int before, int after, int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("部门经理"))
		{
			return vacateMapper.selectVacateToManager(department_id, state, type, before, after, jobId);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("总经理"))
		{
			return vacateMapper.selectVacateToTotalManager(department_id, state, type, before, after);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return vacateMapper.selectVacateToBoard(department_id, state, type, before, after);
		}
		return null;
	}

	@Override
	public int countToLeader(String department_id,String state,String type,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("部门经理"))
		{
			return vacateMapper.countToManager(department_id, state, type, jobId);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("总经理"))
		{
			return vacateMapper.countToTotalManager(department_id, state, type);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return vacateMapper.countToBoard(department_id, state, type);
		}
		return 0;
	}

	//审批请假事项
	@Override
	public String updateVacate(Vacate vacate) 
	{
		// 先检查是否已经审批过，若已审批过,则无法重新审批
		if (vacateMapper.selectVacateById(vacate.getId()).getApprovalDate() == null) 
		{
			if (vacateMapper.selectVacateById(vacate.getId()).getType().equals("年假") 
					|| vacateMapper.selectVacateById(vacate.getId()).getType().equals("年假")) 
			{
				//根据审批状态，修改相应类型的假期剩余信息
				vacate=vacateMapper.selectVacateById(vacate.getId());
				Vacation vacation=vacationMapper.selectRemainByVacate(vacate);
				int virtualUse=vacation.getVirtualUse();//获得此时对应类型的假期虚拟使用天数
				vacation.setJobId(vacate.getJobId());
				vacation.setType(vacate.getType());
				
				if(vacate.getState().equals("不同意"))
				{	
					vacation.setVirtualUse(virtualUse-vacate.getDaySum());
					vacationMapper.updateVirtualUse(vacation);
				}
				else
				{
					vacation.setVirtualUse(virtualUse-vacate.getDaySum());
					vacation.setRemain(vacation.getRemain()-vacate.getDaySum());
					vacation.setAlreadyUse(vacation.getAlreadyUse()+vacate.getDaySum());
					vacationMapper.updateVirtualAll(vacation);
				}
			}
			else
			{
				//根据审批状态，修改相应类型的假期剩余信息
				vacate=vacateMapper.selectVacateById(vacate.getId());
				Vacation vacation=vacationMapper.selectRemainByVacate(vacate);
				int virtualUse=vacation.getVirtualUse();//获得此时对应类型的假期虚拟使用天数
				vacation.setJobId(vacate.getJobId());
				vacation.setType(vacate.getType());
				
				if(vacate.getState().equals("不同意"))
				{	
					vacation.setVirtualUse(virtualUse-vacate.getDaySum());
					vacationMapper.updateVirtualUse(vacation);
				}
				else
				{
					vacation.setVirtualUse(virtualUse-vacate.getDaySum());
					vacation.setAlreadyUse(vacation.getAlreadyUse()+vacate.getDaySum());
					vacationMapper.updateVirtualExceptRemain(vacation);
				}
			}
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			vacate.setApprovalDate(dateFormat.format(new Date()));
			vacateMapper.updateVacate(vacate);//修改审批状态
			return "true";
		} else {
			return "false";
		}
	}
	
	//根据工号查询已同意的请假申请信息
	@Override
	public List<Vacate> selectPassVacateByJobId(int before, int after, int jobId) 
	{
		return vacateMapper.selectPassVacateByJobId(before, after, jobId);
	}

	@Override
	public int countPassVacateByJobId(int jobId) 
	{
		return vacateMapper.countPassVacateByJobId(jobId);
	}
	
	//销假处理
	@Override
	public String cancelVacateApply(Vacate vacate) 
	{
		JSONObject result =new JSONObject();
		vacate=vacateMapper.selectVacateById(vacate.getId());
		// 先检查是否已经销假过，若已销假过,则无法重新销假
		if (vacate.getCancalDate()== null) 
		{
			if(!vacate.getState().equals("同意"))
			{
				result.put("status", false);
				result.put("message", "销假失败，此项申请还未同意！");
			}
			else
			{
				CalculateDaySum cds=new CalculateDaySum();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String today=dateFormat.format(new Date());
				//计算是否可以销假(必须在假期结束日期之后才可销假）
				if(cds.calculate(vacate.getFinishDate(),today)>=0)
				{
					vacate.setCancalDate(today);
					vacateMapper.cancelVacateApply(vacate);// 修改审批状态
					result.put("status", true);
					result.put("message", "销假成功！");
				}
				else
				{
					result.put("status", false);
					result.put("message", "销假失败，未到可销假时间！");
				}
			}
		} 
		else 
		{
			result.put("status", false);
			result.put("message", "销假失败，请勿重复销假！");
		}
		return result.toString();
	}
	
	//查询全部人员当月的请假天数
	@Override
	public List<TotalData> selectAllVacateSum(String year,String month) 
	{
		//先删除表中全部数据
		totalDataMapper.deleteAll();
		
		//1、查出请假开始时间和请假结束时间都是本月的请假天数
		List<Vacate> vacateList=vacateMapper.countVacateSumBySameDate(year,month);
		
		for(int i=0;i<vacateList.size();i++)//将都是本月的请假天数插入totalData表中
		{
			totalDataMapper.insertTotalDataByTotalVacate(vacateList.get(i).getJobId(), vacateList.get(i).getDaySum());
		}
		
		//当初当月最后一天的日期，和查出当月第一天的日期，便于计算跨月份请假申请的对应的当月请假天数
		String firstDay=workingCalendarMapper.selectMonthFirstDate(year,month);
		int firstId=workingCalendarMapper.selectIdByDate(firstDay);
		String lastDay=workingCalendarMapper.selectMonthLastDate(year,month);
		int lastId=workingCalendarMapper.selectIdByDate(lastDay);
		
		//2、查出不是相同月份的请假天数(请假开始时间为本月,但结束时间不为本月)
		vacateList=vacateMapper.countVacateSumByStartIsMonth(year,month);
		if(!vacateList.isEmpty())//判断是否存在此种情况的请假申请
		{
			for(int i=0;i<vacateList.size();i++)
			{
				//获得此项申请的开始时间，用其与最后一天的期间查询之中有几个工作日，为其的请假天数
				totalData=totalDataMapper.selectByJobId(vacateList.get(i).getJobId());
				int startId=workingCalendarMapper.selectIdByDate(vacateList.get(i).getStartDate());
				int day=workingCalendarMapper.selectWorkBetweenDate(startId, lastId);
				//判断在totalData表中是否已存有数据，若无则使用insert语句，所有则使用update语句
				if(totalData==null)
				{
					totalDataMapper.insertTotalDataByTotalVacate(vacateList.get(i).getJobId(),day);
				}
				else
				{
					totalDataMapper.updateTotalDataByTotalVacate(vacateList.get(i).getJobId(),
					        totalData.getTotalVacate()+day);
				}
			}
		}
		
		//3、查出不是相同月份的请假天数(请假结束时间为本月,但开始时间不为本月)
		vacateList=vacateMapper.countVacateSumByFinishIsMonth(year,month);
		if(!vacateList.isEmpty())
		{
			for(int i=0;i<vacateList.size();i++)
			{
				//获得此项申请的结束时间，用其与第一天的期间查询之中有几个工作日，为其的请假天数
				totalData=totalDataMapper.selectByJobId(vacateList.get(i).getJobId()); 
				int finishId=workingCalendarMapper.selectIdByDate(vacateList.get(i).getFinishDate());
				int day=workingCalendarMapper.selectWorkBetweenDate(firstId, finishId);
				//判断在totalData表中是否已存有数据，若无则使用insert语句，所有则使用update语句
				if(totalData==null)
				{
					totalDataMapper.insertTotalDataByTotalVacate(vacateList.get(i).getJobId(),day);
				}
				else
				{
					totalDataMapper.updateTotalDataByTotalVacate(vacateList.get(i).getJobId(),
					        totalData.getTotalVacate()+day);
				}
			}
		}
		return  totalDataMapper.selectAllDataSum();
	}
}
