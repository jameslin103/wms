package cn.fm.service.privilege;

import java.util.List;
import cn.fm.bean.permissions.SystemPrivilege;
import cn.fm.service.base.DAO;



public interface SystemPrivilegeService extends DAO<SystemPrivilege> {
	/**
	 *批量保存系统权限
	 * @param privileges
	 */
	public void batchSave(List<SystemPrivilege> privileges);
}
