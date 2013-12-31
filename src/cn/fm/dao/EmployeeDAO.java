package cn.fm.dao;

import java.util.List;

import cn.fm.bean.PageBean;
import cn.fm.bean.user.Employee;

public interface EmployeeDAO extends DAO<Employee> {
	
	List<Employee> findByCondition(Employee employee);

	PageBean<Employee> findByPage(int page, int pageSize, Employee employee);
	
	
}
