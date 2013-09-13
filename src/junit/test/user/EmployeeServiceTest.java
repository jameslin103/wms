package junit.test.user;


import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.permissions.Employee;
import cn.fm.bean.permissions.PrivilegeGroup;
import cn.fm.bean.permissions.SystemPrivilege;
import cn.fm.bean.user.Gender;
import cn.fm.service.privilege.DepartmentService;
import cn.fm.service.privilege.EmployeeService;
import cn.fm.service.privilege.PrivilegeGroupService;
import cn.fm.service.privilege.SystemPrivilegeService;



public class EmployeeServiceTest {
	private static EmployeeService employeeService;
	private static PrivilegeGroupService privilegeGroupService;
	private static SystemPrivilegeService systemPrivilegeService;
	private static DepartmentService departmentService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			employeeService = (EmployeeService)cxt.getBean("employeeServiceImpl");
			privilegeGroupService = (PrivilegeGroupService)cxt.getBean("privilegeGroupServiceImpl");
			systemPrivilegeService =(SystemPrivilegeService)cxt.getBean("systemPrivilegeServiceImpl");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test public void exsit(){
		System.out.println(employeeService.exist("alax"));
	}
	
	@Test public void delete(){
		employeeService.delete("james");
	}
	
	@Test public void validate(){
		System.out.println(employeeService.validate("wms", "123456"));
	}
	
	/**
	 * 初始化管理员账号
	 */
	@Test
	public void initAdmin() {
		if(employeeService.getCount()==0){
			Employee employee = new Employee();
			employee.setUsername("admin");
			employee.setPassword("123456");
			employee.setRealname("系统管理员");
			employee.setGender(Gender.MAN);
			employee.getGroups().addAll(privilegeGroupService.getScrollData().getResultlist());//赋予权限
			employeeService.save(employee);
		}		
	}
	/**
	 * 初始化系统权限组
	 */
	@Test
	public void initPrivilegeGroup() {
		if(privilegeGroupService.getCount()==0){
			PrivilegeGroup group = new PrivilegeGroup();
			group.setName("系统权限组");
			group.getPrivileges().addAll(systemPrivilegeService.getScrollData().getResultlist());
			privilegeGroupService.save(group);
		}		
	}
	/**
	 * 初始化权限
	 */
	
	@Test
	public void initSystemPrivilege() {
		if(systemPrivilegeService.getCount()==0){
			List<SystemPrivilege> privileges = new ArrayList<SystemPrivilege>();
			privileges.add(new SystemPrivilege("department", "view",   "部门查看"));
			privileges.add(new SystemPrivilege("department", "insert", "部门添加"));
			privileges.add(new SystemPrivilege("department", "update", "部门修改"));
			privileges.add(new SystemPrivilege("department", "delete", "部门删除"));
			
	
			
			privileges.add(new SystemPrivilege("employee","leave","员工离职设置"));
			privileges.add(new SystemPrivilege("employee","insert","员工添加"));
			privileges.add(new SystemPrivilege("employee","update","员工信息修改"));
			privileges.add(new SystemPrivilege("employee","view","员工查看"));
			privileges.add(new SystemPrivilege("employee","privilegeGroupSet","员工权限设置"));
			
	
			
			systemPrivilegeService.batchSave(privileges);
		}		
	}
}
