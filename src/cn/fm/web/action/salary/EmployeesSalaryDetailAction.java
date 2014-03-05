package cn.fm.web.action.salary;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.json.annotations.JSON;

import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.company.EnterpriseProjects;
import cn.fm.bean.salary.BalanceDetail;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.salary.BalanceDetailService;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.utils.StringUtil;
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
	
	private List<EmployeesSalaryDetail>    details=new ArrayList<EmployeesSalaryDetail>();
	
	
	
	private File file;
	
	private BigDecimal   bonusTotal; //开票总额
	private BigDecimal   wargeTotal;//工资总额
	private BigDecimal   fiveInsuranceTotal;//五险一金总额
	private long		 numberPeopleTotal;	//发放人数	
	private long         mingshengsum;
	private long         hebanksum;
	private long         isussesum;
	
	
	private BigDecimal   serviceTotal;//服务费总额
	
	private Integer  	 enterpriseId;
	private Integer  	 employeesId;
	private Integer 	 budgetId;
	private Integer 	 salaryId;
	private Integer      templateId;
	
	/*生成哪月工资？*/
	private Date    salaryDate;
	
	private int   page=1;
	private String    rp;
	private String   query;
	private String   qtype;
	private String   sortname;
	private String   sortorder;
	private String   qop;
	private long  total=0;
	

	
	
	
	
	
	/**
	 * excel批量导入工资预算表数据
	 * @return success
	 * @date   2013-09-01
	 */
	public String  uploadEmployeesSalaryDetail()
	{
		CreateSalaryBudgetTable createSalaryBudgetTablePO=createSalaryBudgetTableService.find(budgetId);
		request.setAttribute("createSalaryBudgetTable", createSalaryBudgetTablePO);
		Enterprise enterprise=WebUtil.getEnterprise(request);
		
		if(enterprise==null || enterprise.getEnterpriseId()==null)return INPUT;
		employeesSalaryDetail=new EmployeesSalaryDetail();
		employeesSalaryDetail.setEnterprise(enterprise);
		employeesSalaryDetail.setBudgettableId(budgetId);
		employeesSalaryDetail.setSalaryDate(salaryDate);
		employeesSalaryDetail.setCreateSalaryBudgetTable(createSalaryBudgetTablePO);
		
		 int count=0;
		 SalaryTemplate salaryTemplatePO=salaryTemplateService.find(templateId);
		 BigDecimal service=salaryTemplatePO.getEnterpriseProjects().getServiceHead()==null?new BigDecimal("0.00"):salaryTemplatePO.getEnterpriseProjects().getServiceHead();
		 employeesSalaryDetail.setServiceCharge(service);
		
			 if(!StringUtil.isEmpty(salaryTemplatePO.getSubsidyList())){
			 	 String[] customt=salaryTemplatePO.getSubsidyList().split(",");
				 count=customt.length+5;
			 }else{
				 count+=5; 
			 }
		
		//上传的名字是否重复
		List<String> employeesNames=employeesSalaryDetailService.saveEmployeesSalaryDetail(file, "员工基本工资信息表", count,1,employeesSalaryDetail,salaryTemplatePO,enterprise.getEnterpriseId());
		if(employeesNames!=null && employeesNames.size()>0){request.setAttribute("employeesNames", employeesNames);return INPUT;}
		
		
		
		//统计五险一金总额
		fiveInsuranceTotal=employeesSalaryDetailService.getEnterpriseSubtotalTotal(enterprise.getEnterpriseId(), budgetId);
		
		//统计特殊五险补贴总额
		//BigDecimal oldFiveInsuranceTotal=employeesSalaryDetailService.getfiveServiceTotal(enterprise.getEnterpriseId(), budgetId);
//		
//		fiveInsuranceTotal=fiveInsuranceTotal.add(oldFiveInsuranceTotal).setScale(2,BigDecimal.ROUND_HALF_EVEN);
		
		//统计工资总额
		
		BigDecimal wage=employeesSalaryDetailService.getWageTotal(enterprise.getEnterpriseId(), budgetId);
		
		BigDecimal getspecialUnemploymentSubsidiesTotal=employeesSalaryDetailService.getspecialUnemploymentSubsidiesTotal(enterprise.getEnterpriseId(), budgetId);
		
		BigDecimal getspecialOldSubsidiesTotal=employeesSalaryDetailService.getspecialOldSubsidiesTotal(enterprise.getEnterpriseId(), budgetId);
		
		BigDecimal getSpecialHealthCareSubsidiesTotal=employeesSalaryDetailService.getSpecialHealthCareSubsidiesTotal(enterprise.getEnterpriseId(), budgetId);
		
		BigDecimal getSpecialAccumulationFundSubsidiesTotal=employeesSalaryDetailService.getSpecialAccumulationFundSubsidiesTotal(enterprise.getEnterpriseId(), budgetId);
		
		
		//统计五险
		fiveInsuranceTotal=fiveInsuranceTotal.subtract(getspecialUnemploymentSubsidiesTotal)
											 .subtract(getspecialOldSubsidiesTotal)
											 .subtract(getSpecialHealthCareSubsidiesTotal)
											 .subtract(getSpecialAccumulationFundSubsidiesTotal).setScale(2,BigDecimal.ROUND_HALF_DOWN);
												
		
		
		wargeTotal=wage.add(getspecialUnemploymentSubsidiesTotal).
					add(getspecialOldSubsidiesTotal).
				    add(getSpecialHealthCareSubsidiesTotal).
					add(getSpecialAccumulationFundSubsidiesTotal).setScale(2,BigDecimal.ROUND_HALF_DOWN);
		
		//统计服务费
		
		if(salaryTemplatePO!=null && salaryTemplatePO.getEnterpriseProjects()!=null && salaryTemplatePO.getEnterpriseProjects().getFee()==1){
			BigDecimal dianshu=salaryTemplatePO.getEnterpriseProjects().getProportion().divide(new BigDecimal("100")).setScale(3,BigDecimal.ROUND_DOWN);
			serviceTotal=wargeTotal.multiply(dianshu).setScale(2,BigDecimal.ROUND_DOWN);
			
		}else{
			serviceTotal=employeesSalaryDetailService.getServiceTotal(enterprise.getEnterpriseId(), budgetId);
		}
		
		
		
		//统计开票总额
		bonusTotal=wargeTotal.add(serviceTotal==null?new BigDecimal("0.00"):serviceTotal).add(fiveInsuranceTotal).setScale(2,BigDecimal.ROUND_HALF_DOWN);

		numberPeopleTotal=employeesSalaryDetailService.getNumberPersonlTotal(enterprise.getEnterpriseId(), budgetId);
		
		
		//统计发放人数
		 isussesum=employeesSalaryDetailService.getSumCashNumber(enterprise.getEnterpriseId(), budgetId);
		 mingshengsum=employeesSalaryDetailService.getSumMingShengBank(enterprise.getEnterpriseId(), budgetId);
		 hebanksum=employeesSalaryDetailService.getSumHeLineBank(enterprise.getEnterpriseId(), budgetId);

		
		//记录到工资预算表汇总信息
		CreateSalaryBudgetTable createSalaryBudgetTableSummary=new CreateSalaryBudgetTable();
		createSalaryBudgetTableSummary.setBudgetId(budgetId);
		createSalaryBudgetTableSummary.setFiveInsurancesTotal(fiveInsuranceTotal);
		createSalaryBudgetTableSummary.setMakeTotal(bonusTotal);
		createSalaryBudgetTableSummary.setWageTotal(wargeTotal);
		createSalaryBudgetTableSummary.setServiceTotal(serviceTotal);
		createSalaryBudgetTableSummary.setIssueNumber(Integer.parseInt(numberPeopleTotal+""));
		createSalaryBudgetTableSummary.setCmbc(Integer.parseInt(mingshengsum+""));
		createSalaryBudgetTableSummary.setHeLines(Integer.parseInt(hebanksum+""));
		createSalaryBudgetTableSummary.setCashnumber(Integer.parseInt(isussesum+""));

		//TODO 医保类型待定
		
		createSalaryBudgetTableService.updateCreateSalaryBudgetTableSummary(createSalaryBudgetTableSummary);	
		
		//统计上传员工工资的总额,保险，开票总额 记录到<资金往来这个表中>
		
		BalanceDetail balanceDetail=balanceDetailService.find(createSalaryBudgetTablePO.getBalanceDetail().getDetailId());
		balanceDetail.setBallotsToal(bonusTotal);
		balanceDetail.setWagesToal(wargeTotal);
		balanceDetail.setServiceToal(serviceTotal);
		balanceDetail.setReceivableFiveFund(fiveInsuranceTotal);
		balanceDetail.setYearMonth(createSalaryBudgetTablePO.getSalaryDate());
		
		balanceDetailService.update(balanceDetail);
		
		CreateSalaryBudgetTable createSalaryBudgetTableNew=createSalaryBudgetTableService.find(budgetId);
		request.setAttribute("createSalaryBudgetTable", createSalaryBudgetTableNew);
		
		return SUCCESS;
	}
	
	public String viewEmployeePersonalSalary()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		EnterpriseEmployees  enterpriseEmployees=enterpriseEmployeesService.find(employeesId);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("salaryDate", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			jpql.append(" o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(enterprise.getEnterpriseId());
			jpql.append(" and o.enterpriseEmployees.employeesId=?").append(params.size()+1);
			params.add(employeesId);
			
			PageView<EmployeesSalaryDetail> pageView = new PageView<EmployeesSalaryDetail>(10,  this.getPage());
			pageView.setQueryResult(employeesSalaryDetailService.getScrollData(pageView.getFirstResult(), 
			pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("employeesId", employeesId);
			request.setAttribute("pageView", pageView);
			request.setAttribute("enterpriseEmployees", enterpriseEmployees);
			
		}
		
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
		if(enterpriseId!=null)
		{
			if(enterprise!=null){
				request.getSession().removeAttribute("enterprise");
			}
			enterprise=enterpriseService.find(enterpriseId);
			request.getSession().setAttribute("enterprise", enterprise);
		}
		
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("salaryId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			jpql.append(" o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(enterprise.getEnterpriseId());
			jpql.append(" and o.budgettableId=?").append(params.size()+1);
			params.add(budgetId);
			
			PageView<EmployeesSalaryDetail> pageView = new PageView<EmployeesSalaryDetail>(10,  this.getPage());
			pageView.setQueryResult(employeesSalaryDetailService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("budgetId", budgetId);
			request.setAttribute("pageView", pageView);
		}
		createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
		employeesSalaryDetail=employeesSalaryDetailService.getSumDateSalaryDeatil(createSalaryBudgetTable);
		if(employeesSalaryDetail==null)employeesSalaryDetail=new EmployeesSalaryDetail();
		
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
			jpql.append(" and o.bank like ?").append(params.size()+1);
			params.add("%民生%");
			
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
			jpql.append(" and o.bank not like '%民生%' ");
			jpql.append(" and o.bank is not null ");
			jpql.append(" and o.bank !='' ");
			
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
			jpql.append(" and ( o.bank is null");
			jpql.append(" or o.bank like '%现金%' or o.bank='' )");
			
			
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
		
		return SUCCESS;
	}
	
	public String updateEmployeesSalaryDetail()
	{
		employeesSalaryDetailService.updateEmployeesSalaryDetail(employeesSalaryDetail);
		
		return SUCCESS;
	}
	
	public String viewSalaryWithBankPersonalNumber()
	{
		if(enterpriseId!=null)
		{
			Enterprise enterprise=WebUtil.getEnterprise(request);
			if(enterprise!=null)request.removeAttribute("enterprise");
			enterprise=enterpriseService.find(enterpriseId);
			request.getSession().setAttribute("enterprise", enterprise);
		}
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
	
	
	
	
	
	
	public int getPage() {
		return page<1?1:page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	
	@JSON(name="details")
	public List<EmployeesSalaryDetail> getDetails() {
		return details;
	}

	public void setDetails(List<EmployeesSalaryDetail> details) {
		this.details = details;
	}

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
	
	
	@JSON(name="page")
	public Integer getPpage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
	@JSON(name="total")
	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
	@JSON(name="rp")
	public String getRp() {
		return rp;
	}

	public void setRp(String rp) {
		this.rp = rp;
	}
	@JSON(name="query")
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQtype() {
		return qtype;
	}

	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

	public String getQop() {
		return qop;
	}

	public void setQop(String qop) {
		this.qop = qop;
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

	public long getMingshengsum() {
		return mingshengsum;
	}

	public void setMingshengsum(long mingshengsum) {
		this.mingshengsum = mingshengsum;
	}

	public long getHebanksum() {
		return hebanksum;
	}

	public void setHebanksum(long hebanksum) {
		this.hebanksum = hebanksum;
	}

	public long getIsussesum() {
		return isussesum;
	}

	public void setIsussesum(long isussesum) {
		this.isussesum = isussesum;
	}

	

}
