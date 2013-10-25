package cn.fm.web.action.user;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.permissions.Menu;
import cn.fm.bean.permissions.Role;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.permissions.MenuService;
import cn.fm.service.permissions.RoleService;
import cn.fm.service.user.WmsUserService;
import cn.fm.utils.StringUtil;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;




@SuppressWarnings("serial")
public class LoginAction extends BaseAction{
	
		private WmsUser wmsUser;
		private WmsUser loginUser;
		
		@Resource
		private WmsUserService wmsUserService;
		@Resource
		private RoleService roleService;
		@Resource
		private MenuService menuService;
		
	
		
		public WmsUser getWmsUser() {
			return wmsUser;
		}

		public void setWmsUser(WmsUser wmsUser) {
			this.wmsUser = wmsUser;
		}

		public WmsUser getLoginUser() {
			return loginUser;
		}

		public void setLoginUser(WmsUser loginUser) {
			this.loginUser = loginUser;
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
			List<Menu> menuList=getUserMenu(loginUser);
			request.getSession().setAttribute("menuList", menuList);
			return SUCCESS;
		}
	
	   private boolean isInvalid(String value)
	   {
		        return (value == null || value.length() == 0);
	   }
	   
		public List<Menu> getUserMenu(WmsUser loginUser){
			List<Menu> menuList = new ArrayList<Menu>();
			String[] ids = loginUser.getRoleIds().split(",");
			String menuIds = "";
			for (int i = 0; i < ids.length; i++) {
				Role role = roleService.find(Long.valueOf(ids[i]));
				menuIds += role.getMenuIds();
			}
			menuList = menuService.getByIds(menuIds);
			return menuList;
		}
	
	   public String toPassword()
	   {
		   return SUCCESS;
	   }
	   public String updateWmsUserPassword(){
		   if(loginUser==null || loginUser.getPassword()==null || StringUtil.isEmpty(loginUser.getPassword())) return INPUT;
		   WmsUser wmsUser=WebUtil.getWmsUser(request);
		   wmsUserService.updatePassword(wmsUser.getPhone(), loginUser.getPassword());
		   
		   
		   
		   return SUCCESS;
	   }
}
