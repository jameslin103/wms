package cn.fm.service.salary.impl;

import java.io.File;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.salary.WageBudgetSummary;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.salary.WageBudgetSummaryService;
import cn.fm.utils.GenerateSqlFromExcel;

@Service @Transactional
public class WageBudgetSummaryServiceImpl extends DaoSupport<WageBudgetSummary> implements WageBudgetSummaryService {

	
	@SuppressWarnings("unchecked")
	public List<WageBudgetSummary> getAllWageBudgetSummary(Integer budgetId)
	{
		Query query=em.createQuery("select w from WageBudgetSummary w  where w.budgetId=?1");
		return query.setParameter(1, budgetId).getResultList();
	}
	
	public void modify(WageBudgetSummary wageBudgetSummary){
		
		super.update(wageBudgetSummary);
	}
	public void delete(WageBudgetSummary wageBudgetSummary)
	{
		super.delete(wageBudgetSummary);
		
	}
}
