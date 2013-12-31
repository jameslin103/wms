package cn.fm.service.privilege;

import java.util.List;

import cn.fm.bean.PageBean;
import cn.fm.bean.user.Employee;


public interface EmployeeService {
	
	List<Employee> getEmps(Employee employee);

	PageBean<Employee> getEmpsByPage(int page, Employee employee);

	void save(Employee employee);
	
	public void deleteEmployee(String id);

	boolean empNoExist(String empNo);

	void bind(String empId, String userId);

	void unbind(String empId);
	
	Employee getById(String empId);
	
	public void updateEmployee(Employee employee);
}
