package cn.fm.service.privilege;

import java.util.List;
import java.util.Set;

import cn.fm.bean.permissions.Privilege;
import cn.fm.service.base.DAO;

public interface PrivilegeService extends DAO<Privilege>{
	
	public List<Privilege> getPrivileges();
	public Set<Privilege> getPrivilegesByUserId(String userId);

}
