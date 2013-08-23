package junit.test.company;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.SalaryTemplate;
import cn.fm.service.company.CreateSalaryBudgetTableService;
import cn.fm.service.company.SalaryTemplateService;

public class CreateSalaryBudgetTableTest {

	private static CreateSalaryBudgetTableService createSalaryBudgetTableService;
	private static SalaryTemplateService salaryTemplateService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext  axt=new ClassPathXmlApplicationContext("beans.xml");
		//createSalaryBudgetTableService=(CreateSalaryBudgetTableService)axt.getBean("CreateSalaryBudgetTableServiceImpl");
		salaryTemplateService=(SalaryTemplateService)axt.getBean("salaryTemplateServiceImpl");
		
	}

	@Test
	public void  testSaveTemplate(){
		
		//CreateSalaryBudgetTable createSalaryBudgetTable=new CreateSalaryBudgetTable();
		SalaryTemplate   sal=new SalaryTemplate();
		sal.setTax(1);
		sal.setFiveInsurances(1);
		sal.setStatus(1);
		sal.setSubsidyList("1,3");
		sal.setTemplateName("工程学院尾期楼改造");

		
		
		salaryTemplateService.save(sal);
	}
	
}
