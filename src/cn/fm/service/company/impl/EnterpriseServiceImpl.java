package cn.fm.service.company.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EnterpriseService;


@Service @Transactional
public class EnterpriseServiceImpl extends DaoSupport<Enterprise> implements EnterpriseService {

	
	public List<Enterprise> getAllEnterprise(WmsUser user)
	{
		if(user==null)return null;
		List<Enterprise> enterpriseListVO=new ArrayList<Enterprise>();
		Set<Enterprise>  enterpriseList=user.getEnterprise();
		if(enterpriseList.size()>0){
			for (Enterprise enterprise : enterpriseList) {
				Long count=getCountEmployees(enterprise.getEnterpriseId());
				Enterprise enterpriseVO=new Enterprise();
				enterpriseVO.setAccountLine(enterprise.getAccountLine());
				enterpriseVO.setAddress(enterprise.getAddress());
				enterpriseVO.setContact(enterprise.getContact());
				enterpriseVO.setEmail(enterprise.getEmail());
				enterpriseVO.setEnterpriseBankAccount(enterprise.getEnterpriseBankAccount());
				enterpriseVO.setFax(enterprise.getFax());
				enterpriseVO.setFullName(enterprise.getFullName());
				enterpriseVO.setEnterpriseId(enterprise.getEnterpriseId());
				enterpriseVO.setLegalRepresentative(enterprise.getLegalRepresentative());
				enterpriseVO.setQq(enterprise.getQq());
				enterpriseVO.setPhone(enterprise.getPhone());
				enterpriseVO.setStatus(enterprise.getStatus());
				enterpriseVO.setRferred(enterprise.getRferred());
				//enterpriseVO.setUserId(enterprise.getUserId());
				enterpriseVO.setCount(count);
				
				enterpriseListVO.add(enterpriseVO);
				
				
			}
		}
		
		return enterpriseListVO;

	}
	/**
	 * 统计公司多少员工
	 * @param enterpriseId
	 * @return
	 */
	public long  getCountEmployees(Integer enterpriseId)
	{
		
		Query query = em.createQuery("select count(e) from EnterpriseEmployees e where e.enterprise.enterpriseId=?1");
		query.setParameter(1, enterpriseId);
		return (Long) query.getSingleResult();
	}
	
	public void saveEnterprise(Enterprise enterprise){
		
		
		super.save(enterprise);
		
	}
	public List<Enterprise> getAllEnterprise(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * 查询这个企业的所有负责人
	 * @param enterprise
	 * @return
	 */
	public List<WmsUser>  getEnterpriseToBoWmsUser(List<Enterprise> enterprise)
	{
		
		List<WmsUser>  wmsUserListVO=new ArrayList<WmsUser>();
		if(enterprise.size()==0)return null;
		for(Enterprise enter : enterprise){
		
			Enterprise en=em.find(Enterprise.class, enter.getEnterpriseId());
			enter.setUser(en.getUser());
			for(WmsUser us :enter.getUser()){
				wmsUserListVO.add(us);
			}
		}
		return wmsUserListVO;
	}
	public boolean updateEnterprise(Enterprise enterprise){
		boolean flag=false;
		try {
			Query query=em.createQuery("update Enterprise set rferred=?1," +
					" fullName=?2," +
					"legalRepresentative=?3," +
					"accountLine=?4," +
					"enterpriseBankAccount=?5," +
					"address=?6," +
					"contact=?7," +
					"phone=?8," +
					"qq=?9," +
					"fax=?10," +
					"email=?11," +
					"status=?12 where enterpriseId=?13");
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
				 .setParameter(13, enterprise.getEnterpriseId())
				 .executeUpdate();
			flag=true;
			
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		
		return flag;
	}

}
