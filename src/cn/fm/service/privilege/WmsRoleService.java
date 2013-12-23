package cn.fm.service.privilege;

import java.util.List;


import cn.fm.bean.permissions.WmsRole;
import cn.fm.service.base.DAO;


public interface WmsRoleService extends DAO<WmsRole>{

	public List<WmsRole> getRoles();

	public void save(WmsRole role);
}
