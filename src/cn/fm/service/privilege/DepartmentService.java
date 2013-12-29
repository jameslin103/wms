package cn.fm.service.privilege;

import java.util.List;

import cn.fm.bean.user.Department;


public interface DepartmentService {
	
	public List<Department> getDepartments();
	public void save(Department department);
	public void update(Department department);
	public void deleteUpdate(Department department);
}
