package cn.fm.service.company;

import java.util.List;

import cn.fm.bean.company.CustomBonus;
import cn.fm.bean.company.SalaryTemplate;
import cn.fm.service.base.DAO;

public interface SalaryTemplateService extends DAO<SalaryTemplate> {

	public List<SalaryTemplate> getAllSalaryTemplate();
	
	public List<SalaryTemplate>  reconfigureTemplate(List<CustomBonus> customBonus,List<SalaryTemplate>  salaryTemplate);
	
}
