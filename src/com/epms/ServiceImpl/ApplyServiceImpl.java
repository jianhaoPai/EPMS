package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Apply;
import com.epms.Bean.Personalinfo;
import com.epms.Mapper.ApplyMapper;
import com.epms.Mapper.PersonalinfoMapper;
import com.epms.Mapper.UserMapper;
import com.epms.Service.ApplyService;

@Service("applyService")
public class ApplyServiceImpl implements ApplyService
{
	@Autowired
	private ApplyMapper applyMapper;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;

	//提交申请事项信息
	@Override
	public String insertApply(Apply apply,String passiveName) 
	{
		JSONObject result = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		apply.setSubmitDate(dateFormat.format(new Date()));
		apply.setState("待审核");
		if(apply.getReason().length()<=0||passiveName=="")//审核输入的信息是否完整
		{
			return null;
		} 
		else
		{
			if(checkIdentity(apply))//当返回true时，说明是部门经理，需要调用checkIfValidInsert，来审核是否是部门经理管理的部门职员
			{
				if(checkIfValidInsert(apply,passiveName))//当返回true，说明在负责范围内，可进行提交
				{
					Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
					apply.setPassiveId(personalinfoPassiver.getJobId());
					return detailInsertApply(apply,passiveName);
				} else //当返回false，说明不在负责范围内，不可进行提交
					{
					result.put("status", false);
					result.put("message", "提交失败，填写的姓名暂无权限为其提交申请表！");
					}
			}
			else //当返回false，说明是总经理，直接调用mapper实现功能即可
			{
				if(personalinfoMapper.selectPersonalByNameNotEducation(passiveName)!=null)
				{
					Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
					apply.setPassiveId(personalinfoPassiver.getJobId());
					return detailInsertApply(apply,passiveName);
				} else{
					result.put("status", false);
					result.put("message", "提交失败，暂无此员工！");
				}
			}
		}
		return result.toString();
	}
	
	//当填写申请事项的填表人是部门经理时，要审核填写的工号是否是属于他管理的部门的员工
	public boolean checkIfValidInsert(Apply apply,String passiveName)
	{
		Personalinfo personalinfoWriter=personalinfoMapper.selectPersonalByIdNotEducation(apply.getWriteId());
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
	
	//查看是否重复提交
	public boolean checkIfRepeat(Apply apply)
	{
		if(applyMapper.checkIfRepeat(apply)>0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String detailInsertApply(Apply apply,String passiveName)
	{
		JSONObject result = new JSONObject();
		if(checkIfRepeat(apply))
		{
			result.put("status", false);
			result.put("message", "提交失败，请勿重复提交！");
		}
		else if(apply.getPassiveId()==apply.getWriteId())
		{
			result.put("status", false);
			result.put("message", "提交失败，请勿为自己申请事项！");
		}
		else
		{
			Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
			if(apply.getApplytype().getApplytypeId()==2)
			{
				if(personalinfoPassiver.getOccupation().getOccupationId()!=0)
				{
					result.put("status", false);
					result.put("message", "提交失败，被申请人不是实习生，不可为其申请实习生转正事项！");
				}
				else
				{
					applyMapper.insertApply(apply);
					result.put("status", true);
					result.put("message", "提交成功！");
				}
			}
			else
			{
				applyMapper.insertApply(apply);
				result.put("status", true);
				result.put("message", "提交成功！");
			}
		}
		return result.toString();
	}
	//查询全部直接下属提交的申请事项信息
	@Override
	public List<Apply> selectAllApply(String department_id,String state,String applytype_name,
			                          int before, int after, int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("总经理"))
		{
			return applyMapper.selectApplyToTotalManager(department_id, state, applytype_name, before, after);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return applyMapper.selectApplyToBoard(department_id, state, applytype_name, before, after);
		}
		return null;
	}

	@Override
	public int count(String department_id,String state,String applytype_name,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("总经理"))
		{
			return applyMapper.countToTotalManager(department_id, state, applytype_name);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return applyMapper.countToBoard(department_id, state, applytype_name);
		}
		return 0;
	}
	
	//审批申请事项
	@Override
	public String updateApply(Apply apply) 
	{
		String state=apply.getState();
		apply=applyMapper.selectApplyById(apply.getId());
		apply.setState(state);
		//先检查是否已经审批过，若已审批过,则无法重新审批
		if(apply.getApprovalDate()==null)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			apply.setApprovalDate(dateFormat.format(new Date()));
			if(apply.getApplytype().getApplytypeName().equals("开除员工") && apply.getState().equals("同意"))
			{
				if(userMapper.updateState(apply.getPassiveId(), "离职")>0)
				{
					applyMapper.updateApply(apply);
					return "true";
				}
				else
				{
					return "false";
				}
			}
			else if(apply.getApplytype().getApplytypeName().equals("实习生转正") && apply.getState().equals("同意"))
			{
				if(personalinfoMapper.updatePersonalToEmployee(apply.getPassiveId())>0)
				{
					applyMapper.updateApply(apply);
					return "true";
				}
				else
				{
					return "false";
				}
			}
			else
			{
				applyMapper.updateApply(apply);
				return "true";
			}
		}
		else
		{
			return "false";
		}
	}

	//查询提交给直接上级的申请事项信息
	@Override
	public List<Apply> selectAllApplyByWriteId(String department_id,String state,String applytype_name,int before, int after, int jobId) 
	{
		return applyMapper.selectAllApplyByWriteId(department_id, state, applytype_name, before, after, jobId);
	}
	
	@Override
	public int countByWriteId(String department_id,String state,String applytype_name,int jobId) 
	{
		return applyMapper.countByWriteId(department_id, state, applytype_name, jobId);
	}
	
	
}
