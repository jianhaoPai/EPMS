package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.ExternalResume;
import com.epms.Bean.Personalinfo;
import com.epms.Mapper.DepartmentMapper;
import com.epms.Mapper.ExternalResumeMapper;
import com.epms.Mapper.OccupationMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.ResumeMapper;
import com.epms.Service.ExternalResumeService;

//外部人员简历表
@Service(value="externalResumeService")
public class ExternalResumeServiceImpl implements ExternalResumeService
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
	private ExternalResumeMapper externalResumeMapper;
	
	//提交简历
	@Override
	public String insertExternalResume(ExternalResume externalResume) 
	{	
		JSONObject result = new JSONObject();
		if(externalResume.getResume().getWorkExperience().length()<=0||externalResume.getAddress().length()<=0
				||externalResume.getName().length()<=0
				||externalResume.getAge()<=0||externalResume.getAge()>120)
		{
			return null;
		}
		else if(externalResume.getEducation().equals(""))
		{
			result.put("status", false);
			result.put("message", "提交失败，还未选择学历！");
		}
		else
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			externalResume.getResume().setSubmitDate(dateFormat.format(new Date()));
			externalResume.getResume().setStatus("待审核");
			
			//根据部门名查询部门id
			int departmentId=departmentMapper.selectIdByName(externalResume.getResume().getToDepartment().getDepartmentName());
			externalResume.getResume().getToDepartment().setDepartmentId(departmentId);
			
			//根据职位名查询职位id
			int occupationId=occupaionMapper.selectIdByName(externalResume.getResume().getToOccupation().getOccupationName());
			externalResume.getResume().getToOccupation().setOccupationId(occupationId);
			
			if(externalResumeMapper.checkIfRepect(externalResume)>0)
			{
				result.put("status", false);
				result.put("message", "提交失败，请勿重复提交！");
			}
			else
			{
				resumeMapper.insertResume(externalResume.getResume());
				externalResume.setResumeId(resumeMapper.selectMaxId());
				externalResumeMapper.insertExternalResume(externalResume);
				externalResume=externalResumeMapper.selectMyResume(resumeMapper.selectMaxId());
				result.put("status", true);
				result.put("message", "提交成功！");
			}
		}
		return result.toString();
	}

	//查询外部人员简历
	@Override
	public List<ExternalResume> selectAllExternalResume(int before, int after,int jobId) 
	{
		personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationId()==2)
		{
			return externalResumeMapper.selectAllExternalResumeToManager(before,after,personalinfo.getDepartment().getDepartmentId());
		}
		else if(personalinfo.getOccupation().getOccupationId()==3 || personalinfo.getOccupation().getOccupationId()==4)
		{
			return externalResumeMapper.selectAllExternalResume(before, after);
		}
		return null;
	}

	@Override
	public int countSelectAllExternalResume(int jobId) 
	{
		personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationId()==2)
		{
			return externalResumeMapper.countSelectAllExternalResumeToManager(personalinfo.getDepartment().getDepartmentId());
		}
		else if(personalinfo.getOccupation().getOccupationId()==3 || personalinfo.getOccupation().getOccupationId()==4)
		{
			return externalResumeMapper.countSelectAllExternalResume();
		}
		return 0;

	}

}
