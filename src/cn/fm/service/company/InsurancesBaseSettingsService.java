package cn.fm.service.company;

import cn.fm.bean.company.InsurancesBaseSettings;
import cn.fm.service.base.DAO;


public interface InsurancesBaseSettingsService extends DAO<InsurancesBaseSettings> {
       public void save(InsurancesBaseSettings insurancesBaseSettings);
}
