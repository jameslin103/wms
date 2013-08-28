package cn.fm.service.salary;

import java.util.List;

import cn.fm.bean.salary.WageBudgetSummary;
import cn.fm.service.base.DAO;

public interface WageBudgetSummaryService extends DAO<WageBudgetSummary> {

	public List<WageBudgetSummary> getAllWageBudgetSummary(Integer budgetId);
	
	public void modify(WageBudgetSummary wageBudgetSummary);

	
	
}
