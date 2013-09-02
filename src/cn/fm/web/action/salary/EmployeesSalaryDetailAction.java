package cn.fm.web.action.salary;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class EmployeesSalaryDetailAction extends BaseAction{

	private EmployeesSalaryDetail employeesSalaryDetail;
	@Resource
	private EmployeesSalaryDetailService employeesSalaryDetailService;
	private EnterpriseEmployees   enterpriseEmployees;
	private Enterprise    enterprise;
	private File file;
	private Double   bonusTotal;
	private Double   wargeTotal;
	
	
	
	
	
	
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
		if(enterprise==null || enterprise.getId()==null)return INPUT;
		List<EnterpriseEmployees>  enterpriseEmployees=employeesSalaryDetailService.uploadImportWageBudgetSummary(file, "工资预算表", 31, enterprise.getId());
		if(enterpriseEmployees.size()>0){
			request.setAttribute("enterpriseEmployees", enterpriseEmployees);
			return INPUT;
		}else{
			List<EmployeesSalaryDetail> employeesSalaryDetailList=employeesSalaryDetailService.saveTempEmployeesSalaryDetail(file, "工资预算表", 31, enterprise.getId());
			for (EmployeesSalaryDetail employeesSalaryDetail : employeesSalaryDetailList) {
				
					wargeTotal+=Double.valueOf(employeesSalaryDetail.getWage().toString());
			}
			request.setAttribute("wargeTotal", wargeTotal);
			request.getSession().setAttribute("employeesSalaryDetail", employeesSalaryDetailList);
			
			
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
