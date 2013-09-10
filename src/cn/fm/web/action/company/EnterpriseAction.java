package cn.fm.web.action.company;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Preparable;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.user.WmsUserService;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class EnterpriseAction extends BaseAction implements Preparable{
	
	@Resource
	private EnterpriseService enterpriseService;
	@Resource
	private WmsUserService    wmsUserService;
	@Resource
	private EnterpriseEmployeesService  enterpriseEmployeesService;
	
	private Enterprise        enterprise;
	private Integer			  enterpriseId;
	
	private EnterpriseEmployees  enterpriseEmployees=new EnterpriseEmployees();
	

	
	
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
	public void setEnterpriseEmployeesService(
			EnterpriseEmployeesService enterpriseEmployeesService) {
		this.enterpriseEmployeesService = enterpriseEmployeesService;
	}
	public EnterpriseEmployees getEnterpriseEmployees() {
		return enterpriseEmployees;
	}
	public void setEnterpriseEmployees(EnterpriseEmployees enterpriseEmployees) {
		this.enterpriseEmployees = enterpriseEmployees;
	}
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	
		
		
	}


	public String  addEnterprise()
	{
		if(enterprise==null)return INPUT;
		if(enterprise!=null){
			WmsUser user=(WmsUser)request.getSession().getAttribute("user");
			enterprise.addWmsUser(user);
			enterpriseService.save(enterprise);
		}
		
		return SUCCESS;
	}
	
	public String  viewEnterprise()
	{
		List<WmsUser> wmsUsers=wmsUserService.getAllWmsUser();
		List<WmsUser> wmsUserList=enterpriseService.getEnterpriseToBoWmsUser(getWmsUserToBeEnterprise());
		if(wmsUserList.size()==0)
			wmsUserList=new ArrayList<WmsUser>();
		if(wmsUsers.size()==0)
			wmsUsers=new ArrayList<WmsUser>();
		request.setAttribute("wmsUserList", wmsUserList);
		request.setAttribute("wmsUsers", wmsUsers);
		return SUCCESS;
	}
	
	public String toBeResponsibleEnterprise()
	{
		
		getWmsUserToBeEnterprise();
		return SUCCESS;
	}
	/**
	 * 查询当前用户所负责的企业
	 * @return
	 */
	public List<Enterprise> getWmsUserToBeEnterprise()
	{
		WmsUser user=WebUtil.getWmsUser(request);
		WmsUser userPO=wmsUserService.find(user.getUserId());
		List<Enterprise> enterpriseList=enterpriseService.getAllEnterprise(userPO);
	
		if(enterpriseList.size()==0){
			enterpriseList=new ArrayList<Enterprise>();
		}
		
		request.setAttribute("enterprises", findToBeEnterpriseAndCreateSalaryBudgetTable(enterpriseList));
		request.setAttribute("enterpriseList", enterpriseList);
		
		return enterpriseList;
	}
	
	public List<Enterprise> findToBeEnterpriseAndCreateSalaryBudgetTable(List<Enterprise> enterpriseList)
	{
		List<Enterprise> enterprises=new ArrayList<Enterprise>();
		for (Enterprise enterprise : enterpriseList) {
			Enterprise enterprisePO=enterpriseService.find(enterprise.getEnterpriseId());
			enterprisePO.setAddCount(enterpriseEmployeesService.newStaffCount(enterprisePO.getEnterpriseId()));
			enterprisePO.setRenewalCount(enterpriseEmployeesService.renewalPersonnel(enterprisePO.getEnterpriseId()));
			enterprisePO.setWhetherGinsengCount(enterpriseEmployeesService.ginsengPersonnel(enterprisePO.getEnterpriseId()));
			enterprises.add(enterprisePO);
			
		}
		return enterprises;
	}
	public String viewEnterpriseDetailed()
	{
		if(this.enterpriseId==null || this.enterpriseId==0)return SUCCESS;
		
		Enterprise enterprise=enterpriseService.find(this.enterpriseId);
		request.getSession().setAttribute("enterprise", enterprise);
		return SUCCESS;
	}
	public String viewWorkersIncreased()
	{
		
		List<EnterpriseEmployees> enterprisEmployeesList=enterpriseEmployeesService.findWorkersIncreasedToEmployees(this.enterpriseId);
		if(enterprisEmployeesList.size()==0)
			enterprisEmployeesList=new ArrayList<EnterpriseEmployees>();
		Enterprise enterprise=enterpriseService.find(this.enterpriseId);
		request.getSession().setAttribute("enterprise", enterprise);
		request.setAttribute("employees", enterprisEmployeesList);
		return SUCCESS;
	}


	
}
