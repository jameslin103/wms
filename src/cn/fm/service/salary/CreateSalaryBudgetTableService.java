package cn.fm.service.salary;

import java.util.List;

import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.service.base.DAO;

public interface CreateSalaryBudgetTableService extends DAO<CreateSalaryBudgetTable> {
	public void save(CreateSalaryBudgetTable createSalaryBudgetTable);
	public List<CreateSalaryBudgetTable> getAllCreateSalaryBudgetTable(Integer enterpriseId);
	/**
	 * 更新工资预算表
	 * @param createSalaryBudgetTable
	 * @param budgerId
	 */
	public void updateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable,Integer budgerId);
	
	/**
	 * 更新工资Update Set 预算表
	 * @param createSalaryBudgetTable
	 * @param 
	 */
	public boolean updateCreateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable);

}
