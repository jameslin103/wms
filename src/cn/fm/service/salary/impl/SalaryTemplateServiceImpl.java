package cn.fm.service.salary.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.fm.bean.company.CustomBonus;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.salary.SalaryTemplateService;

@Service @Transactional
public class SalaryTemplateServiceImpl extends DaoSupport<SalaryTemplate> implements SalaryTemplateService {

	/**
	 * 保存实体
	 */
	public void save(SalaryTemplate salaryTemplate)
	{
		super.save(salaryTemplate);
	}
	/**
	 * 更新实体
	 */
	public void updateSalaryTemplate(SalaryTemplate salaryTemplate)
	{
		try {
			em.createQuery("update SalaryTemplate s set s.templateName=?1," +
					"s.subsidyList=?2,s.fiveInsurances=?3," +
					"s.tax=?4,s.status=?5 where templateId=?6")
			.setParameter(1,salaryTemplate.getTemplateName())
			.setParameter(2,salaryTemplate.getSubsidyList())
			.setParameter(3,salaryTemplate.getFiveInsurances())
			.setParameter(4,salaryTemplate.getTax())
			.setParameter(5,salaryTemplate.getStatus())
			.setParameter(6,salaryTemplate.getTemplateId()).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 获取当前企业的所有模板
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<SalaryTemplate> getAllSalaryTemplate(Integer enterpriseId) {
		Query query=em.createQuery("select s from SalaryTemplate s where s.enterprise.enterpriseId=?1");
		return query.setParameter(1, enterpriseId).getResultList();
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
			
				salaryTemplateVO.setTemplateId(salPO.getTemplateId());
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
