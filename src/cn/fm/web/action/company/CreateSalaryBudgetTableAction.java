package cn.fm.web.action.company;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.bean.user.User;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.salary.BalanceDetailService;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.StringUtil;
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
	@Resource
	private BalanceDetailService		 balanceDetailService;
	
	private CreateSalaryBudgetTable      createSalaryBudgetTable;
	
	List<CreateSalaryBudgetTable>  createSalaryBudgetTableList;
	

	private String excelName;

	private String salaryDate;
	
	private String error;
	
	private String message; 
	
	private File   file;
	
	private Integer templateId;
	
	private Integer  enterpriseId;
	
	private Integer  budgetId;
	
	private Integer  year;
	
	

	/**
	 * 查看当前企业的预算表
	 * @return
	 */
	public String viewSalaryBudgetTable()
	{
		
		if(year==null)
		{
			this.year=Integer.parseInt(DateUtil.getCurrentTime().toString().substring(0, 4));
		}
		
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null || enterprise.getEnterpriseId()==null){
			enterprise=enterpriseService.find(enterpriseId);
			request.getSession().setAttribute("enterprise",enterprise );
		}
		
		List<CreateSalaryBudgetTable> createSalaryBudgetTableList=createSalaryBudgetTableService.getAllCreateSalaryBudgetTable(enterprise.getEnterpriseId(),this.year);
		List<Object> map=null;
		if(createSalaryBudgetTableList!=null){
			map=reconstructToMonthGroupByTableDate(createSalaryBudgetTableList);
		}
		
		request.setAttribute("map", map);
		return SUCCESS;
	}
	
	public List<Object> reconstructToMonthGroupByTableDate(List<CreateSalaryBudgetTable> createSalaryBudgetTableList)
	{
		List<Object> list=new ArrayList<Object>();
		Map<String, Object> map=null;
		for (CreateSalaryBudgetTable table : createSalaryBudgetTableList) {
			map=new HashMap<String, Object>();
			String date=table.getSalaryDate()==null?"":table.getSalaryDate().toString();
			map.put("date", date);
			map.put("createSalaryBudgetTable", table);
			list.add(map);
		}
		
		
		return list;
	}
	
	
	
	/**
	 * 新增工资预算表
	 * @return
	 */
	public String  addSalaryBudgetTable()
	{
			User user=WebUtil.getUser(request);
			Enterprise  enterprise=WebUtil.getEnterprise(request);
			CreateSalaryBudgetTable	createSalaryBudgetTablePO=null;
			if(createSalaryBudgetTable==null || StringUtil.isEmpty(createSalaryBudgetTable.getName()) || templateId==0){
				this.addFieldError("message", "工资预算表必填项!");
				this.addFieldError("message", "模板必填项!");
				return INPUT;
			
			}
			if(createSalaryBudgetTable!=null && createSalaryBudgetTable.getBudgetId()!=null)
			{
				createSalaryBudgetTable.setSalaryDate(DateUtil.StringToDate(this.salaryDate, DateUtil.FORMAT_DATE));
				createSalaryBudgetTableService.updateCreateSalaryBudgetTable(createSalaryBudgetTable);
			}else{
				if(createSalaryBudgetTable==null || createSalaryBudgetTable.getName()==null || enterpriseId==null)return INPUT;
				if(budgetId!=null && budgetId!=0){
					createSalaryBudgetTable.setMergeId(budgetId);
					createSalaryBudgetTable.setIsTax(1);
					CreateSalaryBudgetTable createSalaryBudgetTable_PO=createSalaryBudgetTableService.find(budgetId);
					createSalaryBudgetTable.setChooseTax(createSalaryBudgetTable_PO.getName());
				}
				if(user==null || enterprise==null)return INPUT;
				createSalaryBudgetTable.setUser(user);
				createSalaryBudgetTable.setSalaryDate(DateUtil.StringToDate(this.salaryDate, DateUtil.FORMAT_DATE));
				createSalaryBudgetTable.setEnterprise(enterprise);
				
				SalaryTemplate salaryTemplate=salaryTemplateService.find(templateId);
				createSalaryBudgetTable.setTemplateName(salaryTemplate.getTemplateName());
				createSalaryBudgetTable.setSalaryTemplate(salaryTemplate);
				createSalaryBudgetTableService.save(createSalaryBudgetTable);
				
				try {
					createSalaryBudgetTableService.save(createSalaryBudgetTable);
				} catch (Exception e) {
						e.printStackTrace();
						this.addActionError("系统繁忙!!");

				}
				
			}
			if(createSalaryBudgetTable==null ||createSalaryBudgetTable.getBudgetId()==null)return INPUT;
			createSalaryBudgetTablePO=createSalaryBudgetTableService.find(createSalaryBudgetTable.getBudgetId());
			if(createSalaryBudgetTablePO==null){
				createSalaryBudgetTablePO=new CreateSalaryBudgetTable();
			}
			request.setAttribute("createSalaryBudgetTable",createSalaryBudgetTablePO);
			return SUCCESS;
	}
	public String  updateSalaryBudgetTable()
	{
		
		CreateSalaryBudgetTable createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
		getSalaryTemplate();
		request.setAttribute("createSalaryBudgetTable",createSalaryBudgetTable);
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
	public String returnToModifyBudgetTable(){
		createSalaryBudgetTable=createSalaryBudgetTableService.find(budgetId);
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
		Enterprise enterprise=WebUtil.getEnterprise(request);
		createSalaryBudgetTableList=createSalaryBudgetTableService.findBeforeCurrentDateTemplate(salaryDate,enterprise.getEnterpriseId());
		if(createSalaryBudgetTableList==null || createSalaryBudgetTableList.size()==0){
			createSalaryBudgetTableList=new ArrayList<CreateSalaryBudgetTable>();
			this.addActionError("暂无记录");
		}else{
			this.setError("true");
		}
	
		return "createSalaryBudgetTableList";
	
	}

	
	public String downloadSalaryBudgetTable()
	{
		
		
		return SUCCESS;
		
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
	    	if(enterpriseId!=null && enterpriseId!=0){
	    		if(enterprise!=null ){
	    			request.removeAttribute("enterprise");
		    	}
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
			
			return SUCCESS;
		}
		/**
		 * 更新预算表名称
		 * @return
		 */
		public String updateSalayBudgetTable()
		{
			
			BigDecimal maketotal=createSalaryBudgetTable.getMakeTotal()==null?new BigDecimal("0.00"):createSalaryBudgetTable.getMakeTotal();
			BigDecimal serviceHeTotal=createSalaryBudgetTable.getServiceHeTotal()==null?new BigDecimal("0.00"):createSalaryBudgetTable.getServiceHeTotal();
			BigDecimal serviceTotal=createSalaryBudgetTable.getServiceTotal()==null?new BigDecimal("0.00"):createSalaryBudgetTable.getServiceTotal();
			BigDecimal summaketotal=serviceHeTotal.add(maketotal).setScale(2,BigDecimal.ROUND_HALF_DOWN);
			BigDecimal sumServiceTotal=serviceTotal.add(serviceHeTotal).setScale(2,BigDecimal.ROUND_HALF_DOWN);
			createSalaryBudgetTable.setMakeTotal(summaketotal);
			createSalaryBudgetTable.setServiceTotal(sumServiceTotal);
			createSalaryBudgetTableService.updateSalaryBudgetTable(createSalaryBudgetTable);
			return SUCCESS;
		}
		/**
		 * 删除预算表
		 * @return
		 */
		public String deleteSalayBudgetTable()
		{
			
			balanceDetailService.deleteBalanceDetail(budgetId);
			employeesSalaryDetailService.deleteEmployeesSalaryDetail(budgetId);
			createSalaryBudgetTableService.delete(budgetId);
			return SUCCESS;
		}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		public Integer getYear() {
			return year;
		}
		public void setYear(Integer year) {
			this.year = year;
		}
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
		
}
