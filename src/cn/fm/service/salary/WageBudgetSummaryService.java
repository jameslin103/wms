package cn.fm.service.salary;

import java.util.List;

import cn.fm.bean.salary.WageBudgetSummary;
import cn.fm.service.base.DAO;

public interface WageBudgetSummaryService extends DAO<WageBudgetSummary> {

	
	public void modify(WageBudgetSummary wageBudgetSummary);
	
	/**
	 * 查询当前企业所有工资预算表
	 * 2013-09-17
	 * version 1.0
	 */
	public List<WageBudgetSummary> getAllWageBudgetSummary(Integer budgetId,Integer enterpriseId);
	
	/**
	 * 更新预算表
	 * @param wageBudgetSummary
	 * @return
	 */
	public boolean updateWageBudgetSummary(WageBudgetSummary wageBudgetSummary);
}
