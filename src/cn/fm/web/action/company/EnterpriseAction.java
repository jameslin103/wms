package cn.fm.web.action.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Preparable;

import cn.fm.bean.PageView;
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
	private boolean      isSystemAdmin;
	
	
	public boolean isSystemAdmin() {
		return isSystemAdmin;
	}
	public void setSystemAdmin(boolean isSystemAdmin) {
		this.isSystemAdmin = isSystemAdmin;
	}
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
		WmsUser user=WebUtil.getWmsUser(request);
		if(user==null)return INPUT;
		if(enterprise==null)return INPUT;
		if(enterprise!=null){
			enterprise.addWmsUser(wmsUserService.find(user.getUserId()));
			enterpriseService.save(enterprise); 	
		  }

		return SUCCESS;
	}
	
	public String  viewEnterprise()
	{
		
		WmsUser user=WebUtil.getWmsUser(request);
		isSystemAdmin=isStysemUserRole(user);
		
		List<WmsUser> wmsUsers=wmsUserService.getAllWmsUser();
		if(wmsUsers.size()==0)wmsUsers=new ArrayList<WmsUser>();
		
		
		if(isSystemAdmin==true)
		{
			List<Enterprise> allEnterpsie=enterpriseService.getAllEnterprise();
			if(allEnterpsie==null || allEnterpsie.size()==0)allEnterpsie=new ArrayList<Enterprise>();
			request.setAttribute("isSystemAdmin", isSystemAdmin);
			request.setAttribute("enterpsie", allEnterpsie);
		}
		if(isSystemAdmin!=true)
		{
			List<Enterprise> enterpsies=getWmsUserToBeEnterprise();
			request.setAttribute("enterpsie", enterpsies);
			
		}
		
		request.setAttribute("wmsUsers", wmsUsers);
		
		return SUCCESS;
	}
	/**
	 * 是否高级管理员
	 * @param user
	 * @return
	 */
	public boolean isStysemUserRole(WmsUser user)
	{
		boolean isSystem=false;
		String[] userRoleIds= user.getRoleIds()!=null?user.getRoleIds().split(","):null;
		  if(userRoleIds!=null){
			  for (int i=0; i<userRoleIds.length;i++) 
			  {
				  Long roleId=Long.valueOf(userRoleIds[i]);
					Role  role=roleService.find(roleId)==null?null:roleService.find(roleId);
					if(role==null)return false;
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
		request.setAttribute("enterpriseList", enterpriseList);
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
			//enterprisePO.setRenewalCount(enterpriseEmployeesService.renewalPersonnel(enterprisePO.getEnterpriseId()));
			enterprisePO.setWhetherGinsengCount(enterpriseEmployeesService.ginsengPersonnel(enterprisePO.getEnterpriseId()));
			enterprisePO.setReductionTotal(enterpriseEmployeesService.reductionTotal(enterprisePO.getEnterpriseId()));
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
		
		Enterprise enterprisePO=enterpriseService.find(enterprise.getEnterpriseId());
		request.getSession().setAttribute("enterprise", enterprisePO);
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			jpql.append(" o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(enterprise.getEnterpriseId());
			jpql.append(" and (o.whetherGinseng=?").append(params.size()+1);
			params.add(1);
			jpql.append(" or o.reduction=?").append(params.size()+1);
			params.add(1);
			jpql.append(" or o.ginsengProtectNature=?").append(params.size()+1);
			params.add(1);

			jpql.append(" ) and o.pseudoDelete=?").append(params.size()+1);
			params.add(0);
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		
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
