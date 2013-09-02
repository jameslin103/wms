package cn.fm.service.salary;

import java.io.File;
import java.util.List;

import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.service.base.DAO;

public interface EmployeesSalaryDetailService extends DAO<EmployeesSalaryDetail> {
	
	
	public List<EnterpriseEmployees>  uploadImportWageBudgetSummary(File file , String fiName,int number,int enterpriseId);

	public List<EmployeesSalaryDetail> saveTempEmployeesSalaryDetail(File file , String fileName,int number,int enterpriseId);


}
