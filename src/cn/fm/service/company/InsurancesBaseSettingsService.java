package cn.fm.service.company;

import java.util.List;

import cn.fm.bean.company.InsurancesBaseSettings;
import cn.fm.service.base.DAO;


public interface InsurancesBaseSettingsService extends DAO<InsurancesBaseSettings> {
       public void save(InsurancesBaseSettings insurancesBaseSettings);
       public List<InsurancesBaseSettings> getAllInsurancesBase();
       /**
        * 更新实体
        * @param insurancesBaseSettings
        * @return
        */
       public boolean updateInsurancesBaseSettings(InsurancesBaseSettings insurancesBaseSettings);
}
