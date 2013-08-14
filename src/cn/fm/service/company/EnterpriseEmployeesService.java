package cn.fm.service.company;

import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.base.DAO;

public interface EnterpriseEmployeesService extends DAO<EnterpriseEmployees>{
	public void save(EnterpriseEmployees entity);
}
