package cn.fm.web.action.company;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.company.CustomBonus;
import cn.fm.service.company.CustomBonusServices;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class CustomBonusAction extends BaseAction {
	@Resource
	private CustomBonusServices  customBonusActionService;
	private CustomBonus   customBonus;
	
	
	
	
	
	
	public CustomBonus getCustomBonus() {
		return customBonus;
	}

	public void setCustomBonus(CustomBonus customBonus) {
		this.customBonus = customBonus;
	}

	public void setCustomBonusActionService(
			CustomBonusServices customBonusActionService) {
		this.customBonusActionService = customBonusActionService;
	}

	public String addCustomBonus()
	{
		if(customBonus==null)return SUCCESS;
		customBonusActionService.save(customBonus);
		return SUCCESS;
	}
	
	public String viewCustomBonus()
	{
		List<CustomBonus> customBonus=customBonusActionService.getAllCustomBonus();
		if(customBonus.size()==0)
			customBonus=new ArrayList<CustomBonus>();
		request.setAttribute("customBonus", customBonus);
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
}
