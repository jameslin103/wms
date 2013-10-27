package cn.fm.web.action.user;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.permissions.Menu;
import cn.fm.bean.permissions.Role;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.permissions.MenuService;
import cn.fm.service.permissions.RoleService;
import cn.fm.service.user.WmsUserService;
import cn.fm.utils.StringUtil;
import cn.fm.web.action.BaseAction;

public class PermissionAction extends BaseAction{

	private static final long serialVersionUID = -7194722712505157911L;
	
	private List<Menu> menuList;
	
	private List<Role> roleList;
	
	private List<WmsUser> wmsUserList;
	
	private String[] menuIds;
	
	private String[] roleIds;
	
	private String[] userIds;
	
	private Integer  userId;
	
	private Role role;
	
	private WmsUser wmsUser;
	
	@Resource
	private MenuService menuService;
	@Resource
	private RoleService roleService;
	@Resource
	private WmsUserService wmsUserService;
	
	
	
	
	
	
	public String toAuthorizationUser(){
		roleList = roleService.getAll();
		wmsUserList = wmsUserService.getAllWmsUser();
		for (Iterator<WmsUser> iterator = wmsUserList.iterator(); iterator.hasNext();) {
			WmsUser user = iterator.next();
			String[] roleIds = user.getRoleIds()!=null?user.getRoleIds().split(","):null;
			if(roleIds!=null){
				for (int i = 0; i < roleIds.length; i++) {
					user.getRoles().add(roleService.find(Long.valueOf(roleIds[i])));
				}
			}
		}
		return SUCCESS;
	}
	
	public String addAuthorization(){
		if(roleIds==null || userIds==null)return INPUT;
		wmsUserService.assignRoles(roleIds, userIds);
		return SUCCESS;
	}
	
	public String updateAuthorization(){
		if(wmsUser==null || roleIds==null )return INPUT;
		wmsUserService.assignRoles(roleIds, wmsUser);
		return SUCCESS;
	}
	
	public String loadWmsUser(){
		try {
			wmsUser = wmsUserService.find(wmsUser.getUserId());
			WmsUser wmsUserVO = new WmsUser();
			wmsUserVO.setUserId(wmsUser.getUserId());
			wmsUserVO.setUsername(wmsUser.getUsername());
			wmsUserVO.setRoleIds(wmsUser.getRoleIds());
			responseJson(wmsUserVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NONE;
	}
	
	public String toRoleManage(){
		menuList = menuService.getAll();
		roleList = roleService.getAll();
		for (Iterator<Role> iterator = roleList.iterator(); iterator.hasNext();) {
			Role role = iterator.next();
			String[] menuIds = role.getMenuIds().split(",");
			for (int i = 0; i < menuIds.length; i++) {
				role.getMenus().add(menuService.find(Long.valueOf(menuIds[i])));
			}
		}
		return SUCCESS;
	}
	
	public String toHelp()
	{
		
		return SUCCESS;
	}
	
	public String addWmsUser()
	{
		if(wmsUser==null)return INPUT;
		wmsUserService.save(wmsUser);
		
		return SUCCESS;
	}
	
	public String addRole(){
		roleService.addRole(menuIds, role);
		return SUCCESS;
	}
	
	public String deleteRole(){
		roleService.delete(role.getRoleId());
		return SUCCESS;
	}
	
	public String updateRole(){
		roleService.updateRole(menuIds, role);
		return SUCCESS;
	}
	
	public String loadRole(){
		role = roleService.find(role.getRoleId());
		responseJson(role);
		return NONE;
	}
	public String isExitPhone()
	{
		String errorString=null;
		if(wmsUser==null || StringUtil.isEmpty(wmsUser.getPhone()))return INPUT;
		 boolean isPhone=wmsUserService.isExitPhone(wmsUser.getPhone());
		if(isPhone==true){
			errorString="此号码已存在!";
		}else{
			errorString="此号码可用!";
		}
		responseJson(errorString);
		return NONE;
	}
	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public String[] getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(String[] menuIds) {
		this.menuIds = menuIds;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<WmsUser> getWmsUserList() {
		return wmsUserList;
	}

	public void setWmsUserList(List<WmsUser> wmsUserList) {
		this.wmsUserList = wmsUserList;
	}

	public WmsUser getWmsUser() {
		return wmsUser;
	}

	public void setWmsUser(WmsUser wmsUser) {
		this.wmsUser = wmsUser;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String[] getUserIds() {
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
	
}
