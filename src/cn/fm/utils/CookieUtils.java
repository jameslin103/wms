package cn.fm.utils;

import javax.servlet.http.Cookie;  
import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import cn.fm.bean.user.User;
import cn.fm.service.user.UserService;
import cn.fm.web.action.user.UserAction;



public class CookieUtils {
	
	 public static final String USER_COOKIE = "user.cookie";  
	  
	    // 添加一个cookie  
	    public Cookie addCookie(User user) {  
	        Cookie cookie = new Cookie(USER_COOKIE, user.getAccount()+ ","  
	                + MD5.MD5Encode(user.getPassword()));  
	        cookie.setMaxAge(60 * 60 * 24 * 14);// cookie保存两周  
	        return cookie;  
	    }  
	  
	    // 得到cookie  
	    public boolean getCookie(HttpServletRequest request, UserService userService) {  
	        Cookie[] cookies = request.getCookies();  
	        System.out.println("cookies: " + cookies);  
	        if (cookies != null) {  
	            for (Cookie cookie : cookies) {  
	                System.out.println("cookie: " + cookie.getName());  
	                if (CookieUtils.USER_COOKIE.equals(cookie.getName())) {  
	                    String value = cookie.getValue();  
	                    if (!StringUtils.isEmpty(value)) {  
	                        String[] split = value.split(",");  
	                        String phone= split[0];  
	                        String password = split[1];
	                        User user=null;
	                      User loginUser=userService.login(phone, password);
	                       if(loginUser!=null){
		                         HttpSession session = request.getSession();  
		                         session.setAttribute("user", user);// 添加用户到session中  
		                         return true;  
	                       }
	                    }  
	                }  
	            }  
	        }  
	        return false;  
	    }  
	  
	    // 删除cookie  
	    public Cookie delCookie(HttpServletRequest request) {  
	        Cookie[] cookies = request.getCookies();  
	        if (cookies != null) {  
	            for (Cookie cookie : cookies) {  
	                if (USER_COOKIE.equals(cookie.getName())) {  
	                    cookie.setValue("");  
	                    cookie.setMaxAge(0);  
	                    return cookie;  
	                }  
	            }  
	        }  
	        return null;  
	    }  
	
	
	
}
