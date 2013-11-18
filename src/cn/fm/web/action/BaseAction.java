package cn.fm.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.collection.internal.PersistentList;
import org.hibernate.proxy.HibernateProxy;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
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
	
	/**
	 * Object转换Json格式
	 * @param obj
	 * @return
	 */
	protected String toJson(Object obj){
		GsonBuilder gb = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
		gb.registerTypeHierarchyAdapter(HibernateProxy.class,new JsonSerializer<HibernateProxy>(){
			public JsonElement serialize(HibernateProxy src,Type typeOfSrc, JsonSerializationContext context) {
				return null;
			}
		});
		gb.registerTypeHierarchyAdapter(PersistentList.class,new JsonSerializer<PersistentList>(){
			public JsonElement serialize(PersistentList src,Type typeOfSrc, JsonSerializationContext context) {
				return null;
			}
		});
		String json = gb.create().toJson(obj);
		System.out.println(json);
		return json;
	}
}
