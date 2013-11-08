package cn.fm.bean.filter;

import java.util.Map;

import cn.fm.bean.user.WmsUser;
import cn.fm.utils.Constant;
import cn.fm.web.action.user.LoginAction;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionIterceptor extends AbstractInterceptor {
	
	
	 public String intercept(ActionInvocation actionInvocation) throws Exception {  
	        ActionContext ctx = ActionContext.getContext(); 
	        Map session = ctx.getSession();  
	        Action action = (Action) actionInvocation.getAction();
	        if (action instanceof LoginAction) {  
	            return actionInvocation.invoke();  
	        }  
	        WmsUser userName = (WmsUser)session.get(Constant.USER_SESSION);  
	        if (userName == null) {  
	            return Action.LOGIN;  
	        } else {  
	            return actionInvocation.invoke();  
	        }  
	    }  
	  
	
	
	

}
