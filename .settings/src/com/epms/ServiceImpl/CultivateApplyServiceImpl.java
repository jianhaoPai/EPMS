package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.CultivateApply;
import com.epms.Bean.Department;
import com.epms.Bean.Personalinfo;
import com.epms.Mapper.CultivateApplyMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.WorkingCalendarMapper;
import com.epms.Service.CultivateApplyService;
import com.epms.Tools.CalculateDaySum;

@Service(value="cultivateApplyService")
public class CultivateApplyServiceImpl implements CultivateApplyService{
	@Autowired
	private CultivateApplyMapper cultivateApplyMapper;
	
	@Autowired
	private WorkingCalendarMapper workingCalendarMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
    //发布培训计划
	@Override
	public String insertCultivateApply(CultivateApply cultivateApply,int jobId) 
	{
		JSONObject result = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cultivateApply.setSubmitDate(dateFormat.format(new Date()));
		int startId=workingCalendarMapper.selectIdByDate(cultivateApply.getStartDate());
		int nowId=workingCalendarMapper.selectIdByDate(cultivateApply.getSubmitDate());

		if(cultivateApply.getCultivateType().getCultivateId()==0)
		{
			result.put("status", false);
			result.put("message", "提交失败，还未选择培训类型！");
		}
		else if(cultivateApply.getFacePeople().equals(""))
		{
			result.put("status", false);
			result.put("message", "提交失败，还未选择面对人员类型！");
		}
		else if(cultivateApply.getTeacher().getId()==0)
		{
			result.put("status", false);
			result.put("message", "提交失败，还未选择老师！");
		}
		else if(startId<=nowId)//查询输入的日期是否为未来的日期
		{
			result.put("status", false);
			result.put("message", "提交失败，请输入一个未来的培训日期！");
		}
		else if(calculateCultivateDay(cultivateApply)<=0) //计算请假天数并判断输入的开始时间和结束时间是否合理
		{
			result.put("status", false);
			result.put("message", "提交失败，培训结束日期必须在培训开始日期之后！");
		}
		else
		{
			Personalinfo writerPersonal=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
			if(writerPersonal.getOccupation().getOccupationName().equals("部门经理"))
			{
				if(cultivateApply.getFacePeople().equals("针对全公司实习员工")||cultivateApply.getFacePeople().equals("全公司人员"))
				{
					result.put("status", false);
					result.put("message", "提交失败，无权限发布所选择的面对人员范围！");
				}
				else
				{
					return detailinsertCultivateApply(cultivateApply, writerPersonal);
				}
			}
			else
			{
				return detailinsertCultivateApply(cultivateApply, writerPersonal);
			}
		}
		return result.toString();
	}
	
	public String detailinsertCultivateApply(CultivateApply cultivateApply,Personalinfo writerPersonal) 
	{
		JSONObject result = new JSONObject();
		Department department=new Department(writerPersonal.getDepartment().getDepartmentId());
		cultivateApply.setDepartment(department);	
		cultivateApply.setStatus("未审核");
		if(cultivateApplyMapper.checkIfRepect(cultivateApply)>0)
		{
			result.put("status", false);
			result.put("message", "提交失败，请勿重复提交！");
		}
		else
		{
			cultivateApplyMapper.insertCultivateApply(cultivateApply);
			result.put("status", true);
			result.put("message", "提交成功！");
		}
		return result.toString();
	}
	
	// 计算培训天数
	public int calculateCultivateDay(CultivateApply cultivateApply) 
	{
		// 计算请假天数(包括其中包括的法定节假日天数)
		CalculateDaySum cds = new CalculateDaySum();
		int count =cds.calculate(cultivateApply.getStartDate(),
				cultivateApply.getFinishDate());
		return count;
	}

	// 下级查询自己提交给上级的培训计划
	@Override
	public List<CultivateApply> selectAllCultivateApplyByWriteId(String cultivateId,String status,int before,
			int after, int writeId) {
		return cultivateApplyMapper.selectAllCultivateApplyByWriteId(cultivateId,status,before,
				after, writeId);
	}

	@Override
	public int countSelectAllCultivateApplyByWriteId(String cultivateId,String status,int writeId) {
		return cultivateApplyMapper
				.countSelectAllCultivateApplyByWriteId(cultivateId,status,writeId);
	}

	// 上级查询直接下级提交的招聘计划
	@Override
	public List<CultivateApply> selectAllCultivateApply(int before, int after,
			int jobId) {
		Personalinfo personal = personalinfoMapper
				.selectPersonalByIdNotEducation(jobId);
		if (personal.getOccupation().getOccupationName().equals("总经理")) {
			return cultivateApplyMapper.selectCultivateApplyToTotalManager(
					before, after);
		} else if (personal.getOccupation().getOccupationName().equals("董事")) {
			return cultivateApplyMapper.selectCultivateApplyToBoard(before,
					after);
		}
		return null;
	}

	@Override
	public int countAllCultivateApply(int jobId) {
		Personalinfo personal = personalinfoMapper
				.selectPersonalByIdNotEducation(jobId);
		if (personal.getOccupation().getOccupationName().equals("总经理")) {
			return cultivateApplyMapper.countToTotalManager();
		} else if (personal.getOccupation().getOccupationName().equals("董事")) {
			return cultivateApplyMapper.countToBoard();
		}
		return 0;
	}

	// 审核培训计划
	@Override
	public String updateCultivateApplyStatus(CultivateApply cultivateApply) {
		if (cultivateApplyMapper.selectCultivateApplyById(
				cultivateApply.getId()).getApprovalDate() == null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			cultivateApply.setApprovalDate(dateFormat.format(new Date()));
			cultivateApplyMapper.updateCultivateApplyStatus(cultivateApply);
			return "true";
		} else {
			return "false";
		}
	}

	// 员工查询培训计划
	@Override
	public List<CultivateApply> selectAllCultivateApplyToEmployee(int before,int after)
	{
		return cultivateApplyMapper.selectAllCultivateApplyToEmployee(before,after);
	}

	@Override
	public int countSelectAllCultivateApplyToEmployee() 
	{
		return cultivateApplyMapper.countSelectAllCultivateApplyToEmployee();
	}

}
