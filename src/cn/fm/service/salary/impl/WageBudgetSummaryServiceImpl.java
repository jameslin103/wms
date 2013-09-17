package cn.fm.service.salary.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.salary.WageBudgetSummary;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.salary.WageBudgetSummaryService;

@Service @Transactional
public class WageBudgetSummaryServiceImpl extends DaoSupport<WageBudgetSummary> implements WageBudgetSummaryService {

	
	@SuppressWarnings("unchecked")
	/**
	 * 查询当前企业所有工资预算表
	 * 2013-09-17
	 * version 1.0
	 */
	public List<WageBudgetSummary> getAllWageBudgetSummary(Integer budgetId,Integer enterpriseId)
	{
		Query query=em.createQuery("select w from WageBudgetSummary w  where w.budgetId=?1 and w.enterpriseId=?2");
		return query.setParameter(1, budgetId).setParameter(2, enterpriseId).getResultList();
	}
	
	public void modify(WageBudgetSummary wageBudgetSummary){
		
		super.update(wageBudgetSummary);
	}
	public void delete(WageBudgetSummary wageBudgetSummary)
	{
		super.delete(wageBudgetSummary);
		
	}
}
