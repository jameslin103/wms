package cn.fm.service.company.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.oscache.util.StringUtil;

import cn.fm.bean.PageView;
import cn.fm.bean.QueryResult;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.user.User;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EnterpriseService;


@Service @Transactional
public class EnterpriseServiceImpl extends DaoSupport<Enterprise> implements EnterpriseService {

	
	@SuppressWarnings("unchecked")
	public PageView<Enterprise> getAllEnterprise(int maxresult, int page,User user,Enterprise enterprise)
	{
		
		String sql="";
		String countSql="";
		if(user!=null && !user.getId().equals("0") && !user.getId().equals("") || enterprise!=null && !StringUtil.isEmpty(enterprise.getFullName())){
			sql="select e from Enterprise e join e.user u "+createCondition(enterprise,user)+" order by e.enterpriseId desc";
			countSql="select count(e) from Enterprise e join e.user u "+createCondition(enterprise,user)+" order by e.enterpriseId desc";
		}else{
			sql="select e from Enterprise e order by e.enterpriseId desc ";
			countSql="select count(e) from Enterprise e order by e.enterpriseId desc ";
		}
		
		PageView<Enterprise> pageView = new PageView<Enterprise>(maxresult,page);
		QueryResult<Enterprise> qr = new QueryResult<Enterprise>();
		Query query = em.createQuery(sql);
	
		if(pageView.getFirstResult()!=-1 && pageView.getMaxresult()!=-1)
			query.setFirstResult(pageView.getFirstResult()).setMaxResults(pageView.getMaxresult());
		qr.setResultlist(query.getResultList());
		
		query = em.createQuery(countSql);
		qr.setTotalrecord((Long)query.getSingleResult());
		pageView.setQueryResult(qr);
		
		return pageView;

	}
	@SuppressWarnings("unchecked")
	public List<Enterprise> getUserToAllEnterprise(User user)
	{
		try {
		 return	em.createQuery("select e from Enterprise e join e.user u  where u.id=?1 order by e.enterpriseId desc")
		 		.setParameter(1, user.getId()).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	/**
	 * 统计公司多少员工
	 * @param enterpriseId
	 * @return
	 */
	public long  getCountEmployees(Integer enterpriseId)
	{
		
		Query query = em.createQuery("select count(e) from EnterpriseEmployees e where e.enterprise.enterpriseId=?1 and e.departure=0 and e.reduction=0 and pseudoDelete=0");
		query.setParameter(1, enterpriseId);
		return (Long) query.getSingleResult();
	}
	
	public BigDecimal findBalanceDetail(Integer enterpriseId) {
		
		Query query=null;
		try {
			query=em.createQuery("select sum(b.balance) from BalanceDetail b where b.enterprise.enterpriseId=?1");
			  query.setParameter(1, enterpriseId);
		} catch (Exception e) {
			
		}
	
	return (BigDecimal)query.getSingleResult();
		
	}
	
	/**
	 * 查询这个企业的所有负责人
	 * @param enterprise
	 * @return
	 */
	public List<User>  getEnterpriseToBoWmsUser(List<Enterprise> enterprise)
	{
		
		List<User>  userListVO=new ArrayList<User>();
		if(enterprise.size()==0)return null;
		for(Enterprise enter : enterprise){
		
			Enterprise en=em.find(Enterprise.class, enter.getEnterpriseId());
			enter.setUser(en.getUser());
			for(User us :enter.getUser()){
				userListVO.add(us);
			}
		}
		return userListVO;
	}
	
	public boolean updateEnterStatus(Enterprise enterprise){
		if(enterprise==null || enterprise.getEnterpriseId()==null)return false;
			Enterprise enterprisePO=em.find(Enterprise.class,enterprise.getEnterpriseId());
			enterprisePO.setAuditStatus(enterprise.getAuditStatus());
			enterprisePO.setAudituser(enterprise.getAudituser());
			enterprisePO.setAuditDate(new Date());
			enterprisePO.setNote(enterprise.getNote().trim());
			
		return true;
		
	}
	
	
	
	public boolean updateEnterprise(Enterprise enterprise){
		boolean flag=false;
		try {
			Query query=em.createQuery("update Enterprise set rferred=?1," +
					" fullName=?2," +
					" legalRepresentative=?3," +
					" accountLine=?4," +
					" enterpriseBankAccount=?5," +
					" address=?6," +
					" contact=?7," +
					" phone=?8," +
					" qq=?9," +
					" fax=?10," +
					" email=?11," +
					" status=?12," +
					" send=?13,"+
					" contatId=?14," +
					" industryType=?15" +
					" where enterpriseId=?16");
			query.setParameter(1, enterprise.getRferred())
				 .setParameter(2, enterprise.getFullName())
				 .setParameter(3, enterprise.getLegalRepresentative())
				 .setParameter(4, enterprise.getAccountLine())
				 .setParameter(5, enterprise.getEnterpriseBankAccount())
				 .setParameter(6, enterprise.getAddress())
				 .setParameter(7, enterprise.getContact())
				 .setParameter(8, enterprise.getPhone())
				 .setParameter(9, enterprise.getQq())
				 .setParameter(10, enterprise.getFax())
				 .setParameter(11, enterprise.getEmail())
				 .setParameter(12, enterprise.getStatus())
				 .setParameter(13, enterprise.getSend())
				 .setParameter(14, enterprise.getContatId())
				 .setParameter(15, enterprise.getIndustryType())
				 .setParameter(16, enterprise.getEnterpriseId())
				 .executeUpdate();
			flag=true;
			
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		
		return flag;
	}
	/**
	 * 修改企业联系人
	 * @param enterprise
	 * @return
	 */
	public boolean updateEnterpriseContact(Enterprise enterprise){
		
		try {
			Query query=em.createQuery("update Enterprise set contact=?1," +
					"phone=?2," +
					"qq=?3," +
					"fax=?4," +
					"email=?5 where enterpriseId=?6");
			
			query.setParameter(1, enterprise.getContact())
				 .setParameter(2, enterprise.getPhone())
				 .setParameter(3, enterprise.getQq())
				 .setParameter(4, enterprise.getFax())
				 .setParameter(5, enterprise.getEmail())
				 .setParameter(6, enterprise.getEnterpriseId())
				 .executeUpdate();
		
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
		
		return true;
		
		
	}
	/**
	 * 查询所有企业
	 */
	@SuppressWarnings("unchecked")
	public List<Enterprise> getAllEnterprise() {
		try {
			return em.createQuery("select e from Enterprise e order by e.enterpriseId asc ").getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 增加企业负责人
	 * @param enterprise
	 * @param user
	 */
	public void saveEnterpriseToBeResponsible(Integer enterpriseId,String userId){
		if(enterpriseId==null || userId==null)return;
		User userPo=em.find(User.class, userId);
		Enterprise enterprisePO=em.getReference(Enterprise.class, enterpriseId);
		enterprisePO.addUser(userPo);
		em.persist(enterprisePO);
	}
	
	/**
	 * 解除企业负责人
	 * @return
	 */
	public void removeToEnterpriseHeadUser(Integer enterpriseId,String userId)
	{
		
		if(enterpriseId==null || userId==null)return;
		User userPo=em.find(User.class, userId);
		Enterprise enterprisePO=em.getReference(Enterprise.class,enterpriseId);
		enterprisePO.removeUser(userPo);
	}
	public Long findByFullName(String fullName) {
		try {
			return (Long)em.createQuery(" select count(*) from Enterprise e where 1=1 and e.fullName=?1")
			.setParameter(1, fullName).getSingleResult();
			 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageView<Enterprise> getUserToByEnterprise(int maxresult, int page,User user,Enterprise enterprise) {
		
		String sql;
		String countSql;
		
		if(enterprise!=null && !StringUtil.isEmpty(enterprise.getFullName())){
			countSql="select count(e) from Enterprise e join e.user u  where 1=1 and u.id=?1 and e.fullName like '%" + enterprise.getFullName().trim() + "%' order by e.enterpriseId desc";
			sql="select e from Enterprise e join  e.user u    where 1=1 and u.id=?1   and e.fullName like '%" + enterprise.getFullName().trim() + "%' order by e.enterpriseId desc";
		}else{
			countSql="select count(e) from Enterprise e join e.user u  where 1=1 and u.id=?1  order by e.enterpriseId desc";
			sql="select e from Enterprise e join e.user u  where 1=1 and u.id=?1 order by e.enterpriseId desc";
		}
		
		
		PageView<Enterprise> pageView = new PageView<Enterprise>(maxresult,page);
		QueryResult<Enterprise> qr = new QueryResult<Enterprise>();
		Query query = em.createQuery(sql);
	
		if(pageView.getFirstResult()!=-1 && pageView.getMaxresult()!=-1)
			query.setFirstResult(pageView.getFirstResult()).setMaxResults(pageView.getMaxresult());
		if(user!=null && user.getId()!=null)query.setParameter(1, user.getId());
		qr.setResultlist(query.getResultList());
		
		query = em.createQuery(countSql);
		if(user!=null && user.getId()!=null)query.setParameter(1, user.getId());
		qr.setTotalrecord((Long)query.getSingleResult());
		pageView.setQueryResult(qr);
		
		return pageView;
	}
	
	private String createCondition(Enterprise enterprise,User user) {
		
		
		StringBuilder builder = new StringBuilder(" where 1=1 ");
		if (enterprise!=null && enterprise.getFullName()!= null && !enterprise.getFullName().trim().equals("")) {
			builder.append(" and e.fullName like '%" + enterprise.getFullName().trim() + "%'");
		}
		if (enterprise!=null && enterprise.getContatId()!= null && !enterprise.getContatId().trim().equals("")) {
			builder.append(" and e.contatId='" + enterprise.getContatId().trim() + "'");
		}
		if (user.getId()!= null && !user.getId().trim().equals("") && !user.getId().equals("0")) {
			builder.append(" and u.id='" + user.getId().trim()+ "'");
		}
		return builder.toString();
	}

}
