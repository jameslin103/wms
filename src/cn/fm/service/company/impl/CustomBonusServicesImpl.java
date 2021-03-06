package cn.fm.service.company.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.CustomBonus;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.CustomBonusServices;

@Service @Transactional
public class CustomBonusServicesImpl extends DaoSupport<CustomBonus> implements CustomBonusServices {

	@SuppressWarnings("unchecked")
	public List<CustomBonus> getAllCustomBonus(Integer enterpriseId) {
		Query query=em.createQuery("select c from CustomBonus c where c.enterprise.enterpriseId=?1").setParameter(1, enterpriseId);
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<CustomBonus> getStatusEnableCustomBonus(Integer enterpriseId) {
		Query query=em.createQuery("select c from CustomBonus c where c.state=1 and c.enterprise.enterpriseId=?1").setParameter(1, enterpriseId);
		return query.getResultList();
	}
	
	public void save(CustomBonus customBonus) {
		super.save(customBonus);
	}

	public void update(CustomBonus customBonus,Integer id) {
		Query query=em.createQuery("update  customBonus c set c.bonusName=?1 where c.id=?2");
		
		query.setParameter(1, customBonus.getBonusName()).setParameter(2, customBonus.getId());
		query.getSingleResult();
		
	}

	public void updateCustomBonus(CustomBonus customBonus) {
		try {
			em.createQuery("update CustomBonus c set c.bonusName=?1,c.state=?2 where id=?3")
			.setParameter(1, customBonus.getBonusName())
			.setParameter(2, customBonus.getState()).setParameter(3, customBonus.getId()).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
