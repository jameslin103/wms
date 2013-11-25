package cn.fm.service.company.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.EmployeesContract;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EmployeesContractService;


@Transactional @Service
public class EmployeesContractServiceImpl extends DaoSupport<EmployeesContract>implements EmployeesContractService {
	
	
	public void updateEmployeesContract(EmployeesContract employeesContract){
		em.createQuery("update EmployeesContract e set e.contractStatrDate=?1,e.contractEndDate=?2 where e.contractid=?3")
					  .setParameter(1, employeesContract.getContractStatrDate()).setParameter(2, employeesContract.getContractEndDate())
					  .setParameter(3, employeesContract.getContractid()).executeUpdate();
	}

}
