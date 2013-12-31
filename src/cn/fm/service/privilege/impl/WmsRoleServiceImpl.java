package cn.fm.service.privilege.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.dao.RoleDAO;
import cn.fm.service.privilege.RoleService;
import cn.fm.bean.permissions.Role;

@Service @Transactional
public class WmsRoleServiceImpl implements RoleService{

	@Resource
	private RoleDAO roleDAO;

	public List<Role> getRoles() {
		return roleDAO.findAll();
	}

	public void save(Role role) {
		roleDAO.save(role);		
	}
	
	public void romveRole(String id) {
		if(id!=null){
			Role role=roleDAO.findById(id);
			roleDAO.delete(role);	
		}
			
	}

	@Override
	public Role getRoleById(String id) {
		return roleDAO.findById(id);
	}
}
