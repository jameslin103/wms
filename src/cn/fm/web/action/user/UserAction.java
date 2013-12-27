package cn.fm.web.action.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;

import com.opensymphony.oscache.util.StringUtil;

import cn.fm.web.action.BaseAction;
import cn.fm.service.user.*;
import cn.fm.bean.user.User;



public class UserAction extends BaseAction{
	
	
	@Resource
	private UserService userServcie;
	
	private  User user;
	
	private boolean isExitAccount;
	
	public String toAddWmsUser(){
		
		return SUCCESS;
	}

	public String saveUser() {
		userServcie.addUser(user);
		return SUCCESS;
	}
	public String toUpdateUser() {
		request.setAttribute("users", userServcie.getById(user.getId()));
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
	

	public void del(){
		userServcie.delUsers(user.getId());
	}
	
	public String login(String account,String password,HttpSession session,Model model){
		User user=userServcie.login(account, password);
		if(user==null){
			model.addAttribute("msg", "用户名或密码错误!");
			return INPUT;
		}else{
			if(user.getEmployee()==null || StringUtils.isBlank(user.getEmployee().getId())){
				model.addAttribute("msg", "该用户没有分配给任何员工使用!,请联系管理员!");
				return INPUT;
			}
			if(user.getStatus().equals(User.ABSNORMAL)){
				model.addAttribute("msg", "该用户已经被禁用!,请联系管理员!");
				return INPUT;
			}
			session.setAttribute("user", user);
			return SUCCESS;
		}
	}
	
	public String logout(HttpSession session){
		
		session.removeAttribute("user");
		return SUCCESS;
	}
	
	public String selUser(){
		
		request.setAttribute("users", userServcie.getUsers());
		return SUCCESS;
	}
	public String toEmpsel()
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
	
	
	
	
	
	
	
	
}
