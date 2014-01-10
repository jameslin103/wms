package junit.test.company;
import java.util.ArrayList;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
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
	@Test
	public void test()
	{
		String []aa={"3793,商祖勇,350181198707021519,3792,阮小健,352202199109204215,3791,陈秋红,350823199207032624,3790," +
				"		黄松涛,350182199108152278,3789," +
				"		兰先文,512925197102166299,3788,方樟榕,350125199212204713,3787,林章架,350124195611195433,3785," +
				"		朱汉阳,35024199008182558,3784,吴华兴,350825198609013619,3783,张夏开,350124197309174999,3782," +
				"		刘珊,350124198907270184,3781,严希银,350426198707093530,3780,赵炜,350121199401023755,3779," +
				"		江贤鹏,350121199109043732,3778,叶翔,35012319920705039x,3777,方芳超,350321198605311915,3776," +
				"		陈毅微,350623199101090032,3775,赵久明,612324197512250332,3774,李远彬,350783199011047534,3773," +
				"		林城,350125198711230312,3772,陈奇贞,350823198611251410,3771,范雅琳,350321198912242948,3770," +
				"		林晓枝,35212819721125252x,3769,兰树尔,512925197510066297,3768,陈绍炎,350124198909276299"};
		//String [] ss=aa.substring(0, aa.length() - 1).split(",");
		String [] b={"23,22,12,23"};
		String [] c={"xx,aaa,6ggg,llll"};
		List<String[]> lsit=new ArrayList<String[]>();
		List li=new ArrayList();
		lsit.add(b);
		lsit.add(c);
		for (String[] str : lsit) {
			System.out.println(str[0]);
		}
		System.out.println("=============================");
		for (int i = 0; i < c.length; i++) {
			System.out.println(c[i]);
			String a=new String();
			a=c[i];
			li.add(a);
		}
		for (int k = 0; k < b.length; k++) {
			System.out.println("=============================kkkkk");
			for (int i = 0; i < k; i++) {
				System.out.println(c[i]);
			}
			System.out.println(b[k]);
			String s=new String();
			s=b[k];
			li.add(s);
		}
		 
		for (Object ob : li) {
			
			
			System.out.println(ob);
			
		}
		
	}
	
}
