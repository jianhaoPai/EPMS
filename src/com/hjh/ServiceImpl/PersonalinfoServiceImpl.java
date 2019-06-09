package com.hjh.ServiceImpl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.Personalinfo;
import com.hjh.Mapper.PersonalinfoMapper;
import com.hjh.Service.PersonalinfoService;
import com.hjh.Utils.CalculateAgeByBirthday;


@Service("personalinfoService")
public class PersonalinfoServiceImpl implements PersonalinfoService
{
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	//根据工号查询个人信息(不查学历，排除还没填学历，然后就返回空)
	@Override
	public Personalinfo selectPersonalByIdNotEducation(int jobId) {
		return personalinfoMapper.selectPersonalByIdNotEducation(jobId);
	}
	
	//根据工号查询个人信息
	public Personalinfo selectPersonalById(int jobId)
	{
		return personalinfoMapper.selectPersonalById(jobId);
	}
	
	//根据姓名查询个人信息(不查学历，排除还没填学历，然后就返回空)
	@Override
	public Personalinfo selectPersonalByNameNotEducation(String name) {
		return personalinfoMapper.selectPersonalByNameNotEducation(name);
	}

	//根据姓名查询个人信息
	@Override
	public Personalinfo selectPersonalByName(String name) {
		return personalinfoMapper.selectPersonalByName(name);
	}

	//根据id或姓名查询个人信息
	@Override
	public List<Personalinfo> selectPersonByIdOrName(Personalinfo personal) 
	{
		return personalinfoMapper.selectPersonByIdOrName(personal);
	}

	//根据部门id查询
	@Override
	public List<Personalinfo> selectPersonalByDepartmentId(Personalinfo personal) 
	{
		return personalinfoMapper.selectPersonalByDepartmentId(personal);
	}
	
	//查询全部人员信息
	@Override
	public List<Personalinfo> selectAllPersonal(String occupation_id,String job_id,String name,
			String department_id,int before,int after,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("总经理")||personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return personalinfoMapper.selectAllPersonal(occupation_id, job_id, name, department_id, before, after);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("部门经理"))
		{
			int managerDepartmentId=personalinfo.getDepartment().getDepartmentId();
			return personalinfoMapper.selectAllPersonalToManager(occupation_id, job_id, name, department_id, before, after, managerDepartmentId);
		}
		return null;
	}

	@Override
	public int count(String occupation_id,String job_id,String name,String department_id,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("总经理")||personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return personalinfoMapper.count(occupation_id, job_id, name, department_id);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("部门经理"))
		{
			int managerDepartmentId=personalinfo.getDepartment().getDepartmentId();
			return personalinfoMapper.countToManager(occupation_id, job_id, name, department_id, managerDepartmentId);
		}
		return 0;
	}
	
	//修改个人信息
	@Override
	public String updatePersonal(Personalinfo personal)
	{
		JSONObject result = new JSONObject();
		CalculateAgeByBirthday calculate=new CalculateAgeByBirthday();
		try 
		{
			int age=calculate.getAge(personal.getBirthday());
			personal.setAge(age);
			if(personal.getAddress()==""||personal.getCensus()==""
					||personal.getBirthday()=="" ||personal.getEducation().getEducation()==""
					||personal.getEducation().getInDate()==""||personal.getEducation().getInDate()==""
					||personal.getEducation().getOutDate()==""||personal.getEducation().getSchoolName()==""
					||personal.getEmail()==""||personal.getMarital()==""||personal.getName()==""
					||personal.getPhone()==""||personal.getIdentityCard().length()!=18)
			{
				return null;
			}
			else
			{
				if(personal.getSex().equals("男")||personal.getSex().equals("女"))
				{
					if(personalinfoMapper.updatePersonal(personal)>0)
					{
						result.put("status", true);
						result.put("message", "修改成功！");
					}
					else 
					{
						result.put("status", false);
						result.put("message", "修改失败！");
					}
				}
				else
				{
					return null;
				}
			}
		} catch (Exception e) 
		{
			e.printStackTrace();
			return null;
		}
		return result.toString();
	}

}
