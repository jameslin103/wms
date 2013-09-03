package cn.fm.service.salary.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

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
		employeesSalaryDetailVO.setBonus(fileDate[2].toString()==null?null:new BigDecimal(fileDate[2]));
		employeesSalaryDetailVO.setSubsidies(fileDate[3].toString()==null?null:new BigDecimal(fileDate[3]));
		employeesSalaryDetailVO.setShouldPay(fileDate[4].toString()==null?null:new BigDecimal(fileDate[4]));
		employeesSalaryDetailVO.setSocialInsuranceBase(fileDate[5].toString()==null?null:new BigDecimal(fileDate[5]));
		employeesSalaryDetailVO.setEnterprisePensionInsurance(fileDate[6].toString()==null?null:new BigDecimal(fileDate[6]));
		employeesSalaryDetailVO.setPersonalPensionInsurance(fileDate[7].toString()==null?null:new BigDecimal(fileDate[7]));
		employeesSalaryDetailVO.setEnterpriseUnemploymentInsurance(fileDate[8].toString()==null?null:new BigDecimal(fileDate[8]));
		employeesSalaryDetailVO.setPersonalUnemploymentInsurance(fileDate[9].toString()==null?null:new BigDecimal(fileDate[9]));
		employeesSalaryDetailVO.setBirthInsuranceBase(fileDate[10].toString()==null?null:new BigDecimal(fileDate[10]));
		employeesSalaryDetailVO.setEnterpriseBirthInsurance(fileDate[11].toString()==null?null:new BigDecimal(fileDate[11]));
		employeesSalaryDetailVO.setInductrialInjuryBase(fileDate[12].toString()==null?null:new BigDecimal(fileDate[12]));
		employeesSalaryDetailVO.setEnterpriseInductrialInjuryBase(fileDate[13].toString()==null?null:new BigDecimal(fileDate[13]));
		employeesSalaryDetailVO.setMedicalPaymentBase(fileDate[14].toString()==null?null:new BigDecimal(fileDate[14]));
		employeesSalaryDetailVO.setEnterpriseMedicalBase(fileDate[15].toString()==null?null:new BigDecimal(fileDate[15]));
		employeesSalaryDetailVO.setPersonalMedicalBase(fileDate[16].toString()==null?null:new BigDecimal(fileDate[16]));
		employeesSalaryDetailVO.setHousingReserveBase(fileDate[17].toString()==null?null:new BigDecimal(fileDate[17]));
		employeesSalaryDetailVO.setEnterpriseReserveBase(fileDate[18].toString()==null?null:new BigDecimal(fileDate[18]));
		employeesSalaryDetailVO.setPersonalReserveBase(fileDate[19].toString()==null?null:new BigDecimal(fileDate[19]));
		employeesSalaryDetailVO.setMorbidityStatistics(fileDate[20].toString()==null?null:new BigDecimal(fileDate[20]));
		employeesSalaryDetailVO.setEnterpriseSubtotal(fileDate[21].toString()==null?null:new BigDecimal(fileDate[21]));
		employeesSalaryDetailVO.setPersonalSubtotal(fileDate[22].toString()==null?null:new BigDecimal(fileDate[22]));
		employeesSalaryDetailVO.setBeforeSalary(fileDate[23].toString()==null?null:new BigDecimal(fileDate[23]));
		employeesSalaryDetailVO.setEnterpriseTax(fileDate[24].toString()==null?null:new BigDecimal(fileDate[24]));
		employeesSalaryDetailVO.setPersonalTax(fileDate[25].toString()==null?null:new BigDecimal(fileDate[25]));
		employeesSalaryDetailVO.setServiceCharge(fileDate[26].toString()==null?null:new BigDecimal(fileDate[26]));
		employeesSalaryDetailVO.setAggregate(fileDate[27].toString()==null?null:new BigDecimal(fileDate[27]));
		employeesSalaryDetailVO.setMoneyToCards(fileDate[28].toString()==null?null:new BigDecimal(fileDate[28]));
		employeesSalaryDetailVO.setNote(fileDate[29].toString());
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
	
	@SuppressWarnings("unchecked")
	public List<EmployeesSalaryDetail>  getAllEmployeesSalaryDetail(Integer enterpriseId,Integer employeesId)
	{
		Query query=em.createQuery("select e from EmployeesSalaryDetail e where e.enterpriseId=?1 and e.empolyessId=?2");
		
		return query.setParameter(1, enterpriseId).setParameter(2, employeesId).getResultList();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
