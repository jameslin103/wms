package cn.fm.service.company.impl;

import javax.annotation.Resource;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.Enterprise;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EnterpriseService;


@Resource @Transactional
public class EnterpriseServiceImpl extends DaoSupport<Enterprise> implements EnterpriseService {

	

}
