package cn.fm.service.privilege.impl;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.fm.service.base.DaoSupport;
import cn.fm.bean.user.Department;
import cn.fm.dao.DepartmentDAO;
import cn.fm.service.privilege.DepartmentService;

@Service  @Transactional
public class DepartmentServiceImpl extends  DaoSupport<Department> implements DepartmentService {

	
	@Resource
	private DepartmentDAO departmentDAO;

	public List<Department> getDepartments() {
		return departmentDAO.findAll();
	}

	public void save(Department department) {
		departmentDAO.save(department);
		
	}
	public void update(Department department) {
		Department departmentPO=departmentDAO.findById(department.getId());
		departmentPO.setName(department.getName());
		departmentDAO.update(departmentPO);
		
	}
	public void deleteUpdate(Department department){
		if(department==null || department.getId()==null)return;
		Department departmentPO=departmentDAO.findById(department.getId());
		departmentDAO.delete(departmentPO);
	}

}
