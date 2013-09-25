package cn.fm.web.action.salary;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class EmployeesSalaryDetailAction extends BaseAction{

	@Resource
	private EmployeesSalaryDetailService employeesSalaryDetailService;
	@Resource
	private EnterpriseEmployeesService   enterpriseEmployeesService;
	@Resource
	private CreateSalaryBudgetTableService  createSalaryBudgetTableService; 
	
	private EnterpriseEmployees   enterpriseEmployees;
	private Enterprise    enterprise;
	private CreateSalaryBudgetTable   createSalaryBudgetTable;
	private EmployeesSalaryDetail     employeesSalaryDetail;
	
	private File file;
	
	private BigDecimal   bonusTotal; //开票总额
	private BigDecimal   wargeTotal;//工资总额
	private BigDecimal   fiveInsuranceTotal;//五险一金总额
	private Integer	 numberPeopleTotal;	//发放人数		
	private BigDecimal   serviceTotal;//服务费总额
	
	private Integer  enterpriseId;
	private Integer  employeesId;
	private Integer  budgetId;
	
	
	
	
	
	public BigDecimal getFiveInsuranceTotal() {
		return fiveInsuranceTotal;
	}

	public void setFiveInsuranceTotal(BigDecimal fiveInsuranceTotal) {
		this.fiveInsuranceTotal = fiveInsuranceTotal;
	}

	public Integer getNumberPeopleTotal() {
		return numberPeopleTotal;
	}

	public void setNumberPeopleTotal(Integer numberPeopleTotal) {
		this.numberPeopleTotal = numberPeopleTotal;
	}

	public BigDecimal getServiceTotal() {
		return serviceTotal;
	}

	public void setServiceTotal(BigDecimal serviceTotal) {
		this.serviceTotal = serviceTotal;
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

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}



	public void setEmployeesSalaryDetailService(EmployeesSalaryDetailService employeesSalaryDetailService) {
		this.employeesSalaryDetailService = employeesSalaryDetailService;
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
		Enterprise enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		
		if(enterprise==null || enterprise.getEnterpriseId()==null)return INPUT;
		employeesSalaryDetail=new EmployeesSalaryDetail();
		employeesSalaryDetail.setEnterpriseId(enterprise.getEnterpriseId());
		employeesSalaryDetail.setBudgettableId(budgetId);
		employeesSalaryDetailService.saveEmployeesSalaryDetail(file, "工资预算表", 33,3,employeesSalaryDetail);
		
		//查找统计上传员工工资的总额
		bonusTotal=employeesSalaryDetailService.getInvoiceTotal(enterpriseId, budgetId);
		wargeTotal=employeesSalaryDetailService.getWageTotal(enterpriseId, budgetId);
		fiveInsuranceTotal=employeesSalaryDetailService.getFiveInsuranceTotal(enterpriseId, budgetId);
		numberPeopleTotal=employeesSalaryDetailService.getNumberPersonlTotal(enterpriseId, budgetId);
		serviceTotal=employeesSalaryDetailService.getServiceTotal(enterpriseId, budgetId);
		
		//记录到工资预算表汇总信息
		createSalaryBudgetTable.setFiveInsurancesTotal(fiveInsuranceTotal);
		createSalaryBudgetTable.setMakeTotal(bonusTotal);
		createSalaryBudgetTable.setWageTotal(wargeTotal);
		createSalaryBudgetTable.setServiceTotal(serviceTotal);
		createSalaryBudgetTable.setIssueNumber(numberPeopleTotal);
				
		
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
	
		List<EmployeesSalaryDetail> employeesSalaryDetailList=employeesSalaryDetailService.getAllEmployeesSalaryDetail(enterprise.getEnterpriseId(), 26);
		if(employeesSalaryDetailList.size()==0)
			employeesSalaryDetailList=new ArrayList<EmployeesSalaryDetail>();
		request.setAttribute("employeesSalaryDetail", employeesSalaryDetailList);
		return SUCCESS;
	}
	
	
	public String viewSalaryWithBankDetail()
	{
		
		
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
	
	
	
}
