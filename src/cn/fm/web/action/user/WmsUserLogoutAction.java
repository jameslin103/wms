package cn.fm.web.action.user;

import cn.fm.utils.CookieUtils;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class WmsUserLogoutAction extends BaseAction {

	
	public String loginOut() {
		
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("enterprise");
		CookieUtils  cookie=new CookieUtils();
		cookie.delCookie(request);
		return SUCCESS;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}
		
	
	
	
}
