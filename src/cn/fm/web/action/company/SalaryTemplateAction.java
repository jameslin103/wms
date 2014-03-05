package cn.fm.web.action.company;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.company.CustomBonus;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseProjects;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.company.CustomBonusServices;
import cn.fm.service.company.EnterpriseProjectsService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.utils.StringUtil;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;


@SuppressWarnings("serial")

public class SalaryTemplateAction extends BaseAction {
	
	@Resource
	private SalaryTemplateService salaryTemplateService;
	@Resource
	private CustomBonusServices   customBonusService;
	@Resource 
	private EnterpriseService   enterpriseService;
	@Resource 
	private EnterpriseProjectsService   enterpriseProjectsService;
	
	private EnterpriseProjects   enterpriseProjects;
	
	private SalaryTemplate       salaryTemplate;
	private CustomBonus          customBonus;
	private Integer				 templateId;

	
	
	
	
	
	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

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
		
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null)return INPUT;
		Enterprise enterprisePO=enterpriseService.find(enterprise.getEnterpriseId());
		List<CustomBonus> customBonus=customBonusService.getStatusEnableCustomBonus(enterprise.getEnterpriseId());
		List<SalaryTemplate> salaryTemplate=salaryTemplateService.getAllSalaryTemplate(enterprise.getEnterpriseId());
		if(salaryTemplate==null || salaryTemplate.size()==0)
			salaryTemplate=new ArrayList<SalaryTemplate>();
		if(customBonus.size()==0)
			customBonus=new ArrayList<CustomBonus>();
		request.setAttribute("customBonus", customBonus);
		request.setAttribute("salaryTemplate",salaryTemplate);
		request.setAttribute("enterprisePO",enterprisePO);
		return SUCCESS;
	}
	
	public String addSalaryTemplate()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		if(enterprise==null)return INPUT;
		if(salaryTemplate==null || StringUtil.isEmpty(enterpriseProjects.getId()))return INPUT;
		salaryTemplate.setEnterprise(enterprise);
		enterpriseProjects=enterpriseProjectsService.getByIdEnterpriseProjects(enterpriseProjects.getId());
		salaryTemplate.setEnterpriseProjects(enterpriseProjects);
		salaryTemplate.setTemplateName(enterpriseProjects.getProjects());
		salaryTemplateService.save(salaryTemplate);
		
		return SUCCESS;
		
	}
	public String findToIdSalaryTemplate(){
		
		salaryTemplate=salaryTemplateService.find(templateId);
		
		return SUCCESS;
	}
	public String updateSalaryTemplate()
	{
		
		salaryTemplateService.updateSalaryTemplate(salaryTemplate);
		return SUCCESS;
	}

	public EnterpriseProjects getEnterpriseProjects() {
		return enterpriseProjects;
	}

	public void setEnterpriseProjects(EnterpriseProjects enterpriseProjects) {
		this.enterpriseProjects = enterpriseProjects;
	}
	
}
