package junit.test.user;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.user.WmsUser;
import cn.fm.service.user.WmsUserService;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class LoginActionTest extends BaseAction{

	private static WmsUserService buyerService;
	@BeforeClass
	public static void setUpBeforeClass()
	{
		ApplicationContext cxt=new ClassPathXmlApplicationContext("beans.xml");
		buyerService=(WmsUserService)cxt.getBean("buyerServiceImpl");
	}
	
	@Test
	public void testFind()
	{
		
	  WmsUser	buyer=buyerService.find("13809505940");
	  assertEquals(buyer.getUsername(),"13809505940");
	  assertEquals(buyer.getEmail(),"test@sina.com");
	  //assertTrue(buyer.getEmail()=="test@sina.com");
	}
	
	
}
