package cn.fm.service.company.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.CreateSalaryBudgetTable;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.CreateSalaryBudgetTableService;

@Service @Transactional
public class CreateSalaryBudgetTableServiceImpl extends	DaoSupport<CreateSalaryBudgetTable> implements	CreateSalaryBudgetTableService {

	public void save(CreateSalaryBudgetTable createSalaryBudgetTable){
		
		super.save(createSalaryBudgetTable);
	}

	@SuppressWarnings("unchecked")
	public List<CreateSalaryBudgetTable> getAllCreateSalaryBudgetTable()
	{
		Query query=em.createQuery("select c from CreateSalaryBudgetTable c");
		
		return (List<CreateSalaryBudgetTable>)query.getResultList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
