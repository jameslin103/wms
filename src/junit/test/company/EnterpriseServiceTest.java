package junit.test.company;


import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.user.WmsUserService;
public class EnterpriseServiceTest {

	static EnterpriseService  enterpriseService;
	static WmsUserService     wmsUserService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			enterpriseService = (EnterpriseService)cxt.getBean("enterpriseServiceImpl");
			//wmsUserService = (WmsUserService)cxt.getBean("wmsUserServiceImpl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void find()
	{
		List<Enterprise> enterpriseList=enterpriseService.getAllEnterprise(1);
		for (Enterprise enterprise : enterpriseList) {
			for ( CreateSalaryBudgetTable str : enterprise.getCreateSalaryBugetTables()) {
			      System.out.println(str.getName());
			      
			}
			
		}
		
		Enterprise  en=enterpriseService.find(1);
		
		for (CreateSalaryBudgetTable str : en.getCreateSalaryBugetTables()) {
			System.out.println(str.getName());
		}
		
	}
	@Test
	public void findEnterpriseBalanceDetail()
	{
		
		//Enterprise  en=enterpriseService.find(1);
		//enterpriseService.getAllEnterprise(1);
		WmsUser  user=new WmsUser();
		user.setUserId(1);
		System.out.println(enterpriseService.getAllEnterprise(user).size());
		
		
		//System.out.println(en.getBalanceDetails().size());
		
		
	}
	
	@Test
	public void saveEnterprise()
	{
		
		Enterprise en=new Enterprise();
		en.setAccountLine("69869784562456");
		en.setAddress("罗星塔");
		en.setFullName("枫叶科技有限公司");
		WmsUser  user=new WmsUser();
		user.setUserId(2);
		en.addWmsUser(user);
		enterpriseService.save(en);
		
	}
	@Test
	public void addUserAndEnterpriseRelationship()
	{
		
		enterpriseService.saveEnterpriseToBeResponsible(17, 3);

		
	}
	
	@Test
	public void removeToEnterpriseHeadUser()
	{
		enterpriseService.removeToEnterpriseHeadUser(17, 3);
		
	}
	
	
	
	@Test
	
	public void findEnterprise()
	{
		
	Enterprise	enterprise=enterpriseService.find(17);
	
	
	System.out.println(enterprise.getUser().iterator().next().getUsername());
	System.out.println(enterprise.getFullName());
	
	
	
	}
	@Test
	public void deleteEnterprise(){
		Enterprise	enterprise=enterpriseService.find(13);
		System.out.println(enterprise.getUser().size());
		
		for(WmsUser user : enterprise.getUser()){
			
			enterprise.getUser().remove(user);
			//user.getEnterprise().remove(enterprise);
			
			
		}
		enterpriseService.delete(enterprise);
		
	}
	
	@Test
	public void findUserToEnterprise()
	{
		
		WmsUser user=wmsUserService.find(1);	
		for(Enterprise enterprise:user.getEnterprise()){
			System.out.println(user.getUserId());
			System.out.println(enterprise.getFullName());
		}
	}
	
	@Test
	public void findEnterpriseToBeWmsUser()
	{
		
		Enterprise enter=enterpriseService.find(16);	
		for(WmsUser user:enter.getUser()){
			System.out.println(user.getUserId());
			System.out.println(user.getUsername());
		}
	}
	@Test
	public void findEnterpriseToBeCreateSalaryBudgetTable()
	{
		
		Enterprise enter=enterpriseService.find(16);
		for(CreateSalaryBudgetTable cr : enter.getCreateSalaryBugetTables()){
			System.out.println(cr.getName());
		}
	}
//	
//	@Test
//	public void getUserToAllEnterprise()
//	{
//		WmsUser user=new WmsUser();
//		user.setUserId(5);
//		enterpriseService.getUserToAllEnterprise(user);
//		
//	}


}
