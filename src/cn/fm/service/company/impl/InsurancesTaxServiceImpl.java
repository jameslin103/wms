package cn.fm.service.company.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.InsurancesTax;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.InsurancesTaxService;

@Service @Transactional
public class InsurancesTaxServiceImpl extends DaoSupport<InsurancesTax> implements InsurancesTaxService {

	public void save(InsurancesTax insurancesTax) {
		super.save(insurancesTax);

	}

}
