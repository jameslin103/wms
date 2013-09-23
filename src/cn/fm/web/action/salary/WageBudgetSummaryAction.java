package cn.fm.web.action.salary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.bean.salary.WageBudgetSummary;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.service.salary.WageBudgetSummaryService;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class WageBudgetSummaryAction extends BaseAction {
	
	@Resource
	private WageBudgetSummaryService  wageBudgetSummaryService;
	@Resource
	private EnterpriseService           enterpriseService;
	@Resource
	private SalaryTemplateService  salaryTemplateService;
	
	private WageBudgetSummary     wageBudgetSummary;
	private Integer             enterpriseId;
	private File                file;
	private Integer   			budgetId;
	private Integer				wageId;
	
	
	
	
	public Integer getWageId() {
		return wageId;
	}
	public void setWageId(Integer wageId) {
		this.wageId = wageId;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public void setWageBudgetSummaryService(
			WageBudgetSummaryService wageBudgetSummaryService) {
		this.wageBudgetSummaryService = wageBudgetSummaryService;
	}
	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	
	public WageBudgetSummary getWageBudgetSummary() {
		return wageBudgetSummary;
	}
	public void setWageBudgetSummary(WageBudgetSummary wageBudgetSummary) {
		this.wageBudgetSummary = wageBudgetSummary;
	}
	public Integer getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}
	/**
	 * 当前企业下的某个项目工资预算表
	 * @return
	 */
	public String viewWageBudgetSummary()
	{	
		if(this.enterpriseId==null || this.enterpriseId==0)return SUCCESS;
		Enterprise enterprise=enterpriseService.find(this.enterpriseId);
		List<WageBudgetSummary> wageBudgetSummaryList=wageBudgetSummaryService.getAllWageBudgetSummary(this.budgetId,enterpriseId);
		if(wageBudgetSummaryList.size()==0)
			wageBudgetSummaryList=new ArrayList<WageBudgetSummary>();
		
		request.setAttribute("wageBudgetSummarys", wageBudgetSummaryList);
		request.getSession().setAttribute("enterprise", enterprise);
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
	public String findToIdSalayBudegSummary()
	{
		
		wageBudgetSummary=wageBudgetSummaryService.find(wageId);
		
		return "wageBudgetSummary";
	}
	public String updateWageBudgetSummary()
	{
		
		wageBudgetSummaryService.updateWageBudgetSummary(wageBudgetSummary);
		return SUCCESS;
	}
	
	
	

}
