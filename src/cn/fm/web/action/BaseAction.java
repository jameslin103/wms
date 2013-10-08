package cn.fm.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

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


	
}
