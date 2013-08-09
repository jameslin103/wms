package junit.test.user;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.user.Buyer;
import cn.fm.service.user.BuyerService;
import cn.fm.web.action.BaseAction;

public class LoginActionTest extends BaseAction{

	private static BuyerService buyerService;
	@BeforeClass
	public static void setUpBeforeClass()
	{
		ApplicationContext cxt=new ClassPathXmlApplicationContext("beans.xml");
		buyerService=(BuyerService)cxt.getBean("buyerServiceImpl");
	}
	
	@Test
	public void testFind()
	{
		
	  Buyer	buyer=buyerService.find("13809505940");
	  assertEquals(buyer.getUsername(),"13809505940");
	  assertEquals(buyer.getEmail(),"test@sina.com");
	  //assertTrue(buyer.getEmail()=="test@sina.com");
	}
	
	
}
