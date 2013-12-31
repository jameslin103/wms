package cn.fm.web.action.user;

import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import com.opensymphony.oscache.util.StringUtil;
import cn.fm.web.action.BaseAction;
import cn.fm.service.user.UserService;
import cn.fm.bean.user.Employee;
import cn.fm.bean.user.User;



public class UserAction extends BaseAction{
	
	private static final long serialVersionUID = 4338257425805962793L;

	@Resource
	private UserService userServcie;
	
	private  User user;
	
	private Employee employee;
	
	private boolean isExitAccount;
	
	public String toAddUser(){
		
		return SUCCESS;
	}

	public String addUser() {
		
		if(employee!=null && employee.getId()!=null){
			user.getEmployee().setId(employee.getId());
		}
		userServcie.saveUser(user);
		return SUCCESS;
	}
	public String toUpdateUser() {
		request.setAttribute("user", userServcie.getById(user.getId()));
		return SUCCESS;
	}
	
	
	public String viewUserList() {
		
		request.setAttribute("users", userServcie.getUsers());
		return SUCCESS;
	}
	
	public String isExistUser(){

		if(!StringUtil.isEmpty(user.getAccount())){
			responseJson(userServcie.isExist(user.getAccount()));
		}
		return NONE;
		
	}
	

	public String delUser(){
		userServcie.delUsers(user.getId());
		return SUCCESS;
	}
	
	public String userLogin(){
		if(user!=null && user.getAccount()!=null && user.getPassword()!=null){
			User loninUser=userServcie.login(user.getAccount(), user.getPassword());
			if(loninUser==null){
				request.setAttribute("msg", "用户名或密码错误!");
				return INPUT;
			}else{
				if(loninUser.getEmployee()==null || StringUtils.isBlank(loninUser.getEmployee().getId())){
					request.setAttribute("msg", "该用户没有分配给任何员工使用!,请联系管理员!");
					return INPUT;
				}
				if(loninUser.getStatus().equals(User.ABSNORMAL)){
					request.setAttribute("msg", "该用户已经被禁用!,请联系管理员!");
					return INPUT;
				}
				session.setAttribute("user", loninUser);
			}
			
		}
		return SUCCESS;
	}
	
	public String logout(){
		session.removeAttribute("user");
		return SUCCESS;
	}
	
	public String toSearchUser(){
		
		request.setAttribute("users", userServcie.getUsers());
		return SUCCESS;
	}
	public String toEmpsel()
	{
		
		return SUCCESS;
	}
	
	public String toTabpanel()
	{
		
		return SUCCESS;
	}
	
	public String toBottom()
	{
		
		return SUCCESS;
	}
	
	public String toTop()
	{
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isExitAccount() {
		return isExitAccount;
	}

	public void setExitAccount(boolean isExitAccount) {
		this.isExitAccount = isExitAccount;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
	
	
	
	
	
}
