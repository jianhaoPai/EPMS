package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Apply;
import com.epms.Bean.Applytype;
import com.epms.Bean.OccupationAdjust;
import com.epms.Bean.Personalinfo;
import com.epms.Mapper.ApplyMapper;
import com.epms.Mapper.OccupationAdjustMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Service.OccupationAdjustService;

@Service("occupationAdjustService")
public class OccupationAdjustServiceImpl implements OccupationAdjustService
{
	@Autowired
	private Apply apply;
	
	@Autowired
	private Applytype applytype;
	
	@Autowired
	private ApplyMapper applyMapper;
	
	@Autowired
	private OccupationAdjustMapper occupationAdjustMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;
	
	//提交薪资调动申请表
	@Override
	public String insertOccupationAdjust(OccupationAdjust occupationAdjust,String passiveName) 
	{
		JSONObject result = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		occupationAdjust.getApply().setSubmitDate(dateFormat.format(new Date()));
		occupationAdjust.getApply().setState("待审核");
		
		if(!checkErrorMess(occupationAdjust,passiveName).equals(""))//审核是否出现错误输入信息
		{
			return checkErrorMess(occupationAdjust,passiveName);
		}
		else
		{
			if(checkIdentity(occupationAdjust.getApply()))//当返回true时，说明是部门经理，需要调用checkIfValidInsert，来审核是否是部门经理管理的部门职员
			{
				if(checkIfValidInsert(occupationAdjust,passiveName))//当返回true，说明在负责范围内，可进行提交
				{
					detailinsertSalaryAdjust(occupationAdjust,passiveName);
					result.put("status", true);
					result.put("message", "提交成功！");
				} 
				else //当返回false，说明不在负责范围内，不可进行提交
				{ 
					result.put("status", false);
				    result.put("message", "提交失败，填写的姓名暂无权限为其提交申请表！");
				}
			}
			else //当返回false，说明是总经理，直接调用mapper实现功能即可
			{
				detailinsertSalaryAdjust(occupationAdjust,passiveName);
				result.put("status", true);
				result.put("message", "提交成功！");
			}
		}
		return result.toString();
	}
	
	public String checkErrorMess(OccupationAdjust occupationAdjust,String passiveName)
	{
		JSONObject result = new JSONObject();
		if(occupationAdjust.getApply().getReason().length()<=0||passiveName.length()<=0)//审核输入的信息是否完整
		{
			return null;
		}
		else if(personalinfoMapper.selectPersonalByNameNotEducation(passiveName)==null)//是否输入了错误员工姓名
		{
			result.put("status", false);
			result.put("message", "提交失败，暂无此员工！");
			return result.toString();
		}
		else //是否为自己提交了申请表
		{
			Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
			occupationAdjust.getApply().setPassiveId(personalinfoPassiver.getJobId());
			if((occupationAdjust.getApply().getWriteId()==occupationAdjust.getApply().getPassiveId()))
			{
				result.put("status", false);
				result.put("message", "提交失败，请勿为自己填写申请表！");
				return result.toString();
			}
			else if(occupationAdjust.getToDepartmentId()==personalinfoPassiver.getDepartment().getDepartmentId()
					&&occupationAdjust.getToOccupationId()==personalinfoPassiver.getOccupation().getOccupationId())
			{
				result.put("status", false);
				result.put("message", "提交失败，调往的岗位并无变动！");
				return result.toString();
			}
			else
			{ 
				if(occupationAdjustMapper.selectIfRepeat(occupationAdjust)>0)//是否重复提交
				{
					result.put("status", false);
					result.put("message", "提交失败，请勿重复提交！");
					return result.toString();
				}
				else
				{
					return ""; 
				}
			}
		}
	}
	
	public void detailinsertSalaryAdjust(OccupationAdjust occupationAdjust,String passiveName)
	{
		//先插入apply表，注意此时用来设置申请表类型！！！！
		Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
		occupationAdjust.getApply().setPassiveId(personalinfoPassiver.getJobId());
		occupationAdjust.getApply().setApplytype(applytype);
		occupationAdjust.getApply().getApplytype().setApplytypeId(5);
		applyMapper.insertApply(occupationAdjust.getApply());
			
		//再插入occupationAdjust表
		occupationAdjust.setApplyId(applyMapper.selectApplyMaxId());
		occupationAdjust.setPreDepartmentId(personalinfoPassiver.getDepartment().getDepartmentId());
		occupationAdjust.setPreOccupationId(personalinfoPassiver.getOccupation().getOccupationId());
		occupationAdjust.setType(checkType(occupationAdjust));
		
		if(occupationAdjustMapper.insertOccupationAdjust(occupationAdjust)<=0)//当职务调动信息插入失败时，应将刚刚apply插入的记录删除
		{
			applyMapper.delectApplyMaxId();
		}
	}
	
	//当填写申请事项的填表人是部门经理时，要审核填写的工号是否是属于他管理的部门的员工
	public boolean checkIfValidInsert(OccupationAdjust occupationAdjust,String passiveName)
	{
		Personalinfo personalinfoWriter=personalinfoMapper.selectPersonalByIdNotEducation(occupationAdjust.getApply().getWriteId());
		Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
		if(personalinfoWriter!=null && personalinfoPassiver!=null)//先检查是否存在此工号人员
		{
			if(personalinfoWriter.getDepartment().getDepartmentId()==personalinfoPassiver.getDepartment().getDepartmentId())
			{
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	
	//检查填表人是部门经理还是总经理（两个人的权限不同）
	public boolean checkIdentity(Apply apply)
	{
		Personalinfo personalinfoWriter=personalinfoMapper.selectPersonalByIdNotEducation(apply.getWriteId());
		if(personalinfoWriter.getOccupation().getOccupationName().equals("总经理"))
		{
			return false;
		} else{
			return true;
		}
	}
	
	public String checkType(OccupationAdjust occupationAdjust)
	{
		if(occupationAdjust.getToOccupationId()<occupationAdjust.getPreOccupationId())
		{
			return "降职";
		}
		else if(occupationAdjust.getToOccupationId()==occupationAdjust.getPreOccupationId())
		{
			return "平调";
		}
		else if(occupationAdjust.getToOccupationId()>occupationAdjust.getPreOccupationId())
		{
			return "升职";
		}
		return "";
	}
	
	//根据填表人id查询所提交的全部申请信息
	@Override
	public List<OccupationAdjust> selectAllOccupationAdjustByWriteId(String pre_departmentid,
		   String state,String type,int before, int after, int jobId) 
	{
		return  occupationAdjustMapper.selectAllOccupationAdjustByWriteId(pre_departmentid, state, type, before, after, jobId);
	}

	@Override
	public int countByWriteId(String pre_departmentid,String state,String type,int jobId) 
	{
		return occupationAdjustMapper.countByWriteId(pre_departmentid, state, type, jobId);
	}
	
	//上级查看下属提交的全部职位调动申请
	@Override
	public List<OccupationAdjust> selectAllSalaryAdjust(String pre_departmentid, String state, String type, int before,
			int after, int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("总经理"))
		{
			return occupationAdjustMapper.selectOccupationAdjustToLeader(pre_departmentid, state, type, before, after, 2);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return occupationAdjustMapper.selectOccupationAdjustToLeader(pre_departmentid, state, type, before, after, 3);
		}
		return null;
	}
	
	@Override
	public int countAllOccupationAdjust(String pre_departmentid, String state,String type, int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("总经理"))
		{
			return occupationAdjustMapper.countToLeader(pre_departmentid, state, type, 2);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return occupationAdjustMapper.countToLeader(pre_departmentid, state, type, 3);
		}
		return 0;
	}
	
	//审批职位调动申请
	@Override
	public String updateOccupationAdjust(OccupationAdjust occupationAdjust,String state) 
	{
		//通过id获得此项申请的信息
		apply=applyMapper.selectApplyById(occupationAdjust.getApplyId());
		occupationAdjust.setApply(apply);
		// 先检查是否已经审批过，若已审批过,则无法重新审批
		if (apply.getApprovalDate() == null) 
		{
			//判断是否同意，若同意则修改调动情况
			if(state.equals("同意"))
			{
				occupationAdjust=occupationAdjustMapper.selectOccupationAdjustByApplyId(occupationAdjust.getApplyId());
				personalinfoMapper.updatePersonalByOccupationAdjust(
						 occupationAdjust.getToOccupationId(), occupationAdjust.getToDepartmentId(),apply.getPassiveId());
			}
			// 更新申请表状态和审批时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			apply.setApprovalDate(dateFormat.format(new Date()));
			apply.setId(occupationAdjust.getApplyId());
			apply.setState(state);
			applyMapper.updateApply(apply);
			return "true";
		} 
		else {
			return "false";
		}
	}

	//根据工号查询相关的职位调动记录
	@Override
	public List<OccupationAdjust> selectAllOccupationAdjustByJobId(String state, String type, int before, int after, int jobId) 
	{
		return occupationAdjustMapper.selectAllOccupationAdjustByJobId(state, type, before, after, jobId);
	}

	@Override
	public int countByJobId(String state, String type, int jobId) 
	{
		return occupationAdjustMapper.countByJobId(state, type, jobId);
	}
	
}
