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
	
	public boolean updateInsurancesTax(InsurancesTax insurancesTax)
	{
		
		Query query=em.createQuery("update InsurancesTax set insurancesType=?1," +
				"endowmentInsurance=?2," +
				"personalEndowmentInsurance=?3," +
				"unemploymentInsurance=?4," +
				"personalUnemploymentInsurance=?5," +
				"birthEnterprise=?6," +
				"injuriesEnterprise=?7," +
				"medicalEnterprise=?8," +
				"personalEnterprise=?9," +
				"housingFundEnterprise=?10," +
				"personalHousingFund=?11," +
				"startDate=?12 where id=?13");
		query.setParameter(1, insurancesTax.getInsurancesType())
			 .setParameter(2, insurancesTax.getEndowmentInsurance())
			 .setParameter(3, insurancesTax.getPersonalEndowmentInsurance())
			 .setParameter(4, insurancesTax.getUnemploymentInsurance())
			 .setParameter(5, insurancesTax.getPersonalUnemploymentInsurance())
			 .setParameter(6, insurancesTax.getBirthEnterprise())
			 .setParameter(7, insurancesTax.getInjuriesEnterprise())
			 .setParameter(8, insurancesTax.getMedicalEnterprise())
			 .setParameter(9, insurancesTax.getPersonalEnterprise())
			 .setParameter(10, insurancesTax.getHousingFundEnterprise())
			 .setParameter(11, insurancesTax.getPersonalHousingFund())
			 .setParameter(12, insurancesTax.getStartDate())
			 .setParameter(13, insurancesTax.getId()).executeUpdate();
		return true;
	}

	public InsurancesTax getInsurancesTax() {
		
		return (InsurancesTax)em.createQuery("select t from InsurancesTax t").getSingleResult();
	}

}
