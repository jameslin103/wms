package cn.fm.web.action.ireport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.company.InsurancesTax;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.bean.user.User;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.company.InsurancesTaxService;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.service.user.UserService;
import cn.fm.utils.Constant;
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
	@Resource
	private InsurancesTaxService				insurancesTaxService;
	@Resource
	private EnterpriseService					enterpriseService;
	@Resource
	private UserService							userService;
	
	
	private InsurancesTax						InsurancesTax;
	
	private Integer								budgetId;
	
	private String      selected;
	
	private Integer     enterpriseId;
	
	//全部
	private boolean     allexport;
	
	//民生银行
	private boolean     minshengbank;
	
	//其它银行
	private boolean     otherbanks;
	
	//现金
	private boolean     cashissue;
	
	

	

/**
 * 下载社医保办理与减员表
 * @return  
 */
	public String downloadInsuranceWithEmployeeList()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		User    user=WebUtil.getUser(request);
		if(enterprise.getEnterpriseId()==null)return INPUT;
		List<EnterpriseEmployees> employeesList=enterpriseEmployeesService.getAllEnterpriseEmployees(enterprise.getEnterpriseId());
		Map<String, Object> parameters=new HashMap<String, Object>();
		String currentPath = ServletActionContext.getServletContext().getRealPath("");
		String images= currentPath+"/images/fullname.jpg";
		parameters.put("title",enterprise.getFullName()); 
		parameters.put("username",user.getEmployee().getName()); 
		parameters.put("image",images); 
		 //String currentPath = ServletActionContext.getServletContext().getRealPath("");
		 //String sqlJasper= currentPath+"/report/insurance_with_employee_list.jrxml";
		 //String sqlXmlFile= WMSResource.getResourcesRootPath()+"/report/insurance_with_employee_list.jrxml";
		  String sqlJasper="insurance_with_employee_list.jasper";
		try {
			downloadExcel(sqlJasper, enterprise.getFullName()+"社医保办理与减员表", parameters,employeesList);
			
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
		User    user=WebUtil.getUser(request);
		User    userPO=userService.getById(user.getId());
		if(enterprise.getEnterpriseId()==null)return INPUT;
		List<EnterpriseEmployees> employeesList=enterpriseEmployeesService.getAllEnterpriseEmployees(enterprise.getEnterpriseId());
		Map<String, Object> parameters=new HashMap<String, Object>();
		String currentPath = ServletActionContext.getServletContext().getRealPath("");
		String images= currentPath+"/images/fullname.jpg";
		parameters.put("fullname",enterprise.getFullName()); 
		parameters.put("username",userPO.getEmployee().getName()); 
		parameters.put("image",images); 
		String sqlJasper="employee-list.jasper";
		 
		try {
			downloadExcel(sqlJasper, enterprise.getFullName()+"在职员工信息表", parameters,employeesList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	/**
	 * 工资各类明细
	 * @return
	 */
	public String downloadEmployeesSalaryDetail()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		StringBuffer  sb=new StringBuffer();
		if(enterprise.getEnterpriseId()==null)return INPUT;
		InsurancesTax=insurancesTaxService.getInsurancesTax();
		CreateSalaryBudgetTable createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
		List<EmployeesSalaryDetail> employeesSalaryDetailList=employeesSalaryDetailService.getAllEmployeesSalaryDetail(enterprise.getEnterpriseId(),budgetId);
		Map<String, Object> parameters=new HashMap<String, Object>();
		String salaryDate=DateUtil.dateToString(createSalaryBudgetTable.getSalaryDate(),DateUtil.FORMAT_DATE_MONTH);
		String currentPath = ServletActionContext.getServletContext().getRealPath("");
		String images= currentPath+"/images/fullname.jpg";
		
		sb.append(salaryDate).append("-份各类费用预算表");
		
		parameters.put("salaryDate",sb.toString());
		parameters.put("createDate",DateUtil.dateToString(createSalaryBudgetTable.getCreateDate(),DateUtil.FORMAT_DATE_MONTH));
		parameters.put("makeTotal",createSalaryBudgetTable.getMakeTotal()==null?"":createSalaryBudgetTable.getMakeTotal().toString());
		parameters.put("wageTotal",createSalaryBudgetTable.getWageTotal()==null?"":createSalaryBudgetTable.getWageTotal().toString());
		parameters.put("serviceTotal",createSalaryBudgetTable.getServiceTotal()==null?"":createSalaryBudgetTable.getServiceTotal().toString());
		parameters.put("fiveInsurancesTotal",createSalaryBudgetTable.getFiveInsurancesTotal()==null?"":createSalaryBudgetTable.getFiveInsurancesTotal().toString());
		parameters.put("budgetName", createSalaryBudgetTable.getName());
		parameters.put("serviceHeTotal", createSalaryBudgetTable.getServiceHeTotal());
		parameters.put("fullname",createSalaryBudgetTable.getEnterprise().getFullName()); 
		parameters.put("username",createSalaryBudgetTable.getUser().getEmployee().getName());
		parameters.put("usernote",createSalaryBudgetTable.getNote());
		parameters.put("image", images);
		parameters.put("endowmentInsurance", InsurancesTax.getBirthEnterprise()+"%");
		parameters.put("personalEndowmentInsurance", InsurancesTax.getPersonalEndowmentInsurance()+"%");
		parameters.put("unemploymentInsurance", InsurancesTax.getUnemploymentInsurance()+"%");
		parameters.put("personalUnemploymentInsurance", InsurancesTax.getPersonalUnemploymentInsurance()+"%");
		parameters.put("birthEnterprise", InsurancesTax.getBirthEnterprise()+"%");
		parameters.put("injuriesEnterprise", InsurancesTax.getInjuriesEnterprise()+"%");
		parameters.put("medicalEnterprise", InsurancesTax.getMedicalEnterprise()+"%");
		parameters.put("personalEnterprise", InsurancesTax.getPersonalEnterprise()+"%");
		parameters.put("housingFundEnterprise", InsurancesTax.getHousingFundEnterprise()+"%");
		parameters.put("personalHousingFund", InsurancesTax.getPersonalHousingFund()+"%");
		
		String sqlJasper;
		if(selected.equals("true")){
			sqlJasper="special_salaryDateail.jasper";
		}else{
			sqlJasper="salaryDateail.jasper";
		}
	
		 sb.append("-"+enterprise.getFullName());
		try {
			downloadExcel(sqlJasper, sb.toString()+"-工资明细表", parameters,employeesSalaryDetailList);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 银行发放明细表
	 * @return
	 */
	public String downloadBankIssueSalary()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null){
			if(enterpriseId==null)return INPUT;
				enterprise=enterpriseService.find(enterpriseId);
				request.getSession().setAttribute("enterprise", enterprise);
		}
		if(enterprise.getEnterpriseId()==null)return INPUT;
		String currentPath = ServletActionContext.getServletContext().getRealPath("");
		String images= currentPath+"/images/fullname.jpg";
		CreateSalaryBudgetTable createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
		String salaryDate=DateUtil.dateToString(createSalaryBudgetTable.getSalaryDate(),DateUtil.FORMAT_DATE_MONTH);
		List<EmployeesSalaryDetail> employeesSalaryDetailList=employeesSalaryDetailService.getBankEmployeesSalaryDetail(budgetId);
		Map<String, Object> parameters=new HashMap<String, Object>();
		if(createSalaryBudgetTable==null)createSalaryBudgetTable=new CreateSalaryBudgetTable();
		
		String username=(createSalaryBudgetTable.getUser()!=null)?(createSalaryBudgetTable.getUser().getEmployee().getName()):"".toString();
		parameters.put("title",enterprise.getFullName()); 
		parameters.put("username",username); 
		parameters.put("salaryDate",createSalaryBudgetTable.getSalaryDate()==null?"":DateUtil.dateToString(createSalaryBudgetTable.getSalaryDate(), DateUtil.FORMAT_DATE_MONTH)+Constant.WMS_SALARY); 
		parameters.put("image",images); 
		String sqlJasper="salary-with-bank-detail.jasper";
		 
		try {
			downloadExcel(sqlJasper, enterprise.getFullName()+"-"+salaryDate+"-(银行发放信息表)", parameters,employeesSalaryDetailList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String downloadSalaryWithSumOfCategoriesReport()
	{       
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise.getEnterpriseId()==null)return INPUT;
		String currentPath = ServletActionContext.getServletContext().getRealPath("");
		String images= currentPath+"/images/fullname.jpg";
		List<CreateSalaryBudgetTable> wageBudgetSummaryList=createSalaryBudgetTableService.getFindCreateSalaryBudgetTables(budgetId);
		CreateSalaryBudgetTable createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("fullName",enterprise.getFullName()); 
		parameters.put("username",createSalaryBudgetTable.getUser().getEmployee().getName()); 
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
	
	

	
	
	
	
	
	
	
	public boolean isAllexport() {
		return allexport;
	}
	public void setAllexport(boolean allexport) {
		this.allexport = allexport;
	}
	public boolean isMinshengbank() {
		return minshengbank;
	}
	public void setMinshengbank(boolean minshengbank) {
		this.minshengbank = minshengbank;
	}
	public boolean isOtherbanks() {
		return otherbanks;
	}
	public void setOtherbanks(boolean otherbanks) {
		this.otherbanks = otherbanks;
	}
	public boolean isCashissue() {
		return cashissue;
	}
	public void setCashissue(boolean cashissue) {
		this.cashissue = cashissue;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getSelected() {
		return selected;
	}
	public void setSelected(String selected) {
		this.selected = selected;
	}
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
	
	
	public InsurancesTax getInsurancesTax() {
		return InsurancesTax;
	}
	public void setInsurancesTax(InsurancesTax insurancesTax) {
		InsurancesTax = insurancesTax;
	}
	
}
