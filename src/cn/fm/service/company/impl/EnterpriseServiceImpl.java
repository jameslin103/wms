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
	public List<Enterprise> getAllEnterprise()
	{
	
		List<Enterprise> enterpriseList=new ArrayList<Enterprise>();
		Query query = em.createQuery("select b from Enterprise b ");
		enterpriseList=query.getResultList();
		return enterpriseList;

	}

}
