package cn.fm.service.company;

import cn.fm.bean.company.EmployeesContract;
import cn.fm.service.base.DAO;

public interface EmployeesContractService extends DAO<EmployeesContract>{

	public void updateEmployeesContract(EmployeesContract employeesContract);

	
}
