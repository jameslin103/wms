package cn.fm.web.action.company;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Preparable;

import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.ExcelFileGenerator;
import cn.fm.utils.ExportExcelUtils;
import cn.fm.utils.StringUtil;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;
import cn.fm.web.action.ReportAction;


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
	private String    inputPath;  
	private String    excelName;
	private Integer   enterpriseId;
	private String    employessName;
	
	
	
	 //工程目录下的模板文件名称
   private String employeeFileName;
	
    
    
    
	public String getEmployeeFileName() {
		return employeeFileName;
	}
	public void setEmployeeFileName(String employeeFileName) {
		this.employeeFileName = employeeFileName;
	}
	public String getEmployessName() {
		return employessName;
	}
	public void setEmployessName(String employessName) {
		this.employessName = employessName;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) throws UnsupportedEncodingException {
		this.excelName = new String(excelName.getBytes("ISO8859-1"), "utf-8");
	}
	public String getInputPath() {  
	       return inputPath;  
	 }  
	 public void setInputPath(String inputPath) {  
	        this.inputPath = inputPath;  
	 }  

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
	
	  public InputStream getDownloadFile()  
	   {  
	        return ServletActionContext.getServletContext().getResourceAsStream("/doc/"+this.getEmployeeFileName());  
	        
	        
	    }  
	    public String employeesFileExcelTempl()
	    {  
	    	this.setEmployeeFileName("员工基本信息表.xls");
	    	try {
				excelName = new String(employeeFileName.getBytes(), "iso8859-1");//解决中文 文件名问题
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
	    	return SUCCESS;
	    }
	
	
	
	public String  addEnterpriseEmployees()
	{
		
		if(enterpriseEmployees==null || enterpriseEmployees.getEmployeesName()==null || enterpriseEmployees.getEmployeesName().equals(""))return INPUT;
			enterpriseEmployeesService.save(enterpriseEmployees);
		
		
		return SUCCESS;
	}
	/**
	 * excel批量导入
	 * @return success
	 */
	public String addImportExcelEmployees()
	{
		Enterprise enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		if(enterprise==null || enterprise.getEnterpriseId()==null)return INPUT;
		enterpriseEmployeesService.saveImportExcelEmployees(file, "增员员工信息表",34,2,enterprise);
		return SUCCESS;
	}
	/**
	 * 
	 * @return
	 */
	public String viewEnterpriseEmployees(){
		Enterprise enter=WebUtil.getEnterprise(request);
		if(enter!=null)this.setEnterpriseId(enter.getEnterpriseId());
		List<EnterpriseEmployees>  listEmployees=enterpriseEmployeesService.getAllEnterpriseEmployees(enter.getEnterpriseId());
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createDate", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(this.enterpriseId!=null)
		{
			jpql.append(" o.enterprise.id=? ");
			params.add(this.enterpriseId);
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		
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
		Enterprise enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		if(StringUtil.isEmpty(employessName) && all==null)return INPUT;
			List<EnterpriseEmployees> enterpriseEmployeesList=enterpriseEmployeesService.findAllEnterpriseEmployees(employessName, all, enterprise.getEnterpriseId());
			if(enterpriseEmployeesList==null ||enterpriseEmployeesList.size()==0)
				enterpriseEmployeesList=new ArrayList<EnterpriseEmployees>();
			request.setAttribute("enterpriseEmployeesList", enterpriseEmployeesList);
		
		return SUCCESS;
	}
	
	
	
	public EnterpriseEmployees getAccordingToIdEmployees(){

		if(employeesId==null || employeesId==0)return null;
		EnterpriseEmployees employees=enterpriseEmployeesService.findEnterpriseEmployees(employeesId);
		if(employees==null)
			employees=new EnterpriseEmployees();
		
		return employees;
	}
	
	public String  batchExcelDataEmployee()
	{
		
		return SUCCESS;
	}
	
	public String newStaffEmployees(){
		
		List<EnterpriseEmployees> enterpriseEmployeesList=enterpriseEmployeesService.findNewStaffAndRenewalEmployees(this.enterpriseId, "新增");
		if(enterpriseEmployeesList.size()==0)
			enterpriseEmployeesList=new ArrayList<EnterpriseEmployees>();
		request.setAttribute("employees", enterpriseEmployeesList);
		return SUCCESS;
	}
	
	public String  renewalEmployees()
	{
		List<EnterpriseEmployees> enterpriseEmployeesList=enterpriseEmployeesService.findNewStaffAndRenewalEmployees(this.enterpriseId, "续保");
		if(enterpriseEmployeesList.size()==0)
			enterpriseEmployeesList=new ArrayList<EnterpriseEmployees>();
		request.setAttribute("employees", enterpriseEmployeesList);
		return SUCCESS;
	}

	public String deleteEmployees()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterpriseEmployees!=null)
			enterpriseEmployeesService.delete((Serializable)enterpriseEmployees.getEmployeesId());
		List<EnterpriseEmployees> enterprisEmployeesList=enterpriseEmployeesService.findWorkersIncreasedToEmployees(enterprise.getEnterpriseId());
		if(enterprisEmployeesList.size()==0)
			enterprisEmployeesList=new ArrayList<EnterpriseEmployees>();
		request.setAttribute("employees", enterprisEmployeesList);
		return SUCCESS;
		
	}
	
	public String viewInsuranceWithMonth()
	{
		
		return SUCCESS;
	}
	/**
	 * 批量增减员；进行数据库匹配识别
	 * @return
	 */
	public String batchIncreaseEmployees()
	{
		
		boolean falg=enterpriseEmployeesService.batchIncreaseEmployees(file ,"社医保增减员",16,1);
		if(falg==false){
			request.setAttribute("message", "数据有误上传失败!!");
			return INPUT;
			
		}
		
		
		return SUCCESS;
	}
	public String increaseEmployeesUI()
	{
		
		return SUCCESS;
	}
	
	
	/**
	 * 下载社医保增减员模板
	 * @return 下载文件名称
	 */
	public String downloadBatchIncreaseEmployeesExcel()
	{
		this.setEmployeeFileName("社医保增减员.xls");
    	try {
			excelName = new String(employeeFileName.getBytes(), "iso8859-1");//解决中文 文件名问题
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
	
	
	/**
	 * 日期转换格式
	 */
	
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
