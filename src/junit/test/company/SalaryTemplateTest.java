package junit.test.company;


import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.utils.DateUtil;
import cn.fm.utils.ExportExcelUtils;

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

	@Test
	public void save()
	{
		SalaryTemplate sal=new SalaryTemplate();
		sal.setTemplateName("huoxinyihao");
		sal.setTax(1);
		Enterprise enterprise=new Enterprise();
		enterprise.setEnterpriseId(16);
		sal.setEnterprise(enterprise);
		salaryTemplateService.save(sal);
		
	}
	
	@Test
	public void excelImpot()
	{
//		ExportExcelUtils<SalaryTemplate> ex = new ExportExcelUtils<SalaryTemplate>();
//		SalaryTemplate salaryTemplate=salaryTemplateService.find(3);
//		String headers=salaryTemplate.getSubsidyList();
//        OutputStream out = new FileOutputStream("E://a.xls");
//        OutputStream out2 = new FileOutputStream("E://b.xls");
        
//        
//        ex.exportExcel(headers,null, out);
//        
//        out.close();
//        JOptionPane.showMessageDialog(null, "导出成功!");
//        System.out.println("excel导出成功！");
//		
		
		
		
	}

}
