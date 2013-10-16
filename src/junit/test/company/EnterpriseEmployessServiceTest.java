package junit.test.company;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.utils.DateUtil;
public class EnterpriseEmployessServiceTest {

	
	private static EnterpriseEmployeesService enterpriseEmployeesService;
	private static EnterpriseService    enterpriseService;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		try {
			ApplicationContext cxt=new ClassPathXmlApplicationContext("beans.xml");
			enterpriseEmployeesService=(EnterpriseEmployeesService)cxt.getBean("enterpriseEmployeesServiceImpl");
			//enterpriseService=(EnterpriseService)cxt.getBean("enterpriseServiceImpl");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			System.out.println(e.getClass());
		}
		
		
	}
	@Test
	public void find()
	{
		List<EnterpriseEmployees> enterpriseEmployeesList=enterpriseEmployeesService.getAllEnterpriseEmployees(3);
		System.out.println(enterpriseEmployeesList.size());
		for (EnterpriseEmployees enterpriseEmployees : enterpriseEmployeesList) {
			System.out.println(enterpriseEmployees.getHomeAddress());
		}
		
		
	}
	@Test
	public void testFindEmplyess()
	{
		List<EnterpriseEmployees> list=enterpriseEmployeesService.findInsuranceEnterpriseEmployees(1,17);
		for (EnterpriseEmployees es : list) {
			System.out.println(es.getEmployeesName());
		}
		
	}
	
	@Test
	public void fildAllEnterpriseEmployees()
	{
		List<EnterpriseEmployees> list=enterpriseEmployeesService.findAllEnterpriseEmployees(null, 1,3);
		System.out.println(list.size());
		for (EnterpriseEmployees es : list) {
			System.out.println(es.getEmployeesName());
			System.out.println(es.getEnterprise().getFullName());
		}
	}
	
	@Test  
	public void testSave()
	{
		EnterpriseEmployees enterpriseEmployees=new EnterpriseEmployees();
		enterpriseEmployees.setBank("民生银行");
		enterpriseEmployees.setBankCardNumber("东街");
		enterpriseEmployees.setCardNumber("82006549758469587");
		enterpriseEmployeesService.save(enterpriseEmployees);
	}
	@Test
	public void testSaveEnterprise()
	{
		Enterprise enterprise=new Enterprise();
		enterprise.setAddress("松岛枫");
		enterprise.setContact("到付");
		enterprise.setFax("1536984521");
		enterprise.setEmail("rrr@cc.xom");
		enterpriseService.save(enterprise);
		
	}
	
	@Test
	public void testStringToDate()
	{
		
		Date date=DateUtil.StringToDate(DateUtil.FORMAT_DATE, "2013-08-12");
		
		System.out.println(date);

		String d=DateUtil.dateToString(new Date(),DateUtil.FORMAT_DATE);
		System.out.println(d);
	}
	
	@Test
	public void getAllEnterpris()
	{
		List<Enterprise> listEnterprise=enterpriseService.getAllEnterprise(1);
		for (Enterprise enterprise : listEnterprise) {
			System.out.println(enterprise.getAddress());
			System.out.println(enterprise.getCount());
			System.out.println(enterprise.getEmail());

			
		}
		
	}
	
	@Test
	public void countSafft()
	{
		
		
		System.out.println(enterpriseEmployeesService.newStaffCount(16));
		System.out.println(enterpriseEmployeesService.renewalPersonnel(16));
		System.out.println(enterpriseEmployeesService.ginsengPersonnel(16));
		
		
	}
	@Test
	public void  findWorkersIncreasedToEmployees()
	{
		
		for (EnterpriseEmployees en : enterpriseEmployeesService.findWorkersIncreasedToEmployees(16)) {
			
			System.out.println(en.getEmployeesName());
			
		}		
	}
	
	@Test
	public void findNewStaffAndRenewalEmployees()
	{
		
		for (EnterpriseEmployees en : enterpriseEmployeesService.findNewStaffAndRenewalEmployees(16,"新增")) {
			
			System.out.println(en.getEmployeesName());
			
		}		
		
	}
	
	@Test 
	public void testIsBean() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		EnterpriseEmployees baseBean = new EnterpriseEmployees();
		 
		         Class class1 = baseBean.getClass();
//		 
//		         Field[] fileds = class1.getDeclaredFields();
//		
//		         for (int i = 0; i < fileds.length; i++) {
//		
//		             Field field = fileds[i];
//		
//		             String fieldName = field.getName();
//		             System.out.println(fieldName);
//		         }
		         Method[] sourceMethods = class1.getMethods();
		 
		         for (int i = 0; i < sourceMethods.length; i++) {
		
		             if (sourceMethods[i].getName().startsWith("get")) {
		
		                 String fieldName = sourceMethods[i].getName().substring(3) ;
		
		                 Object value = sourceMethods[i].invoke(baseBean,null);
		                 if(value==null){
		                	 value="";
		                 }else{
		                	 System.out.println(value);
		                 }
		                
		             }
		         }
	}
	
	
	@Test
	public void uploadExcelDateByDatabaseEmployees()
	{
		String[] fileDate={"1","张森锋","3508811990011041370","2013-10-30"};
		//String[] fileDate={" "," "," "};
		
		
		List<String> message=enterpriseEmployeesService.uploadExcelDateByDatabaseEmployees(fileDate, 18);
		if(message==null)return;
		for (String str : message) {
			System.out.println(str);
		}
		
		
		
	}

   @Test
   public void dateBefore()
   {
	   String date2="2013-02-20";
	   Date  Date1=DateUtil.StringToDate(date2, DateUtil.FORMAT_DATE);
	   
	  
	   
	   System.out.println(DateUtil.compareDateWithNow(Date1)==1);
	   
	   
   }
   @Test
   public void isExistSameToByEnterprise()
   {
	   String carNumber="352202198003129980";
	   String name="张芳芳";
	   
	   enterpriseEmployeesService.isExistSameToByEnterprise(name, carNumber, 18);
	   
	   
	   
	   
	   
	   
   }
   
   
   
   
}
