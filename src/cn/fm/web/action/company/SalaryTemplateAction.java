package cn.fm.web.action.company;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.company.CustomBonus;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.company.CustomBonusServices;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.utils.StringUtil;
import cn.fm.web.action.BaseAction;


@SuppressWarnings("serial")

public class SalaryTemplateAction extends BaseAction {
	
	@Resource
	private SalaryTemplateService salaryTemplateService;
	@Resource
	private CustomBonusServices   customBonusService;
	
	private SalaryTemplate       salaryTemplate;
	private CustomBonus          customBonus;

	public SalaryTemplate getSalaryTemplate() {
		return salaryTemplate;
	}

	public void setSalaryTemplate(SalaryTemplate salaryTemplate) {
		this.salaryTemplate = salaryTemplate;
	}

	public void setSalaryTemplateService(SalaryTemplateService salaryTemplateService) {
		this.salaryTemplateService = salaryTemplateService;
	}
	
	public CustomBonus getCustomBonus() {
		return customBonus;
	}

	public void setCustomBonus(CustomBonus customBonus) {
		this.customBonus = customBonus;
	}

	public void setCustomBonusService(CustomBonusServices customBonusService) {
		this.customBonusService = customBonusService;
	}

	public String viewSalaryTemplate()
	{
		
		List<CustomBonus> customBonus=customBonusService.getAllCustomBonus();
		Enterprise enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		List<SalaryTemplate> salaryTemplate=salaryTemplateService.getAllSalaryTemplate(enterprise.getId());
		if(customBonus.size()==0)
			salaryTemplate=new ArrayList<SalaryTemplate>();
		if(customBonus.size()==0)
			customBonus=new ArrayList<CustomBonus>();
		request.setAttribute("customBonus", customBonus);
		request.setAttribute("salaryTemplate", salaryTemplate);
//		List<SalaryTemplate> templates=salaryTemplateService.reconfigureTemplate(customBonus,salaryTemplate);
//		if(templates==null || templates.size()==0)
//			templates=new ArrayList<SalaryTemplate>();
//		request.setAttribute("salaryTemplate",templates);
		
		return SUCCESS;
	}
	
	public String addSalaryTemplate()
	{
		if(salaryTemplate==null || StringUtil.isEmpty(salaryTemplate.getTemplateName()))return INPUT;
			
		salaryTemplateService.save(salaryTemplate);
		
		return SUCCESS;
		
	}
	
}
