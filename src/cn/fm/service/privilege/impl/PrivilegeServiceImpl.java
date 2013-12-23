package cn.fm.service.privilege.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.springframework.stereotype.Service;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.WmsRole;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.privilege.PrivilegeService;



@Service
@SuppressWarnings("unchecked")
public class PrivilegeServiceImpl extends DaoSupport<Privilege> implements PrivilegeService {

	
	public List<Privilege> getPrivileges() {
		return (List<Privilege>)em.createQuery(" from Privilege p").getResultList();
	}

	public Set<Privilege> getPrivilegesByUserId(String userId) {
		
		WmsUser user=em.find(WmsUser.class, userId);
		Set<Privilege> privileges=new TreeSet<Privilege>();
		privileges.addAll(user.getPrivileges());
		
		Set<WmsRole> roles=user.getRoless();
		for (WmsRole role : roles) {
			privileges.addAll(role.getPrivileges());
		}
		return privileges;
		
	}

	

}
