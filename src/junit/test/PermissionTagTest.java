package junit.test;

import javax.servlet.jsp.JspException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.service.permissions.RoleService;
import cn.fm.web.taglib.PermissionTag;

public class PermissionTagTest {

	private static RoleService roleService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			RoleService	roleService=(RoleService)cxt.getBean("roleServiceImpl");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Test
	public void permissionTag() throws JspException
	{
		PermissionTag  tag=new PermissionTag();
		tag.getRoleService(roleService);
		tag.doStartTag();
		
		
	}
	
	
	
}
