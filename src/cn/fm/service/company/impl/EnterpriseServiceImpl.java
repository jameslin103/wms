package cn.fm.service.company.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.Enterprise;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EnterpriseService;


@Service @Transactional
public class EnterpriseServiceImpl extends DaoSupport<Enterprise> implements EnterpriseService {

	@SuppressWarnings("unchecked")
	public List<Enterprise> getAllEnterprise(Integer userId)
	{
		List<Enterprise> enterpriseListVO=new ArrayList<Enterprise>();
		List<Enterprise> enterpriseList=new ArrayList<Enterprise>();
		Query query = em.createQuery("select b from Enterprise b where b.userId=?1");
		enterpriseList=query.setParameter(1, userId).getResultList();
		if(enterpriseList.size()>0){
			for (Enterprise enterprise : enterpriseList) {
				Long count=getCountEmployees(enterprise.getId());
				Enterprise enterpriseVO=new Enterprise();
				enterpriseVO.setAccountLine(enterprise.getAccountLine());
				enterpriseVO.setAddress(enterprise.getAddress());
				enterpriseVO.setContact(enterprise.getContact());
				enterpriseVO.setEmail(enterprise.getEmail());
				enterpriseVO.setEnterpriseBankAccount(enterprise.getEnterpriseBankAccount());
				enterpriseVO.setFax(enterprise.getFax());
				enterpriseVO.setFullName(enterprise.getFullName());
				enterpriseVO.setId(enterprise.getId());
				enterpriseVO.setLegalRepresentative(enterprise.getLegalRepresentative());
				enterpriseVO.setQq(enterprise.getQq());
				enterpriseVO.setPhone(enterprise.getPhone());
				enterpriseVO.setStatus(enterprise.getStatus());
				enterpriseVO.setRferred(enterprise.getRferred());
				enterpriseVO.setUserId(enterprise.getUserId());
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
		
		Query query = em.createQuery("select count(e) from EnterpriseEmployees e where e.enterpriseId=?1");
		query.setParameter(1, enterpriseId);
		return (Long) query.getSingleResult();
	}


}
