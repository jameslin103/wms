package cn.fm.service.salary;

import java.util.List;

import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.service.base.DAO;

public interface CreateSalaryBudgetTableService extends DAO<CreateSalaryBudgetTable> {
	

	public List<CreateSalaryBudgetTable> getAllCreateSalaryBudgetTable(Integer enterpriseId,Integer year);
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
	
	
	public List<CreateSalaryBudgetTable>  getFindCreateSalaryBudgetTables(Integer budgetId);
	
	public void updateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable);
	/**
	 * 插入汇总开票，五险。。总额
	 * @param createSalaryBudgetTable
	 */
	public void updateCreateSalaryBudgetTableSummary(CreateSalaryBudgetTable createSalaryBudgetTable);
	
	
	public void deleteCreateSalaryBudgetTable(Integer budgetId);
	
	/**
	 * 所有企业的汇总情况
	 * @return  List<CreateSalaryBudgetTable>
	 */
	public List<CreateSalaryBudgetTable> getAllCreateSalaryBudgetTable();
	
	/**
	 * 获取工资预算表
	 * @param date 时间段获取
	 * @param enterpriseId 根据企业
	 * @return list
	 */
	public List<CreateSalaryBudgetTable> findBeforeCurrentDateTemplate(String date,Integer enterpriseId);
	/**
	 * 保存工资预算表
	 * @param createSalaryBudgetTable
	 */
	public void saveCreateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable,Integer enterpriseId,Integer templateId);
}
