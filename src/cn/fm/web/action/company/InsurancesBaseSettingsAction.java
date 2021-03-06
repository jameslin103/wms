package cn.fm.web.action.company;

import java.util.ArrayList;
import java.util.List;

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
	private InsurancesBaseSettings            insurancesBaseSettings;
	private String				  			  startDate;
	private InsurancesBaseSettings		      insurancesBaseSettingsJosn;
	private Integer 						  baseId; 
	
	
	
	public InsurancesBaseSettings getInsurancesBaseSettingsJosn() {
		return insurancesBaseSettingsJosn;
	}

	public void setInsurancesBaseSettingsJosn(
			InsurancesBaseSettings insurancesBaseSettingsJosn) {
		this.insurancesBaseSettingsJosn = insurancesBaseSettingsJosn;
	}

	public Integer getBaseId() {
		return baseId;
	}

	public void setBaseId(Integer baseId) {
		this.baseId = baseId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public InsurancesBaseSettings getInsurancesBaseSettings() {
		return insurancesBaseSettings;
	}

	public void setInsurancesBaseSettings(
			InsurancesBaseSettings insurancesBaseSettings) {
		this.insurancesBaseSettings = insurancesBaseSettings;
	}

	public void setInsurancesBaseSettingsService(
			InsurancesBaseSettingsService insurancesBaseSettingsService) {
		this.insurancesBaseSettingsService = insurancesBaseSettingsService;
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
	public String viewInsurancesBaseSettings()
	{
		List<InsurancesBaseSettings> insurancesBaseSettings=insurancesBaseSettingsService.getAllInsurancesBase();
		if(insurancesBaseSettings.size()==0)
			insurancesBaseSettings=new ArrayList<InsurancesBaseSettings>();
		request.setAttribute("insurancesBase", insurancesBaseSettings);
		return SUCCESS;
	}
	
	public String findIdToBaseTax()
	{
		insurancesBaseSettingsJosn=insurancesBaseSettingsService.find(baseId);
		return "insurancesBaseSettingsJosn";
	}
	public String updateInsurancesBaseSettings()
	{
		insurancesBaseSettingsService.updateInsurancesBaseSettings(insurancesBaseSettings);
		return SUCCESS;
	}
}
