package cn.fm.service.permissions;

import java.util.List;

import cn.fm.bean.permissions.Role;
import cn.fm.service.base.DAO;

public interface RoleService extends DAO<Role>{
	List<Role> getAll();
	void addRole(String[] menuIds,Role role);
	void updateRole(String[] menuIds,Role role);
}
