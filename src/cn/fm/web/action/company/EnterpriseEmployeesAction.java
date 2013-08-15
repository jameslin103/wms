package cn.fm.web.action.company;

import javax.annotation.Resource;

import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.utils.StringUtil;
import cn.fm.web.action.BaseAction;


@SuppressWarnings("serial")
public class EnterpriseEmployeesAction extends BaseAction {
	
	@Resource 
	EnterpriseEmployeesService enterpriseEmployeesService;
	EnterpriseEmployees  enterpriseEmployees;
	
	
	

	@SuppressWarnings("unused")
	private String  addEnterpriseEmployees()
	{
		
		if(enterpriseEmployees.getEmployeesName()==null || enterpriseEmployees.getEmployeesName().equals(""))return INPUT;
			enterpriseEmployeesService.save(enterpriseEmployees);
		
		return SUCCESS;
	}
	public void vialteFile(){
		if(StringUtil.isEmpty(enterpriseEmployees.getEmployeesName())){
			this.addFieldError(enterpriseEmployees.getEmployeesName(), "姓名必填*");
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getNativePlace())){
			this.addFieldError(enterpriseEmployees.getNativePlace(), "籍贯必填项");
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getCardNumber()))
		{
			this.addFieldError(enterpriseEmployees.getCardNumber(), "身份证必填项*");
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getPhone()))
		{
			this.addFieldError(enterpriseEmployees.getPhone(), "电话必填项*");
			
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getHomeAddress()))
		{
			this.addFieldError(enterpriseEmployees.getHomeAddress(), "家庭地址必填项*");
			
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getBankCardNumber()))
		{
			this.addFieldError(enterpriseEmployees.getBankCardNumber(), "银行卡号必填项*");
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getBank()))
		{
			this.addFieldError(enterpriseEmployees.getBank(), "");
		}
	}
	

}
