package cn.fm.web.action.company;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import cn.fm.bean.company.CreateSalaryBudgetTable;
import cn.fm.bean.company.SalaryTemplate;
import cn.fm.service.company.CreateSalaryBudgetTableService;
import cn.fm.service.company.SalaryTemplateService;
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
	
	
	
	public String getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
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
		List<SalaryTemplate> salaryTemplateList=salaryTemplateService.getAllSalaryTemplate();
		if(salaryTemplateList.size()==0)
			salaryTemplateList=new ArrayList<SalaryTemplate>();
		request.setAttribute("salaryTemplates", salaryTemplateList);
		return SUCCESS;
	}
	
	
	
	
	
	

}
