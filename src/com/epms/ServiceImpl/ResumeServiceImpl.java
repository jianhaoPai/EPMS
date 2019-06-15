package com.epms.ServiceImpl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.ExternalResume;
import com.epms.Bean.InteralResume;
import com.epms.Bean.Resume;
import com.epms.Mapper.ExternalResumeMapper;
import com.epms.Mapper.InteralResumeMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.ResumeMapper;
import com.epms.Service.ExternalResumeService;
import com.epms.Service.InteralResumeService;
import com.epms.Service.ResumeService;

@Service(value= "resumeService")
public class ResumeServiceImpl implements ResumeService
{
	@Autowired
	private ResumeMapper resumeMapper;
	
	@Autowired
	private InteralResumeService interalResumeService;
	
	@Autowired
	private ExternalResumeService externalResumeService;

	@Override
	public List<Object> selectAllResumeByJobId(int before, int after, int jobId) 
	{
		List<ExternalResume> externalResumelist = externalResumeService.selectAllExternalResume(before, after, jobId);
		List<InteralResume> interalResumeList = interalResumeService.selectAllInteralResume(before, after, jobId);

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
	public int countByJobId(int jobId) 
	{
		int count = externalResumeService.countSelectAllExternalResume(jobId)
				+ interalResumeService.countAllInteralResume(jobId);
		return count;
	}

	@Override
	public String updateAllExternalResume(Resume resume) {
		if(resumeMapper.selectResumeById(resume.getId()).getApprovalDate()==null)
		{
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 resume.setApprovalDate(dateFormat.format(new Date()));
			 resumeMapper.updateAllExternalResume(resume);
			 return "true";
		}
		else
		{
			return "false";
		}
	}

}
