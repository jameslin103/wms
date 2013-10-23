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
import cn.fm.utils.StringUtil;
import cn.fm.web.action.BaseAction;


@SuppressWarnings("serial")
public class CompanylistWithSalaryAction extends BaseAction{

	
	@Resource
	private CreateSalaryBudgetTableService  createSalaryBudgetTableService;
	
	@Resource
	private EnterpriseEmployeesService  enterpriseEmployeesService;
	
	@Resource
	private   BalanceDetailService balanceDetailService;
	
	private   EnterpriseEmployees employeesRecution;
	
	private   Integer        month; 
	private   String		 year;
	private   Integer        yearSub;
	private   Integer        enterpriseId;
	private   Integer        recutionState;

	
	
	public Integer getRecutionState() {
		return recutionState;
	}

	public void setRecutionState(Integer recutionState) {
		this.recutionState = recutionState;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public Integer getYearSub() {
		return yearSub;
	}

	public void setYearSub(Integer yearSub) {
		this.yearSub = yearSub;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

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
	
	

	public EnterpriseEmployees getEmployeesRecution() {
		return employeesRecution;
	}

	public void setEmployeesRecution(EnterpriseEmployees employeesRecution) {
		this.employeesRecution = employeesRecution;
	}

	/**
	 * 查看工资预算表
	 * @return SUCCESS
	 */
	public String viewCompanyListWithSaraly()
	{
		String yearAndMonth = null;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("budgetId", "asc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(!StringUtil.isEmpty(year))
		{
			yearSub=Integer.parseInt(year.substring(0, 4));
			yearAndMonth=yearSub+""+month;
			if(month!=null && month!=0){
				
				jpql.append(" date_format(o.salaryDate,'%Y%m')=?").append(params.size()+1);
				params.add(yearAndMonth);
			}else{

				jpql.append(" year(o.salaryDate)=?").append(params.size()+1);
				params.add(yearSub);
				
			}
		}
		
		PageView<CreateSalaryBudgetTable> pageView = new PageView<CreateSalaryBudgetTable>(10,  this.getPage());
		pageView.setQueryResult(createSalaryBudgetTableService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
			
		return SUCCESS;
	}
	
	/**
	 * 查看增减员与参保
	 * @return SUCCESS
	 */
	public String viewCompanyListWithInsurance()
	{
		String yearAndMonth = null;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("cinsengDate", "asc");
		StringBuffer jpql = new StringBuffer("");

		List<Object> params = new ArrayList<Object>();
		if(!StringUtil.isEmpty(year))
		{
			yearSub=Integer.parseInt(year.substring(0, 4));
			yearAndMonth=yearSub+""+month;
			if(month!=null && month!=0){
				
				jpql.append(" date_format(o.cinsengDate,'%Y%m')=?").append(params.size()+1);
				params.add(yearAndMonth);
			}else{

				jpql.append(" year(o.cinsengDate)=?").append(params.size()+1);
				params.add(yearSub);
				
			}
		}
		PageView<EnterpriseEmployees> pageView = new PageView<EnterpriseEmployees>(10,  this.getPage());
		pageView.setQueryResult(enterpriseEmployeesService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
			request.setAttribute("year", yearSub);
		return SUCCESS;
	}
	
	/**
	 * 查看资金往来
	 * @return SUCCESS
	 */
	public String viewCompanyListWithBalance()
	{
		String yearAndMonth = null;
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("detailId", "asc");
		StringBuffer jpql = new StringBuffer("");
		
		List<Object> params = new ArrayList<Object>();
		if(!StringUtil.isEmpty(year))
		{
			yearSub=Integer.parseInt(year.substring(0, 4));
			yearAndMonth=yearSub+""+month;
			if(month!=null && month!=0){
				
				jpql.append(" date_format(o.yearMonth,'%Y%m')=?").append(params.size()+1);
				params.add(yearAndMonth);
			}else{

				jpql.append(" year(o.yearMonth)=?").append(params.size()+1);
				params.add(yearSub);
				
			}
		}
		PageView<BalanceDetail> pageView = new PageView<BalanceDetail>(10,  this.getPage());
		pageView.setQueryResult(balanceDetailService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
			request.setAttribute("year", yearSub);
			
		return SUCCESS;
	}
	
	public String findEnterpriseEmployeesRecution()
	{
		
		employeesRecution=enterpriseEmployeesService.findRecutionState(enterpriseId);
		
		
		return "employeesRecution";
	}
	/**
	 * 修改增员,参保，状态
	 * @return
	 */
	public String updateRecutionState()
	{
		if(enterpriseId==null || recutionState==null)return INPUT;
		
		enterpriseEmployeesService.updateRecutionState(enterpriseId,recutionState);
		
		return SUCCESS;
	}
	
}
