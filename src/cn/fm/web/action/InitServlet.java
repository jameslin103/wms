package cn.fm.web.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class InitServlet extends HttpServlet {
	private static ApplicationContext context;

	public void init() throws ServletException {
//		getServletContext().setAttribute("base",getServletContext().getContextPath() + "/");
//		context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
	}

	public static ApplicationContext getContext() {
		return context;
	}
	
}
