package cn.fm.service.salary.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.PageView;
import cn.fm.bean.QueryResult;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.bean.user.User;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.utils.DateUtil;

@Service @Transactional
public class CreateSalaryBudgetTableServiceImpl extends	DaoSupport<CreateSalaryBudgetTable> implements	CreateSalaryBudgetTableService {


	/**
	 * 保存工资预算表
	 * @param createSalaryBudgetTable
	 */
	public void saveCreateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable,Integer enterpriseId,Integer templateId)
	{
		
		
		Enterprise enterprisePO=em.find(Enterprise.class, enterpriseId);
		SalaryTemplate salaryTemplatePO=em.find(SalaryTemplate.class, templateId);
		createSalaryBudgetTable.setTemplateName(salaryTemplatePO.getTemplateName());
		createSalaryBudgetTable.setEnterprise(enterprisePO);
		createSalaryBudgetTable.setSalaryTemplate(salaryTemplatePO);
		super.save(createSalaryBudgetTable);

	}

	@SuppressWarnings("unchecked")
	public PageView<CreateSalaryBudgetTable>  getAllCreateSalaryBudgetTable(int maxresult, int page,Enterprise enterprise,CreateSalaryBudgetTable createSalaryBudgetTable,User user)
	{
		String sql="select c from CreateSalaryBudgetTable c "+createCondition(enterprise,createSalaryBudgetTable,user)+"  order by c.salaryDate desc ";
		String countsql="select count(c) from CreateSalaryBudgetTable c "+createCondition(enterprise,createSalaryBudgetTable,user)+"  order by c.salaryDate desc ";
		PageView<CreateSalaryBudgetTable> pageView = new PageView<CreateSalaryBudgetTable>(maxresult,page);
		QueryResult<CreateSalaryBudgetTable> qr = new QueryResult<CreateSalaryBudgetTable>();
		Query query = em.createQuery(sql);
	
		if(pageView.getFirstResult()!=-1 && pageView.getMaxresult()!=-1)
			query.setFirstResult(pageView.getFirstResult()).setMaxResults(pageView.getMaxresult());
		qr.setResultlist(query.getResultList());
		
		query = em.createQuery(countsql);
		qr.setTotalrecord((Long)query.getSingleResult());
		pageView.setQueryResult(qr);
		
		return pageView;
		
	}
	
	private String createCondition(Enterprise enterprise,CreateSalaryBudgetTable createSalaryBudgetTable,User user) {
		
		StringBuilder builder = new StringBuilder(" where 1=1 ");
		if (enterprise!=null && enterprise.getEnterpriseId()!= null && !enterprise.getEnterpriseId().equals("")) {
			builder.append(" and c.enterprise.enterpriseId=" + enterprise.getEnterpriseId());
		}
		if (createSalaryBudgetTable!=null && createSalaryBudgetTable.getName()!= null && !createSalaryBudgetTable.getName().equals("")) {
			builder.append(" and c.name like '%" + createSalaryBudgetTable.getName().trim()+"%'");
		}
		if(createSalaryBudgetTable!=null && createSalaryBudgetTable.getSalaryDate()!=null && !createSalaryBudgetTable.getSalaryDate().equals(""))
		{
			String dateYear=DateUtil.dateToString(createSalaryBudgetTable.getSalaryDate(), DateUtil.FORMAT_DATE);
			builder.append(" and YEAR(c.salaryDate)=" +dateYear.substring(0,4));
			builder.append(" and month(c.salaryDate)=" +dateYear.substring(5,7));
		}
		if(user!=null && user.getId()!=null && !user.getId().equals("") && !user.getId().equals("0")){
			builder.append(" and c.user.id='" + user.getId().trim()+"'");
			
		}
		return builder.toString();
	}
	
	/**
	 * 更新工资预算表
	 * @param createSalaryBudgetTable
	 * @param budgerId
	 */
	public void updateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable,Integer budgerId)
	{
		CreateSalaryBudgetTable createSalaryBudgetTablePO=em.find(CreateSalaryBudgetTable.class, budgerId);
		createSalaryBudgetTablePO.setBudgetId(createSalaryBudgetTablePO.getBudgetId());
		createSalaryBudgetTablePO.setChooseTax(createSalaryBudgetTable.getChooseTax());
		createSalaryBudgetTablePO.getCreateDate();
		createSalaryBudgetTablePO.setServiceHeTotal(createSalaryBudgetTable.getServiceHeTotal());
		//BigDecimal mmakeTotal=createSalaryBudgetTable.getServiceHeTotal().add(createSalaryBudgetTablePO.getMakeTotal().setScale(2, BigDecimal.ROUND_HALF_EVEN));
		
		
		createSalaryBudgetTablePO.setName(createSalaryBudgetTable.getName());
		createSalaryBudgetTablePO.setNote(createSalaryBudgetTable.getNote());
		
		super.update(createSalaryBudgetTablePO);
		
	}
	public boolean updateCreateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable)
	{
		if(createSalaryBudgetTable==null)return false;
		boolean falag=true;
		try {
			Query query=em.createQuery("update CreateSalaryBudgetTable c set name=?1 ," +
					" salaryDate=?2,chooseTax=?3 , note=?4, updateDate=?5 where c.budgetId=?7");
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
			em.createQuery("update CreateSalaryBudgetTable c " +
					"set c.name=?1," +
					"c.salaryDate=?2," +
					"c.note=?4, " +
					"c.updateDate=?5, " +
					"c.mergeId=?6, " +
					"c.serviceHeTotal=?7,"+
					"c.serviceTotal=?8,"+
					"c.makeTotal=?9"+
					" where c.budgetId=?10")
				.setParameter(1, createSalaryBudgetTable.getName().trim())
				.setParameter(2, createSalaryBudgetTable.getSalaryDate())
				//.setParameter(3, createSalaryBudgetTable.getChooseTax())
				.setParameter(4, createSalaryBudgetTable.getNote().trim())
				.setParameter(5, createSalaryBudgetTable.getUpdateDate())
				.setParameter(6, createSalaryBudgetTable.getMergeId())
				.setParameter(7, createSalaryBudgetTable.getServiceHeTotal())
				.setParameter(8, createSalaryBudgetTable.getServiceTotal())
				.setParameter(9, createSalaryBudgetTable.getMakeTotal())
				.setParameter(10, createSalaryBudgetTable.getBudgetId())
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
						" c.wageTotal=?2," +
						" c.serviceTotal=?3," +
						" c.fiveInsurancesTotal=?4, " +
						" c.issueNumber=?5, " +
						" c.cmbc=?6," +
						" c.heLines=?7, " +
						" c.cashnumber=?8 " +
						" where c.budgetId=?9 ")

						.setParameter(1, createSalaryBudgetTable.getMakeTotal())
						.setParameter(2, createSalaryBudgetTable.getWageTotal())
						.setParameter(3, createSalaryBudgetTable.getServiceTotal())
						.setParameter(4, createSalaryBudgetTable.getFiveInsurancesTotal())
						.setParameter(5, createSalaryBudgetTable.getIssueNumber())
						.setParameter(6, createSalaryBudgetTable.getCmbc())
						.setParameter(7, createSalaryBudgetTable.getHeLines())
						.setParameter(8, createSalaryBudgetTable.getCashnumber())
						.setParameter(9, createSalaryBudgetTable.getBudgetId())
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
	public void deleteCreateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable){
		em.remove(createSalaryBudgetTable);
	}
	

	
	/**
	 * 获取工资预算表
	 * @param date 时间段获取
	 * @param enterpriseId 根据企业
	 * @return list
	 */
	@SuppressWarnings("unchecked")
	public List<CreateSalaryBudgetTable> findBeforeCurrentDateTemplate(String date,Integer enterpriseId)
	{
		List<CreateSalaryBudgetTable> createTableList=new ArrayList<CreateSalaryBudgetTable>();
		CreateSalaryBudgetTable     createSalaryBudgetTable=null;
		try {
			
			String sql="select * from createsalarybudgetTable c " +
					"where c.salaryDate between date_sub('"+date+"',interval 2 month) " +
					" and '"+date+"' " +
					" and c.enterpriseId=1 " +
					" and c.isTax=0";
			List object=em.createNativeQuery(sql).getResultList();
			
			for (Object obj : object) {
				Object[] tempObject=(Object[])obj;
				createSalaryBudgetTable=new CreateSalaryBudgetTable();
				createSalaryBudgetTable.setBudgetId((Integer)tempObject[0]);
				createSalaryBudgetTable.setName(tempObject[3].toString());
				createSalaryBudgetTable.setSalaryDate((Date)tempObject[5]);
				
				createTableList.add(createSalaryBudgetTable);
			}

	
		
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return createTableList;
	}

	public void updateSalaryStatus(CreateSalaryBudgetTable createSalaryBudgetTable) 
	{
		
		em.createQuery("update CreateSalaryBudgetTable c" +
				" set c.cmbcDate=?1," +
				"c.cashnumberDate=?2," +
				"c.heLinesDate=?3," +
				"c.user_operator=?4 ," +
				"c.status=?5 " +
				" where c.budgetId=?6")
				.setParameter(1, createSalaryBudgetTable.getCmbcDate())
				.setParameter(2, createSalaryBudgetTable.getCashnumberDate())
				.setParameter(3, createSalaryBudgetTable.getHeLinesDate())
				.setParameter(4, createSalaryBudgetTable.getUser_operator())
				.setParameter(5, createSalaryBudgetTable.getStatus())
				.setParameter(6, createSalaryBudgetTable.getBudgetId()).executeUpdate();
	}

	


}
