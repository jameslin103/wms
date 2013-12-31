package cn.fm.service.privilege;

import java.util.List;
import java.util.Set;

import cn.fm.bean.permissions.Privilege;


public interface PrivilegeService {
	
	public List<Privilege> getPrivileges();
	public Set<Privilege> getPrivilegesByUserId(String userId);
	public Set<Privilege> getPrivilegesByRoleId(String roleId);
}
