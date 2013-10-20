package cn.fm.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware{

	private static final long serialVersionUID = -710016518867307570L;

	protected String resourceName;

	protected final String DEFAULT_CHAIN = "defaultChain";
	protected final String DEFAULT = "default";
	protected final String DEFAULT_REDIRECT = "defaultRedirect";
	protected final String DEFAULT_REDIRECTACTION = "defaultRedirectAction";

	protected HttpServletRequest request;
	protected HttpSession session;
	protected HttpServletResponse response;
	protected int  page=1;
	
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
		this.session = this.request.getSession();
	}

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public int getPage() {
		return page<1? 1 : page;
	}

	public void setpage(int page) {
		this.page = page;
	}


	protected void responseJson(Object obj){
		String json = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(obj);
		System.out.println(json);
		response(json);
	}
	
	private void response(String json){
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		try {
			PrintWriter writer = response.getWriter();
			writer.print(json);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
