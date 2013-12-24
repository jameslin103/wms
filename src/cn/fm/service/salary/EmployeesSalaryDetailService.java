package cn.fm.service.salary;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.service.base.DAO;

public interface EmployeesSalaryDetailService extends DAO<EmployeesSalaryDetail> {
	
	
	/**
	 * @version 1.0
	 * 批量上传员工工资基本工资信息
	 * @return enterpriseEmployeesIsExistVO
	 * @author jameslin
	 * @date   2013-09-01
	 */
	public List<String> saveEmployeesSalaryDetail(File file , String fileName,int number,int readRows, EmployeesSalaryDetail employeesSalaryDetail,Integer templateId,Integer enterpriseId);

	/**
	 * 获取当前企业的员工工资明细表
	 * @param enterpriseId
	 * @param employeesId
	 * @return
	 */
	public List<EmployeesSalaryDetail>  getAllEmployeesSalaryDetail(Integer enterpriseId,Integer budgettableId);

	/**
	 * 统计预算工资
	 */
	public List<EmployeesSalaryDetail> findImportEmployeesSalaryDetailStatistics(Integer budgetId,Integer enterpriseId);
	
	
	/**
	 * 统计工资总额（元）
	 */
	public BigDecimal getWageTotal(Integer enterpriseId, Integer budgettableId);
	
	/**
	 * 工伤生育补贴总额（元）
	 */
	public BigDecimal getspecialUnemploymentSubsidiesTotal(Integer enterpriseId, Integer budgettableId);

	/**
	 * 特殊养老保险补贴总额（元）
	 */
	public BigDecimal getspecialOldSubsidiesTotal(Integer enterpriseId, Integer budgettableId);
	
	/**
	 * 特殊医保补贴补贴总额（元）
	 */
	public BigDecimal getSpecialHealthCareSubsidiesTotal(Integer enterpriseId, Integer budgettableId);
	
	/**
	 * 特殊公积金补贴补贴总额（元）
	 */
	public BigDecimal getSpecialAccumulationFundSubsidiesTotal(Integer enterpriseId, Integer budgettableId);
	
	
	/**
	 * 统计企业小计总额（元）
	 */
	public BigDecimal getEnterpriseSubtotalTotal(Integer enterpriseId, Integer budgettableId);
	
	
	/**
	 * 统计发放人数
	 */
	public long getNumberPersonlTotal(Integer enterpriseId,Integer budgettableId);
	
	/**
	 * 统计服务费总额
	 */
	public BigDecimal getServiceTotal(Integer enterpriseId,Integer budgettableId);
	
	
	public void updateEmployeesSalaryDetail(EmployeesSalaryDetail employeesSalaryDetail);
	
	
	public void deleteEmployeesSalaryDetail(Integer budgetId);
	
	/**
	 * 统计特殊补贴五险一金
	 * @param enterpriseId
	 * @param budgettableId
	 * @return
	 */
	public BigDecimal  getfiveServiceTotal(Integer enterpriseId, Integer budgettableId);
	
	/**
	 * 计算五险一金规则
	 * @param wage
	 * @param tax
	 */
	public EmployeesSalaryDetail toCalculateFiveInsurances(EnterpriseEmployees enterprsieEmployees);

	
	public List<String> isExitUploadEnterpriseEmployees(List<EnterpriseEmployees> enterpriseEmployeesListPO,EmployeesSalaryDetail employeesSalaryDetailVO);

	
	public void updateEmployeesCarNumber(EnterpriseEmployees enterpriseEmployees);
	
	public EmployeesSalaryDetail structureEmployeesSalaryDetail(EmployeesSalaryDetail employeesSalaryDetail ,Integer templateId);
	
	public  List<EmployeesSalaryDetail>   getPayrollStaff(Integer budgetId);

	/**
	 * 查看发放人员情况
	 * @return
	 */
	public List<EmployeesSalaryDetail>  getBankEmployeesSalaryDetail(Integer budgetId);
	
	/**
	 * 统计民生人数
	 */
	public long getSumMingShengBank(Integer enterpriseId,Integer budgettableId);
	
	/**
	 * 统计现金人数
	 */
	public long getSumCashNumber(Integer enterpriseId,Integer budgettableId);
	
	/**
	 * 统计他行人数
	 */
	public long getSumHeLineBank(Integer enterpriseId,Integer budgettableId);
	
	
	public EmployeesSalaryDetail  getSumDateSalaryDeatil(CreateSalaryBudgetTable createSalaryBudgetTable);
}
