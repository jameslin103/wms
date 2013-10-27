package cn.fm.web.action.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Preparable;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.permissions.Role;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.permissions.RoleService;
import cn.fm.service.user.WmsUserService;
import cn.fm.utils.Constant;
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
	@Resource
	private RoleService					roleService;
	
	private Enterprise        enterprise;
	private Integer			  enterpriseId;
	
	private Enterprise enterpriseJson;
	
	private EnterpriseEmployees  enterpriseEmployees=new EnterpriseEmployees();
	
	private Integer				userId;
	
	
	public Enterprise getEnterpriseJson() {
		return enterpriseJson;
	}
	public void setEnterpriseJson(Enterprise enterpriseJson) {
		this.enterpriseJson = enterpriseJson;
	}
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
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
	
	
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
	
		
		
	}
	/**
	 * 分配负责人
	 * @return
	 */
	public String addEnterpriseToUser()
	{
		Set<WmsUser>  users=new HashSet<WmsUser>();
		if(userId==null)return INPUT;
		WmsUser  user=wmsUserService.find(userId);
		users.add(user);
		enterprise=enterpriseService.find(enterpriseId);
		enterprise.setUser(users);
		enterpriseService.update(enterprise);
		
		return SUCCESS;
	}

	public String  addEnterprise()
	{
		boolean isSystem=false;
		WmsUser user=WebUtil.getWmsUser(request);
		if(user==null)return INPUT;
		if(enterprise==null)return INPUT;
		if(enterprise!=null){
			isSystem=isStysemUserRole(user);
			if(isSystem==true)
			{
				 enterpriseService.save(enterprise);
			}
			if(isSystem!=true)
			{
				  enterprise.addWmsUser(wmsUserService.find(user.getUserId()));
				  enterpriseService.save(enterprise);
			}		 	
		  }

		return SUCCESS;
	}
	
	public String  viewEnterprise()
	{
		boolean isSystem=false;
		WmsUser user=WebUtil.getWmsUser(request);
		isSystem=isStysemUserRole(user);
		
		List<WmsUser> wmsUsers=getTemplWmsUser(wmsUserService.getAllWmsUser());
		if(wmsUsers.size()==0)wmsUsers=new ArrayList<WmsUser>();
		
		
		if(isSystem==true)
		{
			List<Enterprise> allEnterpsie=enterpriseService.getAllEnterprise();
			if(allEnterpsie==null || allEnterpsie.size()==0)allEnterpsie=new ArrayList<Enterprise>();
			request.setAttribute("enterpsie", allEnterpsie);
		}
		if(isSystem!=true)
		{
			List<Enterprise> enterpsies=getWmsUserToBeEnterprise();
			request.setAttribute("enterpsie", enterpsies);
			
		}
		
		request.setAttribute("wmsUsers", wmsUsers);
		
		return SUCCESS;
	}
	
	public boolean isStysemUserRole(WmsUser user)
	{
		boolean isSystem=false;
		String[] userRoleIds= user.getRoleIds()!=null?user.getRoleIds().split(","):null;
		  if(userRoleIds!=null){
			  for (int i=0; i<userRoleIds.length;i++) 
			  {
				  Long roleId=Long.valueOf(userRoleIds[i]);
					Role  role=roleService.find(roleId);
					if(roleId.equals(role.getRoleId()) && role.getName().equals(Constant.WMS_GAO_JI_GUANLI_YUAN)){
					  isSystem=true;
				  }
			  }
		  }
		return isSystem;
	}
	public List<WmsUser> getTemplWmsUser(List<WmsUser> wmsUsers)
	{
		boolean falg=false;
		List<WmsUser> users=new ArrayList<WmsUser>();
		WmsUser  us=new WmsUser();
		for (WmsUser user : wmsUsers){
			falg=false;
			String[] roleIds = user.getRoleIds()!=null?user.getRoleIds().split(","):null;
			if(roleIds!=null){
				for (int i = 0; i < roleIds.length; i++) {
					Long roleId=Long.valueOf(roleIds[i]);
					Role  role=roleService.find(roleId);
					if(roleId.equals(role.getRoleId()) && role.getName().equals(Constant.WMS_GENDANYUAN)){
						falg=true;
					}
				}
			}
			if(falg==true){
				us=user;
				users.add(us);
			}
		}
		return users;
	}
	public String toBeResponsibleEnterprise()
	{
		WmsUser user=WebUtil.getWmsUser(request);
		WmsUser userPO=wmsUserService.find(user.getUserId());
		List<Enterprise> enterpriseList=enterpriseService.getAllEnterprise(userPO);
		request.setAttribute("enterprises", findToBeEnterpriseAndCreateSalaryBudgetTable(enterpriseList));
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
		if(enterpriseList.size()==0)enterpriseList=new ArrayList<Enterprise>();
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
		if(enterprise.getEnterpriseId()==null)return INPUT;
		List<EnterpriseEmployees> enterprisEmployeesList=enterpriseEmployeesService.findWorkersIncreasedToEmployees(enterprise.getEnterpriseId());
		if(enterprisEmployeesList.size()==0)
			enterprisEmployeesList=new ArrayList<EnterpriseEmployees>();
		Enterprise enterprisePO=enterpriseService.find(enterprise.getEnterpriseId());
		request.getSession().setAttribute("enterprise", enterprisePO);
		request.setAttribute("employees", enterprisEmployeesList);
		return SUCCESS;
	}
	/**
	 * json 格式返回数据
	 * @return Enterprise
	 */
	public String findToIdEnterprise()
	{
		if(enterpriseId==null)return INPUT;
		enterpriseJson=enterpriseService.find(enterpriseId);
		if(enterpriseJson==null)
			enterpriseJson=new Enterprise();
		
		return "enterpriseJson";
	}
	public String updateEnterprise()
	{
		
		enterpriseService.updateEnterprise(enterprise);
		
		return SUCCESS;
	}
	/**
	 * 修改企业联系人
	 * @return
	 */
	public String updateEnterpriseContact()
	{
		enterpriseService.updateEnterpriseContact(enterprise);
		
		return SUCCESS;
	}

}
