package cn.fm.web.action.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;

import cn.fm.bean.permissions.Menu;
import cn.fm.bean.permissions.Role;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.permissions.MenuService;
import cn.fm.service.permissions.RoleService;
import cn.fm.service.user.WmsUserService;
import cn.fm.utils.CookieUtils;
import cn.fm.utils.DateUtil;
import cn.fm.utils.StringUtil;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;




public class LoginAction extends BaseAction{
	
	    private static final long serialVersionUID = 6650955874307814247L;  
	    public static final String USER_SESSION = "user"; 
	    private CookieUtils cookieUtils = new CookieUtils();  
		private WmsUser wmsUser;
		private WmsUser loginUser;
		
		@Resource
		private WmsUserService wmsUserService;
		@Resource
		private RoleService roleService;
		@Resource
		private MenuService menuService;
		
	
		private Integer remember_me;
		private boolean userCookie;  
		
		 
		public void setRoleService(RoleService roleService) {
			this.roleService = roleService;
		}

		public Integer getRemember_me() {
			return remember_me;
		}

		public void setRemember_me(Integer rememberMe) {
			remember_me = rememberMe;
		}

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

		public boolean isUserCookie() {
			return userCookie;
		}

		public void setUserCookie(boolean userCookie) {
			this.userCookie = userCookie;
		}

		public String userLogin()
		{
			 if (cookieUtils.getCookie(request,  wmsUserService)) 
			 {  
		            return SUCCESS;  
		      } else { 
		            return "login";  
		     }

		}
		 public String execute() throws Exception 
		 {
			
			 if(wmsUser==null)return INPUT;
				if (isInvalid(wmsUser.getPhone().trim()))
				{
					this.addFieldError("username", "手机号号码不能为空!"); 
			        return INPUT;
				}
			    if (isInvalid(wmsUser.getPassword().trim())){
			    	this.addFieldError("username", "密码不能为空!"); 
			        return INPUT;
			    }
				boolean isCheckUser=wmsUserService.checkUser(wmsUser.getPhone(), wmsUser.getPassword());
				if(isCheckUser!=true){
					 this.addFieldError("username", "用户名或密码错误!");  
					 return INPUT;
				}
			
				loginUser=wmsUserService.find(wmsUser.getPhone());
				if( loginUser!=null){
					System.out.println(loginUser.getUsername()+"登录系统，系统时间："+DateUtil.getCurrentTime());
					request.getSession().setAttribute("user",loginUser);
					if (userCookie==true) 
					{  
			            Cookie cookie = cookieUtils.addCookie(loginUser);  
			            response.addCookie(cookie);// 添加cookie到response中  
			        }  
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
