package cn.fm.service.privilege;

import java.util.List;
import cn.fm.bean.permissions.Role;

public interface RoleService {

	
	public List<Role> getRoles();
	public void save(Role role);
	public void romveRole(String id);
	public Role getRoleById(String id);
	
	

}
