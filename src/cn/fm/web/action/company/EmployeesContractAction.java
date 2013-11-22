package cn.fm.web.action.company;

import javax.annotation.Resource;

import cn.fm.bean.company.EmployeesContract;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.company.EmployeesContractService;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.web.action.BaseAction;

public class EmployeesContractAction extends BaseAction {

	@Resource
	private  EmployeesContractService   employeesContractService;
	@Resource
	private  EnterpriseEmployeesService enterpriseEmployeesService;
	
	
	private   EmployeesContract          employeesContract;
	private   EnterpriseEmployees        enterpriseEmployees;
	
	
	private   Integer             employeesId;
	private   Integer			  contractid;
	
	

	public String findContractJson()
	{
		System.out.println(contractid);
		employeesContract=employeesContractService.find(contractid);
		
		toJson(employeesContract);
		return SUCCESS;
	}
	
	

	public String updateEmployeesContract()
	{
		if(employeesContract==null)return SUCCESS;
		
		enterpriseEmployees=enterpriseEmployeesService.find(employeesId);
		employeesContract.setEnterpriseEmployees(enterpriseEmployees);
		employeesContractService.save(employeesContract);
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public EmployeesContract getEmployeesContract() {
		return employeesContract;
	}

	public void setEmployeesContract(EmployeesContract employeesContract) {
		this.employeesContract = employeesContract;
	}

	public EnterpriseEmployees getEnterpriseEmployees() {
		return enterpriseEmployees;
	}

	public void setEnterpriseEmployees(EnterpriseEmployees enterpriseEmployees) {
		this.enterpriseEmployees = enterpriseEmployees;
	}

	public Integer getEmployeesId() {
		return employeesId;
	}

	public void setEmployeesId(Integer employeesId) {
		this.employeesId = employeesId;
	}
	public Integer getContractid() {
		return contractid;
	}

	public void setContractid(Integer contractid) {
		this.contractid = contractid;
	}
	
	
}
