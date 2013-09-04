package junit.test.company;


import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.company.EnterpriseService;
public class EnterpriseServiceTest {

	static EnterpriseService  enterpriseService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			enterpriseService = (EnterpriseService)cxt.getBean("enterpriseServiceImpl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	
	public void save()
	{
		//Enterprise enterprise=new Enterprise();
//		enterprise.setFullName("火星计划");
//		enterprise.setAddress("gouliu");
//		enterprise.setContact("XXXXFFFF000202");
	    EntityManagerFactory factory = Persistence.createEntityManagerFactory("EnterpriseServiceTest");  
		EntityManager em = factory.createEntityManager();  
		em.getTransaction().begin();  
		WmsUser  user= em.find(WmsUser.class, 1);  
		user.addEterprise(em.getReference(Enterprise.class, 1));  
		em.getTransaction().commit();  
		em.close();  
		factory.close(); 

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
		Enterprise  en=enterpriseService.find(3);
		System.out.println(en.getBalanceDetails().size());
		
		
		
		
	}

}
