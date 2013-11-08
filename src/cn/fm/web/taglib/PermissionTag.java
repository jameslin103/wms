package cn.fm.web.taglib;

  
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;   

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import cn.fm.bean.permissions.Menu;
import cn.fm.bean.permissions.Role;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.permissions.RoleService;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.user.LoginAction;

@SuppressWarnings("serial")
public class PermissionTag extends TagSupport implements ServletRequestAware{
	
	LoginAction  action=new LoginAction();
	
	private HttpServletRequest request ;
	HttpSession session;
	
	private String tourl;
	
	private String toname;
	

	
	public String getTourl() {
		return tourl;
	}

	public void setTourl(String tourl) {
		this.tourl = tourl;
	}
	
	
	public String getToname() {
		return toname;
	}

	public void setToname(String toname) {
		this.toname = toname;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession();
	}
	
	public void getRoleService(RoleService  roleService){
		
		action.setRoleService(roleService);
		
	}
	
	@Override
	public int doStartTag() throws JspException{
		
		boolean result = false;
		WmsUser loginUser=WebUtil.getWmsUser((HttpServletRequest)pageContext.getRequest());
		
		  List<Menu>	menu=action.getUserMenu(loginUser);
		  for (Menu me : menu) {
				for (Role ro : loginUser.getRoles()) {
					String[] roles=ro.getMenuIds().split(",");
					for (String meids : roles) {
						if(meids.equals(me.getMenuId())){
							result = true;
							break;
						}
					}
					
				}
		  }

		return result? EVAL_BODY_INCLUDE : SKIP_BODY;

		
	}
	
	
	
}
