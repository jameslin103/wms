package cn.fm.web.action.user;

import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class BuyerLogoutAction extends BaseAction {

	
	public String loginOut() {
		
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("enterprise");
		return SUCCESS;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}
		
	
	
	
}
