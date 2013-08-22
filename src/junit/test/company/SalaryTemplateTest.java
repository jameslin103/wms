package junit.test.company;


import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.SalaryTemplate;
import cn.fm.service.company.SalaryTemplateService;

public class SalaryTemplateTest {

	private static SalaryTemplateService salaryTemplateService;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext axt=new ClassPathXmlApplicationContext("beans.xml");
		salaryTemplateService=(SalaryTemplateService)axt.getBean("salaryTemplateServiceImpl");
	
		
	}
	
	@Test
	public void find()
	{
//		List<SalaryTemplate> list=salaryTemplateService.getAllSalaryTemplate();
//		String splitString="21,33,55,66";
//		for (SalaryTemplate s : list) {
//			if(s.getSubsidyList()!=null){
//				//splitString=s.getSubsidyList();
//				String[] names = splitString.split(",");
//				for(String name:names)
//				{
//					if(name.trim().equals("1")){
//						System.out.println("高温补贴");
//					}
//					if(name.trim().equals("55")){
//						System.out.println("交通补贴");
//					}
//					if(name.trim().equals("66")){
//						System.out.println("伙食补贴");
//					}
//					System.out.println(name.trim());
//				}
//			}
//			System.out.println("=================="+s.getSubsidyList());
//		}
		//System.out.println(list.size());
		List<SalaryTemplate> lists=salaryTemplateService.reconfigureTemplate(null, null);
		lists=new ArrayList<SalaryTemplate>();
		if(lists.size()==0 || lists==null){
			System.out.println("xxxxx");
		}
		
	}
	@Test
	public void getSalaryList()
	{
		SalaryTemplate salaryTemplate=new SalaryTemplate();
		String[] sub={"0","1","2","3","4"};

		for (String s : sub) {
			salaryTemplate.setSubsidyList(s);
		}
		
		
		System.out.println(salaryTemplate.getSubsidyList());
		
	}

}
