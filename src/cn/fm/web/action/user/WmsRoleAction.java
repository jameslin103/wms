package cn.fm.web.action.user;

import javax.annotation.Resource;
import com.google.gson.GsonBuilder;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.WmsRole;
import cn.fm.service.privilege.PrivilegeService;
import cn.fm.service.privilege.WmsRoleService;
import cn.fm.web.action.BaseAction;

public class WmsRoleAction extends BaseAction{

	
	@Resource
	private WmsRoleService wmsRoleService;
	@Resource
	private PrivilegeService privilegeService;
	
	private WmsRole  wmsRole;
	private int[] prives;
	
	
	
	
	public String toAdd() {
		  String menu = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(privilegeService.getPrivileges());
		  request.setAttribute("menu", menu);
		return SUCCESS;
	}
	
	
	public String addWmsRole() {
		for (int privId : prives) {
			Privilege p = new Privilege();
			p.setId(privId);
			wmsRole.getPrivileges().add(p);
		}
		wmsRoleService.save(wmsRole);
		return SUCCESS;
	}
	
	
}
