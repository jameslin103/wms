
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
	public List<String> saveImportExcelEmployees(File file , String fiName,int number,int readRow,Enterprise enterprise);
	/**
	 * 查看所有企业员工
	 * @return list
	 */
	public List<EnterpriseEmployees> getAllEnterpriseEmployees(Integer enterpriseId);
	
	public List<EnterpriseEmployees> getEnterpriseEmployeesSalaryDetail(Serializable entityId);
	
	public EnterpriseEmployees findEnterpriseEmployees(Integer employeesId);
	/**
	 * 查看参保人员与未参保人员
	 * @param insurance
	 * @param enterpriseId
	 * @return
	 */
	public List<EnterpriseEmployees> findInsuranceEnterpriseEmployees(Integer  insurance,Integer enterpriseId);
	
	public List<EnterpriseEmployees>  findAllEnterpriseEmployees(String employessName,Integer all,Integer enterpriseId);
	
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
	 * 统计减员
	 * @param enterpriseId
	 * @return
	 */
	public long reductionTotal(Integer enterpriseId);
	
	
	/**
	 * 查询增员，减员，续保
	 * @param enterpriseId
	 * @return
	 */
	public List<EnterpriseEmployees>  findWorkersIncreasedToEmployees(Integer enterpriseId);
	
	/**
	 * 筛选新增或者续保，减员人员
	 * @param enterpriseId
	 * @param type
	 * @return
	 */
	public List<EnterpriseEmployees>    findNewStaffAndRenewalEmployees(Integer enterpriseId,String type);
	
	/**
	 * 批量上传参保；减员
	 * @param file
	 * @param fiName
	 * @param number
	 * @param readRow
	 * @return
	 */
	public List<String> batchIncreaseEmployees(File file , String fiName,int number,int readRow,Integer enterpriseId);
	
	/**
	 * 更新实体
	 * @param enterpriseEmployees
	 * @return
	 */
	public boolean updateEnterpriseEmployees(EnterpriseEmployees enterpriseEmployees);
	 /**
	 * 查询隐藏员工
	 * @return
	 */
    public List<EnterpriseEmployees>  findEmployeesHidden(Integer enterpriseId);
    
	
	/**
	 * 查询离职员工
	 * @return
	 */
    public List<EnterpriseEmployees>  findEmployeesDeparture(Integer enterpriseId);
	
    /**
	 * 查看统计企业增减员参保明细表
	 * @return
	 */

	public List<EnterpriseEmployees> getInsuranceWithEmployeeList(Integer enterpriseId,Integer year,Integer month);

	
	/**
	 * 批量上传减员
	 * @param file
	 * @param string
	 * @param number
	 * @param rows
	 */
	public List<String> uploadExcelByInsuranceReduction(File file, String fileName, Integer number, Integer readRow,Integer enterpriseId);
	
	
	
	/**
	 * 上传的exce减员数据与数据库人员进行匹配
	 * @param fileDate
	 * @param enterpriseId
	 * @return 存在提示信息(isExistingEmployees)
	 */
    public List<String>  uploadExcelDateByDatabaseEmployees(String[] fileDate,Integer enterpriseId);
	
    /**
	 * 
	 * @return 所有企业员工
	 */
	public List<EnterpriseEmployees> getAllEnterpriseEmployees();
	
	/**
	 * 封装excel上传的数据
	 * @param fileDate
	 * @param employeesId
	 * @return
	 */
   

	
	public EnterpriseEmployees temporaryBuildingEmployees(String[] fileDate,Integer employeesId );

	
	/**
	 * 判断是否未离职的员工；重复上传数据
	 * @param employees
	 * @return
	 */
	
	public  String isExitSameEnterpriseEmployees(String[] fileDate,List<EnterpriseEmployees> enterpriseEmployeeslist);
	
	/**
	 * 查看增减员的执行状态
	 * @param enterpriseId
	 * @return
	 */
	public List<EnterpriseEmployees> findRecutionState(Integer enterpriseId,Integer month,Integer year);
	
	/**
	 * 修改增减员状态
	 * @param enterpriseId
	 * @param recutionState
	 */
	public void updateRecutionState(Integer enterpriseId,Integer recutionState,String reductionNote,Integer month,Integer year);
	
	
	
	/**
	 * 统计，增员，减员，参保人员
	 * @param year
	 * @return
	 */
	public List<Object[]>     getViewInsuranceWithMonthTotal(Integer year,Integer enterpriseId);
	
	public EnterpriseEmployees findId(Integer employeesId);
	
	
	public long deleteEmployees(Serializable ... ids);
	
	public long deleteEmployeesChecbox(Serializable ... ids);
	
	/**
	 * 判断是否上传重复数据
	 * @return
	 */
	public List<String> duplicateData(List list);
	
	
}
