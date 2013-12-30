package cn.fm.web.action.permissions;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.json.JSONArray;

import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.GsonBuilder;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.WmsRole;
import cn.fm.bean.user.User;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.privilege.PrivilegeService;
import cn.fm.service.privilege.WmsRoleService;
import cn.fm.service.user.UserService;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

public class PrivilegeAction extends BaseAction{
	
	private static final long serialVersionUID = -6021299187809618346L;
	
	@Resource
	private WmsRoleService     roleService;
	@Resource
	private PrivilegeService   privilegeService;       
	@Resource
	private UserService userService;
	
	String userId;
	private String[] roleIds;
	private int[] prives;
	
	
	public String toViewPrivis() {
		WmsUser user =(WmsUser)session.getAttribute("user");
		
		Set<Privilege> privileges=privilegeService.getPrivilegesByUserId(user.getUserId());
		//String privilegess= new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(privileges);
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
	
	public String addByUserPrivileg() {
		User sessionuser=WebUtil.getUser(request);
		User user=userService.getById(sessionuser.getId());
		for (String roleId : roleIds) {
			WmsRole role=new WmsRole();
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
		return SUCCESS;
	}
	
	public String toByRole(){
		return SUCCESS;
	}
	
	public String toByPrivilege(){
		return SUCCESS;
	}
	
}
