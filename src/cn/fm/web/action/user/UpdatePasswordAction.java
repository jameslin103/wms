package cn.fm.web.action.user;

import javax.annotation.Resource;

import cn.fm.bean.user.WmsUser;
import cn.fm.service.user.WmsUserService;
import cn.fm.utils.StringUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class UpdatePasswordAction extends BaseAction {
	@Resource
	private WmsUserService  wmsUserService;
	private WmsUser         wmsUser;
	private String        newPassword;
	
	public String updatePassword(){
		if(!StringUtil.isEmpty(newPassword))
			wmsUserService.updatePassword(wmsUser.getUsername(), newPassword);
		
		
		
		return SUCCESS;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
