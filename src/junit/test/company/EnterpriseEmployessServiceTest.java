package junit.test.company;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import cn.fm.bean.company.EmployeesContract;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.company.EnterpriseService;
import cn.fm.utils.DateUtil;
import cn.fm.web.action.salary.CompanylistWithSalaryAction;
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
		Enterprise enterprise=new Enterprise();
		enterprise.setEnterpriseId(1);
		enterpriseEmployees.setEnterprise(enterprise);
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
	   String date2="2013-12-01";
	   //Date  Date1=DateUtil.StringToDate(date2, DateUtil.FORMAT_DATE);
	   
	   String date1="2013-10-31";
	   //Date   da=DateUtil.StringTypeToDate(date1, DateUtil.FORMAT_DATE);
	  // System.out.println(da);
	   
	   System.out.println(DateUtil.timeCompare(date2,date1));
	   
	   
   }

   @Test	
   public void temporaryBuildingEmployees()
   {
	   List<String[]> list=new ArrayList<String[]>();
	   
	   String[] file={"1","杨斌","352202198003130532","2013-05-1","2014-04-30","医保","社保","公积金","增员","2013-08-03","备注"};
	   String[] fileDate={"2","余哲彬","350681199108107013","2013-07-05","2013-7-15","医保","社保","公积金","续保","2013-08-03","备注"};
	   String[] fileDate1={"3","张吕文","350821199006211252","2013-07-05","2013-7-15","医保","社保","公积金","续保","2013-08-03","备注"};
	   list.add(file);
	   list.add(fileDate);
	   list.add(fileDate1);
	   
	   for (int i = 0; i < list.size(); i++)
	   {
		   String[] date=list.get(i);
		   EnterpriseEmployees  enterpriseEmployees=enterpriseEmployeesService.temporaryBuildingEmployees(date, 18);
		   
		   System.out.println(enterpriseEmployees.getEmployeesName());
		
	   }
	   
   }

   @Test
   public void getfileDate()
   {
	   Map<String, Integer> map=new HashMap<String, Integer>();
	  
	   
	   List<String>  list=new ArrayList<String>();
	   list.add(6+"");
	   list.add(8+"");
	   
	  for (String str : list) 
	  {
		  String aa=str;
		  if(list.size()==0){
			  System.out.println(str+"====");
		  }else{
			  System.out.println(list.get(0));
			  System.out.println(str+"else");
		  }
//		  if(str.==0){
//			  map.put("aa", Integer.parseInt(list.get(i)));
//		  }
//		  if(i==1){
//		  
//		   map.put("bb", Integer.parseInt(list.get(i)));
//		  }
//		  System.out.println(list.get(i));
	  }
	  for (Object obj : map.keySet()) 
	  {
		  Object key = obj;           
		  Object value = map.get(key);   
		  System.out.println(value);
	  }
   }
   
 
   
   @Test
   public void findRecutionState()
   {
	   
	   enterpriseEmployeesService.findRecutionState(18,2013,3);
   }
   @Test
   public void isExitSameEnterpriseEmployees()
   {
	   String[] employees={"关羽","33333333338888"};
	   List<EnterpriseEmployees> enterpriseEmployeeslist=new ArrayList<EnterpriseEmployees>();
	  String error=enterpriseEmployeesService.isExitSameEnterpriseEmployees(employees,enterpriseEmployeeslist);
	  System.out.println(error);
   }
   @Test
   public void getViewInsuranceWithMonthTotal(){
	   
	 List<Object[]>  list=enterpriseEmployeesService.getViewInsuranceWithMonthTotal(2013,1);
	 for (Object[] ob : list) {
		
		 System.out.println("状态\t"+"增员\t"+"参保\t"+"减员\t"+"月份\t");
		 System.out.println(ob[0]+"\t"+ob[1]+"\t"+ob[2]+"\t"+ob[3]+"\t"+ob[4]);
		 System.out.println("===============================");
	}
	System.out.println( DateUtil.getCurrentTime().substring(0, 4));
	 
	 
   }
   @Test
	public void viewCompanyListWithInsuranceAction()
	{
		
		CompanylistWithSalaryAction action=new CompanylistWithSalaryAction();
		action.setEnterpriseEmployeesService(enterpriseEmployeesService);
		action.viewCompanyListWithInsurance();
		
		
	}
   
   @Test
   public void deleteemployees()
   {
	   int[] ids={192,193};
//	   long rows=enterpriseEmployeesService.deleteEmployees(ids); 
//	   
//	   System.out.println(rows);
	   enterpriseEmployeesService.deleteEmployeesChecbox(ids);
	   
   }
   @Test
   public void update()
   {
	   EmployeesContract  employeesContract=new EmployeesContract();
	   
	   EnterpriseEmployees  enterpriseEmployeesPO=enterpriseEmployeesService.find(170);
	   enterpriseEmployeesService.clear();
	   enterpriseEmployeesPO.setAccumulationFund("是");
	   enterpriseEmployeesPO.setBank("交通银行");
	   employeesContract.setContractStatrDate(enterpriseEmployeesPO.getStartContractDeadline());
	   employeesContract.setContractEndDate(enterpriseEmployeesPO.getEndContractDeadline());
	   enterpriseEmployeesPO.addEmployeesContract(employeesContract);
	   
	   enterpriseEmployeesService.update(enterpriseEmployeesPO);
   }
   @Test
   public void duplicateData(){
	   
	   List<String> list= new ArrayList<String>();
	   String  file=" 1	, DNNNC001,	杨斌	,男	,福建	,是	,福建福州市,	是	,本科,	是	,3.52202E+17,	88888888888	,领导,	民生银行	,xxxx-xxxx-xxxx-xxxx	,2013/9/1	,2015/8/31	,30	,新增	是	,2015/8/31	,是	,是	,是	,65	,是	,1200	,1200	,1200	,1200	,1200	,50	,是";
	   String  file1=" 杨斌,  88888888888";
	   String  file2="韩运财, 88888888888";
	   String  file3=" 杨斌, 88888888888";
	   String  file4=" 1, DNNNC001,	杨斌	,男	,福建	,是	,福建福州市,	是	,本科,	是	,3.52202E+17,	88888888888	,领导,	民生银行	,xxxx-xxxx-xxxx-xxxx	,2013/9/1	,2015/8/31	,30	,新增	是	,2015/8/31	,是	,是	,是	,65	,是	,1200	,1200	,1200	,1200	,1200	,50	,是";

	   list.add(file);
	   list.add(file1);
	   list.add(file2);
	   list.add(file3);
	   list.add(file4);
	 
//	   for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )//外循环是循环的次数
//	   {
//	          for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {//内循环是 外循环一次比较的次数
//	               if  (list.get(j).equals(list.get(i)))  {
//	                  //list.remove(j);
//	                  System.out.println("重复"+list.get(i));
//	                } 
//	            } 
//	    } 
//	   
	   
	   
	   List<String>  s= enterpriseEmployeesService.duplicateData(list);
	   for (String string : s) {
		System.out.println(string);
	}
	   
	   
   }
  
}
