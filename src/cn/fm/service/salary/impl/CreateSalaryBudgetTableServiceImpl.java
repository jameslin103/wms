package cn.fm.service.salary.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.salary.CreateSalaryBudgetTableService;

@Service @Transactional
public class CreateSalaryBudgetTableServiceImpl extends	DaoSupport<CreateSalaryBudgetTable> implements	CreateSalaryBudgetTableService {

	public void save(CreateSalaryBudgetTable createSalaryBudgetTable){
		
		super.save(createSalaryBudgetTable);
	}

	@SuppressWarnings("unchecked")
	public List<CreateSalaryBudgetTable> getAllCreateSalaryBudgetTable(Integer enterpriseId)
	{
		Query query=em.createQuery("select c from CreateSalaryBudgetTable  c where c.enterprise.enterpriseId=?1");
			   query.setParameter(1,enterpriseId);
		return query.getResultList();
	}
	/**
	 * 更新工资预算表
	 * @param createSalaryBudgetTable
	 * @param budgerId
	 */
	public void updateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable,Integer budgerId)
	{
		CreateSalaryBudgetTable createSalaryBudgetTablePO=em.find(CreateSalaryBudgetTable.class, budgerId);
		CreateSalaryBudgetTable  createSalaryBudgetTableVO=new CreateSalaryBudgetTable();
		createSalaryBudgetTableVO.setBudgetId(createSalaryBudgetTablePO.getBudgetId());
		createSalaryBudgetTableVO.setChooseTax(createSalaryBudgetTable.getChooseTax());
		createSalaryBudgetTableVO.getCreateDate();
		createSalaryBudgetTableVO.setName(createSalaryBudgetTable.getName());
		createSalaryBudgetTableVO.setNote(createSalaryBudgetTable.getNote());
		createSalaryBudgetTableVO.setTemple(createSalaryBudgetTable.getTemple());
		
		super.update(createSalaryBudgetTableVO);
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
