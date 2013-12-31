package cn.fm.service.privilege.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.oscache.util.StringUtil;

import cn.fm.bean.PageBean;
import cn.fm.bean.user.Employee;
import cn.fm.bean.user.User;
import cn.fm.dao.EmployeeDAO;
import cn.fm.dao.UserDAO;
import cn.fm.service.privilege.EmployeeService;
import cn.fm.utils.WmsUtil;


@Service @Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeDAO employeeDAO;
	
	@Resource
	private UserDAO userDAO;

	@Override
	public List<Employee> getEmps(Employee employee) {
		return employeeDAO.findByCondition(employee);
	}

	@Override
	public PageBean<Employee> getEmpsByPage(int page, Employee employee) {
		return employeeDAO.findByPage(page, WmsUtil.getPageSize(), employee);
	}

	@Override
	public void save(Employee employee) {
		employee.setEmpState("正常");
		employeeDAO.save(employee);
	}

	@Override
	public boolean empNoExist(String empNo) {
		return employeeDAO.isExist("empNo", empNo);
	}

	@Override
	public void bind(String empId, String userId) {
		Employee employee=employeeDAO.findById(empId);
		User user=userDAO.findById(userId);
		employee.setUser(user);
		user.setEmployee(employee);		
	}

	@Override
	public void unbind(String empId) {
		Employee employee=employeeDAO.findById(empId);
		employee.getUser().setEmployee(null);
		employee.setUser(null);		
	}

	@Override
	public Employee getById(String empId) {
		return employeeDAO.findById(empId);
	}

	@Override
	public void updateEmployee(Employee employee) {
		if(employee==null || employee.getId()==null)return;
		
		Employee employeePO=employeeDAO.findById(employee.getId());
		
		employeePO.setDepartment(employee.getDepartment());
		employeePO.setDegree(employee.getDegree());
		employeePO.setEmail(employee.getEmail());
		employeePO.setEmpNo(employee.getEmpNo());
		employeePO.setIdCard(employee.getIdCard());
		employeePO.setGender(employee.getGender());
		employeePO.setImage(employee.getImage());
		employeePO.setName(employee.getName());
		employeePO.setPhone(employee.getPhone());
		employeePO.setTelPhone(employee.getTelPhone());
		employeePO.setMarryState(employee.getMarryState());
		employeePO.setBirthDate(employee.getBirthDate());
		employeeDAO.update(employeePO);
		
	}

	@Override
	public void deleteEmployee(String id) {
		if(StringUtil.isEmpty(id))return;
		Employee employee=employeeDAO.findById(id);
		employee.setUser(null);
		employeeDAO.delete(employee);
		
	}

}
