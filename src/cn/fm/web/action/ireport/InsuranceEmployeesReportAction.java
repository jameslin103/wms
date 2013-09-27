package cn.fm.web.action.ireport;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.ReportAction;

@SuppressWarnings("serial")
public class InsuranceEmployeesReportAction extends ReportAction {
	@Resource
	private EnterpriseEmployeesService  		enterpriseEmployeesService;
	@Resource
	private EmployeesSalaryDetailService        employeesSalaryDetailService;
	@Resource
	private CreateSalaryBudgetTableService		createSalaryBudgetTableService;
	
	
	private Integer								budgetId;
	
	
	
	
	
	

	
	public Integer getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}
	public void setEnterpriseEmployeesService(
			EnterpriseEmployeesService enterpriseEmployeesService) {
		this.enterpriseEmployeesService = enterpriseEmployeesService;
	}
/**
 * 下载社医保办理与减员表
 * @return  
 */
	public String downloadInsuranceWithEmployeeList()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		WmsUser    user=WebUtil.getWmsUser(request);
		if(enterprise.getEnterpriseId()==null)return INPUT;
		List<EnterpriseEmployees> employeesList=enterpriseEmployeesService.getAllEnterpriseEmployees(enterprise.getEnterpriseId());
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("title",enterprise.getFullName()); 
		parameters.put("username",user.getUsername()); 
		 //String currentPath = ServletActionContext.getServletContext().getRealPath("");
		 //String sqlJasper= currentPath+"/report/insurance_with_employee_list.jrxml";
		 //String sqlXmlFile= WMSResource.getResourcesRootPath()+"/report/insurance_with_employee_list.jrxml";
		  String sqlJasper="insurance_with_employee_list.jasper";
		try {
			downloadExcel(sqlJasper, "社医保办理与减员表", parameters,employeesList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**  
	* @Name: export
	* @Description:导出excel全体在职员工信息的报表数据
	* @Author: jameslin（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-08-25（创建日期）
	* @Parameters: 无
	* @Return: 无
	*/
	public String exportEmployeesExcel(){
		Enterprise enterprise=WebUtil.getEnterprise(request);
		WmsUser    user=WebUtil.getWmsUser(request);
		if(enterprise.getEnterpriseId()==null)return INPUT;
		List<EnterpriseEmployees> employeesList=enterpriseEmployeesService.getAllEnterpriseEmployees(enterprise.getEnterpriseId());
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("fullname",enterprise.getFullName()); 
		parameters.put("username",user.getUsername()); 
		String sqlJasper="employee-list.jasper";
		 
		try {
			downloadExcel(sqlJasper, "全体在职员工信息表", parameters,employeesList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	public String downloadEmployeesSalaryDetail()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		StringBuffer  sb=new StringBuffer();
		WmsUser    user=WebUtil.getWmsUser(request);
		if(enterprise.getEnterpriseId()==null)return INPUT;
		CreateSalaryBudgetTable createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
		List<EmployeesSalaryDetail> employeesSalaryDetailList=employeesSalaryDetailService.getAllEmployeesSalaryDetail(enterprise.getEnterpriseId(),budgetId);
		Map<String, Object> parameters=new HashMap<String, Object>();
		String salaryDate=DateUtil.dateToString(createSalaryBudgetTable.getSalaryDate(),DateUtil.FORMAT_DATE_MONTH);
		String currentPath = ServletActionContext.getServletContext().getRealPath("");
		String images= currentPath+"/images/fullname.jpg";
		
		sb.append(salaryDate).append("份各类费用预算表");
		
		parameters.put("salaryDate",sb.toString());
		parameters.put("createDate",DateUtil.dateToString(createSalaryBudgetTable.getCreateDate(),DateUtil.FORMAT_DATE_MONTH));
		parameters.put("makeTotal",createSalaryBudgetTable.getMakeTotal().toString());
		parameters.put("wageTotal",createSalaryBudgetTable.getWageTotal().toString());
		parameters.put("serviceTotal",createSalaryBudgetTable.getServiceTotal().toString());
		parameters.put("fiveInsurancesTotal",createSalaryBudgetTable.getFiveInsurancesTotal().toString());
		parameters.put("budgetName", createSalaryBudgetTable.getName());
		parameters.put("fullname",enterprise.getFullName()); 
		parameters.put("username",user.getUsername());
		parameters.put("image", images);
		
		
		String sqlJasper="salaryDateail.jasper";
		 
		try {
			downloadExcel(sqlJasper, "员工工资明细表", parameters,employeesSalaryDetailList);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	public String downloadBankIssueSalary()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		WmsUser    user=WebUtil.getWmsUser(request);
		if(enterprise.getEnterpriseId()==null)return INPUT;
		String currentPath = ServletActionContext.getServletContext().getRealPath("");
		String images= currentPath+"/images/fullname.jpg";
		List<EnterpriseEmployees> employeesList=enterpriseEmployeesService.getAllEnterpriseEmployees(enterprise.getEnterpriseId());
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("title",enterprise.getFullName()); 
		parameters.put("username",user.getUsername()); 
		parameters.put("image",images); 
		String sqlJasper="salary-with-bank-detail.jasper";
		 
		try {
			downloadExcel(sqlJasper, "银行发放信息表", parameters,employeesList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String downloadSalaryWithSumOfCategoriesReport()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		WmsUser    user=WebUtil.getWmsUser(request);
		if(enterprise.getEnterpriseId()==null)return INPUT;
		String currentPath = ServletActionContext.getServletContext().getRealPath("");
		String images= currentPath+"/images/fullname.jpg";
		List<CreateSalaryBudgetTable> wageBudgetSummaryList=createSalaryBudgetTableService.getFindCreateSalaryBudgetTables(budgetId);
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("fullName",enterprise.getFullName()); 
		parameters.put("username",user.getUsername()); 
		parameters.put("image",images); 

		String sqlJasper="salary-with-sum-of-categories.jasper";
		 
		try {
			downloadExcel(sqlJasper, "工资预算表汇总信息表", parameters,wageBudgetSummaryList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	

}
