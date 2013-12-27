package cn.fm.web.action.permissions;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.multipart.MultipartFile;
import cn.fm.web.action.BaseAction;
import cn.fm.bean.user.Department;
import cn.fm.bean.user.Employee;
import cn.fm.bean.user.User;
import cn.fm.service.privilege.EmployeeService;
import cn.fm.service.privilege.DepartmentService;
import cn.fm.utils.WmsUtil;
import cn.fm.bean.PageBean;



public class EmployeeAction  extends BaseAction{

	
	@Resource
	private EmployeeService employeeService;
	@Resource
	private DepartmentService departmentService;
	
	private Employee		employee;
	private Department		department;
	private User			user;
	
	private int page=0;
	
	private MultipartFile  file;
	
	private PageBean<Employee> employees;
	
	
	public String toEmployeeManage() {
		request.setAttribute("pageBean",employeeService.getEmpsByPage(this.getPage(), employee));
		return SUCCESS;
	}

	public String toAddEmployee() {
		
		request.setAttribute("employee",new Employee());
		request.setAttribute("departments", departmentService.getDepartments());
		
		return SUCCESS;
	}

	public String addEmployee(){
		if (!file.isEmpty()) {
			String fileName =WmsUtil.getId();// 新文件名称
			String oldFileName = file.getOriginalFilename();// 原始文件名称
			String suffix = oldFileName.substring(oldFileName.lastIndexOf("."));
			
			String currentPath = ServletActionContext.getServletContext().getRealPath("/upload/employees");
			try {
				// 讲文件写入服务器中
				file.transferTo(new File(currentPath + "/" + fileName + suffix));
				// 数据库中存放是是一个地址
				employee.setImage("upload/employees/" + fileName + suffix);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		employeeService.save(employee);
		return SUCCESS;
	}
	
	public String empNoExist() {
		
		responseJson(employeeService.empNoExist(employee.getEmpNo()));
		return NONE;
	}
	
	
	public String getEmpByDeptId() {
		
		Employee employee = new Employee();
		employee.getDepartment().setId(department.getId());
		employees=employeeService.getEmpsByPage(this.getPage(), employee);
		return SUCCESS;
	}
	
	public String bindUser(){
		employeeService.bind(employee.getId(),user.getId());
		return SUCCESS;
	}
	
	public String unbindUser(){
		
		employeeService.unbind(employee.getId());
		
		return SUCCESS;
	}
	
	public String export(){
		
		Employee employee=employeeService.getById("employeeId");
		Map<String,Employee> model=new HashMap<String,Employee>();
		model.put("emp", employee);
		
		return SUCCESS;
	}
	
	
	
	
	
	

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPage() {
		return page<1?1:page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public PageBean<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(PageBean<Employee> employees) {
		this.employees = employees;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
