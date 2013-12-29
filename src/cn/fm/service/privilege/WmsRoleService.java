package cn.fm.service.privilege;

import java.util.List;
import cn.fm.bean.permissions.WmsRole;

public interface WmsRoleService {

	
	public List<WmsRole> getRoles();
	public void save(WmsRole role);
	public void romveRole(String id);
	public WmsRole getRoleById(String id);
	
	

}
