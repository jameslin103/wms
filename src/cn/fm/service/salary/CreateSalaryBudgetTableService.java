package cn.fm.service.salary;

import java.util.List;

import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.service.base.DAO;

public interface CreateSalaryBudgetTableService extends DAO<CreateSalaryBudgetTable> {
	public void save(CreateSalaryBudgetTable createSalaryBudgetTable);
	public List<CreateSalaryBudgetTable> getAllCreateSalaryBudgetTable();
	
	

}
