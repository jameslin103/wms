package cn.fm.web.action.salary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class EmployeesSalaryDetailAction extends BaseAction{

	private EmployeesSalaryDetail employeesSalaryDetail;
	@Resource
	private EmployeesSalaryDetailService employeesSalaryDetailService;
	@Resource
	private EnterpriseEmployeesService   enterpriseEmployeesService;
	
	
	private EnterpriseEmployees   enterpriseEmployees;
	private Enterprise    enterprise;
	private File file;
	private Double   bonusTotal;
	private Double   wargeTotal;
	private Integer  enterpriseId;
	private Integer  employeesId;
	
	
	
	
	
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

	public Double getBonusTotal() {
		return bonusTotal;
	}

	public void setBonusTotal(Double bonusTotal) {
		this.bonusTotal = bonusTotal;
	}

	public Double getWargeTotal() {
		return wargeTotal;
	}

	public void setWargeTotal(Double wargeTotal) {
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

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void setEmployeesSalaryDetail(EmployeesSalaryDetail employeesSalaryDetail) {
		this.employeesSalaryDetail = employeesSalaryDetail;
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
		
		Enterprise enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		if(enterprise==null || enterprise.getEnterpriseId()==null)return INPUT;
		List<EnterpriseEmployees>  enterpriseEmployees=employeesSalaryDetailService.uploadImportWageBudgetSummary(file, "工资预算表", 31,1, enterprise.getEnterpriseId());
		if(enterpriseEmployees.size()>0){
			request.setAttribute("enterpriseEmployees", enterpriseEmployees);
			return INPUT;
		}else{
			List<EmployeesSalaryDetail> employeesSalaryDetailList=employeesSalaryDetailService.saveTempEmployeesSalaryDetail(file, "工资预算表", 31,1, enterprise.getEnterpriseId());
			for (EmployeesSalaryDetail employeesSalaryDetail : employeesSalaryDetailList) {
				
					wargeTotal+=Double.valueOf(employeesSalaryDetail.getWage().toString());
			}
			request.setAttribute("wargeTotal", wargeTotal);
			request.getSession().setAttribute("employeesSalaryDetail", employeesSalaryDetailList);
			
			
		}
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
	
	
	
	
}
