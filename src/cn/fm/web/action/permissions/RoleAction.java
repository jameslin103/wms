package cn.fm.web.action.permissions;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import net.sf.json.JSONArray;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.Role;
import cn.fm.service.privilege.PrivilegeService;
import cn.fm.service.privilege.RoleService;
import cn.fm.web.action.BaseAction;

public class RoleAction extends BaseAction {
	
	private static final long serialVersionUID = 6200397307280622180L;
	
	
	@Resource
	private RoleService roleService;
	@Resource
	private PrivilegeService privilegeService;

	
	
	private Role role;
	
	private int[] priveIds;
	
	
	
	
	public String toViewRoleManage() {
		
		request.setAttribute("roles", roleService.getRoles());
		
		return SUCCESS;
	}

	public String toAddRole() {
		
		List<Privilege> privileges = privilegeService.getPrivileges();
		String privs= JSONArray.fromObject(privileges).toString();
		request.setAttribute("privs", privs);
		return SUCCESS;
	}
	
	public String getPrivlieges(){
		
		return JSONArray.fromObject(privilegeService.getPrivileges()).toString();
	}
	
	
	public String addRole() {
		
		if(priveIds!=null){
			for (int privId : priveIds) {
				Privilege privilege= new Privilege();
				privilege.setId(privId);
				role.getPrivileges().add(privilege);
			}
			roleService.save(role);
		}
		return SUCCESS;
	}
	public String deleteRole() {
		
		if(role.getId()!=null){
			roleService.romveRole(role.getId());
		}
		
		return SUCCESS;
	}
	
	public String toUpdateRole()
	{
		if(role.getId()!=null){
			Role rolePO=roleService.getRoleById(role.getId());
			List<Privilege> privileges = privilegeService.getPrivileges();
			String privs= JSONArray.fromObject(privileges).toString();
			request.setAttribute("privs", privs);
			request.setAttribute("role", rolePO);
		}
		return SUCCESS;
	}
	public String updateRole()
	{
		if(role.getId()!=null){
			Role rolePO=roleService.getRoleById(role.getId());
			request.setAttribute("role", rolePO);
		}
		
		return SUCCESS;
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int[] getPriveIds() {
		return priveIds;
	}

	public void setPriveIds(int[] priveIds) {
		this.priveIds = priveIds;
	}

	
	
	
	
	
}
