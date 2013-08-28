package cn.fm.web.action.company;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Preparable;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.company.EnterpriseService;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class EnterpriseAction extends BaseAction implements Preparable{
	
	@Resource
	private EnterpriseService enterpriseService;
	private Enterprise        enterprise;
	private Integer			  enterpriseId;
	
	
	
	
	
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}



	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	
		
		
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
		WmsUser user=(WmsUser)request.getSession().getAttribute("user");
		List<Enterprise> enterpriseList=enterpriseService.getAllEnterprise(user.getUserId());
		if(enterpriseList.size()==0)
			enterpriseList=new ArrayList<Enterprise>();
		request.setAttribute("enterpriseList", enterpriseList);
		return SUCCESS;
	}
	
	public String toBeResponsibleEnterprise()
	{
		 WmsUser user=(WmsUser)request.getSession().getAttribute("user");
		List<Enterprise> enterpriseList=enterpriseService.getAllEnterprise(user.getUserId());
		if(enterpriseList.size()==0)
			enterpriseList=new ArrayList<Enterprise>();
		request.setAttribute("enterpriseList", enterpriseList);
		
		return SUCCESS;
	}
	public String viewEnterpriseDetailed()
	{
		if(this.enterpriseId==null || this.enterpriseId==0)return SUCCESS;
		
		Enterprise enterprise=enterpriseService.find(this.enterpriseId);
		request.getSession().setAttribute("enterprise", enterprise);
		return SUCCESS;
	}


}
