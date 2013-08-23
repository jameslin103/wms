package cn.fm.service.company;

import java.util.List;

import cn.fm.bean.company.CreateSalaryBudgetTable;
import cn.fm.service.base.DAO;

public interface CreateSalaryBudgetTableService extends DAO<CreateSalaryBudgetTable> {
	public void save(CreateSalaryBudgetTable createSalaryBudgetTable);
	public List<CreateSalaryBudgetTable> getAllCreateSalaryBudgetTable();
	
	

}
