package cn.fm.service.company.impl;

import java.util.List;

import javax.persistence.Query;

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

	@SuppressWarnings("unchecked")
	public List<InsurancesTax> getAllInsurancesTax() {
		Query query=em.createQuery("select t from InsurancesTax t");
		return query.getResultList();
	}

}
