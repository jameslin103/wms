package cn.fm.web.action.company;

import javax.annotation.Resource;

import cn.fm.bean.company.InsurancesBaseSettings;
import cn.fm.service.company.InsurancesBaseSettingsService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.StringUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class InsurancesBaseSettingsAction extends BaseAction {
	@Resource
	private InsurancesBaseSettingsService     insurancesBaseSettingsService;
	private InsurancesBaseSettings   insurancesBaseSettings;
	private String				  startDate;
	
	
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	/**
	 * addInsurancesTax
	 * @return SUCCESS
	 */
	public String addInsurancesBaseSettings()
	{
		if(insurancesBaseSettings==null)return INPUT;
		if(StringUtil.isEmpty(this.startDate))return INPUT;
		if(insurancesBaseSettings!=null){
			if(!StringUtil.isEmpty(this.startDate)){
				insurancesBaseSettings.setStartDate(DateUtil.StringToDate(this.startDate, DateUtil.FORMAT_DATE));
				insurancesBaseSettingsService.save(insurancesBaseSettings);
			}
		}
		return SUCCESS;
		
	}
}
