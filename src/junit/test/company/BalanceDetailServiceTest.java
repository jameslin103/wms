package junit.test.company;


import java.math.BigDecimal;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.salary.BalanceDetail;
import cn.fm.service.salary.BalanceDetailService;

public class BalanceDetailServiceTest {

	static BalanceDetailService balanceDetailService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext  axt=new ClassPathXmlApplicationContext("beans.xml");
		
		balanceDetailService=(BalanceDetailService)axt.getBean("balanceDetailServiceImpl");	
		
	}
	
	@Test
	public void testAllBalanceDetail()
	{
		List<BalanceDetail> list=balanceDetailService.getAllBalanceDetail();
		
		System.out.println(list.size());
		
		
	}
	@Test
	public void update()
	{
		BalanceDetail balanceDetail=new BalanceDetail();
		balanceDetail.setServiceWith(new BigDecimal(1000));
		balanceDetail.setReceivedFunds(new BigDecimal(800));
		balanceDetail.setNote("已完成配置");
		balanceDetail.setFiveFund(new BigDecimal(300));
		balanceDetail.setServiceWith(new BigDecimal(700));
		balanceDetail.setWages(new BigDecimal(200));
		balanceDetailService.update(balanceDetail, 2,3);

		
	}

}
