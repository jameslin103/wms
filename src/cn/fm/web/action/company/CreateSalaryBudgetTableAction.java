package cn.fm.web.action.company;



import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.utils.DateUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class CreateSalaryBudgetTableAction extends BaseAction {
	
	@Resource
	private CreateSalaryBudgetTableService createSalaryBudgetTableService;
	@Resource
	private SalaryTemplateService           salaryTemplateService;
	
	private CreateSalaryBudgetTable      createSalaryBudgetTable;

	private String salaryDate;
	
	private String error;
	
	
	public String getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}
	
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
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

	public String viewSalaryBudgetTable()
	{
		
		List<CreateSalaryBudgetTable> createSalaryBudgetTableList=createSalaryBudgetTableService.getAllCreateSalaryBudgetTable();
		
		if(createSalaryBudgetTableList.size()==0)
			createSalaryBudgetTableList=new ArrayList<CreateSalaryBudgetTable>();
		
		request.setAttribute("createSalaryBudgetTable", createSalaryBudgetTableList);
		
		
		return SUCCESS;
	}
	
	public String  addSalaryBudgetTable()
	{
		if(createSalaryBudgetTable==null)return INPUT;
		createSalaryBudgetTable.setSalaryDate(DateUtil.StringToDate(this.salaryDate, DateUtil.FORMAT_DATE));
		createSalaryBudgetTableService.save(createSalaryBudgetTable);
		return SUCCESS;
	}
	
	public String newSalaryBudgetTable()
	{
		List<SalaryTemplate> salaryTemplateList=salaryTemplateService.getAllSalaryTemplate(3);
		if(salaryTemplateList.size()==0)
			salaryTemplateList=new ArrayList<SalaryTemplate>();
		request.setAttribute("salaryTemplates", salaryTemplateList);
		return SUCCESS;
	}
	
	public String findBeforeCurrentDateTemplate()
	{
		
		List<CreateSalaryBudgetTable>  createSalaryBudgetTableList=salaryTemplateService.findBeforeCurrentDateTemplate(DateUtil.StringToDate(salaryDate, DateUtil.FORMAT_DATE),3);
		if(createSalaryBudgetTableList.size()==0)
			createSalaryBudgetTableList=new ArrayList<CreateSalaryBudgetTable>();
		this.setError("true");
		
		return null;
	
	}
	
	
	
	

}
