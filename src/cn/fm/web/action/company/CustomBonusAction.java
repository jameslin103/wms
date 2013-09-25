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
	private CustomBonusServices  customBonusService;
	private CustomBonus   customBonus;
	
	private Integer      id;
	
	
	
	
	public CustomBonus getCustomBonus() {
		return customBonus;
	}

	public void setCustomBonus(CustomBonus customBonus) {
		this.customBonus = customBonus;
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCustomBonusService(CustomBonusServices customBonusService) {
		this.customBonusService = customBonusService;
	}

	public String addCustomBonus()
	{
		if(customBonus==null)return SUCCESS;
		customBonusService.save(customBonus);
		return SUCCESS;
	}
	
	public String viewCustomBonus()
	{
		List<CustomBonus> customBonus=customBonusService.getAllCustomBonus();
		if(customBonus.size()==0)
			customBonus=new ArrayList<CustomBonus>();
		request.setAttribute("customBonus", customBonus);
		return SUCCESS;
	}
	
	public String  findToIdCustomBonus()
	{
		customBonus=customBonusService.find(id);
		
		return "customBonus";
		
	}
	public String   updateCustomBonus()
	{
		
		customBonusService.updateCustomBonus(customBonus);
		
		return SUCCESS;
	}
		
	
	
	
	
	
	
	
	
}
