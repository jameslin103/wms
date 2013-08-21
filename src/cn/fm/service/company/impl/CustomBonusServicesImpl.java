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
	public List<CustomBonus> getAllCustomBonus() {
		Query query=em.createQuery("select c from CustomBonus c");
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
}
