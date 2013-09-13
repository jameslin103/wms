package cn.fm.web.action.ireport;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.utils.WMSResource;
import cn.fm.web.action.ReportAction;

@SuppressWarnings("serial")
public class InsuranceEmployeesReportAction extends ReportAction {
	@Resource
	private EnterpriseEmployeesService  enterpriseEmployeesService;

	
	
	
	
	
	
	
	
	
	
	public void setEnterpriseEmployeesService(
			EnterpriseEmployeesService enterpriseEmployeesService) {
		this.enterpriseEmployeesService = enterpriseEmployeesService;
	}

	public String downloadInsuranceWithEmployeeList()
	{
		
		List<EnterpriseEmployees> employeesList=enterpriseEmployeesService.getAllEnterpriseEmployees(16);
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("reportTitle", "我的第一个程序"); 
		 //String currentPath = ServletActionContext.getServletContext().getRealPath("");
		 //String sqlJasper= currentPath+"/report/insurance_with_employee_list.jrxml";
		 //String sqlXmlFile= WMSResource.getResourcesRootPath()+"/report/insurance_with_employee_list.jrxml";
		String sqlJasper="salary-with-bank-detail.jasper";
		//String sqlJasper="plan_schedule_history_list.jasper";
		//String sqlXmlFile="insurance_with_employee_list.jrxml";
		try {
			downloadExcel(sqlJasper, "xxxxxx", parameters,employeesList);
			//JasperHelperAction  jha=new JasperHelperAction();
			//jha.exportmain(jha.EXCEL_TYPE,sqlJasper,employeesList,"xxxxx");
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

}
