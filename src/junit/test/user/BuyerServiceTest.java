package junit.test.user;



import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.fm.bean.user.Buyer;
import cn.fm.bean.user.ContactInfo;
import cn.fm.service.user.BuyerService;




public class BuyerServiceTest {
	private static BuyerService buyerService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			buyerService = (BuyerService)cxt.getBean("buyerServiceImpl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testSave() {
		Buyer buyer = new Buyer("alax","123456","test@sina.com");
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setAddress("北京市");
		contactInfo.setMobile("13671323507");
		contactInfo.setPhone("0591-81815642");
		contactInfo.setPostalcode("350001");
		buyer.setContactInfo(contactInfo);
		buyer.setRealname("县政府");
		buyerService.save(buyer);
	}

	@Test
	public void testFind1()
	{
		
	
		boolean falg=buyerService.exsit("jameslin");
		System.out.println(falg);
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testUsername()
	{

		List<Buyer> listBuyer=new ArrayList<Buyer>();
		listBuyer=buyerService.getBuyerInfo("jameslin","jameslin110","jameslin666");
		for (Buyer buyer : listBuyer) {
			System.out.println(buyer.getEmail());
		}
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testGetfall()
	{
		List<Buyer> listBuyer=new ArrayList<Buyer>();
		listBuyer=buyerService.getAllByuer();
		System.out.println(listBuyer.size());
		
	}
	
}
