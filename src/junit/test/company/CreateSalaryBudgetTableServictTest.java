package junit.test.company;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.salary.CreateSalaryBudgetTableService;

public class CreateSalaryBudgetTableServictTest {

	private static CreateSalaryBudgetTableService createSalaryBudgetTableService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext  axt=new ClassPathXmlApplicationContext("beans.xml");
		
		createSalaryBudgetTableService=(CreateSalaryBudgetTableService)axt.getBean("createSalaryBudgetTableServiceImpl");
		
		
		
		
	}
	@Test
	public void save()
	{
		CreateSalaryBudgetTable createSalaryBudgetTable=new CreateSalaryBudgetTable();
		Enterprise en=new Enterprise();
		SalaryTemplate  salary=new SalaryTemplate();
		salary.setTemplateId(4);
		en.setEnterpriseId(16);
		
		createSalaryBudgetTable.setEnterprise(en);
		createSalaryBudgetTable.setName("中交二航局海西高速公路网东山联络线路基土建工程施工");
		createSalaryBudgetTable.setSalaryTemplate(salary);

		
		createSalaryBudgetTableService.save(createSalaryBudgetTable);
		
	}
	
	@Test
	public void testUpdate()
	{

		CreateSalaryBudgetTable createSalaryBudgetTable=createSalaryBudgetTableService.find(8);
		createSalaryBudgetTable.setName("海西建设");
		createSalaryBudgetTable.setNote("修改成功!!");
		createSalaryBudgetTableService.updateCreateSalaryBudgetTable(createSalaryBudgetTable);
		
		
	}


		
}
