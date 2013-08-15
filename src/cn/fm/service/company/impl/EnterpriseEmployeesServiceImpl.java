package cn.fm.service.company.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EnterpriseEmployeesService;

@Service @Transactional
public class EnterpriseEmployeesServiceImpl extends	DaoSupport<EnterpriseEmployees> implements EnterpriseEmployeesService {
	
	public void save(EnterpriseEmployees entity)
	{
		super.save(entity);	
	}


}
