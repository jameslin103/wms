package cn.fm.utils;

import java.util.ArrayList;  
import java.util.List;  
import javax.servlet.http.Cookie;  
import javax.servlet.http.HttpServletRequest; 

import cn.fm.bean.user.WmsUser;



public class CookieUtils {
	
	 public static final String USER_COOKIE = "user"; 
	 public List<Cookie> addCookie(WmsUser user) {  
	        Cookie cookieU = new Cookie("phone", user.getPhone());  
	        Cookie cookieP = new Cookie("password", user.getPassword());  
	        cookieU.setMaxAge(60 * 60 * 24 * 14);  
	        cookieP.setMaxAge(60 * 60 * 24 * 14);  
	        cookieU.setPath("/");  
	        cookieP.setPath("/");  
	        List<Cookie> list = new ArrayList<Cookie>();  
	        list.add(cookieP);  
	        list.add(cookieU);  
	        return list;  
	    }  
	  
	    public static String getCookie(HttpServletRequest request, String key) {  
	        Cookie[] cookies = request.getCookies();  
	        System.out.println("cookies: " + cookies);  
	        if (cookies != null) {  
	            for (Cookie cookie : cookies) {  
	                System.out.println("cookieName: " + cookie.getName());  
	                if (key.equals(cookie.getName())) {  
	                    String value = cookie.getValue();  
	                    System.out.println("cookieValue: " + cookie.getValue());  
	                    return value;  
	                }  
	            }  
	        }  
	        return null;  
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
