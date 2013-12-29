package cn.fm.web.action.permissions;

import java.util.List;

import javax.annotation.Resource;
import net.sf.json.JSONArray;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.WmsRole;
import cn.fm.service.privilege.PrivilegeService;
import cn.fm.service.privilege.WmsRoleService;
import cn.fm.web.action.BaseAction;

public class RoleAction extends BaseAction {
	
	private static final long serialVersionUID = 6200397307280622180L;
	
	
	@Resource
	private WmsRoleService roleService;
	@Resource
	private PrivilegeService privilegeService;

	
	
	private WmsRole role;
	private int[] prives;
	
	
	
	
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
		if(prives!=null){
			for (int privId : prives) {
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
			WmsRole rolePO=roleService.getRoleById(role.getId());
			request.setAttribute("role", rolePO);
		}
		
		request.setAttribute("privileges", toJson(getPrivlieges()));
		return SUCCESS;
	}
	public String updateRole()
	{
		if(role.getId()!=null){
			WmsRole rolePO=roleService.getRoleById(role.getId());
			request.setAttribute("role", rolePO);
		}
		
		return SUCCESS;
	}
	
	public WmsRole getRole() {
		return role;
	}

	public void setRole(WmsRole role) {
		this.role = role;
	}

	public int[] getPrives() {
		return prives;
	}

	public void setPrives(int[] prives) {
		this.prives = prives;
	}
	
	
	
	
}
