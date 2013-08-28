package junit.test.company;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.WageBudgetSummary;
import cn.fm.service.salary.CreateSalaryBudgetTableService;

public class CreateSalaryBudgetTableServictTest {

	static CreateSalaryBudgetTableService createSalaryBudgetTableService;
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
		en.setId(3);
		createSalaryBudgetTable.setEnterprise(en);
		createSalaryBudgetTable.setName("中国卫星发射");
		createSalaryBudgetTable.setTemple("安装系统");

		
		createSalaryBudgetTableService.save(createSalaryBudgetTable);
		
	}

}
