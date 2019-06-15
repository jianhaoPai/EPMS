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
	
	//�ύ����
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
			interalResume.getResume().setStatus("�����");
			
			//��ݲ������ѯ����id
			int departmentId=departmentMapper.selectIdByName(interalResume.getResume().getToDepartment().getDepartmentName());
			interalResume.getResume().getToDepartment().setDepartmentId(departmentId);
			
			//���ְλ���ѯְλid
			int occupationId=occupaionMapper.selectIdByName(interalResume.getResume().getToOccupation().getOccupationName());
			interalResume.getResume().getToOccupation().setOccupationId(occupationId);
			
			if(interalResumeMapper.checkIfRepect(interalResume)>0)
			{
				result.put("status", false);
				result.put("message", "�ύʧ�ܣ������ظ��ύ��");
			}
			else
			{
				resumeMapper.insertResume(interalResume.getResume());
				interalResume.setResumeId(resumeMapper.selectMaxId());
				interalResumeMapper.insertInteralResume(interalResume);
				result.put("status", true);
				result.put("message", "�ύ�ɹ���");
			}
		}
		return result.toString();
	}
	
	//ͨ��Ų�ѯ�ύ�ļ�����Ϣ
	@Override
	public List<InteralResume> selectInteralResumeByJobId(String departmentId,String occupationId,String status,int before,int after, int jobId) {
		return interalResumeMapper.selectInteralResumeByJobId(departmentId,occupationId,status,before, after, jobId);
	}

	@Override
	public int countByJobId(String departmentId,String occupationId,String status,int jobId) 
	{
		return interalResumeMapper.countByJobId(departmentId,occupationId,status,jobId);
	}
	
	//���ž�����ܾ����ѯ������Ϣ
	@Override
	public List<InteralResume> selectAllInteralResume(int before, int after,int jobId) 
	{
		personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);		
		int managerDepartmentId=personalinfo.getDepartment().getDepartmentId();
		if(personalinfo.getOccupation().getOccupationId()==2)
		{
			return interalResumeMapper.selectAllInteralResumeToManager(before, after, jobId, managerDepartmentId);
		}
		else if(personalinfo.getOccupation().getOccupationId()==3 || personalinfo.getOccupation().getOccupationId()==4)
		{
			return interalResumeMapper.selectAllInteralResume(before, after);
		}
		return null;
	}
	
	@Override
	public int countAllInteralResume(int jobId) {
		personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		int managerDepartmentId=personalinfo.getDepartment().getDepartmentId();
		if(personalinfo.getOccupation().getOccupationId()==2)
		{
			return interalResumeMapper.countAllInteralResumeToManager(jobId, managerDepartmentId);
		}
		else if(personalinfo.getOccupation().getOccupationId()==3 || personalinfo.getOccupation().getOccupationId()==4)
		{
			return interalResumeMapper.countAllInteralResume();
		}
		return 0;
	}


}
