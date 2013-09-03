package cn.fm.service.company;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.service.base.DAO;

public interface EnterpriseEmployeesService extends DAO<EnterpriseEmployees>{
	/**
	 * 保存企业员工
	 */
	public void save(EnterpriseEmployees entity);
	
	/**
	 * 批量导入企业员工数据
	 * @param file
	 * @param fiName
	 */
	public void saveImportExcelEmployees(File file , String fiName,int number,int enterpriseId);
	/**
	 * 查看所有企业员工
	 * @return list
	 */
	public List<EnterpriseEmployees> getAllEnterpriseEmployees(Integer enterpriseId);
	
	public List<EnterpriseEmployees> getEnterpriseEmployeesSalaryDetail(Serializable entityId);
	
	public EnterpriseEmployees findEnterpriseEmployees(Integer employeesId);
	
	public List<EnterpriseEmployees> findInsuranceEnterpriseEmployees(Integer  insurance);
	
	public List<EnterpriseEmployees>  findAllEnterpriseEmployees(String employessName,Integer all);
	
	public List<EnterpriseEmployees> getExcelFiledDataList(EnterpriseEmployees enterpriseEmployees,int enterpriseId);
	
	public List<String> getExcelFiledNameList();
	

	
}
