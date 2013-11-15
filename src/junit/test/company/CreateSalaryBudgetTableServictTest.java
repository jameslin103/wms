package junit.test.company;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.web.action.company.ExportSalaryBudgetByPoiAction;

public class CreateSalaryBudgetTableServictTest {

	private static CreateSalaryBudgetTableService createSalaryBudgetTableService;
	private static SalaryTemplateService salaryTemplateService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext  axt=new ClassPathXmlApplicationContext("beans.xml");
		
		createSalaryBudgetTableService=(CreateSalaryBudgetTableService)axt.getBean("createSalaryBudgetTableServiceImpl");
		//salaryTemplateService=(SalaryTemplateService)axt.getBean("salaryTemplateServiceImpl");
		
		
		
		
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
	
	@Test
	public void excel()
	{
		ExportSalaryBudgetByPoiAction ca=new ExportSalaryBudgetByPoiAction();
		ca.setSalaryTemplateService(salaryTemplateService);
		ca.downloadSalaryBudgetTable();
		
		
	}
	
	@Test
	public void map()
	{
		Map<Integer, Object> map=new HashMap<Integer, Object>();
		List<CreateSalaryBudgetTable> list=createSalaryBudgetTableService.getAllCreateSalaryBudgetTable(1,2013);
		for (CreateSalaryBudgetTable c : list) {
			map.put(Integer.parseInt(c.getCreateDate().toString().substring(5,7)), c);
		}
		for (Map.Entry<Integer, Object> entry : map.entrySet()) {
			String key=entry.getKey().toString();
			String value=entry.getValue().toString();
			System.out.println(key);
			System.out.println(value);
		}
		
	}
	
	@Test
	public void findBeforeCurrentDateTemplate()
	{
		
		List<CreateSalaryBudgetTable> list=createSalaryBudgetTableService.findBeforeCurrentDateTemplate("2013-11-10",1);
		for (CreateSalaryBudgetTable cr : list) {
			System.out.println(cr.getSalaryDate());
		}
		
	}
	@Test
	public void saveCreateSalaryBudgetTable(){
		
		CreateSalaryBudgetTable c=new CreateSalaryBudgetTable();
		c.setName("松岛枫");
		c.setCashnumber(2);
		
		createSalaryBudgetTableService.saveCreateSalaryBudgetTable(c, 1, 3);
	}
	@Test
	public void update()
	{
		CreateSalaryBudgetTable  createSalaryBudgetTable=new CreateSalaryBudgetTable();
		
		createSalaryBudgetTable.setBudgetId(44);
		createSalaryBudgetTable.setCashnumber(5);
		createSalaryBudgetTable.setChooseTax("8888");
		createSalaryBudgetTableService.updateSalaryStatus(createSalaryBudgetTable);
		
	}
}
