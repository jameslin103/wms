package cn.fm.web.action.salary;



import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.PageView;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.BalanceDetail;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.salary.BalanceDetailService;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.web.action.BaseAction;


@SuppressWarnings("serial")
public class CompanylistWithSalaryAction extends BaseAction{

	
	@Resource
	private CreateSalaryBudgetTableService  createSalaryBudgetTableService;
	
	@Resource
	private EnterpriseEmployeesService  enterpriseEmployeesService;
	
	@Resource
	private   BalanceDetailService balanceDetailService;
	
	
	
	  
	
	
	
	

	public void setCreateSalaryBudgetTableService(
			CreateSalaryBudgetTableService createSalaryBudgetTableService) {
		this.createSalaryBudgetTableService = createSalaryBudgetTableService;
	}

	public void setEnterpriseEmployeesService(
			EnterpriseEmployeesService enterpriseEmployeesService) {
		this.enterpriseEmployeesService = enterpriseEmployeesService;
	}

	public void setBalanceDetailService(BalanceDetailService balanceDetailService) {
		this.balanceDetailService = balanceDetailService;
	}

	/**
	 * 查看工资预算表
	 * @return SUCCESS
	 */
	public String viewCompanyListWithSaraly()
	{
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("budgetId", "desc");
		StringBuffer jpql = new StringBuffer("");
		PageView<CreateSalaryBudgetTable> pageView = new PageView<CreateSalaryBudgetTable>(10,  this.getPage());
		pageView.setQueryResult(createSalaryBudgetTableService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),null, orderby));
			request.setAttribute("pageView", pageView);
			
		return SUCCESS;
	}
	
	/**
	 * 查看增减员与参保
	 * @return SUCCESS
	 */
	public String viewCompanyListWithInsurance()
	{
		
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("employeesId", "desc");
		StringBuffer jpql = new StringBuffer("");
		PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
		pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),null, orderby));
			request.setAttribute("pageView", pageView);
		return SUCCESS;
	}
	
	/**
	 * 查看资金往来
	 * @return SUCCESS
	 */
	public String viewCompanyListWithBalance()
	{
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("detailId", "desc");
		StringBuffer jpql = new StringBuffer("");
		PageView<BalanceDetail> pageView = new PageView<BalanceDetail>(10,  this.getPage());
		pageView.setQueryResult(balanceDetailService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),null, orderby));
			request.setAttribute("pageView", pageView);
			
		return SUCCESS;
	}
	
}
