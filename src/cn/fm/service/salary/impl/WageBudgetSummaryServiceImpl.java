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
	/**
	 * 更新预算表
	 * @param wageBudgetSummary
	 * @return
	 */
	public boolean updateWageBudgetSummary(WageBudgetSummary wageBudgetSummary)
	{
		try {
			em.createQuery("update WageBudgetSummary w set w.wageSheetName=?1" +
					",w.wageMonth=?2,w.mergeTax=?3,w.note=?4 where w.wageId=?5")
			.setParameter(1, wageBudgetSummary.getWageSheetName()).setParameter(2, wageBudgetSummary.getWageMonth())
			.setParameter(3, wageBudgetSummary.getMergeTax()).setParameter(4, wageBudgetSummary.getNote())
			.setParameter(5, wageBudgetSummary.getWageId()).executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
