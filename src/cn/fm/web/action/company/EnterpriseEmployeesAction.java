package cn.fm.web.action.company;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Preparable;
import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.StringUtil;
import cn.fm.utils.WebUtil;
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
	private String    inputPath;  
	private String    excelName;
	private Integer   enterpriseId;
	private String    employessName;
	private Integer   year;
	private Integer   month;
	private int page;
	
	 //工程目录下的模板文件名称
   private String employeeFileName;
	
    
	

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
	 * 查看企业所有在职员工
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
			jpql.append(" o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(this.enterpriseId);
			jpql.append(" and o.departure=?").append(params.size()+1);
			params.add(0);
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(8,  this.getPage());
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
	
	/**
	 * 查看参保人员与未参保人员
	 * @return
	 */
	public String fildInsuranceEnterpriseEmployees()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null || enterprise.getEnterpriseId()==null)return SUCCESS;
		
		List<EnterpriseEmployees> listEmployees=enterpriseEmployeesService.findInsuranceEnterpriseEmployees(insurance,enterprise.getEnterpriseId());
		if(listEmployees==null || listEmployees.size()==0)
			listEmployees=new ArrayList<EnterpriseEmployees>();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			jpql.append(" o.whetherGinseng=?1 and ");
			jpql.append(" o.enterprise.enterpriseId=?2 ");
			params.add(this.insurance);
			params.add(this.enterpriseId);
			
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		
		
		
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
	/**
	 * 查看新增人员
	 * @return
	 */
	public String newStaffEmployees(){
		
		List<EnterpriseEmployees> enterpriseEmployeesList=enterpriseEmployeesService.findNewStaffAndRenewalEmployees(this.enterpriseId, "新增");
		if(enterpriseEmployeesList.size()==0)
			enterpriseEmployeesList=new ArrayList<EnterpriseEmployees>();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(this.enterpriseId!=null)
		{
			
			jpql.append(" o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(this.enterpriseId);
			jpql.append(" and o.ginsengProtectNature like ?").append(params.size()+1);
			params.add("%新增%");
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}

		request.setAttribute("employees", enterpriseEmployeesList);
		return SUCCESS;
	}
	
	/**
	 * 查看续保人员
	 * @return
	 */
	public String  renewalEmployees()
	{
		List<EnterpriseEmployees> enterpriseEmployeesList=enterpriseEmployeesService.findNewStaffAndRenewalEmployees(this.enterpriseId, "续保");
		if(enterpriseEmployeesList==null || enterpriseEmployeesList.size()==0)
			enterpriseEmployeesList=new ArrayList<EnterpriseEmployees>();
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(this.enterpriseId!=null)
		{
			jpql.append(" o.ginsengProtectNature like ?").append(params.size()+1);
			params.add("%续保%");
			jpql.append(" and  o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(this.enterpriseId);
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		
		request.setAttribute("employees", enterpriseEmployeesList);
		return SUCCESS;
	}
	/**
	 * 查看减员情况
	 * @return
	 */
	public String personnelReduction()
	{
		List<EnterpriseEmployees> enterpriseEmployeesList=enterpriseEmployeesService.findNewStaffAndRenewalEmployees(this.enterpriseId, "减员");
		if(enterpriseEmployeesList==null || enterpriseEmployeesList.size()==0)
			enterpriseEmployeesList=new ArrayList<EnterpriseEmployees>();
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(this.enterpriseId!=null)
		{
			jpql.append(" o.ginsengProtectNature like ?").append(params.size()+1);
			params.add("%减员%");
			jpql.append(" and  o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(this.enterpriseId);
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		
		request.setAttribute("employees", enterpriseEmployeesList);
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
		
		return SUCCESS;
	}
	
	/**
	 * 查看统计企业增减员参保明细表
	 * @return
	 */
	public String insuranceWithEmployeeList()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null || enterprise.getEnterpriseId()==null)return SUCCESS;
		List<EnterpriseEmployees> employees=enterpriseEmployeesService.getInsuranceWithEmployeeList(enterprise.getEnterpriseId(),2013,8);
		if(employees==null || employees.size()==0)employees=new ArrayList<EnterpriseEmployees>();
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			jpql.append("( o.ginsengProtectNature like ?").append(params.size()+1);
			params.add("%新增%");
			jpql.append("  or  o.ginsengProtectNature like ?").append(params.size()+1);
			params.add("%续保%");
			jpql.append(" or o.ginsengProtectNature like ?").append(params.size()+1).append(")");
			params.add("%减员%");
			jpql.append(" and o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(this.enterpriseId);
			jpql.append(" and o.pseudoDelete=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and o.departure=?").append(params.size()+1);
			params.add(0);
			jpql.append(" and year(o.cinsengDate)=?").append(params.size()+1);
			params.add(this.year);
			jpql.append(" and month(o.cinsengDate)=?").append(params.size()+1);
			params.add(this.month);
			
			
			
			PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
			pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		request.setAttribute("employees", employees);
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
		this.setEmployeeFileName("社医保增减员.xls");
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
		
		List<EnterpriseEmployees>  listEmployees=enterpriseEmployeesService.findEmployeesHidden(enterpriseId);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(this.enterpriseId!=null)
		{
			jpql.append(" o.pseudoDelete=0  and ");
			jpql.append(" o.enterprise.enterpriseId=?1 ");
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
	/**
	 * 查找离职员工
	 * @return
	 */
	public String findEmployeesDeparture()
	{
		
		List<EnterpriseEmployees>  listEmployees=enterpriseEmployeesService.findEmployeesDeparture(enterpriseId);
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(this.enterpriseId!=null)
		{
			jpql.append(" o.departure=?1 and ");
			jpql.append(" o.enterprise.enterpriseId=?2 ");
			params.add(1);
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

	

}
