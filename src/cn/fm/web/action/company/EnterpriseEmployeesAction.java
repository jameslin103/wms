package cn.fm.web.action.company;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Preparable;
import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.StringUtil;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;


@SuppressWarnings("serial")
public class EnterpriseEmployeesAction extends BaseAction implements Preparable{
	
	@Resource
	private EnterpriseEmployeesService enterpriseEmployeesService;
	@Resource
	private EnterpriseService			enterpriseService;
	
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
	private Integer   year;
	private Integer   month;
	private int page;
	private String    insuranceYear;
	private Integer   staff_renewal_reduction;
	
	 //工程目录下的模板文件名称
   private String employeeFileName;
	
    
	
   
	public Integer getStaff_renewal_reduction() {
		return staff_renewal_reduction;
	}
	public void setStaff_renewal_reduction(Integer staffRenewalReduction) {
		staff_renewal_reduction = staffRenewalReduction;
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
	
	public int getPage() {
		return page<=0?1:page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
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
	
	
	public String getInsuranceYear() {
		return insuranceYear;
	}
	public void setInsuranceYear(String insuranceYear) {
		this.insuranceYear = insuranceYear;
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
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null || enterprise.getEnterpriseId()==null)return INPUT;
		List<String> messageList=enterpriseEmployeesService.saveImportExcelEmployees(file, "增员员工信息表",34,2,enterprise);
		if(messageList!=null && messageList.size()>0){request.setAttribute("messageList", messageList);return INPUT;}
		
		return SUCCESS;
	}
	/**
	 * 查看企业所有在职员工
	 * @return
	 */
	public String viewEnterpriseEmployees(){
		Enterprise enter=WebUtil.getEnterprise(request);
		if(enter!=null)this.setEnterpriseId(enter.getEnterpriseId());
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createDate", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(this.enterpriseId!=null)
		{
			jpql.append(" o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(this.enterpriseId);
			jpql.append(" and o.reduction=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and o.departure=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and o.pseudoDelete=?").append(params.size()+1);
			params.add(0);
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		
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
	
	/**
	 * 查看参保人员与未参保人员
	 * @return
	 */
	public String fildInsuranceEnterpriseEmployees()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null || enterprise.getEnterpriseId()==null)return SUCCESS;
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createDate", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			
			jpql.append("  o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(enterprise.getEnterpriseId());
		    jpql.append(" and o.reduction=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and o.departure=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and o.pseudoDelete=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and o.whetherGinseng=?").append(params.size()+1);
			params.add(this.insurance);
			
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		
		
		
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
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
	/**
	 * 查看新增，续保，减员，人员
	 * @return
	 */
	public String viewStaffAndRenewalAndReductionEmployees(){
		
		Enterprise enterprise=WebUtil.getEnterprise(request);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			
			jpql.append(" o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(enterprise.getEnterpriseId());
			jpql.append(" and o.ginsengProtectNature=?").append(params.size()+1);
			params.add(staff_renewal_reduction);
			jpql.append(" and o.pseudoDelete=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and o.departure=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and year(o.cinsengDate)=?").append(params.size()+1);
			params.add(Integer.parseInt(insuranceYear));
			jpql.append(" and month(o.cinsengDate)=?").append(params.size()+1);
			params.add(this.month);
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}

		return SUCCESS;
	}
	
	
	/**
	 * 删除减员续保增员
	 * @return
	 */
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
	/**
	 * 查看统计企业增减员参保
	 * @return
	 */
	public String viewInsuranceWithMonth()
	{
		Enterprise  enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null)return INPUT;
		if(StringUtil.isEmpty(insuranceYear))
		{
			this.insuranceYear=DateUtil.getCurrentTime().substring(0, 4);
		}else{
			this.insuranceYear=insuranceYear.substring(0,4);
		}
	    List<Object[]>	insuranceSumTotal=enterpriseEmployeesService.getViewInsuranceWithMonthTotal(Integer.parseInt(insuranceYear),enterprise.getEnterpriseId());
		request.setAttribute("insuranceSumTotal", insuranceSumTotal);
		
		return SUCCESS;
	}
	
	/**
	 * 查看统计企业增减员参保明细表
	 * @return
	 */
	public String insuranceWithEmployeeList()
	{
		Enterprise enterprise=null;
		if(enterpriseId!=null){
			enterprise=enterpriseService.find(enterpriseId);
			request.getSession().setAttribute("enterprise", enterprise);
			
		}else{
			enterprise=WebUtil.getEnterprise(request);
		}
		
		if(enterprise==null || enterprise.getEnterpriseId()==null)return SUCCESS;
		if(StringUtil.isEmpty(insuranceYear))
		{
			this.insuranceYear=DateUtil.getCurrentTime().substring(0, 4);
		}else{
			this.insuranceYear=insuranceYear.substring(0,4);
		}
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
			jpql.append(" and o.pseudoDelete=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and o.departure=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and year(o.cinsengDate)=?").append(params.size()+1);
			params.add(Integer.parseInt(insuranceYear));
			jpql.append(" and month(o.cinsengDate)=?").append(params.size()+1);
			params.add(this.month);
			
			
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
			request.setAttribute("insuranceYear", insuranceYear);
			request.setAttribute("month", month);
		}
		return SUCCESS;
	}
	/**
	 * 批量增减员；进行数据库匹配识别
	 * @return
	 */
	public String batchIncreaseEmployees()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null || enterprise.getEnterpriseId()==null)return INPUT;
		List<String> messageList=enterpriseEmployeesService.batchIncreaseEmployees(file ,"增员续保信息表",9,2,enterprise.getEnterpriseId());
		

		boolean isNumeric=false;
		if(messageList.size()!=0){
			for (int i=0; i<messageList.size(); i++)
			{
				if(isNumeric(messageList.get(i))){
					isNumeric=true;
					if(i==0){
						request.setAttribute("zenyua", Integer.parseInt(messageList.get(0)));
					}else{
						request.setAttribute("xubao", Integer.parseInt(messageList.get(1)));
					}
					
				}
			}
			if(isNumeric==true){
				return SUCCESS;
			}
			
			request.setAttribute("message", messageList);
			return INPUT;
			
		}
		return SUCCESS;
	}
	public String insuranceReductionUI(){
		
		
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
		this.setEmployeeFileName("增员续保信息表.xls");
    	try {
			excelName = new String(employeeFileName.getBytes(), "iso8859-1");//解决中文 文件名问题
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String findIdToEmployees()
	{
		enterpriseEmployees=enterpriseEmployeesService.find(employeesId);
		
		return "enterpriseEmployees";
	}
	
	public String updateEnterpriseEmployees()
	{
		if(enterpriseEmployees==null)return SUCCESS;
		
		String sex=Integer.parseInt(enterpriseEmployees.getEmployeesSex())==1?"男":"女";
		String paymentWay= Integer.parseInt(enterpriseEmployees.getPaymentWay())==1?"个人":"企业";
		enterpriseEmployees.setEmployeesSex(sex);
		enterpriseEmployees.setPaymentWay(paymentWay);
		enterpriseEmployeesService.updateEnterpriseEmployees(enterpriseEmployees);
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
	/**
	 * 查找隐藏的员工
	 * @return
	 */
	public String findEmployeesHidden()
	{
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(this.enterpriseId!=null)
		{
			jpql.append(" o.pseudoDelete=?").append(params.size()+1);
			params.add(1);
			jpql.append(" and o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(this.enterpriseId);
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(8,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		return SUCCESS;
	}
	/**
	 * 查找离职员工
	 * @return
	 */
	public String findEmployeesDeparture()
	{
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(this.enterpriseId!=null)
		{
			jpql.append(" o.departure=?").append(params.size()+1);
			params.add(1);
			jpql.append(" and  o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(this.enterpriseId);
			
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		return SUCCESS;
	}
	/**
	 * 下载减员信息模板
	 * @return
	 */
	public String downloadReductionTemplate()
	{
		
		this.setEmployeeFileName("减员信息表.xls");
    	try {
			excelName = new String(employeeFileName.getBytes(), "iso8859-1");//解决中文 文件名问题
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 批量excel上传减员信息
	 * @return
	 */
	public String uploadInsuranceReduction()
	{
		Enterprise  enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null || enterprise.getEnterpriseId()==0)return INPUT;
		List<String> messageList=enterpriseEmployeesService.uploadExcelByInsuranceReduction(file ,"减员信息表",5,2,enterprise.getEnterpriseId());
		
		int count=0;
		if(messageList.size()!=0)
		{
			for (String str : messageList)
			{
				if(isNumeric(str)){
					count+=Integer.parseInt(str);	
				}
			}
			if(count==0){
				request.setAttribute("messageList", messageList);
				return INPUT;
			}
		}
		request.setAttribute("count", count);
		return SUCCESS;
	}
	
	//判断字符串是否为数字
	public static boolean isNumeric(String str){ 
	    Pattern pattern = Pattern.compile("[0-9]*"); 
	    return pattern.matcher(str).matches();    
	 } 
}
