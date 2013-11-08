package cn.fm.web.action.salary;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.BalanceDetail;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.company.CustomBonusServices;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.salary.BalanceDetailService;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class EmployeesSalaryDetailAction extends BaseAction{

	@Resource
	private EmployeesSalaryDetailService 	employeesSalaryDetailService;
	@Resource
	private EnterpriseEmployeesService   	enterpriseEmployeesService;
	@Resource
	private CreateSalaryBudgetTableService  createSalaryBudgetTableService; 
	@Resource
	private EnterpriseService				enterpriseService;
	@Resource 
	private BalanceDetailService			balanceDetailService;
	@Resource
	private SalaryTemplateService			salaryTemplateService;
	
	
	private EnterpriseEmployees   enterpriseEmployees;
	private Enterprise    enterprise;
	private CreateSalaryBudgetTable   createSalaryBudgetTable;
	private EmployeesSalaryDetail     employeesSalaryDetail;
	
	
	private File file;
	
	private BigDecimal   bonusTotal; //开票总额
	private BigDecimal   wargeTotal;//工资总额
	private BigDecimal   fiveInsuranceTotal;//五险一金总额
	private long		 numberPeopleTotal;	//发放人数		
	private BigDecimal   serviceTotal;//服务费总额
	
	private Integer  	 enterpriseId;
	private Integer  	 employeesId;
	private Integer 	 budgetId;
	private Integer 	 salaryId;
	private Integer      templateId;
	
	/*生成哪月工资？*/
	private Date    salaryDate;
	
	
	

	public Date getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}

	public Integer getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}

	public BigDecimal getFiveInsuranceTotal() {
		return fiveInsuranceTotal;
	}

	public void setFiveInsuranceTotal(BigDecimal fiveInsuranceTotal) {
		this.fiveInsuranceTotal = fiveInsuranceTotal;
	}

	public long getNumberPeopleTotal() {
		return numberPeopleTotal;
	}

	public void setNumberPeopleTotal(long numberPeopleTotal) {
		this.numberPeopleTotal = numberPeopleTotal;
	}

	public BigDecimal getServiceTotal() {
		return serviceTotal;
	}

	public void setServiceTotal(BigDecimal serviceTotal) {
		this.serviceTotal = serviceTotal;
	}
	
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getEmployeesId() {
		return employeesId;
	}

	public void setEmployeesId(Integer employeesId) {
		this.employeesId = employeesId;
	}

	public BigDecimal getBonusTotal() {
		return bonusTotal;
	}

	public void setBonusTotal(BigDecimal bonusTotal) {
		this.bonusTotal = bonusTotal;
	}

	public BigDecimal getWargeTotal() {
		return wargeTotal;
	}

	public void setWargeTotal(BigDecimal wargeTotal) {
		this.wargeTotal = wargeTotal;
	}

	public EnterpriseEmployees getEnterpriseEmployees() {
		return enterpriseEmployees;
	}

	public void setEnterpriseEmployees(EnterpriseEmployees enterpriseEmployees) {
		this.enterpriseEmployees = enterpriseEmployees;
	}

	public Enterprise getEnterprise() {
		return enterprise;
	}
	
	public CreateSalaryBudgetTable getCreateSalaryBudgetTable() {
		return createSalaryBudgetTable;
	}
	
	public EmployeesSalaryDetail getEmployeesSalaryDetail() {
		return employeesSalaryDetail;
	}

	public void setEmployeesSalaryDetail(EmployeesSalaryDetail employeesSalaryDetail) {
		this.employeesSalaryDetail = employeesSalaryDetail;
	}

	public void setCreateSalaryBudgetTable(
			CreateSalaryBudgetTable createSalaryBudgetTable) {
		this.createSalaryBudgetTable = createSalaryBudgetTable;
	}

	public void setSalaryTemplateService(SalaryTemplateService salaryTemplateService) {
		this.salaryTemplateService = salaryTemplateService;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}



	public void setEnterpriseEmployeesService(
			EnterpriseEmployeesService enterpriseEmployeesService) {
		this.enterpriseEmployeesService = enterpriseEmployeesService;
	}

	public void setCreateSalaryBudgetTableService(
			CreateSalaryBudgetTableService createSalaryBudgetTableService) {
		this.createSalaryBudgetTableService = createSalaryBudgetTableService;
	}

	public void setBalanceDetailService(BalanceDetailService balanceDetailService) {
		this.balanceDetailService = balanceDetailService;
	}
	public void setEmployeesSalaryDetailService(EmployeesSalaryDetailService employeesSalaryDetailService) {
		this.employeesSalaryDetailService = employeesSalaryDetailService;
	}
	

	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}

	
	
	
	
	
	
	/**
	 * excel批量导入工资预算表数据
	 * @return success
	 * @date   2013-09-01
	 */
	public String  uploadEmployeesSalaryDetail()
	{
		createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
		request.setAttribute("createSalaryBudgetTable", createSalaryBudgetTable);
		Enterprise enterprise=WebUtil.getEnterprise(request);
		
		if(enterprise==null || enterprise.getEnterpriseId()==null)return INPUT;
		employeesSalaryDetail=new EmployeesSalaryDetail();
		employeesSalaryDetail.setEnterpriseId(enterprise.getEnterpriseId());
		employeesSalaryDetail.setBudgettableId(budgetId);
		employeesSalaryDetail.setSalaryDate(salaryDate);
		
		 SalaryTemplate salaryTemplate=salaryTemplateService.find(templateId);
	 	 String[] customt=salaryTemplate.getSubsidyList().split(",");
		 int count=customt.length+5;
		//上传的名字是否重复
		List<String> employeesNames=employeesSalaryDetailService.saveEmployeesSalaryDetail(file, "员工基本工资信息表", count,1,employeesSalaryDetail,createSalaryBudgetTable.getSalaryTemplate().getTemplateId(),enterprise.getEnterpriseId());
		if(employeesNames.size()>0){request.setAttribute("employeesNames", employeesNames);return INPUT;}
		
		//查找统计上传员工工资的总额
		bonusTotal=employeesSalaryDetailService.getInvoiceTotal(enterprise.getEnterpriseId(), budgetId);
		wargeTotal=employeesSalaryDetailService.getWageTotal(enterprise.getEnterpriseId(), budgetId);
		fiveInsuranceTotal=employeesSalaryDetailService.getFiveInsuranceTotal(enterprise.getEnterpriseId(), budgetId);
		numberPeopleTotal=employeesSalaryDetailService.getNumberPersonlTotal(enterprise.getEnterpriseId(), budgetId);
		serviceTotal=employeesSalaryDetailService.getServiceTotal(enterprise.getEnterpriseId(), budgetId);

		
		//记录到工资预算表汇总信息
		CreateSalaryBudgetTable createSalaryBudgetTableSummary=new CreateSalaryBudgetTable();
		createSalaryBudgetTableSummary.setBudgetId(budgetId);
		createSalaryBudgetTableSummary.setFiveInsurancesTotal(fiveInsuranceTotal);
		createSalaryBudgetTableSummary.setMakeTotal(bonusTotal);
		createSalaryBudgetTableSummary.setWageTotal(wargeTotal);
		createSalaryBudgetTableSummary.setServiceTotal(serviceTotal);
		createSalaryBudgetTableSummary.setIssueNumber(Integer.parseInt(numberPeopleTotal+""));

		//TODO 医保类型待定
		
		createSalaryBudgetTableService.updateCreateSalaryBudgetTableSummary(createSalaryBudgetTableSummary);	
		
		//统计上传员工工资的总额,保险，开票总额 记录到<资金往来这个表中>
		BalanceDetail   balanceDetail=new BalanceDetail();
		balanceDetail.setBallotsToal(bonusTotal);
		balanceDetail.setWagesToal(wargeTotal);
		balanceDetail.setServiceToal(serviceTotal);
		balanceDetail.setReceivableFiveFund(fiveInsuranceTotal);
		balanceDetail.setYearMonth(createSalaryBudgetTable.getSalaryDate());
		balanceDetail.setBudgetId(createSalaryBudgetTable.getBudgetId());
		balanceDetail.setNote(createSalaryBudgetTable.getNote());
		balanceDetail.setEnterprise(enterprise);
		
		
		balanceDetailService.save(balanceDetail);
		
		
		
		createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
		
		request.setAttribute("createSalaryBudgetTable", createSalaryBudgetTable);
		
		return SUCCESS;
	}
	
	public String viewEmployeePersonalSalary()
	{
		Enterprise enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		List<EmployeesSalaryDetail> employeesSalaryDetailList=employeesSalaryDetailService.getAllEmployeesSalaryDetail( enterprise.getEnterpriseId(),employeesId);
		if(employeesSalaryDetailList.size()==0)
			employeesSalaryDetailList=new ArrayList<EmployeesSalaryDetail>();
		
		EnterpriseEmployees employees=enterpriseEmployeesService.findEnterpriseEmployees(employeesId);
		request.setAttribute("employeesSalaryDetails", employeesSalaryDetailList);
		request.setAttribute("employees", employees);
		return SUCCESS;
		
	}
	
	public String updateSalaryEnterpriseEmployees()
	{
		try {
			EnterpriseEmployees enterpriseEmployeesVO=new EnterpriseEmployees();
			enterpriseEmployeesVO=enterpriseEmployees;
			enterpriseEmployeesService.update(enterpriseEmployeesVO);
		} catch (Exception e) {
			return INPUT;
			
			
		}
		return SUCCESS;
	}
	/**
	 * 
	 * @return viewAllEmployeesSalaryDetail这个项目的所有工资明细
	 */
	public String viewAllEmployeesSalaryDetail()
	{
		
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null || enterprise.getEnterpriseId()==null){
			enterprise=enterpriseService.find(enterpriseId);
			request.getSession().setAttribute("enterprise", enterprise);
		}
		
		List<EmployeesSalaryDetail> employeesSalaryDetailList=employeesSalaryDetailService.getAllEmployeesSalaryDetail(enterprise.getEnterpriseId(), budgetId);
		if(employeesSalaryDetailList==null ||employeesSalaryDetailList.size()==0)
			employeesSalaryDetailList=new ArrayList<EmployeesSalaryDetail>();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createDate", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			jpql.append(" o.enterpriseId=?").append(params.size()+1);
			params.add(enterprise.getEnterpriseId());
			jpql.append(" and o.budgettableId=?").append(params.size()+1);
			params.add(budgetId);
			
			PageView<EmployeesSalaryDetail> pageView = new PageView<EmployeesSalaryDetail>(8,  this.getPage());
			pageView.setQueryResult(employeesSalaryDetailService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("budgetId", budgetId);
			request.setAttribute("pageView", pageView);
		}
		
		
		
		
		
		request.setAttribute("employeesSalaryDetail", employeesSalaryDetailList);
		return SUCCESS;
	}
	
	
	public String viewSalaryWithBankPersonalNumber()
	{
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("salaryId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
			jpql.append(" o.budgettableId=?").append(params.size()+1);
			params.add(this.budgetId);
			
			PageView<EmployeesSalaryDetail> pageView = new PageView<EmployeesSalaryDetail>(10,  this.getPage());
			pageView.setQueryResult(employeesSalaryDetailService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		
			request.setAttribute("budgetId", budgetId);
		return SUCCESS;
		
	}
	/**
	 * 查看民生银行发放人数
	 * @return
	 */
	public String viewMinshengBank(){
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("salaryId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
			jpql.append(" o.budgettableId=?").append(params.size()+1);
			params.add(this.budgetId);
			jpql.append(" and o.note like ?").append(params.size()+1);
			params.add("%民生银行%");
			
			PageView<EmployeesSalaryDetail> pageView = new PageView<EmployeesSalaryDetail>(10,  this.getPage());
			pageView.setQueryResult(employeesSalaryDetailService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		
			request.setAttribute("budgetId", budgetId);
		
		return SUCCESS;
	}
	/**
	 * 查看其它银行发放人数
	 * @return
	 */
	public String viewOtherBank(){
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("salaryId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
			jpql.append(" o.budgettableId=?").append(params.size()+1);
			params.add(this.budgetId);
			jpql.append(" and o.note!=?").append(params.size()+1);
			params.add(" 民生银行");
			jpql.append(" and o.note!=?").append(params.size()+1);
			params.add("现金");
			
			PageView<EmployeesSalaryDetail> pageView = new PageView<EmployeesSalaryDetail>(10,  this.getPage());
			pageView.setQueryResult(employeesSalaryDetailService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		
			request.setAttribute("budgetId", budgetId);
			
			return SUCCESS;
	}
	/**
	 * 查看现金发放人数
	 * @return
	 */
	public String viewCashIssue(){
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("salaryId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
			jpql.append(" o.budgettableId=?").append(params.size()+1);
			params.add(this.budgetId);
			jpql.append(" and o.note=?").append(params.size()+1);
			params.add("like '现金' ");
			
			PageView<EmployeesSalaryDetail> pageView = new PageView<EmployeesSalaryDetail>(10,  this.getPage());
			pageView.setQueryResult(employeesSalaryDetailService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		
			request.setAttribute("budgetId", budgetId);
		
		return SUCCESS;
	}
		
	
	
	
	/**
	 * 重新获取工资预算表当前新增的数据信息
	 * @return
	 */
	public String toImportSalaryData()
	{
		
		return SUCCESS;
	}
	
	public String findToIdSalaryDetail()
	{
		
		employeesSalaryDetail=employeesSalaryDetailService.find(salaryId);
		return "employeesSalaryDetail";
	}
	
	public String updateEmployeesSalaryDetail()
	{
		employeesSalaryDetailService.updateEmployeesSalaryDetail(employeesSalaryDetail);
		
		return SUCCESS;
	}
}
