
package cn.fm.service.company;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import cn.fm.bean.company.Enterprise;
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
	public void saveImportExcelEmployees(File file , String fiName,int number,int readRow,Enterprise enterprise);
	/**
	 * 查看所有企业员工
	 * @return list
	 */
	public List<EnterpriseEmployees> getAllEnterpriseEmployees(Integer enterpriseId);
	
	public List<EnterpriseEmployees> getEnterpriseEmployeesSalaryDetail(Serializable entityId);
	
	public EnterpriseEmployees findEnterpriseEmployees(Integer employeesId);
	
	public List<EnterpriseEmployees> findInsuranceEnterpriseEmployees(Integer  insurance);
	
	public List<EnterpriseEmployees>  findAllEnterpriseEmployees(String employessName,Integer all,Integer enterpriseId);
	
	public List<EnterpriseEmployees> getExcelFiledDataList(EnterpriseEmployees enterpriseEmployees,int enterpriseId);
	
	public List<String> getExcelFiledNameList();
	
	
	/**
	 * 统计新增人数
	 * @param enterpriseId
	 * @return
	 */
	public long newStaffCount(Integer enterpriseId);
	
	/**
	 * 统计续保人数
	 * @param enterpriseId
	 * @return
	 */
	public long renewalPersonnel(Integer enterpriseId);
	
	/**
	 * 统计参保人数
	 * @param enterpriseId
	 * @return
	 */
	public long ginsengPersonnel(Integer enterpriseId);
	
	/**
	 * 查询增员，减员，续保
	 * @param enterpriseId
	 * @return
	 */
	public List<EnterpriseEmployees>  findWorkersIncreasedToEmployees(Integer enterpriseId);
	
	/**
	 * 帅选新增或者续保，减员人员
	 * @param enterpriseId
	 * @param type
	 * @return
	 */
	public List<EnterpriseEmployees>    findNewStaffAndRenewalEmployees(Integer enterpriseId,String type);
	
	public boolean batchIncreaseEmployees(File file , String fiName,int number,int readRow);
}
