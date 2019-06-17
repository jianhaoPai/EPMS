package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.InteralResume;
import com.epms.Bean.Personalinfo;
import com.epms.Mapper.DepartmentMapper;
import com.epms.Mapper.InteralResumeMapper;
import com.epms.Mapper.OccupationMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.ResumeMapper;
import com.epms.Service.InteralResumeService;

@Service(value="innerResumeService")
public class InteralResumeServiceImpl implements InteralResumeService 
{
	@Autowired
	private Personalinfo personalinfo;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	@Autowired
	private ResumeMapper resumeMapper;
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private OccupationMapper occupaionMapper;
	
	@Autowired
	private InteralResumeMapper interalResumeMapper;
	
	//提交简历
	@Override
	public String insertInteralResume(InteralResume interalResume)
	{
		JSONObject result = new JSONObject();
		if(interalResume.getResume().getWorkExperience().length()<=0)
		{
			return null;
		}
		else
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			interalResume.getResume().setSubmitDate(dateFormat.format(new Date()));
			interalResume.getResume().setStatus("待审核");
			
			//根据部门名查询部门id
			int departmentId=departmentMapper.selectIdByName(interalResume.getResume().getToDepartment().getDepartmentName());
			interalResume.getResume().getToDepartment().setDepartmentId(departmentId);
			
			//根据职位名查询职位id
			int occupationId=occupaionMapper.selectIdByName(interalResume.getResume().getToOccupation().getOccupationName());
			interalResume.getResume().getToOccupation().setOccupationId(occupationId);
			
			personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(interalResume.getJobId());
			if(personalinfo.getDepartment().getDepartmentId()==departmentId&&personalinfo.getOccupation().getOccupationId()==occupationId)
			{
				result.put("status", false);
				result.put("message", "提交失败，请勿申请自己的工作岗位！");
			}
			else{
				if(interalResumeMapper.checkIfRepect(interalResume)>0)
				{
					result.put("status", false);
					result.put("message", "提交失败，请勿重复提交！");
				}
				else
				{
					resumeMapper.insertResume(interalResume.getResume());
					interalResume.setResumeId(resumeMapper.selectMaxId());
					interalResumeMapper.insertInteralResume(interalResume);
					result.put("status", true);
					result.put("message", "提交成功！");
				}
			}
		}
		return result.toString();
	}
	
	//通过工号查询提交的简历信息
	@Override
	public List<InteralResume> selectInteralResumeByJobId(String departmentId,String occupationId,String status,int before,int after, int jobId) {
		return interalResumeMapper.selectInteralResumeByJobId(departmentId,occupationId,status,before, after, jobId);
	}

	@Override
	public int countByJobId(String departmentId,String occupationId,String status,int jobId) 
	{
		return interalResumeMapper.countByJobId(departmentId,occupationId,status,jobId);
	}
	
	//部门经理和总经理查询简历信息
	@Override
	public List<InteralResume> selectAllInteralResume(String departmentId,String occupationId,String status,int before, int after,int jobId) 
	{
		personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);		
		int managerDepartmentId=personalinfo.getDepartment().getDepartmentId();
		if(personalinfo.getOccupation().getOccupationId()==2)
		{
			return interalResumeMapper.selectAllInteralResumeToManager(departmentId,occupationId,status,before, after, jobId, managerDepartmentId);
		}
		else if(personalinfo.getOccupation().getOccupationId()==3 || personalinfo.getOccupation().getOccupationId()==4)
		{
			return interalResumeMapper.selectAllInteralResume(departmentId,occupationId,status,before, after);
		}
		return null;
	}
	@Override
	public int countAllInteralResume(String departmentId,String occupationId,String status,int jobId) {
		personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		int managerDepartmentId=personalinfo.getDepartment().getDepartmentId();
		if(personalinfo.getOccupation().getOccupationId()==2)
		{
			return interalResumeMapper.countAllInteralResumeToManager(departmentId,occupationId,status,jobId, managerDepartmentId);
		}
		else if(personalinfo.getOccupation().getOccupationId()==3 || personalinfo.getOccupation().getOccupationId()==4)
		{
			return interalResumeMapper.countAllInteralResume(departmentId,occupationId,status);
		}
		return 0;
	}


}
