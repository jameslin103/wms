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
		Query query=em.createQuery("select c from CreateSalaryBudgetTable  c where c.enterprise.enterpriseId=?1 and YEAR(c.salaryDate)=year(curdate()) group by c.salaryDate ");
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
			em.createQuery("update CreateSalaryBudgetTable c set c.name=?1,c.salaryDate=?2,c.chooseTax=?3,c.note=?4 where c.budgetId=?5")
			.setParameter(1, createSalaryBudgetTable.getName()).setParameter(2, createSalaryBudgetTable.getSalaryDate())
			.setParameter(3, createSalaryBudgetTable.getChooseTax()).setParameter(4, createSalaryBudgetTable.getNote())
			.setParameter(5, createSalaryBudgetTable.getBudgetId())
			.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * 插入各种总额(开票；五险，人数)
	 */
	public void updateCreateSalaryBudgetTableSummary(CreateSalaryBudgetTable createSalaryBudgetTable) {
		
			try {
				em.createQuery("update CreateSalaryBudgetTable c set c.makeTotal=?1," +
						"c.wageTotal=?2," +
						"c.serviceTotal=?3," +
						"c.fiveInsurancesTotal=?4," +
						"c.issueNumber=?5 where c.budgetId=?6")
						.setParameter(1, createSalaryBudgetTable.getMakeTotal())
						.setParameter(2, createSalaryBudgetTable.getWageTotal())
						.setParameter(3, createSalaryBudgetTable.getServiceTotal())
						.setParameter(4, createSalaryBudgetTable.getFiveInsurancesTotal())
						.setParameter(5, createSalaryBudgetTable.getIssueNumber())
						.setParameter(6, createSalaryBudgetTable.getBudgetId())
						.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
	}
	
	public void deleteCreateSalaryBudgetTable(Integer budgetId)
	{
		try {
			em.createQuery("delete CreateSalaryBudgetTable c where c.budgetId=?1").setParameter(1, budgetId).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}
