package cn.fm.service.permissions.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.permissions.Role;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.permissions.RoleService;


@Service   @Transactional
public class RoleServiceImpl extends DaoSupport<Role> implements RoleService{

	@SuppressWarnings("unchecked")
	public List<Role> getAll() {
		List<Role> list = em.createQuery("from Role").getResultList();
		return list;
	}

	public void addRole(String[] menuIds, Role role) {
		role.setMenuIds(mergeMenuIds(menuIds));
		save(role);
	}
	
	public void updateRole(String[] menuIds, Role roleVO) {
		em.createQuery("update Role r set r.menuIds=?1 where r.roleId = ?2")
		.setParameter(1,mergeMenuIds(menuIds)).setParameter(2,roleVO.getRoleId()).executeUpdate();
	}

	private String mergeMenuIds(String[] menuIds){
		String ids = "";
		for (int i = 0; i < menuIds.length; i++) {
			ids+=menuIds[i]+",";
		}
		return ids;
	}

}
