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
import cn.fm.service.base.BaseGrid;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.utils.PersonalTaxUtil;

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
   @Test 
   public void personalTax()
   {
	   BigDecimal result=PersonalTaxUtil.getPersonalTaxResults(4000.00,3500.00, 1000.00);
	   System.out.println(result);
	   
   }
   @Test
   public void sameEmployeesName()
   {
	   List<EmployeesSalaryDetail> edList=new ArrayList<EmployeesSalaryDetail>();
	   EmployeesSalaryDetail ed=new EmployeesSalaryDetail();
	   ed.setEmployeesName("张三xxx");
	   ed.setCardNumber("");
	   edList.add(ed);
	   EmployeesSalaryDetail ed1=new EmployeesSalaryDetail();
	   ed1.setEmployeesName("张三");
	   ed1.setCardNumber("");
	   edList.add(ed1);
	   EmployeesSalaryDetail ed3=new EmployeesSalaryDetail();
	   ed3.setEmployeesName("李刚");
	   ed3.setCardNumber("350123");
	   edList.add(ed3);
	   //==========================================================
	   List<EnterpriseEmployees> eeList=new ArrayList<EnterpriseEmployees>();
	   EnterpriseEmployees ee=new EnterpriseEmployees();
	   ee.setEmployeesName("张三");
	   ee.setCardNumber("350123");
	   eeList.add(ee);
	   EnterpriseEmployees ee1=new EnterpriseEmployees();
	   ee1.setEmployeesName("张三");
	   ee1.setCardNumber("350123");
	   eeList.add(ee1);
	   EnterpriseEmployees ee2=new EnterpriseEmployees();
	   ee2.setEmployeesName("张三");
	   ee2.setCardNumber("");
	   eeList.add(ee2);
	   EnterpriseEmployees ee3=new EnterpriseEmployees();
	   ee3.setEmployeesName("张三");
	   ee3.setCardNumber("");
	   eeList.add(ee3);
	   int count=0;
	   int cardnumber=0;
	   boolean isCar=false;
	   int templCar=0;
	   String detailCar="";
		   for (EmployeesSalaryDetail  employeesSalaryDetail : edList)
		   {
			   boolean falg=false;
			   String detailName=employeesSalaryDetail.getEmployeesName();
			   for ( EnterpriseEmployees enterpriseEmployees : eeList)
			   {
				     
				        String employeName=enterpriseEmployees.getEmployeesName();
						   if(detailName.equals(employeName))
						   {      falg=true;
							       count++;
								   if(count>0)
								   {
									      
										   String card=enterpriseEmployees.getCardNumber()==null?"":enterpriseEmployees.getCardNumber();
										   detailCar=employeesSalaryDetail.getCardNumber();
										   if(detailCar==null || detailCar.equals("")){System.out.println("身份证不能为空");return;}
										   if(card.equals(detailCar))
										   {
											   cardnumber++;
											   
											     if(cardnumber>1)
											      {
											    	 templCar++;
											    	 isCar=true;
											      }
										   } 
								   }
						   } 
				    }
			   		if(falg==false){
						  System.out.println("不存在的名字："+detailName);
						  System.out.println("数据库无法匹配不存在此员工");
						  System.out.println("=======================================");
			   		}
			   		
			   		if(isCar==true)
			   		{
			   		 System.out.println("数据库存在："+count+"个: "+detailName+" 身份证号:"+detailCar+" 相同 : "+templCar+" 个");
			   		}
			   }
	   }
   
   @Test
   public void exitSameUploadEnterpriseEmployees()
   {
	   List<EnterpriseEmployees> enterpriseEmployeesListPO=new ArrayList<EnterpriseEmployees>();
	   EnterpriseEmployees  enterpriseEmployees=new EnterpriseEmployees();
	   enterpriseEmployees.setEmployeesName("张东");
	   enterpriseEmployees.setCardNumber("101");
	   enterpriseEmployeesListPO.add(enterpriseEmployees);
	   
	   EnterpriseEmployees  enterpriseEmployees1=new EnterpriseEmployees();
	   enterpriseEmployees1.setEmployeesName("张东");
	   enterpriseEmployees1.setCardNumber("101");
	   enterpriseEmployeesListPO.add(enterpriseEmployees1);
	   
	   EnterpriseEmployees  enterpriseEmployees2=new EnterpriseEmployees();
	   enterpriseEmployees2.setEmployeesName("张东");
	   enterpriseEmployees2.setCardNumber("101");
	   enterpriseEmployeesListPO.add(enterpriseEmployees2);
	   
	   EnterpriseEmployees  enterpriseEmployees3=new EnterpriseEmployees();
	   enterpriseEmployees3.setEmployeesName("张东");
	   enterpriseEmployees3.setCardNumber("101");
	   enterpriseEmployeesListPO.add(enterpriseEmployees3);
	   
	   EnterpriseEmployees  enterpriseEmployees4=new EnterpriseEmployees();
	   enterpriseEmployees4.setEmployeesName("张东");
	   enterpriseEmployees4.setCardNumber("101");
	   enterpriseEmployeesListPO.add(enterpriseEmployees4);
	   
	   
	   //===========================================================
	   EmployeesSalaryDetail  employeesSalaryDetail=new EmployeesSalaryDetail();
	   employeesSalaryDetail.setEmployeesName("张东");
	   employeesSalaryDetail.setCardNumber("101");
	   
	   
	   List<String> employees=employeesSalaryDetailService.isExitUploadEnterpriseEmployees(enterpriseEmployeesListPO, employeesSalaryDetail);
	   for (String string : employees) 
	   {
		   System.out.println(string);
	   }
	   
   }
   
   @Test
   public void cumotsLegth()
   {
	   String  file="1, 3, 5, 6, 8";
	   String[] customt=file.split(",");
	   System.out.println(customt);
	   int count=customt.length+4;
	  
	   System.out.println(count);
	   
   }
   @Test
   public void updateEmployeesCarNumber()
   {
	  EnterpriseEmployees employees=new  EnterpriseEmployees();
	  employees.setEmployeesId(2);
	  employees.setCardNumber("110");
	  
	  employeesSalaryDetailService.updateEmployeesCarNumber(employees);
	   
   }
   @Test
   public void structureEmployeesSalaryDetail(){
	   
	   EmployeesSalaryDetail ed=new EmployeesSalaryDetail();
	   ed.setWage(new BigDecimal(4900));
	   ed.setBonus(new BigDecimal(500));
	   ed.setSubsidies(new BigDecimal(100));
	   ed.setCardNumber("555999");
	   
	   EmployeesSalaryDetail eDetail=employeesSalaryDetailService.structureEmployeesSalaryDetail(ed,1);
	   
	   System.out.println(eDetail.getShouldPay());
	   System.out.println(eDetail.getBonus());
	   System.out.println(eDetail.getPersonalTax());
   }
   @Test
   public void getPayrollStaff()
   {
	   BaseGrid  gr=new BaseGrid();
	   gr.setTotal(1);
	   gr.setAlis("22");
	   gr.setCountPage(2);
	   gr.setModuleName("EmployeesSalaryDetailService");
	   //employeesSalaryDetailService.getPayrollStaff(gr);
	   
	   
   }
}
