package cn.fm.service.company.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EnterpriseEmployeesService;

@Resource @Transactional
public class EnterpriseEmployeesServiceImpl extends	DaoSupport<EnterpriseEmployees> implements EnterpriseEmployeesService {



}
