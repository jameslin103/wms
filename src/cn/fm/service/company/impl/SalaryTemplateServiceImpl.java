package cn.fm.service.company.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.CustomBonus;
import cn.fm.bean.company.SalaryTemplate;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.SalaryTemplateService;

@Service @Transactional
public class SalaryTemplateServiceImpl extends DaoSupport<SalaryTemplate> implements SalaryTemplateService {


	public void save(SalaryTemplate salaryTemplate)
	{
		super.save(salaryTemplate);
	}
	
	public void updateSalaryTemplate(SalaryTemplate salaryTemplateId)
	{
		super.update(salaryTemplateId);
		
	}

	@SuppressWarnings("unchecked")
	public List<SalaryTemplate> getAllSalaryTemplate() {
		Query query=em.createQuery("select s from SalaryTemplate s");
		return query.getResultList();
	}
	
	/**
	 * 重新组合一个SalaryTemplate
	 * @param customBonus
	 * @param salaryTemplate
	 * @return
	 */
	

	public List<SalaryTemplate>  reconfigureTemplate(List<CustomBonus> customBonus,List<SalaryTemplate>  salaryTemplate)
	{
		
		if(salaryTemplate==null || customBonus==null)return null;
		if(salaryTemplate.size()==0 || customBonus.size()==0)return null;
		
		List<SalaryTemplate>  salaryTemplateListVO=new ArrayList<SalaryTemplate>();
		SalaryTemplate        salaryTemplateVO=new SalaryTemplate();
		for (SalaryTemplate salPO : salaryTemplate) 
		{
			
				salaryTemplateVO.setId(salPO.getId());
				salaryTemplateVO.setStatus(salPO.getStatus());
				salaryTemplateVO.setFiveInsurances(salPO.getFiveInsurances());
				salaryTemplateVO.setTax(salPO.getTax());
				salaryTemplateVO.setTemplateName(salPO.getTemplateName());
				
				salaryTemplateVO.getSubsidys().addAll(bonusName(salPO.getSubsidyList(),customBonus));
				
				salaryTemplateListVO.add(salaryTemplateVO);
		}
		return salaryTemplateListVO;
	}
	public List<String>  bonusName(String SubsidyCode,List<CustomBonus> customBonus)
	{
		List<String> bonusNames=new ArrayList<String>(); 
		if(SubsidyCode!=null)
		{
			String[] subList = SubsidyCode.split(",");
			for(String name:subList)
			{
				for(CustomBonus cb:customBonus){
					
					if(name.trim().equals(cb.getId().toString().trim())){
						bonusNames.add(cb.getBonusName());
					}
				}
				System.out.println(name.trim());
			}
			
		}else{
			bonusNames.add(null);
		}
		
		return bonusNames;
	}
}
