package cn.fm.service.privilege.impl;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.stereotype.Service;

import cn.fm.bean.permissions.Department;
import cn.fm.bean.permissions.Employee;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.privilege.DepartmentService;



@Service
public class DepartmentServiceImpl extends DaoSupport<Department> implements DepartmentService {

	@Override
	public void delete(Serializable... entityids) {
		for(Serializable id : entityids){
			Department department = this.find(id);
			for(Employee employee : department.getEmployees()){
				employee.setDepartment(null);
			}
			em.remove(department);
		}
	}

	@Override
	public void save(Department entity) {
		entity.setDepartmentid(UUID.randomUUID().toString());
		super.save(entity);
	}

}
