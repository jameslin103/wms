package cn.fm.web.action.company;

import java.util.ArrayList;
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
import cn.fm.utils.DateUtil;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class EnterpriseAction extends BaseAction{
	
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
	private int page;
	private List<WmsUser> user=new ArrayList<WmsUser>();
	
	public int getPage() {
		return page<1?1:page;
	}
	public void setPage(int page) {
		this.page = page;
	}
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
	
	
	
	public List<WmsUser> getUser() {
		return user;
	}
	public void setUser(List<WmsUser> user) {
		this.user = user;
	}
	/**
	 * 分配负责人
	 * @return
	 */
	public String addEnterpriseToUser()
	{
		if(userId==null || enterpriseId==null)return INPUT;
		enterpriseService.saveEnterpriseToBeResponsible(enterpriseId,userId);
		
		return SUCCESS;
	}
	
	/**
	 * 解除企业负责人
	 * @return
	 */
	public String removeToEnterpriseHeadUser(){
		
		if(userId==null || enterpriseId==null)return INPUT;
		enterpriseService.removeToEnterpriseHeadUser(enterpriseId,userId);
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
		
		StringBuffer jpql = new StringBuffer("");
		StringBuffer countjpql = new StringBuffer("");

		List<WmsUser> wmsUsers=wmsUserService.getAllWmsUser();
		if(wmsUsers.size()==0)wmsUsers=new ArrayList<WmsUser>();
		
		
		if(isSystemAdmin==true)
		{
			jpql.append("select e from Enterprise e ").append(" order by e.enterpriseId desc");
			countjpql.append("Enterprise e ").append(" order by e.enterpriseId desc");
			request.setAttribute("isSystemAdmin", isSystemAdmin);
		}
		if(isSystemAdmin!=true)
		{

			jpql.append("select e from Enterprise e join e.user u  where u.userId=")
			.append(user.getUserId()).append(" order by e.enterpriseId desc");
			 countjpql.append("Enterprise e join e.user u  where u.userId=")
			.append(user.getUserId()).append(" order by e.enterpriseId desc");
		}
		PageView<Enterprise> pageView = new PageView<Enterprise>(10,  this.getPage());
		pageView.setQueryResult(enterpriseService.getScrollDataManytoMany(pageView.getFirstResult(), pageView.getMaxresult(),jpql.toString(),countjpql.toString()));
		
		request.setAttribute("pageView", pageView);
		request.setAttribute("wmsUsers", wmsUsers);
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("enterpriseId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		
			jpql.append(" o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(enterprise.getEnterpriseId());
			
			PageView<Enterprise> pageView = new PageView<Enterprise>(10,  this.getPage());
			pageView.setQueryResult(enterpriseService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		
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
		enterprise=WebUtil.getEnterprise(request);
		if(enterprise!=null)request.getSession().removeAttribute("enterprise");
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
//	public List<Enterprise> getWmsUserToBeEnterprise()
//	{
//		WmsUser user=WebUtil.getWmsUser(request);
//		WmsUser userPO=wmsUserService.find(user.getUserId());
//		List<Enterprise> enterpriseList=enterpriseService.getAllEnterprise(userPO);
//		if(enterpriseList.size()==0)enterpriseList=new ArrayList<Enterprise>();
//		return enterpriseList;
//	}
	
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
	
		
		
		if(enterprise==null || enterprise.getEnterpriseId()==null)return INPUT;
		
		Enterprise enterprisePO=enterpriseService.find(enterprise.getEnterpriseId());
		request.getSession().setAttribute("enterprise", enterprisePO);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			
			jpql.append("( o.ginsengProtectNature=?").append(params.size()+1);
			params.add(1);
			jpql.append(" or o.ginsengProtectNature=?").append(params.size()+1);
			params.add(2);
			jpql.append(" or o.ginsengProtectNature=?").append(params.size()+1);
			params.add(3);
			jpql.append(" )and o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(enterprise.getEnterpriseId());
			jpql.append("  and o.pseudoDelete=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and o.departure=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and year(o.createDate)=?").append(params.size()+1);
			params.add(Integer.parseInt(DateUtil.getCurrentTime().substring(0, 4)));
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
			request.setAttribute("insuranceYear", DateUtil.getCurrentTime().substring(0, 4));

		}
		
		return SUCCESS;
	}
	/**
	 * json 格式返回数据
	 * @return Enterprise
	 */
	public String findToIdEnterprise()
	{
		WmsUser  us=null;
		if(enterpriseId==null)return INPUT;
		enterpriseJson=enterpriseService.find(enterpriseId);
		for (WmsUser u : enterpriseJson.getUser()) {
			us=new WmsUser();
			us.setUserId(u.getUserId());
			us.setUsername(u.getUsername());
			user.add(us);
		}
		
		if(enterpriseJson==null)
			enterpriseJson=new Enterprise();
		
		return SUCCESS;
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
