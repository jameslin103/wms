package junit.test.company;



import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseContract;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.user.User;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.user.UserService;
public class EnterpriseServiceTest {

	static EnterpriseService  enterpriseService;
	static UserService     userService;
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
	public void saveEnterprise()
	{
		
		Enterprise en=new Enterprise();
		en.setAccountLine("69869784562456");
		en.setAddress("罗星塔");
		en.setFullName("枫叶科技有限公司");
		User  user=new User();
		user.setId("");
		en.addUser(user);
		enterpriseService.save(en);
		
	}
	
	
	@Test
	public void findEnterprise()
	{
		
	//Enterprise	enterprise=enterpriseService.find(17);
		User user=new User();
		Enterprise	enterprise=new Enterprise();
		PageView<Enterprise>  pageView=enterpriseService.getAllEnterprise(10, 1,user,enterprise);
		
		for (Enterprise ent: pageView.getRecords()) {
			
			EnterpriseContract con=(EnterpriseContract)enterpriseService.getEndContractDateDay(ent.getEnterpriseId());
			System.out.println(ent.getEnterpriseId()+"------"+ent.getFullName()+"天数="+con.getEnterprise().getToDay()+"合同结束日期："+con.getEndContractDate());
			
			
			
			//System.out.println(con.getEndContractDate());
			
		}
	
	
	
	
	}
//	public void calculateMaxAndMin(Enterprise enterprise)
//	{
//		
//		Double max=0d;
//		Double  min=0d;
//		Set<EnterpriseContract>  content=enterprise.getEnterpriseContract();
//		
//		max=Collections.max(content,new Comparator<EnterpriseContract>() {
//			@Override
//			public int compare(EnterpriseContract con, EnterpriseContract con2) {
//				if(con.getStatus()>con2.getStatus())
//				{
//					return 1;
//				}else{
//					return -1;
//				}
//				
//			}}).getStatus();
//		min=Collections.min(content,new Comparator<EnterpriseContract>() {
//			@Override
//			public int compare(EnterpriseContract con, EnterpriseContract con2) {
//				if(con.getStatus()>con2.getStatus())
//				{
//					return 1;
//				}else{
//					return -1;
//				}
//				
//			}}).getStatus();
//		
//		
//		
//		
//	}	
	
	
	
	@Test
	public void deleteEnterprise(){
		Enterprise	enterprise=enterpriseService.find(13);
		System.out.println(enterprise.getUser().size());
		
		for(User user : enterprise.getUser()){
			
			enterprise.getUser().remove(user);
			//user.getEnterprise().remove(enterprise);
			
			
		}
		
	}
	
	@Test
	public void findUserToEnterprise()
	{
		
		User user=userService.getById("");	
		for(Enterprise enterprise:user.getEnterprise()){
			System.out.println(user.getId());
			System.out.println(enterprise.getFullName());
		}
	}
	
	@Test
	public void findEnterpriseToBeWmsUser()
	{
		
		Enterprise enter=enterpriseService.find(16);	
		for(User user:enter.getUser()){
			System.out.println(user.getId());
			System.out.println(user.getEmployee().getName());
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
	
	@Test
	public void getUserToAllEnterprise()
	{
		User user=new User();
		user.setId("");
		enterpriseService.getUserToAllEnterprise(user);
		
	}


}
