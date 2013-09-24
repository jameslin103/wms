package cn.fm.service.salary.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

		
		super.update(createSalaryBudgetTableVO);
		
	}
	public boolean updateCreateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable)
	{
		if(createSalaryBudgetTable==null)return false;
		boolean falag=true;
		try {
			Query query=em.createQuery("update CreateSalaryBudgetTable c set name=?1 ," +
					" salaryDate=?2,chooseTax=?3 , note=?4, updateDate=?5  where c.budgetId=?6");
			query.setParameter(1, createSalaryBudgetTable.getName())
				.setParameter(2, createSalaryBudgetTable.getSalaryDate())
				.setParameter(3, createSalaryBudgetTable.getChooseTax())
				.setParameter(4, createSalaryBudgetTable.getNote())
				.setParameter(5, createSalaryBudgetTable.getUpdateDate())
				.setParameter(6, createSalaryBudgetTable.getBudgetId())
			.executeUpdate();
		} catch (Exception e) {
			falag=false;
			e.printStackTrace();
			
		}
		
		
		return falag;
		
	}
	@SuppressWarnings("unchecked")
	public List<CreateSalaryBudgetTable>  getFindCreateSalaryBudgetTables(Integer budgetId){
		
		Query query=em.createQuery("select c from CreateSalaryBudgetTable  c where c.budgetId=?1");
		   query.setParameter(1,budgetId);
		   return query.getResultList();
		
	}

	public void updateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable) {
		try {
			em.createQuery("update CreateSalaryBudgetTable c set c.name=?1,c.wageMonth=?2,c.mergeTax=?3,c.note=?4 where c.budgetId=?5")
			.setParameter(1, createSalaryBudgetTable.getName()).setParameter(2, createSalaryBudgetTable.getWageMonth())
			.setParameter(3, createSalaryBudgetTable.getMergeTax()).setParameter(4, createSalaryBudgetTable.getNote())
			.setParameter(5, createSalaryBudgetTable.getBudgetId())
			.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
