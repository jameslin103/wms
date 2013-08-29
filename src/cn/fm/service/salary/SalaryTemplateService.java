package cn.fm.service.salary;

import java.util.Date;
import java.util.List;

import cn.fm.bean.company.CustomBonus;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.base.DAO;

public interface SalaryTemplateService extends DAO<SalaryTemplate> {

	public List<SalaryTemplate> getAllSalaryTemplate(Integer enterpriseId);
	
	public List<SalaryTemplate>  reconfigureTemplate(List<CustomBonus> customBonus,List<SalaryTemplate>  salaryTemplate);
	
	public List<CreateSalaryBudgetTable> findBeforeCurrentDateTemplate(Date date,Integer enterpriseId);

}
