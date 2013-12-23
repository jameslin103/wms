package junit.test.company;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.service.company.EnterpriseEmployeesService;
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
	public void   numberPersonlTotal()
	{
		
		Integer total=Integer.parseInt(employeesSalaryDetailService.getNumberPersonlTotal(17, 22)+"");
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
		EnterpriseEmployees   enterpriseEmployees=new EnterpriseEmployees();
		enterpriseEmployees.setBase(0);
		
		//*社会保险基数*/
		enterpriseEmployees.setSocialInsurance(1500.00);
		
		//生育保险基数
		enterpriseEmployees.setFertilityInsurance(2404.45);
		
		//工伤基数
		enterpriseEmployees.setInductrialBase(2404.45);
		
		//基本医疗保险   缴费基数
		enterpriseEmployees.setBasicMedical(2805.19);
		
		//住房公积金-缴费基数
		enterpriseEmployees.setHousingFund(1050.00);
		
		employeesSalaryDetail=employeesSalaryDetailService.toCalculateFiveInsurances(enterpriseEmployees);
		
		System.out.println(employeesSalaryDetail.getBirthInsuranceBase());
		System.out.println(employeesSalaryDetail.getSocialInsuranceBase());
		System.out.println(employeesSalaryDetail.getSpecialAccumulationFundSubsidies());
		System.out.println(employeesSalaryDetail.getSpecialHealthCareSubsidies());
		
		
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
	   BigDecimal result=PersonalTaxUtil.getPersonalTaxResults(3816.52,3500.00);
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
	public void proportionToCalculate()
	{
	    BigDecimal proportion=new BigDecimal(100);
	    BigDecimal tax=new BigDecimal(0.8);
	    BigDecimal resultsTax=tax.divide(proportion, 3, BigDecimal.ROUND_HALF_UP);
		BigDecimal baseTax=tax.multiply(resultsTax);
		BigDecimal resultsBase= baseTax.setScale(2, BigDecimal.ROUND_HALF_UP);  
		
		System.out.println(resultsBase);
	}
   @Test
   public void aa()
   {
	   List<String[]> list= new ArrayList<String[]>();
	   String[]  file={" 1"	,"DNNNC001",	"杨斌"	,"男"	,"福建"	,"是	","福建福州市",	"是"	,"本科",	"是"	,"3.52202E+17",	"88888888888"	,"领导",	"民生银行"	,"xxxx-xxxx-xxxx-xxxx"	,"2013/9/1"	,"2015/8/31"	,"30"	,"新增"	,"是"	,"2015/8/31"	,"是	","是"	,"是"	,"65"	,"是	","1200"	,"1200"	,"1200"	,"1200"	,"1200"	,"50"	,"是"};
//	   String  file1="杨斌,88888888888";
//	   String  file2="韩运财,88888888888";
//	   String  file3="杨斌,88888888888";
	  // String[]  file4={" 1	,DNNNC001,	杨斌	,男	,福建	,是	,福建福州市,	是	,本科,	是	,3.52202E+17,	88888888888	,领导,	民生银行	,xxxx-xxxx-xxxx-xxxx	,2013/9/1	,2015/8/31	,30	,新增	是	,2015/8/31	,是	,是	,是	,65	,是	,1200	,1200	,1200	,1200	,1200	,50	,是"};
	   String[]  file4={" 1" ,"DNNNC001",	"杨斌"	,"男"	,"福建"	,"是	","福建福州市",	"是"	,"本科",	"是"	,"3.52202E+17",	"88888888888"	,"领导",	"民生银行"	,"xxxx-xxxx-xxxx-xxxx"	,"2013/9/1"	,"2015/8/31"	,"30"	,"新增"	,"是"	,"2015/8/31"	,"是	","是"	,"是"	,"65"	,"是	","1200"	,"1200"	,"1200"	,"1200"	,"1200"	,"50"	,"是"};

	   list.add(file);
//	   list.add(file1);
//	   list.add(file2);
//	   list.add(file3);
	   list.add(file4);
	   
	  
	   List<String>  str=new ArrayList<String>();
	   
	   for (String[] string : list) 
	   {
		  
		   System.out.println( string[0].toString());
	   }
	   
	   for (int i = 0; i < list.size(); i++) {
		    System.out.println(list.get(i));
		    
			str.add(list.get(i).toString());
		}
	   
	   for  ( int  i  =   0 ; i  <  str.size()  -   1 ; i ++ )//外循环是循环的次数
	   {
	          for  ( int  j  =  str.size()  -   1 ; j  >  i; j -- )  {//内循环是 外循环一次比较的次数
	        	  
	        	  if  (str.get(j).equals(str.get(i)))  {
	                  //list.remove(j);
	                  System.out.println("重复"+str.get(i));
	                } 
	            } 
	    } 
	   
	    for (int i = 0; i < str.size(); i++) {
			System.out.println(str.get(i));
		}
	   
   }
}
