package cn.fm.service.salary.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.salary.TaxOfPerson;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.salary.TaxOfPersonService;

@Transactional @Service
public class TaxOfPersonServiceImpl extends DaoSupport<TaxOfPerson> implements 	TaxOfPersonService {

	
	//查询起征点个税
	public TaxOfPerson getTaxOfPerson()
	{
		try {
			Query query=em.createQuery("select t from TaxOfPerson t ");
			return (TaxOfPerson)query.getSingleResult();
		} catch (Exception e) {
			
			e.printStackTrace();
			return null;
		}
	}
	//修改征税起点
	public void updateTaxOfPerson(TaxOfPerson taxOfPerson )
	{
		try {
			em.createQuery("update TaxOfPerson t set t.taxThreshold=?1,t.statrDate=?2 where t.taxid=?3").
			setParameter(1, taxOfPerson.getTaxThreshold()).setParameter(2, taxOfPerson.getStatrDate()).
			setParameter(3, taxOfPerson.getTaxid()).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
