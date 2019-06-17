package com.epms.ServiceImpl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.CultivateApply;
import com.epms.Bean.CultivateRecord;
import com.epms.Bean.Personalinfo;
import com.epms.Bean.TotalData;
import com.epms.Mapper.CultivateApplyMapper;
import com.epms.Mapper.CultivateRecordMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.TotalDataMapper;
import com.epms.Mapper.WorkingCalendarMapper;
import com.epms.Service.CultivateRecordService;
import com.epms.Tools.CalculateDaySum;

@Service(value = "cultivateRecordService")
public class CultivateRecordServiceImpl implements CultivateRecordService 
{
	@Autowired
	private PersonalinfoMapper peronalinfoMapper;
	
	@Autowired
	private CultivateApply cultivateApply;
	
	@Autowired
	private CultivateApplyMapper cultivateApplyMapper;
	
	@Autowired
	private WorkingCalendarMapper workingCalendarMapper;
	
	@Autowired
	private CultivateRecordMapper cultivateRecordMapper;
	
	@Autowired
	private TotalDataMapper totalDataMapper;
	
	//查询某个月每个员工的培训天数
	@Override
	public List<TotalData> CountCultivate(String year, String month)
	{
		CalculateDaySum ads=new CalculateDaySum();
		int day,startId,finishId,workDay=0;
		
		//首先把totalData表中所有员工读出来
		List<TotalData> totalDataList=totalDataMapper.selectAllDataSum();
		
		//循环查询员工是否参加了培训，根据培训id查出对应的开始时间和结束时间，来计算对应的天数
		for(int i=0;i<totalDataList.size();i++)
		{
			//计算出员工所参加的培训id
			Integer[] ids=cultivateRecordMapper.selectIdByJobId(totalDataList.get(i).getJobId());
			
			if(ids.length>0)
			{
				//根据id查询出对应的所有培训时间(开始和结束),(1)开始时间和结束时间都在相同某月份
				List<CultivateApply> sameList=cultivateApplyMapper.selectTheSameDateInId(ids, year, month);
				if(sameList!=null)
				{
					for(int j=0;j<sameList.size();j++)
					{
						//计算出培训天数,并存入数据库
						day=ads.calculate(sameList.get(j).getStartDate(),sameList.get(j).getFinishDate());
						totalDataMapper.updateTotalDataByCaltivateday(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalCaltivateDay()+day);
						//用已算出的缺勤天数减去，计算出的此培训期间工作日的天数
						startId=workingCalendarMapper.selectIdByDate(sameList.get(j).getStartDate());
						finishId=workingCalendarMapper.selectIdByDate(sameList.get(j).getFinishDate());
						workDay=workingCalendarMapper.selectWorkBetweenDate(startId, finishId);
						totalDataMapper.updateTotalDataByAbsence(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalAbsence()-workDay);
						
					}
				}
				//获取到最新数据
				totalDataList=totalDataMapper.selectAllDataSum();
				
				//(2)开始时间在某月份而和结束时间不在某月份
				List<CultivateApply> sameStartList=cultivateApplyMapper.selectSameStartDateInId(ids, year, month);
				if(sameStartList!=null)
				{
					String lastDay=workingCalendarMapper.selectMonthLastDate(year,month);
					int lastId=workingCalendarMapper.selectIdByDate(lastDay);
					for(int j=0;j<sameStartList.size();j++)
					{
						//计算出培训天数,并存入数据库
						day=ads.calculate(sameStartList.get(j).getStartDate(),lastDay);
						totalDataMapper.updateTotalDataByCaltivateday(totalDataList.get(j).getJobId(),
								totalDataList.get(i).getTotalCaltivateDay()+day);
						//用已算出的缺勤天数减去，计算出的此培训期间工作日的天数
						startId=workingCalendarMapper.selectIdByDate(sameStartList.get(j).getStartDate());
						workDay=workingCalendarMapper.selectWorkBetweenDate(startId, lastId);
						totalDataMapper.updateTotalDataByAbsence(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalAbsence()-workDay);
					}
				}
				totalDataList=totalDataMapper.selectAllDataSum();
				
				//(3)开始时间不在某月份而和结束时间在某月份
				List<CultivateApply> sameFinishList=cultivateApplyMapper.selectSameFinishDateInId(ids, year, month);
				if(sameFinishList!=null)
				{
					String firstDay=workingCalendarMapper.selectMonthFirstDate(year,month);
					int firstId=workingCalendarMapper.selectIdByDate(firstDay);
					for(int j=0;j<sameFinishList.size();j++)
					{
						//计算出培训天数,并存入数据库
						day=ads.calculate(firstDay,sameFinishList.get(j).getFinishDate());
						totalDataMapper.updateTotalDataByCaltivateday(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalCaltivateDay()+day);
						//用已算出的缺勤天数减去，计算出的此培训期间工作日的天数
						finishId=workingCalendarMapper.selectIdByDate(sameFinishList.get(j).getFinishDate());
						workDay=workingCalendarMapper.selectWorkBetweenDate(firstId, finishId);
						totalDataMapper.updateTotalDataByAbsence(totalDataList.get(i).getJobId(),
								totalDataList.get(i).getTotalAbsence()-workDay);
					}
				}
			}
		}
		return totalDataMapper.selectAllDataSum();
	}
	

	//报名培训
		@Override
		public String insertCultivateRecord(int cultivateId,int participatorId) 
		{
			JSONObject result = new JSONObject();
			cultivateApply=cultivateApplyMapper.selectCultivateApplyById(cultivateId);
			if(cultivateApply.getAlreadyPerson()==cultivateApply.getSum())
			{
				result.put("status", false);
				result.put("message", "报名失败，人数已满！");
			}
			else if (cultivateRecordMapper.checkIfRepeat(cultivateId, participatorId)>0)
			{
				result.put("status", false);
				result.put("message", "报名失败，请勿重复报名！");
			}
			else if(checkJoinSum(participatorId,cultivateId)>0)
			{
				result.put("status", false);
				result.put("message", "报名失败，此月份已报名过培训项目！");
			}
			else if(cultivateApply.getTypeId()==1)//内部培训
			{
				return detailInsertCultivateRecord1(cultivateId,participatorId,"通过");
			}
			else //外出培训
			{
				return detailInsertCultivateRecord1(cultivateId,participatorId,"待审核");
			}
			return result.toString();
		}
		
		public String detailInsertCultivateRecord1(int cultivateId,int participatorId,String status)
		{
			JSONObject result = new JSONObject();
			if(cultivateApply.getFacePeople().equals("全公司人员"))
			{
				return detailInsertCultivateRecord(cultivateId,participatorId,status);
			}
			else if(cultivateApply.getFacePeople().equals("针对本部门实习员工"))
			{
				Personalinfo writer=peronalinfoMapper.selectPersonalByIdNotEducation(cultivateApply.getWriteId());
				Personalinfo participator=peronalinfoMapper.selectPersonalByIdNotEducation(participatorId);
				if(writer.getDepartment().getDepartmentId()==participator.getDepartment().getDepartmentId() 
						&& participator.getOccupation().getOccupationName().equals("实习生"))
				{
					return detailInsertCultivateRecord(cultivateId,participatorId,status);
				}
				else
				{
					result.put("status", false);
					result.put("message", "报名失败，此培训仅针对实习生！");
				}
			}
			else if(cultivateApply.getFacePeople().equals("针对全公司实习员工"))
			{
				Personalinfo participator=peronalinfoMapper.selectPersonalByIdNotEducation(participatorId);
				if(participator.getOccupation().getOccupationName().equals("实习生"))
				{
					return detailInsertCultivateRecord(cultivateId,participatorId,status);
				}
				else
				{
					result.put("status", false);
					result.put("message", "报名失败，此培训仅针对实习生！");
				}
			}
			else if(cultivateApply.getFacePeople().equals("本部门人员"))
			{
				Personalinfo writer=peronalinfoMapper.selectPersonalByIdNotEducation(cultivateApply.getWriteId());
				Personalinfo participator=peronalinfoMapper.selectPersonalByIdNotEducation(participatorId);
				if(writer.getDepartment().getDepartmentId()==participator.getDepartment().getDepartmentId())
				{
					return detailInsertCultivateRecord(cultivateId,participatorId,status);
				}
				else
				{
					result.put("status", false);
					result.put("message", "报名失败，你不属于此培训针对的部门！");
				}
			}
			return result.toString();
		}
		
		public String detailInsertCultivateRecord(int cultivateId,int participatorId,String status)
		{
			JSONObject result = new JSONObject();
			cultivateRecordMapper.insertCultivateRecord(cultivateId,participatorId,status);
			if(cultivateApplyMapper.updatealreadyPerson(cultivateApply.getAlreadyPerson()+1,cultivateId)<=0)
			{
				cultivateRecordMapper.deleteMaxId();
				result.put("status", false);
				result.put("message", "报名失败！");
			}
			else
			{
				result.put("status", true);
				result.put("message", "报名成功！");
			}
			return result.toString();
		}
		
		public int checkJoinSum(int participatorId,int cultivateId)
		{
			//先查询出此员工将要报名的培训开始时间
			cultivateApply=cultivateApplyMapper.selectById(cultivateId);
			
			//根据查出的开始时间和工号查询是否开始时间月份已有报名
		    String[] arr = cultivateApply.getStartDate().split("-"); 
		    int count=cultivateRecordMapper.countSumByDate(participatorId, arr[0], arr[1]);
		    return count;
		}
		
		//员工查询自己报名的培训课程
		@Override
		public List<CultivateRecord> selectCultivateRecordByJobId(String cultivateId,String status,int before,int after,int jobId) 
		{
			return cultivateRecordMapper.selectCultivateRecordByJobId(cultivateId,status,before, after,jobId);
		}
		@Override
		public int countSelectCultivateRecordByJobId(String cultivateId,String status,int jobId) 
		{
			return cultivateRecordMapper.countSelectCultivateRecordByJobId(cultivateId,status,jobId);
		}
		
		//上级查询下属所报名的全部培训记录
		@Override
		public List<CultivateRecord> selectAllCultivateRecord(int before, int after,int leaderId) 
		{
			Personalinfo leader=peronalinfoMapper.selectPersonalByIdNotEducation(leaderId);
			if(leader.getOccupation().getOccupationName().equals("部门经理"))
			{
				return cultivateRecordMapper.selectCultivateRecordToManager(before, after, leaderId);
			}
			else if(leader.getOccupation().getOccupationName().equals("总经理")||leader.getOccupation().getOccupationName().equals("董事"))
			{
				return cultivateRecordMapper.selectCultivateRecordToTotalManager(before, after);
			}
			return null;
		}

		@Override
		public int countselectAllCultivateRecord(int leaderId) 
		{
			Personalinfo leader=peronalinfoMapper.selectPersonalByIdNotEducation(leaderId);
			if(leader.getOccupation().getOccupationName().equals("部门经理"))
			{
				return cultivateRecordMapper.countToManager(leaderId);
			}
			else if(leader.getOccupation().getOccupationName().equals("总经理")||leader.getOccupation().getOccupationName().equals("董事"))
			{
				return cultivateRecordMapper.countToTotalManager();
			}
			return 0;
		}
		
		//审核报名培训
		@Override
		public String updateCultivateRecordStatus(int recordId,String recordStatus, String status,int cultivateId) 
		{
			JSONObject result = new JSONObject();
			if(!recordStatus.equals("待审核"))
			{
				result.put("status", false);
				result.put("message", "审核失败，请勿重复审核！");
			}
			else
			{
				int i=cultivateRecordMapper.updateCultivateRecordStatus(recordId, status);
				if(i>0)
				{
					if(status.equals("不通过"))
					{
						cultivateApply=cultivateApplyMapper.selectById(cultivateId);
						System.out.println(cultivateApply);
						cultivateApplyMapper.updatealreadyPerson(cultivateApply.getAlreadyPerson()-1, cultivateId);
					}
					result.put("status", true);
					result.put("message", "审核成功！");
				}
				else
				{
					result.put("status", false);
					result.put("message", "审核失败！");
				}
			}
			return result.toString();
		}
}
