package cn.fm.web.action.company;



import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class CreateSalaryBudgetTableAction extends BaseAction {
	
	@Resource
	private CreateSalaryBudgetTableService createSalaryBudgetTableService;
	@Resource
	private SalaryTemplateService           salaryTemplateService;
	@Resource
	private EnterpriseService               enterpriseService;
	@Resource 
	private EmployeesSalaryDetailService  employeesSalaryDetailService;
	
	private CreateSalaryBudgetTable      createSalaryBudgetTable;
	
	List<CreateSalaryBudgetTable>  createSalaryBudgetTableList;
	
	private String salaryFileName ="工资预算表.xls";
	
	private String excelName;

	private String salaryDate;
	
	private String error;
	
	private String message; 
	
	private File   file;
	
	private Integer templateId;
	
	private Integer  enterpriseId;
	
	private Integer  budgetId;
	
	
	
	public Integer getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
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
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public String getMessage() {   
	        return message;   
	  }   
	 public void setMessage(String message) {   
	        this.message = message;   
	 }   
	public String getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}
	
	
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public List<CreateSalaryBudgetTable> getCreateSalaryBudgetTableList() {
		return createSalaryBudgetTableList;
	}

	public void setCreateSalaryBudgetTableList(
			List<CreateSalaryBudgetTable> createSalaryBudgetTableList) {
		this.createSalaryBudgetTableList = createSalaryBudgetTableList;
	}

	public CreateSalaryBudgetTable getCreateSalaryBudgetTable() {
		return createSalaryBudgetTable;
	}

	public void setCreateSalaryBudgetTable(
			CreateSalaryBudgetTable createSalaryBudgetTable) {
		this.createSalaryBudgetTable = createSalaryBudgetTable;
	}
	
	public void setCreateSalaryBudgetTableService(
			CreateSalaryBudgetTableService createSalaryBudgetTableService) {
		this.createSalaryBudgetTableService = createSalaryBudgetTableService;
	}
	
	/**
	 * 查看当前企业的预算表
	 * @return
	 */
	public String viewSalaryBudgetTable()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null || enterprise.getEnterpriseId()==null){
			enterprise=enterpriseService.find(enterpriseId);
			request.getSession().setAttribute("enterprise",enterprise );
			
		}
		
		List<CreateSalaryBudgetTable> createSalaryBudgetTableList=createSalaryBudgetTableService.getAllCreateSalaryBudgetTable(enterprise.getEnterpriseId());
		if(createSalaryBudgetTableList.size()==0)
			createSalaryBudgetTableList=new ArrayList<CreateSalaryBudgetTable>();
		request.setAttribute("createSalaryBudgetTable", createSalaryBudgetTableList);
		
		
		return SUCCESS;
	}
	/**
	 * 新增工资预算表
	 * @return
	 */
	public String  addSalaryBudgetTable()
	{

			CreateSalaryBudgetTable	createSalaryBudgetTablePO=null;
			if(createSalaryBudgetTable!=null && createSalaryBudgetTable.getBudgetId()!=null)
			{
				createSalaryBudgetTable.setSalaryDate(DateUtil.StringToDate(this.salaryDate, DateUtil.FORMAT_DATE));
				createSalaryBudgetTableService.updateCreateSalaryBudgetTable(createSalaryBudgetTable);
			}else{
				if(createSalaryBudgetTable==null || createSalaryBudgetTable.getName()==null)return INPUT;
				if(enterpriseId==null)return INPUT;
				Enterprise enterprisePO=enterpriseService.find(enterpriseId);
				SalaryTemplate salaryTemplatePO=salaryTemplateService.find(templateId);
				createSalaryBudgetTable.setTemplateName(salaryTemplatePO.getTemplateName());
				createSalaryBudgetTable.setEnterprise(enterprisePO);
				createSalaryBudgetTable.setSalaryTemplate(salaryTemplatePO);
				
				createSalaryBudgetTable.setSalaryDate(DateUtil.StringToDate(this.salaryDate, DateUtil.FORMAT_DATE));
				try {
					createSalaryBudgetTableService.save(createSalaryBudgetTable);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			createSalaryBudgetTablePO=createSalaryBudgetTableService.find(createSalaryBudgetTable.getBudgetId());
			if(createSalaryBudgetTablePO==null){
				createSalaryBudgetTablePO=new CreateSalaryBudgetTable();
			}
			request.setAttribute("createSalaryBudgetTable",createSalaryBudgetTablePO);
			return SUCCESS;
	}
	/**
	 * 
	 * @return 返回修改页面
	 */
	public String returnToModifySalaryBudgetTable()
	{
		if(createSalaryBudgetTable==null || createSalaryBudgetTable.getBudgetId()==null)return INPUT;
		getSalaryTemplate();
		createSalaryBudgetTable=createSalaryBudgetTableService.find(createSalaryBudgetTable.getBudgetId());
		request.setAttribute("createSalaryBudgetTable",createSalaryBudgetTable );
    	return SUCCESS;
		
	}
	/**
	 * 新建工资预算表
	 * @return
	 */
	public String newSalaryBudgetTable()
	{
		getSalaryTemplate();
		return SUCCESS;
		
	}

	
	/**
	 * 当前企业底下的所有模板
	 * @param createSalaryBudgetTable
	 */
	public void getSalaryTemplate()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null)return;
		List<SalaryTemplate> salaryTemplateList=salaryTemplateService.getAllSalaryTemplate(enterprise.getEnterpriseId());
		if(salaryTemplateList.size()==0)
			salaryTemplateList=new ArrayList<SalaryTemplate>();
		request.setAttribute("salaryTemplates", salaryTemplateList);
	}
	
	public String findBeforeCurrentDateTemplate()
	{
		createSalaryBudgetTableList=salaryTemplateService.findBeforeCurrentDateTemplate(DateUtil.StringToDate(salaryDate, DateUtil.FORMAT_DATE),3);
		if(createSalaryBudgetTableList.size()==0){
			createSalaryBudgetTableList=new ArrayList<CreateSalaryBudgetTable>();
			this.setError("暂无记录");
		}else{
			this.setError("true");
		}
	
		return "createSalaryBudgetTableList";
	
	}

	
	public String downloadSalaryBudgetTable()
	{
		
		try {
			excelName = new String(salaryFileName.getBytes(), "iso8859-1");//解决中文 文件名问题
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	 public InputStream getDownloadFile()  
	 {  
	        return ServletActionContext.getServletContext().getResourceAsStream("/doc/"+salaryFileName);  
	        
	        
	 } 
	 
		/**
		 * 当前企业底下的所有模板
		 * @param createSalaryBudgetTable
		 */
		public void getSalaryTemplate(Integer enterpriseId)
		{
		
			if(enterpriseId==null)return;
			List<SalaryTemplate> salaryTemplateList=salaryTemplateService.getAllSalaryTemplate(enterpriseId);
			if(salaryTemplateList.size()==0)
				salaryTemplateList=new ArrayList<SalaryTemplate>();
			request.setAttribute("salaryTemplates", salaryTemplateList);
		}
		
		/**
		 * 查询汇总工资预算表
		 * @return
		 */
	    public String	viewSalaryBudgetTableSummary()
	    {
	    	Enterprise enterprise=WebUtil.getEnterprise(request);
	    	if(enterprise==null || enterprise.getEnterpriseId()==null){
	    		enterprise=enterpriseService.find(enterpriseId);
	    		request.getSession().setAttribute("enterprise", enterprise);
	    	}
	    	
	    	createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
	    	if(createSalaryBudgetTable==null)createSalaryBudgetTable=new CreateSalaryBudgetTable();
	    	request.setAttribute("createSalaryBudgetTable", createSalaryBudgetTable);
	    	
	    	return SUCCESS;
	    	
	    }
		
		public String findToIdSalayBudegTable()
		{
			
			createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
			
			return "createSalaryBudgetTable";
		}
		/**
		 * 更新预算表名称
		 * @return
		 */
		public String updateSalayBudgetTable()
		{
			
			createSalaryBudgetTableService.updateSalaryBudgetTable(createSalaryBudgetTable);
			return SUCCESS;
		}
		/**
		 * 删除预算表
		 * @return
		 */
		public String deleteSalayBudgetTable()
		{
			
			createSalaryBudgetTableService.deleteCreateSalaryBudgetTable(budgetId);
			employeesSalaryDetailService.deleteEmployeesSalaryDetail(budgetId);
			return SUCCESS;
		}
	 

}
