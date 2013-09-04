package cn.fm.web.action.company;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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
import cn.fm.utils.ExcelFileGenerator;
import cn.fm.utils.ExportExcelUtils;
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
	private String    inputPath;  
	private String    excelName;
	private Integer   enterpriseId;
	private String    employessName;
	
	
	
	 //工程目录下的模板文件名称
   private String employeeFileName ="员工基本信息表.xls";
	
    
    
    
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
	        return ServletActionContext.getServletContext().getResourceAsStream("/doc/"+employeeFileName);  
	        
	        
	    }  
	    public String employeesFileExcelTempl()
	    {  
	    
	    	try {
				excelName = new String(employeeFileName.getBytes(), "iso8859-1");//解决中文 文件名问题
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
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
		enterpriseEmployeesService.saveImportExcelEmployees(file, "增员员工信息表",34,enterprise);
		return SUCCESS;
	}
	/**
	 * 
	 * @return
	 */
	public String viewEnterpriseEmployees(){
		Enterprise enter=(Enterprise)request.getSession().getAttribute("enterprise");
		if(enter!=null)this.setEnterpriseId(enter.getEnterpriseId());
		List<EnterpriseEmployees>  listEmployees=enterpriseEmployeesService.getAllEnterpriseEmployees(this.enterpriseId);
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
	
	/**  
	* @Name: export
	* @Description:导出excel的报表数据
	* @Author: jameslin（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2013-08-25（创建日期）
	* @Parameters: 无
	* @Return: 无
	*/
	public String exportExcel(){
		//获取导出的表头和数据
		//获取表头,存放到ArrayList filedName对象中()
		List<String> filedName = enterpriseEmployeesService.getExcelFiledNameList(); 
		Enterprise  enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		List<EnterpriseEmployees> filedData =enterpriseEmployeesService.getExcelFiledDataList(enterpriseEmployees,enterprise.getEnterpriseId());
		try {
			//获取输出流
			OutputStream out = response.getOutputStream();
			//重置输出流
			response.reset();
			//设置导出Excel报表的导出形式
			response.setContentType("application/vnd.ms-excel");
			
			ExcelFileGenerator generator = new ExcelFileGenerator(filedName,filedData);
			ExportExcelUtils<EnterpriseEmployees>  exp=new ExportExcelUtils<EnterpriseEmployees>();
			
			generator.expordExcel(out);
			//设置输出形式
			System.setOut(new PrintStream(out));
			//刷新输出流
			out.flush();
			//关闭输出流
			if(out!=null){
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getClass();
			e.toString();
		}
		return null;
	}

	
	
	public String  batchExcelDataEmployee()
	{
		
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
	 * @author james
	 * @date 2013-09-03
	 * 校验字段
	 */
	
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
