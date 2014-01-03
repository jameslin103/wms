package cn.fm.web.action.company;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.user.User;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.company.InsurancesBaseSettingsService;
import cn.fm.service.company.InsurancesTaxService;
import cn.fm.service.privilege.RoleService;
import cn.fm.service.user.UserService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class EnterpriseAction extends BaseAction{
	
	@Resource
	private EnterpriseService enterpriseService;
	@Resource
	private UserService    userService;
	@Resource
	private EnterpriseEmployeesService  enterpriseEmployeesService;
	@Resource
	private RoleService					roleService;
	@Resource
	private InsurancesTaxService       insurancesTaxService;
	@Resource
	private InsurancesBaseSettingsService insurancesBaseSettingsService;
	
	
	private Enterprise        enterprise;
	private Integer			  enterpriseId;
	
	private Enterprise enterpriseJson;
	
	private EnterpriseEmployees  enterpriseEmployees=new EnterpriseEmployees();
	
	private Integer				userId;
	private boolean      isSystemAdmin;
	private int page;
	private Long  isExitFullName;
	
	private User   user;
	
	

	/**
	 * 分配负责人
	 * @return
	 */
	public String addEnterpriseToUser()
	{
		if(user==null || user.getId()==null || enterpriseId==null)return INPUT;
		enterpriseService.saveEnterpriseToBeResponsible(enterpriseId,user.getId());
		
		return SUCCESS;
	}
	
	/**
	 * 解除企业负责人
	 * @return
	 */
	public String removeToEnterpriseHeadUser(){
		
		if(user==null || user.getId()==null || enterpriseId==null)return INPUT;
		enterpriseService.removeToEnterpriseHeadUser(enterpriseId,user.getId());
		return SUCCESS;
	}
	public String  addEnterprise()
	{
		User user=WebUtil.getUser(request);
		if(user==null)return INPUT;
		if(enterprise==null)return INPUT;
		if(enterprise!=null){
			enterprise.setInsurancesBaseSettings(insurancesBaseSettingsService.find(1));
			enterprise.setInsurancesTax(insurancesTaxService.find(1));
			enterpriseService.save(enterprise); 	
		  }

		return SUCCESS;
	}
	
	public String  viewEnterprise()
	{
		
		List<User> users=userService.getUsers();
		if(users.size()==0)users=new ArrayList<User>();
		PageView<Enterprise>  pageView=enterpriseService.getAllEnterprise(10, this.getPage(),user,enterprise);
		request.setAttribute("pageView", pageView);
		request.setAttribute("users", users);
		
		return SUCCESS;
	}
	
	public String toBeResponsibleEnterprise()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise!=null)request.getSession().removeAttribute("enterprise");
		User user=WebUtil.getUser(request);
		
	    PageView<Enterprise>  pageView=enterpriseService.getUserToByEnterprise(10, this.getPage(), user,this.enterprise);
	    
		List<Enterprise> list=findToBeEnterpriseAndCreateSalaryBudgetTable(pageView.getRecords());
		request.setAttribute("enterprises", list);
		request.setAttribute("pageView", pageView);
		
		return SUCCESS;
	}

	
	public List<Enterprise> findToBeEnterpriseAndCreateSalaryBudgetTable(List<Enterprise> enterpriseList)
	{
		Enterprise enterpriseVO;
		List<Enterprise> enterprises=new ArrayList<Enterprise>();
		for (Enterprise enterprise : enterpriseList) {
			enterpriseVO=new Enterprise();
			enterpriseVO=enterprise;
			
			enterpriseVO.setCount(enterpriseService.getCountEmployees(enterprise.getEnterpriseId()));
			enterpriseVO.setAddCount(enterpriseEmployeesService.newStaffCount(enterprise.getEnterpriseId()));
			//enterprisePO.setRenewalCount(enterpriseEmployeesService.renewalPersonnel(enterprisePO.getEnterpriseId()));
			enterpriseVO.setWhetherGinsengCount(enterpriseEmployeesService.ginsengPersonnel(enterprise.getEnterpriseId()));
			enterpriseVO.setReductionTotal(enterpriseEmployeesService.reductionTotal(enterprise.getEnterpriseId()));
			enterprises.add(enterpriseVO);
			
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
		User  us=null;
		if(enterpriseId==null)return INPUT;
		enterpriseJson=enterpriseService.find(enterpriseId);
		for (User u : enterpriseJson.getUser()) {
			us=new User();
			us.setId(u.getId());
			us.setAccount(u.getAccount());
			users.add(us);
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
	public String isExitFullname(){
		
		String name=null;
		try {
			name=URLDecoder.decode(enterprise.getFullName().trim(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		isExitFullName=enterpriseService.findByFullName(name);
		responseJson(isExitFullName);
		
		return NONE;
	}
	
	public String seacherEnterprise(){
		
		enterprise=enterpriseService.find(enterprise.getEnterpriseId());
		enterprise.setCount(enterpriseService.getCountEmployees(enterprise.getEnterpriseId()));
		return SUCCESS;
	}
	
	
	
	
	private List<User> users=new ArrayList<User>();
	
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
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Long getIsExitFullName() {
		return isExitFullName;
	}

	public void setIsExitFullName(Long isExitFullName) {
		this.isExitFullName = isExitFullName;
	}
	
	

}
