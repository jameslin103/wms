package junit.test.user;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.permissions.Employee;
import cn.fm.bean.permissions.PrivilegeGroup;
import cn.fm.bean.permissions.SystemPrivilege;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.user.ContactInfo;
import cn.fm.bean.user.Gender;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.user.WmsUserService;




public class WmsUserServiceTest {
	private static WmsUserService wmsUserService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			wmsUserService = (WmsUserService)cxt.getBean("wmsUserServiceImpl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testSave() {
		WmsUser wmsUser = new WmsUser("关羽","123","guanyu@sina.com");
		ContactInfo contactInfo = new ContactInfo();
		contactInfo.setAddress("福州市");
		contactInfo.setMobile("13289252236");
		contactInfo.setPhone("0591-33706589");
		contactInfo.setPostalcode("350001");
		wmsUser.setContactInfo(contactInfo);
		//wmsUser.setRealname("三国");
		wmsUserService.save(wmsUser);
	}

	@Test
	public void testFind1()
	{
		
	
		boolean falg=wmsUserService.exsit("jameslin");
		System.out.println(falg);
		
	}

	@Test
	public void find()
	{
		
	
		WmsUser wms=wmsUserService.find("13809505940");
		System.out.println(wms.getPhone());
		
	}
	@SuppressWarnings("unchecked")
	@Test
	public void testUsername()
	{

		List<WmsUser> listWmsUser=new ArrayList<WmsUser>();
		listWmsUser=wmsUserService.getWmsUserInfo("jameslin","jameslin110","jameslin666");
		for (WmsUser buyer : listWmsUser) {
			System.out.println(buyer.getEmail());
		}
		
	}
	@Test
	public void testGetfall()
	{
		List<WmsUser> listBuyer=new ArrayList<WmsUser>();
		listBuyer=wmsUserService.getAllWmsUser();
		System.out.println(listBuyer.size());
		
	}
	@Test
	public void findUserToEnterprise()
	{
		WmsUser user=wmsUserService.find(1);
		Set<Enterprise> enterpriseList1=user.getEnterprise();
		for(Enterprise en: enterpriseList1){
			System.out.println(user.getUserId());
			System.out.println(user.getUsername());
			System.out.println(en.getFullName());
			for(CreateSalaryBudgetTable c : en.getCreateSalaryBugetTables()){
				System.out.println(en.getEnterpriseId());
				System.out.println(c.getName());
				
			}
			
		}
	}
	@Test
	public void deleteWmsUser()
	{
		WmsUser user=wmsUserService.find(1);
		
		for(Enterprise en: user.getEnterprise()){
			en.removeWmsUser(user);
			wmsUserService.delete(en);
		}
		
	
	}
	@Test
	public  void update()
	{
		WmsUser wmsUser=wmsUserService.find(4);
		WmsUser user=new WmsUser();
		user.setUsername("孔子");
		user.setPhone("123456");
		user.setContactInfo(wmsUser.getContactInfo());
		user.setUserId(wmsUser.getUserId());
		
		
		
		wmsUserService.update(wmsUser);
	}
	

}
