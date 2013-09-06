package cn.fm.service.privilege.impl;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.fm.bean.permissions.Employee;
import cn.fm.bean.permissions.PrivilegeGroup;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.privilege.PrivilegeGroupService;


@Service
public class PrivilegeGroupServiceImpl extends DaoSupport<PrivilegeGroup> implements PrivilegeGroupService {

	@Override
	public void delete(Serializable... entityids) {
		for(Serializable id : entityids){
			PrivilegeGroup group = find(id);
			for(Employee employee : group.getEmployees()){
				employee.getGroups().remove(group);
			}
			em.remove(group);
		}
	}

	@Override
	public void save(PrivilegeGroup entity) {
		//entity.setGroupid(UUID.randomUUID().toString());
		super.save(entity);
	}
	
}
