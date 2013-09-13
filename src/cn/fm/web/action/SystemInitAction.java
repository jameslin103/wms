package cn.fm.web.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import cn.fm.bean.permissions.Employee;
import cn.fm.bean.permissions.PrivilegeGroup;
import cn.fm.bean.permissions.SystemPrivilege;
import cn.fm.bean.user.Gender;
import cn.fm.service.privilege.EmployeeService;
import cn.fm.service.privilege.PrivilegeGroupService;
import cn.fm.service.privilege.SystemPrivilegeService;


/**
 * 初始化 (此action是在系统安装完后就执行)
 */
public class SystemInitAction extends BaseAction {
	@Resource SystemPrivilegeService privilegeService;
	@Resource PrivilegeGroupService groupService;
	@Resource EmployeeService employeeService;

	@Override
	public String execute()throws Exception {
		initSystemPrivilege();
		initPrivilegeGroup();
		initAdmin();
		request.setAttribute("message", "初始化完成");
		return SUCCESS;
	}
	/**
	 * 初始化管理员账号
	 */
	private void initAdmin() {
		if(employeeService.getCount()==0){
			Employee employee = new Employee();
			employee.setUsername("admin");
			employee.setPassword("123456");
			employee.setRealname("系统管理员");
			employee.setGender(Gender.MAN);
			employee.getGroups().addAll(groupService.getScrollData().getResultlist());//赋予权限
			employeeService.save(employee);
		}		
	}
	/**
	 * 初始化系统权限组
	 */
	private void initPrivilegeGroup() {
		if(groupService.getCount()==0){
			PrivilegeGroup group = new PrivilegeGroup();
			group.setName("系统权限组");
			group.getPrivileges().addAll(privilegeService.getScrollData().getResultlist());
			groupService.save(group);
		}		
	}
	/**
	 * 初始化权限
	 */
	private void initSystemPrivilege() {
		if(privilegeService.getCount()==0){
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
			
	
			
			privilegeService.batchSave(privileges);
		}		
	}

}
