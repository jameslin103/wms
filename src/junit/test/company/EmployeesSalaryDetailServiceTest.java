package junit.test.company;


import java.math.BigDecimal;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.service.salary.EmployeesSalaryDetailService;

public class EmployeesSalaryDetailServiceTest {

	private static EmployeesSalaryDetailService employeesSalaryDetailService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext cxt=new ClassPathXmlApplicationContext("beans.xml");
		employeesSalaryDetailService=(EmployeesSalaryDetailService)cxt.getBean("employeesSalaryDetailServiceImpl");
		
		
	}
	@Test
	public void   fiveInsuter()
	{
		
		BigDecimal total=employeesSalaryDetailService.getFiveInsuranceTotal(17, 22);
		System.out.println(total);
		
	}
	@Test
	public void   numberPersonlTotal()
	{
		
		Integer total=Integer.parseInt(employeesSalaryDetailService.getNumberPersonlTotal(17, 22)+"");
		System.out.println(total);
		
	}
	@Test
	public void   invoiceTotal()
	{
		
		BigDecimal total=employeesSalaryDetailService.getInvoiceTotal(17, 22);
		System.out.println(total);
		
	}
	@Test
	public void   delete()
	{
		employeesSalaryDetailService.deleteEmployeesSalaryDetail(26);
	}
}
