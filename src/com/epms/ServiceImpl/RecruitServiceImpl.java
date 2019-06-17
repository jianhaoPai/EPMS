package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Department;
import com.epms.Bean.Occupation;
import com.epms.Bean.Personalinfo;
import com.epms.Bean.Recruit;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.RecruitMapper;
import com.epms.Service.RecruitService;

@Service(value= "recruitService")
public class RecruitServiceImpl implements RecruitService{
	@Autowired
	private RecruitMapper recruitMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	
	//提交招聘信息
	@Override
	public String insertRecruit(Recruit recruit) 
	{
		
		JSONObject result = new JSONObject();
		if(recruit.getDepartment().getDepartmentId()==0)
		{
			result.put("status", false);
			result.put("message", "提交失败，还未选择部门！");
		}
		else if(recruit.getOccupation().getOccupationId()==0)
		{
			result.put("status", false);
			result.put("message", "提交失败，还未选择职位！");
		}
		else
		{
			Personalinfo writerPersonal=personalinfoMapper.selectPersonalByIdNotEducation(recruit.getWriteId());
			if(writerPersonal.getOccupation().getOccupationName().equals("部门经理"))
			{
				if(writerPersonal.getDepartment().getDepartmentId()!=recruit.getDepartment().getDepartmentId())
				{
					result.put("status", false);
					result.put("message", "提交失败，无权限发布所选部门的招聘信息！");
				}
				else if(recruit.getOccupation().getOccupationId()!=1)
				{
					result.put("status", false);
					result.put("message", "提交失败，无权限发布所选职位的招聘信息！");
				}
				else
				{
					return detailinsertRecruit(recruit);
				}
			}
			else
			{
				return detailinsertRecruit(recruit);
			}
		}
		return result.toString();
	}
	
	public String detailinsertRecruit(Recruit recruit)
	{
		JSONObject result = new JSONObject();
		recruit.setStatus("未审核");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		recruit.setSubmitDate(dateFormat.format(new Date()));
		if(recruitMapper.checkIfRepect(recruit)>0)
		{
			result.put("status", false);
			result.put("message", "提交失败，请勿重复提交！");
		}
		else
		{
			recruitMapper.insertRecruit(recruit);
			result.put("status", true);
			result.put("message", "提交成功！");
		}
		return result.toString();
	}
	
	//上级查询直接下级提交的招聘计划
	@Override
	public List<Recruit> selectAllRecruit(String occupationId,String departmentId,String status,int before, int after, int jobId) 
	{
		Personalinfo personal=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personal.getOccupation().getOccupationName().equals("总经理"))
		{
			return  recruitMapper.selectRecruitToTotalManager(occupationId,departmentId,status,before, after);
		}
		else if(personal.getOccupation().getOccupationName().equals("董事"))
		{
			return  recruitMapper.selectRecruitToBoard(occupationId,departmentId,status,before, after);
		}
		return null;
	}
	
	@Override
	public int countAllRecruit(String occupationId,String departmentId,String status,int jobId) 
	{
		Personalinfo personal=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personal.getOccupation().getOccupationName().equals("总经理"))
		{
			return  recruitMapper.countToTotalManager(occupationId,departmentId,status);
		}
		else if(personal.getOccupation().getOccupationName().equals("董事"))
		{
			return  recruitMapper.countToBoard(occupationId,departmentId,status);
		}
		return 0;
	}
	
	//审核招聘计划
	@Override
	public String updateRecruitStatus(Recruit recruit) 
	{
		if(recruitMapper.selectRecruitById(recruit.getId()).getApprovalDate()==null)
		{
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 recruit.setApprovalDate(dateFormat.format(new Date()));
			 recruitMapper.updateRecruitStatus(recruit);
			 return "true";
		}
		else
		{
			return "false";
		}
	}
	
	//下级查询自己提交给上级的全部招聘信息
	@Override
	public List<Recruit> selectAllRecruitByWriteId(String departmentId,String occupationId,String status,int before, int after,int writeId) 
	{
		return recruitMapper.selectAllRecruitByWriteId(departmentId,occupationId,status,before, after, writeId);
	}

	@Override
	public int countSelectAllRecruitByWriteId(String departmentId,String occupationId,String status,int writeId) 
	{
		return recruitMapper.countSelectAllRecruitByWriteId(departmentId,occupationId,status,writeId);
	}
	
	//员工查询招聘计划
	@Override
	public List<Recruit> selectAllRecruitToEmployee(String occupationId,String departmentId,int before, int after) 
	{
		return recruitMapper.selectAllRecruitToEmployee(occupationId,departmentId,before, after);
	}

	@Override
	public int countSelectAllRecruitToEmployee(String occupationId,String departmentId) 
	{
		return recruitMapper.countSelectAllRecruitToEmployee(occupationId,departmentId);
	}
	

}
