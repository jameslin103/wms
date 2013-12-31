package cn.fm.bean.filter;

import java.util.Map;

import cn.fm.bean.user.User;
import cn.fm.utils.Constant;
import cn.fm.web.action.user.UserAction;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class SessionIterceptor extends AbstractInterceptor {
	
	
	 @SuppressWarnings("unchecked")
	public String intercept(ActionInvocation actionInvocation) throws Exception {  
		 
	        ActionContext ctx = ActionContext.getContext(); 
	        Map session = ctx.getSession();
	        Action action = (Action) actionInvocation.getAction();
	        if (action instanceof UserAction) {  
	            return actionInvocation.invoke();  
	        }  
	        User name = (User)session.get(Constant.USER_SESSION); 
	        if (name == null) {  
	            return Action.LOGIN;  
	        } else {  
	            return actionInvocation.invoke();  
	        }  
	    }  
	  
	
	
	

}
