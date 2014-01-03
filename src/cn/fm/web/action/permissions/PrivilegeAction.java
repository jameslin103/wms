package cn.fm.web.action.permissions;

import java.util.List;
import java.util.Set;
import javax.annotation.Resource;

import com.opensymphony.oscache.util.StringUtil;

import net.sf.json.JSONArray;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.Role;
import cn.fm.bean.user.User;
import cn.fm.service.privilege.PrivilegeService;
import cn.fm.service.privilege.RoleService;
import cn.fm.service.user.UserService;
import cn.fm.web.action.BaseAction;

public class PrivilegeAction extends BaseAction{
	
	private static final long serialVersionUID = -6021299187809618346L;
	
	@Resource
	private RoleService     roleService;
	@Resource
	private PrivilegeService   privilegeService;       
	@Resource
	private UserService userService;
	
	private String userId;
	
	private String[] roleIds;
	
	private int[] prives;
	
	private Privilege   privilege;
	
	
	public String toViewPrivis() {
		User user =(User)session.getAttribute("user");
		
		Set<Privilege> privileges=privilegeService.getPrivilegesByUserId(user.getId());
		request.setAttribute("privilege", toJson(privileges));
		return SUCCESS;
	}
	public String toViewPrivilegManage() {
		
		request.setAttribute("roles", roleService.getRoles());
		List<Privilege> privileges = privilegeService.getPrivileges();
		String s1 = JSONArray.fromObject(privileges).toString();
		request.setAttribute("privs", s1);
		return SUCCESS;
	}
	
	public String toByUserRole() {
		
		request.setAttribute("roles", roleService.getRoles());
		List<Privilege> privileges = privilegeService.getPrivileges();
		request.setAttribute("privilege", toJson(privileges));
		return SUCCESS;
	}
	
	public String addUserByRoleAndByPrivilege() {
		if(!StringUtil.isEmpty(userId)){
			User user=userService.getById(userId);
			for (String roleId : roleIds) {
				Role role=new Role();
				role.setId(roleId);
				user.getRoles().add(role);
			}
			for (int privId : prives) {
				Privilege privilege=new Privilege();
				privilege.setId(privId);
				user.getPrivileges().add(privilege);
			}
			
			userService.updateUser(user);
			request.setAttribute("msg", "分配权限成功");
		}
		
		
		return SUCCESS;
	}
	
	public String toByRole(){
		return SUCCESS;
	}
	
	public String toByPrivilege(){
		return SUCCESS;
	}
	
	public String toSystemAllPrivilegeManage(){
		List<Privilege> privileges = privilegeService.getPrivileges();
		request.setAttribute("privileges", privileges);
		
		return SUCCESS;
	}
	public String toAddSystemPrivilege(){
		
		return SUCCESS;
	}
	public String addSystemPrivilege(){
		
		privilegeService.savePrivilege(privilege);
		return SUCCESS;
	}
	
	public String toUpdatePrivilege()
	{
		if(privilege!=null && privilege.getId()!=0)
			privilege=privilegeService.findByIdPrivilege(privilege.getId());
		request.setAttribute("privilege", privilege);
		return SUCCESS;
		
	}
	
	public String updatePrivilege()
	{
		if(privilege!=null)
			privilegeService.updatePrivilege(privilege);
		return SUCCESS;
	}
	
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}
	public int[] getPrives() {
		return prives;
	}
	public void setPrives(int[] prives) {
		this.prives = prives;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}
	
	
}
