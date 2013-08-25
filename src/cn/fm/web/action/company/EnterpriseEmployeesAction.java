package cn.fm.web.action.company;

import java.io.File;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import com.opensymphony.xwork2.Preparable;

import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.ExcelFileGenerator;
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
	/**
	 * excel批量导入
	 * @return success
	 */
	public String addImportExcelEmployees()
	{
		Enterprise enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		if(enterprise==null || enterprise.getId()==null)return INPUT;
		enterpriseEmployeesService.saveImportExcelEmployees(file, "增员员工信息表",34,enterprise.getId());
		return SUCCESS;
	}
	/**
	 * 
	 * @return
	 */
	public String viewEnterpriseEmployees(){
		List<EnterpriseEmployees>  listEmployees=enterpriseEmployeesService.getAllEnterpriseEmployees();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createDate", "desc");
		PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
		pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), pageView.getMaxresult(), orderby));
		if(listEmployees.size()==0){
			listEmployees=new ArrayList<EnterpriseEmployees>();
		}
		request.setAttribute("pageView", pageView);
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
	
	/**  
	* @Name: export
	* @Description:导出excel的报表数据
	* @Author: 刘洋（作者）
	* @Version: V1.00 （版本号）
	* @Create Date: 2011-12-31 （创建日期）
	* @Parameters: 无
	* @Return: 无
	*/
	public String export(){
		//获取导出的表头和数据
		//获取表头,存放到ArrayList filedName对象中(登录名	用户姓名	性别	联系电话	是否在职)
		ArrayList<String> filedName = enterpriseEmployeesService.getExcelFiledNameList(); 
		Enterprise  enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		ArrayList<EnterpriseEmployees> filedData = enterpriseEmployeesService.getExcelFiledDataList(enterpriseEmployees,enterprise.getId());
		try {
			//获取输出流
			OutputStream out = response.getOutputStream();
			//重置输出流
			response.reset();
			//设置导出Excel报表的导出形式
			response.setContentType("application/vnd.ms-excel");
			ExcelFileGenerator generator = new ExcelFileGenerator(filedName,filedData);
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
		}
		return null;
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
