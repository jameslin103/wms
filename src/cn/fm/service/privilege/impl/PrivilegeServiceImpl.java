package cn.fm.service.privilege.impl;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.Role;
import cn.fm.bean.user.User;
import cn.fm.dao.PrivilegeDAO;
import cn.fm.dao.UserDAO;
import cn.fm.dao.RoleDAO;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.privilege.PrivilegeService;



@Service @Transactional
public class PrivilegeServiceImpl extends DaoSupport<Privilege> implements PrivilegeService {

	@Resource
	private PrivilegeDAO privilegeDAO;
	@Resource
	private UserDAO userDAO;
	@Resource
	private RoleDAO roleDAO;
	
	
	public List<Privilege> getPrivileges() {
		return privilegeDAO.findAll();
	}

	@Override
	public Set<Privilege> getPrivilegesByUserId(String userId) {
		
		User user=userDAO.findById(userId);
		Set<Privilege> privileges=new TreeSet<Privilege>();
		privileges.addAll(user.getPrivileges());
		
		Set<Role> roles=user.getRoles();
		
		for (Role role : roles) {
			privileges.addAll(role.getPrivileges());
		}
		return privileges;
	}

	@Override
	public Set<Privilege> getPrivilegesByRoleId(String roleId){
		if(roleId==null)return null;
		Set<Privilege> privileges=new TreeSet<Privilege>();
		Role role=roleDAO.findById(roleId);
		if(role!=null){
			
			privileges.addAll(role.getPrivileges());
		}
		
		
		return privileges;
	}

	

}
