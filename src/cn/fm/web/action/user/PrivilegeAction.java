package cn.fm.web.action.user;

import java.util.List;
import javax.annotation.Resource;
import com.google.gson.GsonBuilder;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.privilege.PrivilegeService;
import cn.fm.service.privilege.WmsRoleService;
import cn.fm.web.action.BaseAction;

public class PrivilegeAction extends BaseAction{
	
	
	@Resource
	private WmsRoleService     wmsRoleService;
	@Resource
	private PrivilegeService   privilegeService;       
	
	
	
	
	public String toViewPrivis() {
		WmsUser user =(WmsUser)session.getAttribute("user");
		
		//Set<Privilege> privileges=privilegeService.getPrivilegesByUserId(user.getUserId());
		List<Privilege> privilege=privilegeService.getPrivileges();
		String privilegess= new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(privilege);
		request.setAttribute("privilege", privilegess);
		return SUCCESS;
	}
	
	public String toByUserRole() {
		
		request.setAttribute("roles", wmsRoleService.getRoles());
		List<Privilege> privileges = privilegeService.getPrivileges();
		String privilege= new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(privileges);
		request.setAttribute("privilege", privilege);
		return SUCCESS;
	}
	
//	public String byUser(String userId,String[] roleIds,int[] prives,RedirectAttributes model) {
//		User user=userService.getById(userId);
//		for (String roleId : roleIds) {
//			Role role=new Role();
//			role.setId(roleId);
//			user.getRoles().add(role);
//		}
//		for (int privId : prives) {
//			Privilege privilege=new Privilege();
//			privilege.setId(privId);
//			user.getPrivileges().add(privilege);
//		}
//		
//		userService.updateUser(user);
//		model.addFlashAttribute("msg", "分配权限成功");
//		return "redirect:/priv/byuser";
//	}
	
}
