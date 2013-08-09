package cn.fm.service.privilege;

import cn.fm.bean.permissions.Employee;
import cn.fm.service.base.DAO;



public interface EmployeeService extends DAO<Employee> {
	/**
	 * 判断用户是否存在
	 * @param username 用户名
	 * @return
	 */
	public boolean exist(String username);
	/**
	 *判断用户名密码是否正确
	 * @param username 用户名
	 * @param password 密码
	 * * @return
	 */
	public boolean validate(String username, String password);
}
