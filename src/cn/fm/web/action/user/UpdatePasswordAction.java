package cn.fm.web.action.user;

import javax.annotation.Resource;

import cn.fm.bean.user.Buyer;
import cn.fm.service.user.BuyerService;
import cn.fm.utils.StringUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class UpdatePasswordAction extends BaseAction {
	@Resource
	private BuyerService  buyerService;
	private Buyer         buyer;
	private String        newPassword;
	
	public String updatePassword(){
		if(!StringUtil.isEmpty(newPassword))
			buyerService.updatePassword(buyer.getUsername(), newPassword);
		
		
		
		return SUCCESS;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
