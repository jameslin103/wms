package cn.fm.service.salary.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.company.EnterpriseEmployeesService;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.utils.GenerateSqlFromExcel;
import cn.fm.utils.StringUtil;

@Service @Transactional
public class EmployeesSalaryDetailServiceImpl extends DaoSupport<EmployeesSalaryDetail> implements	EmployeesSalaryDetailService {

	
	/**
	 * @version 1.0
	 * 批量上传员工工资基本工资信息
	 * @return enterpriseEmployeesIsExistVO
	 * @author jameslin
	 * @date   2013-09-01
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public List<EnterpriseEmployees>   uploadImportWageBudgetSummary(File file , String fileName,int number,int enterpriseId)
	{
	
		if(enterpriseId==0)return null;
		List<EnterpriseEmployees>   enterpriseEmployeesIsExistVO=null;
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		try {
			List<String[]> arrayList=excel.generateStationBugSql(file,fileName,number);
			if(arrayList.size()==0)return null;
			for (int i = 0; i < arrayList.size(); i++) {
				String[] data = arrayList.get(i);
				if(!StringUtil.isEmpty(data[0].toString())){
					if(IsExistRepeatEmployees(data[0].toString(),enterpriseId).size()>0){
						enterpriseEmployeesIsExistVO=IsExistRepeatEmployees(data[0].toString(),enterpriseId);
					}
				}
			}	
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		
		return enterpriseEmployeesIsExistVO;
	}
	/**
	 * 临时保存EmployeesSalaryDetail
	 * @param employeesSalaryDetail
	 * @return employeesSalaryDetailListVO
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public List<EmployeesSalaryDetail> saveTempEmployeesSalaryDetail(File file , String fileName,int number,int enterpriseId)
	{
		List<EmployeesSalaryDetail> employeesSalaryDetailListVO=new ArrayList<EmployeesSalaryDetail>();

		if(enterpriseId==0)return null;
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		try {
			List<String[]> arrayList=excel.generateStationBugSql(file,fileName,number);
			if(arrayList.size()==0)return null;
			for (int i = 0; i < arrayList.size(); i++) {
				String[] data = arrayList.get(i);
				employeesSalaryDetailListVO.add(structureEmployeesSalaryDetail(data));	
			}	
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return employeesSalaryDetailListVO;
		
	}

	
	/**
	 * @author jameslin
	 * @date 2013-09-01
	 * @return  EmployeesSalaryDetail
	 * 从excel导入的数据重新构造数据
	 */
	public EmployeesSalaryDetail structureEmployeesSalaryDetail(String[] fileDate)
	{
		EmployeesSalaryDetail   employeesSalaryDetailVO=new EmployeesSalaryDetail();
		employeesSalaryDetailVO.setEmployeesName(fileDate[0].toString());
		employeesSalaryDetailVO.setWage(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setBonus(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setSubsidies(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setShouldPay(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setSocialInsuranceBase(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setEnterprisePensionInsurance(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setPersonalPensionInsurance(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setEnterpriseUnemploymentInsurance(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setPersonalUnemploymentInsurance(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setBirthInsuranceBase(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setEnterpriseBirthInsurance(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setInductrialInjuryBase(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setEnterpriseInductrialInjuryBase(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setMedicalPaymentBase(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setEnterpriseMedicalBase(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setPersonalMedicalBase(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setHousingReserveBase(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setEnterpriseReserveBase(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setPersonalReserveBase(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setMorbidityStatistics(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setSubtotal(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setEnterpriseSubtotal(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setPersonalSubtotal(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setBeforeSalary(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setTax(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setEnterpriseTax(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setPersonalTax(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setServiceCharge(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setAggregate(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setMoneyToCards(fileDate[1].toString()==null?null:new BigDecimal(fileDate[1]));
		employeesSalaryDetailVO.setNote(fileDate[0].toString());
		return employeesSalaryDetailVO;
	}
	
	/**
	 * 
	 * @param employeesName
	 * @param enterpriseId
	 * @return EnterpriseEmployees实体类
	 * @date   2013-09-01
	 * 判断是否存在重复的名字
	 */

	@SuppressWarnings("null")
	public List<EnterpriseEmployees>  IsExistRepeatEmployees(String employeesName,Integer enterpriseId)
	{
		List<EnterpriseEmployees>   enterpriseEmployeesIsExistVO=new ArrayList<EnterpriseEmployees>();
		EnterpriseEmployeesService  enterpriseEmployeesService = null;
		List<EnterpriseEmployees> enterpriseEmployeesList=enterpriseEmployeesService.getAllEnterpriseEmployees(enterpriseId);
		for (EnterpriseEmployees enterpriseEmployees : enterpriseEmployeesList)
		{
			if(!StringUtil.isEmpty(employeesName))
			{
				if(enterpriseEmployees.getEmployeesName().equals(employeesName)){
					if(StringUtil.isEmpty(enterpriseEmployees.getCardNumber())){
						enterpriseEmployeesIsExistVO.add(enterpriseEmployees);
					}
				}
			}
		}
	
		return enterpriseEmployeesIsExistVO;
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
