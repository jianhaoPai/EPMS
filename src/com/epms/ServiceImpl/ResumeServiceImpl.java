package com.epms.ServiceImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.CultivateApply;
import com.epms.Bean.ExternalResume;
import com.epms.Bean.InteralResume;
import com.epms.Bean.Personalinfo;
import com.epms.Bean.Resume;
import com.epms.Mapper.ExternalResumeMapper;
import com.epms.Mapper.InteralResumeMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.ResumeMapper;
import com.epms.Service.ExternalResumeService;
import com.epms.Service.InteralResumeService;
import com.epms.Service.ResumeService;
import com.epms.Tools.CalculateDaySum;

@Service(value= "resumeService")
public class ResumeServiceImpl implements ResumeService
{
	@Autowired
	private Personalinfo personalinfo;
	
	@Autowired
	private Resume resume;
	
	@Autowired
	private ResumeMapper resumeMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	@Autowired
	private InteralResumeService interalResumeService;
	
	@Autowired
	private ExternalResumeService externalResumeService;

	@Override
	public List<Object> selectAllResumeByJobId(String departmentId,String occupationId,String status,int before, int after, int jobId) 
	{
		List<ExternalResume> externalResumelist = externalResumeService.selectAllExternalResume(departmentId,occupationId,status,before, after, jobId);
		List<InteralResume> interalResumeList = interalResumeService.selectAllInteralResume(departmentId,occupationId,status,before, after, jobId);

		List list = new ArrayList();
		Iterator it1 = interalResumeList.iterator();
		while (it1.hasNext()) {
			list.add(it1.next());
		}
		Iterator it2 = externalResumelist.iterator();
		while (it2.hasNext()) {
			list.add(it2.next());
		}
		return list;
		
	}

	@Override
	public int countByJobId(String departmentId,String occupationId,String status,int jobId) 
	{
		int count = externalResumeService.countSelectAllExternalResume(departmentId,occupationId,status,jobId)
				+ interalResumeService.countAllInteralResume(departmentId,occupationId,status,jobId);
		return count;
	}

	@Override
	public String updateAllResume(Resume resume,String interviewName) 
	{
		JSONObject result = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		resume.setApprovalDate(dateFormat.format(new Date()));
		if(!resumeMapper.selectResumeById(resume.getId()).getStatus().equals("待审核"))
		{
			result.put("status", false);
			result.put("message", "审核失败，请勿重复审核！");
		}
		else{
			if(resume.getStatus().equals("通过"))
			{
				if(interviewName.length()<=0)
				{
					result.put("status", false);
					result.put("message", "审核失败，未填写面试人员姓名！");
				}
				else if(resume.getInterviewDate().length()<=0)
				{
					result.put("status", false);
					result.put("message", "审核失败，未填写面试时间！");
				}
				else if(checkIfFuture(resume)<=0) //判断输入的开始时间和结束时间是否合理
				{
					result.put("status", false);
					result.put("message", "审核失败，面试时间必须是未来日期！");
				}
				else if(personalinfoMapper.selectPersonalByNameNotEducation(interviewName)==null)
				{
					result.put("status", false);
					result.put("message", "审核失败，公司暂无此人！");
				}
				else 
				{
					Resume resume2=resumeMapper.selectResumeById(resume.getId());
					personalinfo=personalinfoMapper.selectPersonalByNameNotEducation(interviewName);
					if(resume2.getToDepartment().getDepartmentId()!=personalinfo.getDepartment().getDepartmentId())
					{
						result.put("status", false);
						result.put("message", "审核失败，所填写的面试人无权限面试此申请！");
					}
					else
					{
						resume.setInterviewId(personalinfo.getJobId());
						resumeMapper.updateAllResume(resume);
						result.put("status", true);
						result.put("message", "审核成功！");
					}
				}
			}
			else
			{
				resumeMapper.updateResumeNotAll(resume);
				result.put("status", true);
				result.put("message", "审核成功！");
			}
		}
		return result.toString();
	}
	
	//判断面试时间是否为未来日期
	public int checkIfFuture(Resume resume) 
	{
		// 计算请假天数(包括其中包括的法定节假日天数)
		CalculateDaySum cds = new CalculateDaySum();
		int count =cds.calculate(resume.getApprovalDate(),resume.getInterviewDate());
		return count;
	}

}
