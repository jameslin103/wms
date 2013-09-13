package cn.fm.web.action.user;

import javax.annotation.Resource;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.user.WmsUserService;
import cn.fm.web.action.BaseAction;




@SuppressWarnings("serial")
public class LoginAction extends BaseAction{
	
		private WmsUser wmsUser;
		private WmsUser loginUser;
		
		@Resource
		private WmsUserService wmsUserService;
		
		public WmsUser getWmsUser() {
			return wmsUser;
		}
		public void setWmsUser(WmsUser wmsUser) {
			this.wmsUser = wmsUser;
		}
		
		
		public String userLogin()
		{
			if(wmsUser==null)return INPUT;
			if (isInvalid(wmsUser.getPhone().trim()))
		            return INPUT;
		    if (isInvalid(wmsUser.getPassword().trim()))
		            return INPUT;
			boolean isCheckUser=wmsUserService.checkUser(wmsUser.getPhone(), wmsUser.getPassword());
			if(isCheckUser!=true){
				return INPUT;	
			}
			loginUser=wmsUserService.find(wmsUser.getPhone());
			if( loginUser!=null){
				request.getSession().setAttribute("user",loginUser);
			}
			
			return SUCCESS;
		}
	
		private boolean isInvalid(String value) {
		        return (value == null || value.length() == 0);
		}
	
	public String authorizationUser()
	{
		
		
		
		
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
