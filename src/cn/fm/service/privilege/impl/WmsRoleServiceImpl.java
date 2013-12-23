package cn.fm.service.privilege.impl;

import java.util.List;

import org.springframework.stereotype.Service;


import cn.fm.bean.permissions.WmsRole;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.privilege.WmsRoleService;


@Service
@SuppressWarnings("unchecked")
public class WmsRoleServiceImpl extends DaoSupport<WmsRole> implements WmsRoleService {

	
	
	public List<WmsRole> getRoles() {
		return em.createQuery(" from WmsRole").getResultList();
	}

	@Override
	public void save(WmsRole role) {
		super.save(role);		
	}
	
	
	
	
	
	
	
	
}
