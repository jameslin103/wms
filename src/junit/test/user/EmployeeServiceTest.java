package junit.test.user;


import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.fm.service.privilege.EmployeeService;



public class EmployeeServiceTest {
	private static EmployeeService employeeService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			ApplicationContext cxt = new ClassPathXmlApplicationContext("beans.xml");
			employeeService = (EmployeeService)cxt.getBean("employeeServiceImpl");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test public void exsit(){
		System.out.println(employeeService.exist("alax"));
	}
	
	@Test public void delete(){
		employeeService.delete("itcast");
	}
	
	@Test public void validate(){
		System.out.println(employeeService.validate("wms", "123456"));
	}
}
