package cn.fm.service.privilege.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.WmsRole;
import cn.fm.bean.user.User;
import cn.fm.bean.user.WmsUser;
import cn.fm.dao.PrivilegeDAO;
import cn.fm.dao.UserDAO;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.privilege.PrivilegeService;



@Service
public class PrivilegeServiceImpl extends DaoSupport<Privilege> implements PrivilegeService {

	@Resource
	private PrivilegeDAO privilegeDAO;
	
	@Resource
	private UserDAO userDAO;
	
	public List<Privilege> getPrivileges() {
		return privilegeDAO.findAll();
	}

	public Set<Privilege> getPrivilegesByUserId(Integer userId) {
		
		WmsUser user=em.find(WmsUser.class, userId);
		Set<Privilege> privileges=new TreeSet<Privilege>();
		privileges.addAll(user.getPrivileges());
		
		Set<WmsRole> roles=user.getRoless();
		for (WmsRole role : roles) {
			privileges.addAll(role.getPrivileges());
		}
		return privileges;
		
	}

	@Override
	public Set<Privilege> getPrivilegesByUserId(String userId) {
		User user=userDAO.findById(userId);
		Set<Privilege> privileges=new TreeSet<Privilege>();
		privileges.addAll(user.getPrivileges());
		
		Set<WmsRole> roles=user.getRoles();
		
		for (WmsRole role : roles) {
			privileges.addAll(role.getPrivileges());
		}
		return privileges;
	}

	

}
