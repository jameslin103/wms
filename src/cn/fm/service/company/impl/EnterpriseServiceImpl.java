package cn.fm.service.company.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.Enterprise;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EnterpriseService;


@Service @Transactional
public class EnterpriseServiceImpl extends DaoSupport<Enterprise> implements EnterpriseService {

	

}
