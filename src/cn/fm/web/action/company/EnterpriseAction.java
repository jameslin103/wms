package cn.fm.web.action.company;

import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.company.Enterprise;
import cn.fm.service.company.EnterpriseService;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class EnterpriseAction extends BaseAction {
	
	@Resource
	private EnterpriseService enterpriseService;
	private Enterprise        enterprise;
	
	
	
	
	
	
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}






	public String  addEnterprise()
	{
		if(enterprise==null)return INPUT;
		if(enterprise!=null){
			enterpriseService.save(enterprise);
		}
		
		return SUCCESS;
	}
	
	public String  viewEnterprise()
	{
		List<Enterprise> enterpriseList=enterpriseService.getAllEnterprise();
		System.out.println(enterpriseList.size());
		request.setAttribute("enterpriseList", enterpriseList);
		return SUCCESS;
	}
	

}
