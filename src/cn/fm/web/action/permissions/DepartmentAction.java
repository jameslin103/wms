package cn.fm.web.action.permissions;


import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.annotation.Resource;
import cn.fm.web.action.BaseAction;
import cn.fm.bean.user.Department;
import cn.fm.service.privilege.DepartmentService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

public class DepartmentAction extends BaseAction{

	private static final long serialVersionUID = 1266023526843646068L;


	@Resource
	private DepartmentService departmentService;

	
	private Department       	department;
	
	private List<Department> departments;
	
	private String     depts;   
	
	private String     id;
	
	private String     name;
	
	
	
	
	
	
	public String jsonDepartments() {
		
		departments = departmentService.getDepartments();
		JsonConfig jcfg = new JsonConfig();
		jcfg.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equalsIgnoreCase("empNums")|| name.equalsIgnoreCase("employees")) {
					return true;
				} else {
					return false;
				}
			}
		});
		depts=JSONArray.fromObject(departments, jcfg).toString();
		 // String depts= new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(departments);
		 responseJson(depts);
		 return NONE;
	}
	public String toDepartmentManage()
	{
		
		return SUCCESS;
	}
	
	public String jsonDepartment() {
		List<Department> departments = departmentService.getDepartments();
		JsonConfig jcfg = new JsonConfig();
		jcfg.setJsonPropertyFilter(new PropertyFilter() {
			@Override
			public boolean apply(Object source, String name, Object value) {
				if (name.equalsIgnoreCase("empNums") || name.equalsIgnoreCase("employees")) {
					return true;
				} else {
					return false;
				}
			}
		});
	    depts=JSONArray.fromObject(departments, jcfg).toString();
		 responseJson(depts);
		return NONE;
	}
	

	
	public String addDepartment(){
		try {
			name= new String(request.getParameter("name").getBytes("iso8859-1"),"UTF-8");
			System.out.println(name);
			department.setName(name);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(department!=null && department.getName()!=null){
			departmentService.save(department);
			request.setAttribute("id", department.getId());
		}
		return NONE;
	}
	
	
	public String updateDepartment(){

		try {
			name= new String(request.getParameter("name").getBytes("iso8859-1"),"UTF-8");
			System.out.println(name);
			department.setName(name);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		if(department!=null && department.getId()!=null)
			departmentService.update(department);
		return NONE;
		
	}
	public String deleteDepartment(){
		
		if(department!=null && department.getId()!=null)
			departmentService.deleteUpdate(department);
		
		return NONE;
		
	}
	
	
	
	
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(List<Department> departments) {
		this.departments = departments;
	}

	public String getDepts() {
		return depts;
	}

	public void setDepts(String depts) {
		this.depts = depts;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	
	
}
