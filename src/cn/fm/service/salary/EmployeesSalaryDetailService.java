package cn.fm.service.salary;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;

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
	public void saveEmployeesSalaryDetail(File file , String fileName,int number,int readRows, EmployeesSalaryDetail employeesSalaryDetail);

	/**
	 * 获取当前企业的员工工资明细表
	 * @param enterpriseId
	 * @param employeesId
	 * @return
	 */
	public List<EmployeesSalaryDetail>  getAllEmployeesSalaryDetail(Integer enterpriseId,Integer employeesId);

	/**
	 * 统计预算工资
	 */
	public List<EmployeesSalaryDetail> findImportEmployeesSalaryDetailStatistics(Integer budgetId,Integer enterpriseId);
	/**
	 * 统计开票总额
	 * 
	 */
	public BigDecimal   getInvoiceTotal(Integer enterpriseId,Integer budgettableId);
	
	/**
	 * 统计工资总额（元）
	 */
	public BigDecimal getWageTotal(Integer enterpriseId,Integer budgettableId);
	
	/**
	 * 统计发放人数
	 */
	public Integer getNumberPersonlTotal(Integer enterpriseId,Integer budgettableId);
	
	/**
	 * 统计服务费总额
	 */
	public BigDecimal getServiceTotal(Integer enterpriseId,Integer budgettableId);
	
	/**
	 * 统计五险总额
	 */
	public BigDecimal getFiveInsuranceTotal(Integer enterpriseId,Integer budgettableId);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
