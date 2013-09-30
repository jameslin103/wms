package junit.test.company;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.EmployeesSalaryDetail;
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
	
	@Test
	public void fiveDetail()
	{
		EmployeesSalaryDetail employeesSalaryDetail=new EmployeesSalaryDetail();
		employeesSalaryDetailService.toCalculateFiveInsurances();
		System.out.println(employeesSalaryDetail.getBirthInsuranceBase());
		System.out.println(employeesSalaryDetail.getSocialInsuranceBase());
		
		
//		  System.out.println("===========================================");
//		  BigDecimal aa = new BigDecimal(2086.25);
//		  BigDecimal bb = new BigDecimal(0.7);
//		  BigDecimal par=new BigDecimal(100);
//		  
//		  BigDecimal bb1=bb.divide(par, 3, BigDecimal.ROUND_HALF_UP);
//		  System.out.println("基数"+bb1);
//		  BigDecimal c11=aa.multiply(bb1);
//		  BigDecimal bd2 = c11.setScale(2, BigDecimal.ROUND_HALF_UP);   
//		  
//		  System.out.println(bd2);

	}
   @Test
   public void isExitUploadEnterpriseEmployees()
   {
	   List<EnterpriseEmployees> enterpriseEmployeesListPO=new ArrayList<EnterpriseEmployees>();
	   EnterpriseEmployees  enterpriseEmployees=new EnterpriseEmployees();
	   enterpriseEmployees.setEmployeesName("张东");
	   enterpriseEmployees.setCardNumber("35000189789");
	   enterpriseEmployeesListPO.add(enterpriseEmployees);
	   EmployeesSalaryDetail  employeesSalaryDetail=new EmployeesSalaryDetail();
	   employeesSalaryDetail.setEmployeesName("张东");
	   employeesSalaryDetail.setCardNumber("35000189789");
	   
	  
	   
	   
	   List<String> employees=employeesSalaryDetailService.isExitUploadEnterpriseEmployees(enterpriseEmployeesListPO, employeesSalaryDetail);
	   for (String string : employees) 
	   {
		   System.out.println(string);
	   }
   }

   @Test
   public void count(){
	       List<String> list = new ArrayList<String>();
	       Scanner s= new Scanner(System.in);
	       System.out.print("请输入字符:");
	     	String str=s.nextLine();;
	        char[] c =str.toCharArray();
	           for(char b : c)
	           {
	               list.add(String.valueOf(b));
	           }
	           Collections.sort(list);
	            
	           for(String a : list)
	           {
	               int begin = list.indexOf(a);
	               int end = list.lastIndexOf(a);
	                
	               System.out.println("字母'"+ a + "'有:"+(end - begin + 1)+"个");
	           }
	   
	   
   }
   

}
