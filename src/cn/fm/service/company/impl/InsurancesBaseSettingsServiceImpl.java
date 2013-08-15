package cn.fm.service.company.impl;

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
}
