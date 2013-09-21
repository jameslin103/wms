package cn.fm.service.company.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.InsurancesBaseSettings;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.InsurancesBaseSettingsService;

@Service @Transactional
public class InsurancesBaseSettingsServiceImpl extends DaoSupport<InsurancesBaseSettings> implements InsurancesBaseSettingsService {

	public void save(InsurancesBaseSettings insurancesBaseSettings){	
		super.save(insurancesBaseSettings);
	}

	@SuppressWarnings("unchecked")
	public List<InsurancesBaseSettings> getAllInsurancesBase() {
		Query query=em.createQuery("select s from InsurancesBaseSettings s");
		return query.getResultList();
		
	}
	
	public boolean updateInsurancesBaseSettings(InsurancesBaseSettings insurancesBaseSettings)
	{
		try {
			Query query =em.createQuery("update InsurancesBaseSettings set insurancesType=?1," +
					"socialInsurance=?2," +
					"birthInsurance=?3," +
					"inductrialInjury=?4," +
					"housingMPF=?5," +
					"basicMedical=?6," +
					"povertyStricken=?7," +
					"startDate=?8 where id=?9");
				query.setParameter(1, insurancesBaseSettings.getInsurancesType())
					 .setParameter(2, insurancesBaseSettings.getSocialInsurance())
					 .setParameter(3, insurancesBaseSettings.getBirthInsurance())
					 .setParameter(4, insurancesBaseSettings.getInductrialInjury())
					 .setParameter(5, insurancesBaseSettings.getHousingMPF())
					 .setParameter(6, insurancesBaseSettings.getBasicMedical())
					 .setParameter(7, insurancesBaseSettings.getPovertyStricken())
					 .setParameter(8, insurancesBaseSettings.getStartDate())
					 .setParameter(9, insurancesBaseSettings.getId()).executeUpdate();
				
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
			
		return true;
	}
}
