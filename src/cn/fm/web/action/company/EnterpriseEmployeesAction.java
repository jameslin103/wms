package cn.fm.web.action.company;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Preparable;

import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.StringUtil;
import cn.fm.web.action.BaseAction;


@SuppressWarnings("serial")
public class EnterpriseEmployeesAction extends BaseAction implements Preparable{
	
	@Resource 
	private EnterpriseEmployeesService enterpriseEmployeesService;
	private EnterpriseEmployees  enterpriseEmployees;
	
	private String    endContractDeadline;
	private String    startContractDeadline;
	private String    cinsengDate;  //参保日期
	private File      file;
	private Integer   employeesId;
	private Integer   insurance;
	private Integer   all;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	

	public Integer getEmployeesId() {
		return employeesId;
	}

	public void setEmployeesId(Integer employeesId) {
		this.employeesId = employeesId;
	}
	
	
	public Integer getInsurance() {
		return insurance;
	}

	public void setInsurance(Integer insurance) {
		this.insurance = insurance;
	}
	

	public Integer getAll() {
		return all;
	}

	public void setAll(Integer all) {
		this.all = all;
	}

	public void prepare() throws Exception {
		
		
	}
	public String  addEnterpriseEmployees()
	{
		
		if(enterpriseEmployees==null || enterpriseEmployees.getEmployeesName()==null || enterpriseEmployees.getEmployeesName().equals(""))return INPUT;
			enterpriseEmployeesService.save(enterpriseEmployees);
		
		
		return SUCCESS;
	}
	
	public String addImportExcelEmployees()
	{
		
		enterpriseEmployeesService.saveImportExcelEmployees(file, "增员员工信息表");
		return SUCCESS;
	}
	public String viewEnterpriseEmployees(){
		List<EnterpriseEmployees>  listEmployees=enterpriseEmployeesService.getAllEnterpriseEmployees();
		if(listEmployees.size()==0){
			listEmployees=new ArrayList<EnterpriseEmployees>();
		}
		request.setAttribute("employees", listEmployees);
		return SUCCESS;
	}
	
	public String selectEnterpriseEmployeesWage()
	{
		EnterpriseEmployees employees=getAccordingToIdEmployees();
		request.setAttribute("employees", employees);
		return SUCCESS;
	}
	
	public String viewEmployeeContract()
	{
		EnterpriseEmployees employees=getAccordingToIdEmployees();
		request.setAttribute("employees", employees);
		return SUCCESS;
	}
	
	
	public String fildInsuranceEnterpriseEmployees()
	{
		
		List<EnterpriseEmployees> listEmployees=enterpriseEmployeesService.findInsuranceEnterpriseEmployees(insurance);
		if(listEmployees.size()==0)
			listEmployees=new ArrayList<EnterpriseEmployees>();
		request.setAttribute("employees", listEmployees);
		return SUCCESS;
	}
	
	public String fildAllEnterpriseEmployees()
	{
		
		
		return SUCCESS;
	}
	
	
	
	public EnterpriseEmployees getAccordingToIdEmployees(){

		if(employeesId==null || employeesId==0)return null;
		EnterpriseEmployees employees=enterpriseEmployeesService.getEnterpriseEmployees(employeesId);
		if(employees==null)
			employees=new EnterpriseEmployees();
		
		return employees;
	}
	

	
	public void ConversionTypeFiled()
	{
		if(!StringUtil.isEmpty(this.startContractDeadline)){
			Date startDate=DateUtil.StringToDate(this.startContractDeadline, DateUtil.FORMAT_DATE);
			if(startDate!=null){
				enterpriseEmployees.setStartContractDeadline(startDate);
			}
				
		}
		if(StringUtil.isEmpty(this.endContractDeadline)){
			Date endDate=DateUtil.StringToDate(this.endContractDeadline, DateUtil.FORMAT_DATE);
			if(endDate!=null){
				enterpriseEmployees.setStartContractDeadline(endDate);
			}
				
		}
		if(!StringUtil.isEmpty(this.cinsengDate)){
			Date cinseng=DateUtil.StringToDate(this.cinsengDate, DateUtil.FORMAT_DATE);
			if(cinseng!=null){
				enterpriseEmployees.setCinsengDate(cinseng);
			}
				
		}
	}

	
	
	
	
	
	
	public void vialteFile(){
		if(StringUtil.isEmpty(enterpriseEmployees.getEmployeesName())){
			this.addFieldError(enterpriseEmployees.getEmployeesName(), "姓名必填*");
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getNativePlace())){
			this.addFieldError(enterpriseEmployees.getNativePlace(), "籍贯必填项");
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getCardNumber()))
		{
			this.addFieldError(enterpriseEmployees.getCardNumber(), "身份证必填项*");
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getPhone()))
		{
			this.addFieldError(enterpriseEmployees.getPhone(), "电话必填项*");
			
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getHomeAddress()))
		{
			this.addFieldError(enterpriseEmployees.getHomeAddress(), "家庭地址必填项*");
			
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getBankCardNumber()))
		{
			this.addFieldError(enterpriseEmployees.getBankCardNumber(), "银行卡号必填项*");
		}
		if(StringUtil.isEmpty(enterpriseEmployees.getBank()))
		{
			this.addFieldError(enterpriseEmployees.getBank(), "");
		}
	}
	public EnterpriseEmployeesService getEnterpriseEmployeesService() {
		return enterpriseEmployeesService;
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
	public String getEndContractDeadline() {
		return endContractDeadline;
	}
	public void setEndContractDeadline(String endContractDeadline) {
		this.endContractDeadline = endContractDeadline;
	}
	public String getStartContractDeadline() {
		return startContractDeadline;
	}
	public void setStartContractDeadline(String startContractDeadline) {
		this.startContractDeadline = startContractDeadline;
	}
	public String getCinsengDate() {
		return cinsengDate;
	}
	public void setCinsengDate(String cinsengDate) {
		this.cinsengDate = cinsengDate;
	}

	
	

}
