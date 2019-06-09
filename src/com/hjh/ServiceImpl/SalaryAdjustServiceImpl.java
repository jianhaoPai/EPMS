package com.hjh.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.Apply;
import com.hjh.Bean.Applytype;
import com.hjh.Bean.Personalinfo;
import com.hjh.Bean.SalaryAdjust;
import com.hjh.Bean.StaffWage;
import com.hjh.Mapper.ApplyMapper;
import com.hjh.Mapper.PersonalinfoMapper;
import com.hjh.Mapper.SalaryAdjustMapper;
import com.hjh.Mapper.StaffWageMapper;
import com.hjh.Service.SalaryAdjustService;

@Service("salaryAdjustService")
public class SalaryAdjustServiceImpl implements SalaryAdjustService
{
	@Autowired
	private Apply apply;
	
	@Autowired
	private Applytype applytype;
	
	@Autowired
	private ApplyMapper applyMapper;
	
	@Autowired
	private SalaryAdjustMapper salaryAdjustMapper;
	
	@Autowired
	private StaffWageMapper staffWageMapper;
	
	@Autowired
	private StaffWage staffWage;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;

	//提交薪资调动申请表
	@Override
	public String insertSalaryAdjust(SalaryAdjust salaryAdjust,String passiveName) 
	{
		JSONObject result = new JSONObject();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		salaryAdjust.getApply().setSubmitDate(dateFormat.format(new Date()));
		salaryAdjust.getApply().setState("待审核");
		
		if(!checkErrorMess(salaryAdjust,passiveName).equals(""))//审核是否出现错误输入信息
		{
			return checkErrorMess(salaryAdjust,passiveName);
		}
		else
		{
			if(checkIdentity(salaryAdjust.getApply()))//当返回true时，说明是部门经理，需要调用checkIfValidInsert，来审核是否是部门经理管理的部门职员
			{
				if(checkIfValidInsert(salaryAdjust.getApply(),passiveName))//当返回true，说明在负责范围内，可进行提交
				{
					detailinsertSalaryAdjust(salaryAdjust,passiveName);
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
				detailinsertSalaryAdjust(salaryAdjust,passiveName);
				result.put("status", true);
				result.put("message", "提交成功！");
			}
		}
		return result.toString();
	}
	
	public String checkErrorMess(SalaryAdjust salaryAdjust,String passiveName)
	{
		JSONObject result = new JSONObject();
		if(salaryAdjust.getApply().getReason().length()<=0||passiveName.length()<=0
				||salaryAdjust.getSalaryadjustMoneny()<=0)//审核输入的信息是否完整
		{
			return null;
		}
		else if(salaryAdjust.getSalaryadjustType().length()<=0)
		{
			result.put("status", false);
			result.put("message", "提交失败，还未选择薪资调动类型！");
			return result.toString();
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
			salaryAdjust.getApply().setPassiveId(personalinfoPassiver.getJobId());
			if((salaryAdjust.getApply().getWriteId()==salaryAdjust.getApply().getPassiveId()))
			{
				result.put("status", false);
				result.put("message", "提交失败，请勿为自己填写申请表！");
				return result.toString();
			}
			else
			{ 
				if(salaryAdjustMapper.selectIfRepeat(salaryAdjust)>0)//是否重复提交
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
	
	public void detailinsertSalaryAdjust(SalaryAdjust salaryAdjust,String passiveName)
	{
		//先插入apply表，注意此时用来设置申请表类型！！！！
		Personalinfo personalinfoPassiver=personalinfoMapper.selectPersonalByNameNotEducation(passiveName);
		salaryAdjust.getApply().setPassiveId(personalinfoPassiver.getJobId());
		salaryAdjust.getApply().setApplytype(applytype);
		salaryAdjust.getApply().getApplytype().setApplytypeId(4);;
		applyMapper.insertApply(salaryAdjust.getApply());
			
		//再插入salaryadjust表
		salaryAdjust.setApplyId(applyMapper.selectApplyMaxId());
		salaryAdjustMapper.insertSalaryAdjust(salaryAdjust);
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
	
	//根据填表人工号查询薪资调动申请信息
	@Override
	public List<SalaryAdjust> selectAllSalaryAdjustByWriteId(String department_id,String state,String salaryadjust_type,int before,int after, int jobId) 
	{
		return salaryAdjustMapper.selectAllSalaryAdjustByWriteId(department_id, state, salaryadjust_type, before, after, jobId);
	}

	@Override
	public int countByWriteId(String department_id,String state,String salaryadjust_type,int jobId) 
	{
		return salaryAdjustMapper.countByWriteId(department_id, state, salaryadjust_type, jobId);
	}
	
	//上级查看下属提交的全部薪资调动申请
	@Override
	public List<SalaryAdjust> selectAllSalaryAdjust(String department_id,String state,
			          String salaryadjust_type,int before, int after,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("总经理"))
		{
			return salaryAdjustMapper.selectSalaryAdjustToLeader(department_id, state, salaryadjust_type, before, after, 2);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return salaryAdjustMapper.selectSalaryAdjustToLeader(department_id, state, salaryadjust_type, before, after, 3);
		}
		return null;
	}

	@Override
	public int countAllSalaryAdjust(String department_id,String state,String salaryadjust_type,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("总经理"))
		{
			return salaryAdjustMapper.countToLeader(department_id, state, salaryadjust_type, 2);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return salaryAdjustMapper.countToLeader(department_id, state, salaryadjust_type, 3);
		}
		return 0;
	}

	//审批薪资调动申请
	@Override
	public String updateSalaryAdjust(SalaryAdjust salaryAdjust,String state) 
	{
		// 先检查是否已经审批过，若已审批过,则无法重新审批
		if (applyMapper.selectApplyById(salaryAdjust.getApplyId()).getApprovalDate() == null) 
		{
			if(state.equals("同意"))
			{
				//更新申请员工的工资
				staffWage=staffWageMapper.selectStaffWageByJobId(applyMapper.selectApplyById(salaryAdjust.getApplyId()).getPassiveId());
				//判断是加薪还是减薪
				if(salaryAdjust.getSalaryadjustType().equals("加薪"))
				{
					staffWage.setWage(staffWage.getWage() + salaryAdjust.getSalaryadjustMoneny());
				}
				else
				{
					staffWage.setWage(staffWage.getWage() - salaryAdjust.getSalaryadjustMoneny());
				}
				staffWageMapper.updatetStaffWage(staffWage);
			}
			//更新申请表状态和审批时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			apply.setApprovalDate(dateFormat.format(new Date()));
			apply.setId(salaryAdjust.getApplyId());
			apply.setState(state);
			applyMapper.updateApply(apply);
			return "true";
		} 
		else 
		{
			return "false";
		}
	}

	@Override
	public List<SalaryAdjust> selectAllSalaryAdjustByJobId(String state,
			int before, int after, int jobId) 
	{
		return salaryAdjustMapper.selectAllSalaryAdjustByJobId(state, before, after, jobId);
	}

	@Override
	public int countByJobId(String state, int jobId) 
	{
		return salaryAdjustMapper.countByJobId(state, jobId);
	}

}
