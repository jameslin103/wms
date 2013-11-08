package cn.fm.web.action.user;

import javax.servlet.http.Cookie;

import cn.fm.utils.CookieUtils;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class WmsUserLogoutAction extends BaseAction {

	
	public String loginOut() {
		
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("enterprise");

		CookieUtils cookieUtils=new CookieUtils();
		
	        Cookie cookie = cookieUtils.delCookie(request);  
	        if (cookie != null)  
	            response.addCookie(cookie);  
	        return SUCCESS;
		
		
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		super.validate();
	}
		
	
	
	
}
