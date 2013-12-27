package cn.fm.service.privilege.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.fm.dao.RoleDAO;
import cn.fm.bean.permissions.WmsRole;
import cn.fm.service.privilege.WmsRoleService;

@Service
public class WmsRoleServiceImpl implements WmsRoleService{

	@Resource
	private RoleDAO roleDAO;

	public List<WmsRole> getRoles() {
		return roleDAO.findAll();
	}

	public void save(WmsRole role) {
		roleDAO.save(role);		
	}

}
