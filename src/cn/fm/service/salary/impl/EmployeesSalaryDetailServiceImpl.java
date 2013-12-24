package cn.fm.service.salary.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.company.InsurancesBaseSettings;
import cn.fm.bean.company.InsurancesTax;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.bean.salary.TaxOfPerson;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.utils.Constant;
import cn.fm.utils.GenerateSqlFromExcel;
import cn.fm.utils.PersonalTaxUtil;
import cn.fm.utils.StringUtil;

@Service   @Transactional
public class EmployeesSalaryDetailServiceImpl extends DaoSupport<EmployeesSalaryDetail> implements EmployeesSalaryDetailService {

	/**
	 * @version 1.0 批量上传员工工资基本工资信息
	 * @return enterpriseEmployeesIsExistVO
	 * @author jameslin
	 * @date 2013-09-01
	 */
	@SuppressWarnings( { "unchecked", "static-access" })
	public List<String> saveEmployeesSalaryDetail(File file, String fileName,int number, 
			int readRows,EmployeesSalaryDetail employeesSalaryDetail, Integer templateId,Integer enterpriseId) {
		List<String> employeesName = new ArrayList<String>();
		List<EmployeesSalaryDetail> detailList = new ArrayList<EmployeesSalaryDetail>();
		List<EnterpriseEmployees> enterpriseEmployeesListPO = getAllEnterpriseEmployees(enterpriseId);
		if (employeesSalaryDetail == null|| employeesSalaryDetail.getEnterprise() == null|| employeesSalaryDetail.getEnterprise().getEnterpriseId() == null)
			return null;
		GenerateSqlFromExcel excel = new GenerateSqlFromExcel();
		try {
			List<String[]> arrayList = excel.generateStationBugSql(file,
					fileName, number, readRows);
			if (arrayList.size() == 0)
				return null;
			if (arrayList != null && arrayList.size() > 0) {
				employeesName = duplicateData(arrayList);
			}
			for (int i = 0; i < arrayList.size(); i++) {
				String[] data = arrayList.get(i);

				// 匹配姓名是否重复
				EmployeesSalaryDetail detail = refactoringEmployeesSalaryDetailDate(data);
				employeesName = isExitUploadEnterpriseEmployees(enterpriseEmployeesListPO, detail);

				if (employeesName.size() == 0) {
					// 记录封装每个字段
					EmployeesSalaryDetail employeesSalaryDetailBySalaryDetail = recordEnterpriseEmployeesBySalaryDetail(enterpriseEmployeesListPO, data, number);
					// 计算工资
					EmployeesSalaryDetail employeesSalaryDetailVO = structureEmployeesSalaryDetail(employeesSalaryDetailBySalaryDetail, templateId);
					employeesSalaryDetailVO.setSalaryDate(employeesSalaryDetail.getSalaryDate());
					employeesSalaryDetailVO.setEnterprise(employeesSalaryDetail.getEnterprise());
					employeesSalaryDetailVO.setBudgettableId(employeesSalaryDetail.getBudgettableId());detailList.add(employeesSalaryDetailVO);
				}

			}
			if (employeesName.size() == 0) {
				saveEmployeesSalaryDetail(detailList,employeesSalaryDetail);
			}

		} catch (Exception e) {
			e.printStackTrace();
			employeesName.add("文件出错!!");

		}
		return employeesName;

	}

	/**
	 * 判断是否上传重复数据
	 * 
	 * @return
	 */
	public List<String> duplicateData(List list) {

		if (list == null || list.size() == 0)
			return null;
		List<String> duplicateData = null;
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).equals(list.get(i))) {
					duplicateData = new ArrayList<String>();
					duplicateData.add("存在" + list.get(i) + "");
				}
			}
		}
		return duplicateData;

	}

	/**
	 * 保存数据到数据库
	 * 
	 * @param employeesSalaryDetailList
	 */
	public void saveEmployeesSalaryDetail(List<EmployeesSalaryDetail> employeesSalaryDetailList,EmployeesSalaryDetail employeesSalaryDetail) {
		if (employeesSalaryDetailList == null)
			return;
		EmployeesSalaryDetail employeesSalaryDetailVO = null;
		for (EmployeesSalaryDetail detail : employeesSalaryDetailList) {
			if (StringUtil.isEmpty(detail.getCardNumber())|| StringUtil.isEmpty(detail.getEmployeesName()))
				continue;
			employeesSalaryDetailVO = new EmployeesSalaryDetail();
			employeesSalaryDetailVO.setAggregate(detail.getAggregate());
			employeesSalaryDetailVO.setSubsidies(detail.getSubsidies());
			employeesSalaryDetailVO.setShouldPay(detail.getShouldPay());
			employeesSalaryDetailVO.setBonus(detail.getBonus());
			employeesSalaryDetailVO.setMorbidityStatistics(detail.getMorbidityStatistics());
			employeesSalaryDetailVO.setEmployeesName(detail.getEmployeesName());
			employeesSalaryDetailVO.setCardNumber(detail.getCardNumber());
			employeesSalaryDetailVO.setNote(detail.getNote());
			employeesSalaryDetailVO.setServiceCharge(detail.getServiceCharge());
			employeesSalaryDetailVO.setBankCardNumber(detail.getBankCardNumber());
			employeesSalaryDetailVO.setSocialInsuranceBase(detail.getSocialInsuranceBase());
			employeesSalaryDetailVO.setEnterprisePensionInsurance(detail.getEnterprisePensionInsurance());
			employeesSalaryDetailVO.setPersonalPensionInsurance(detail.getPersonalPensionInsurance());
			employeesSalaryDetailVO.setEnterpriseUnemploymentInsurance(detail.getEnterpriseUnemploymentInsurance());
			employeesSalaryDetailVO.setPersonalUnemploymentInsurance(detail.getPersonalUnemploymentInsurance());
			employeesSalaryDetailVO.setBirthInsuranceBase(detail.getBirthInsuranceBase());
			employeesSalaryDetailVO.setEnterpriseBirthInsurance(detail.getEnterpriseBirthInsurance());
			employeesSalaryDetailVO.setInductrialInjuryBase(detail.getInductrialInjuryBase());
			employeesSalaryDetailVO.setEnterpriseInductrialInjuryBase(detail.getEnterpriseInductrialInjuryBase());
			employeesSalaryDetailVO.setMedicalPaymentBase(detail.getMedicalPaymentBase());
			employeesSalaryDetailVO.setEnterpriseMedicalBase(detail.getEnterpriseMedicalBase());
			employeesSalaryDetailVO.setPersonalMedicalBase(detail.getPersonalMedicalBase());
			employeesSalaryDetailVO.setHousingReserveBase(detail.getHousingReserveBase());
			employeesSalaryDetailVO.setEnterpriseReserveBase(detail.getEnterpriseReserveBase());
			employeesSalaryDetailVO.setPersonalReserveBase(detail.getPersonalReserveBase());
			employeesSalaryDetailVO.setEnterprise(detail.getEnterprise());
			employeesSalaryDetailVO.setSalaryDate(detail.getSalaryDate());
			employeesSalaryDetailVO.setWage(detail.getWage());
			employeesSalaryDetailVO.setPersonalSubtotal(detail.getPersonalSubtotal());
			employeesSalaryDetailVO.setEnterpriseTax(detail.getEnterpriseTax());
			employeesSalaryDetailVO.setMoneyToCards(detail.getMoneyToCards());
			employeesSalaryDetailVO.setEnterpriseSubtotal(detail.getEnterpriseSubtotal());
			employeesSalaryDetailVO.setBudgettableId(detail.getBudgettableId());
			employeesSalaryDetailVO.setEnterpriseEmployees(detail.getEnterpriseEmployees());
			employeesSalaryDetailVO.setBeforeSalary(detail.getBeforeSalary());
			employeesSalaryDetailVO.setCreateDate(detail.getCreateDate());
			employeesSalaryDetailVO.setPersonalTax(detail.getPersonalTax());
			employeesSalaryDetailVO.setBank(detail.getBank().trim());
			employeesSalaryDetailVO.setAccident(detail.getAccident());
			
			employeesSalaryDetailVO.setSpecialOldSubsidies(detail.getSpecialOldSubsidies());
			employeesSalaryDetailVO.setSpecialUnemploymentSubsidies(detail.getSpecialUnemploymentSubsidies());
			employeesSalaryDetailVO.setSpecialHealthCareSubsidies(detail.getSpecialHealthCareSubsidies());
			employeesSalaryDetailVO.setSpecialAccumulationFundSubsidies(detail.getSpecialAccumulationFundSubsidies());
			employeesSalaryDetailVO.setCreateSalaryBudgetTable(employeesSalaryDetail.getCreateSalaryBudgetTable());
			super.save(employeesSalaryDetailVO);
		}

	}

	/**
	 * 重构导入的数据进行封装EmployeesSalaryDetail类
	 * 
	 * @return EmployeesSalaryDetail 2013-10-13
	 */
	public EmployeesSalaryDetail refactoringEmployeesSalaryDetailDate(
			String[] fileDate) {
		EmployeesSalaryDetail salaryDetailExcelDate = new EmployeesSalaryDetail();

		salaryDetailExcelDate.setEmployeesName(fileDate[1] == null ? ""
				: fileDate[1].toString());
		salaryDetailExcelDate.setCardNumber(fileDate[2] == null ? ""
				: fileDate[2].toString());

		return salaryDetailExcelDate;
	}

	/**
	 * @author jameslin
	 * @date 2013-09-01
	 * @return EmployeesSalaryDetail 计算工资，
	 */
	public EmployeesSalaryDetail structureEmployeesSalaryDetail(EmployeesSalaryDetail employeesSalaryDetail, Integer templateId) {
		BigDecimal subsidies =new BigDecimal("0.00"); // 补贴
		BigDecimal wage=new BigDecimal("0.00"); // 基本工资
		BigDecimal enterpriseSubtotal=new BigDecimal("0.00"); // 企业小计
		BigDecimal personalSubtotal=new BigDecimal("0.00");// 个人小计
		BigDecimal beforeSalary=new BigDecimal("0.00"); // 税前工资
		BigDecimal shouldPayTotal=new BigDecimal("0.00"); // 应发工资
		BigDecimal aggregate=new BigDecimal("0.00"); // 合计(企业应付)
		//BigDecimal fiveBaseTotal  =new BigDecimal("0.00"); // 五险一金总基数
		BigDecimal personalTax = new BigDecimal("0.00"); // 个税

		EmployeesSalaryDetail employeesSalaryDetailVO = new EmployeesSalaryDetail();
		try {

			/* 基本工资 */
			wage  =employeesSalaryDetail.getWage() == null ? new BigDecimal("0.00"): employeesSalaryDetail.getWage();
			employeesSalaryDetailVO.setWage(wage.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			// 应发工资=奖金+补贴+工资
			BigDecimal bonus =employeesSalaryDetail.getBonus() == null ? new BigDecimal("0.00"): employeesSalaryDetail.getBonus();
			subsidies = employeesSalaryDetail.getSubsidies() == null ? new BigDecimal("0.00"): employeesSalaryDetail.getSubsidies();

			shouldPayTotal = wage.add(subsidies).add(bonus);

			employeesSalaryDetailVO.setSubsidies(subsidies.setScale(2, BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setShouldPay(shouldPayTotal.setScale(2, BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setBonus(bonus.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setMorbidityStatistics(employeesSalaryDetail.getMorbidityStatistics());
			employeesSalaryDetailVO.setEmployeesName(employeesSalaryDetail.getEmployeesName());
			employeesSalaryDetailVO.setCardNumber(employeesSalaryDetail.getCardNumber());
			employeesSalaryDetailVO.setEnterpriseEmployees(employeesSalaryDetail.getEnterpriseEmployees());
			employeesSalaryDetailVO.setNote(employeesSalaryDetail.getNote());
			employeesSalaryDetailVO.setBank(employeesSalaryDetail.getBank());
			employeesSalaryDetailVO.setServiceCharge(employeesSalaryDetail.getServiceCharge());
			employeesSalaryDetailVO.setBankCardNumber(employeesSalaryDetail.getBankCardNumber());
			employeesSalaryDetailVO.setAccident(employeesSalaryDetail.getAccident());

			// 计算五险一金规则
			SalaryTemplate salaryTemplate = getIsFiveBase(templateId);
			EmployeesSalaryDetail employeesSalaryDetailInsurances = new EmployeesSalaryDetail();

			if (salaryTemplate != null && salaryTemplate.getStatus() == 1 && salaryTemplate.getFiveInsurances() == 1) {
				// 基数计算所得数
				employeesSalaryDetailInsurances = toCalculateFiveInsurances(employeesSalaryDetail.getEnterpriseEmployees());
				employeesSalaryDetailVO.setSocialInsuranceBase(employeesSalaryDetailInsurances.getSocialInsuranceBase());
				employeesSalaryDetailVO.setEnterprisePensionInsurance(employeesSalaryDetailInsurances.getEnterprisePensionInsurance());
				employeesSalaryDetailVO.setPersonalPensionInsurance(employeesSalaryDetailInsurances.getPersonalPensionInsurance());
				employeesSalaryDetailVO.setEnterpriseUnemploymentInsurance(employeesSalaryDetailInsurances.getEnterpriseUnemploymentInsurance());
				employeesSalaryDetailVO.setPersonalUnemploymentInsurance(employeesSalaryDetailInsurances.getPersonalUnemploymentInsurance());
				employeesSalaryDetailVO.setBirthInsuranceBase(employeesSalaryDetailInsurances.getBirthInsuranceBase());
				employeesSalaryDetailVO.setEnterpriseBirthInsurance(employeesSalaryDetailInsurances.getEnterpriseBirthInsurance());
				employeesSalaryDetailVO.setInductrialInjuryBase(employeesSalaryDetailInsurances.getInductrialInjuryBase());
				employeesSalaryDetailVO.setEnterpriseInductrialInjuryBase(employeesSalaryDetailInsurances.getEnterpriseInductrialInjuryBase());
				employeesSalaryDetailVO.setMedicalPaymentBase(employeesSalaryDetailInsurances.getMedicalPaymentBase());
				employeesSalaryDetailVO.setEnterpriseMedicalBase(employeesSalaryDetailInsurances.getEnterpriseMedicalBase());
				employeesSalaryDetailVO.setPersonalMedicalBase(employeesSalaryDetailInsurances.getPersonalMedicalBase());
				employeesSalaryDetailVO.setHousingReserveBase(employeesSalaryDetailInsurances.getHousingReserveBase());
				employeesSalaryDetailVO.setEnterpriseReserveBase(employeesSalaryDetailInsurances.getEnterpriseReserveBase());
				employeesSalaryDetailVO.setPersonalReserveBase(employeesSalaryDetailInsurances.getPersonalReserveBase());
				
				employeesSalaryDetailVO.setSpecialOldSubsidies(employeesSalaryDetailInsurances.getSpecialOldSubsidies());
				employeesSalaryDetailVO.setSpecialUnemploymentSubsidies(employeesSalaryDetailInsurances.getSpecialUnemploymentSubsidies());
				employeesSalaryDetailVO.setSpecialHealthCareSubsidies(employeesSalaryDetailInsurances.getSpecialHealthCareSubsidies());
				employeesSalaryDetailVO.setSpecialAccumulationFundSubsidies(employeesSalaryDetailInsurances.getSpecialAccumulationFundSubsidies());
			} else {

				// TODO: handle exception 如果不包含个税计算方式

				
				employeesSalaryDetailInsurances= toCalculateFiveInsurances(employeesSalaryDetail.getEnterpriseEmployees());
				
				employeesSalaryDetailVO.setSpecialOldSubsidies(employeesSalaryDetailInsurances.getSpecialOldSubsidies());
				employeesSalaryDetailVO.setSpecialUnemploymentSubsidies(employeesSalaryDetailInsurances.getSpecialUnemploymentSubsidies());
				employeesSalaryDetailVO.setSpecialHealthCareSubsidies(employeesSalaryDetailInsurances.getSpecialHealthCareSubsidies());
				employeesSalaryDetailVO.setSpecialAccumulationFundSubsidies(employeesSalaryDetailInsurances.getSpecialAccumulationFundSubsidies());
				

				// *社会保险基数*/
				employeesSalaryDetailVO.setSocialInsuranceBase(employeesSalaryDetailInsurances.getSocialInsuranceBase());

				// 生育保险基数
				employeesSalaryDetailVO.setBirthInsuranceBase(employeesSalaryDetailInsurances.getBirthInsuranceBase());

				// 工伤基数
				employeesSalaryDetailVO.setInductrialInjuryBase(employeesSalaryDetailInsurances.getInductrialInjuryBase());

				// 基本医疗保险 缴费基数
				employeesSalaryDetailVO.setMedicalPaymentBase(employeesSalaryDetailInsurances.getMedicalPaymentBase());

				// 住房公积金-缴费基数
				employeesSalaryDetailVO.setHousingReserveBase(employeesSalaryDetailInsurances.getHousingReserveBase());
				

				
				
			}

			// TODO: handle exception

			// 企业小计=(企业)养老保险+(企业)失业保险+生育（企业）+工伤(企业)+(企业)基本医疗保险+住房公积金(企业)+大病统筹
			enterpriseSubtotal = enterpriseSubtotal(employeesSalaryDetailInsurances, (employeesSalaryDetail.getMorbidityStatistics() == null ? 
							new BigDecimal("0.00") : employeesSalaryDetail.getMorbidityStatistics()));
			employeesSalaryDetailVO.setEnterpriseSubtotal(enterpriseSubtotal.setScale(2, BigDecimal.ROUND_HALF_DOWN));

			// 个人小计=(个人)养老保险+(个人)失业保险+(个人)医疗保险+(个人)住房公积金
			personalSubtotal = personalSubtotal(employeesSalaryDetailInsurances);
			employeesSalaryDetailVO.setPersonalSubtotal(personalSubtotal.setScale(2, BigDecimal.ROUND_HALF_DOWN));

			// 税前工资=应发工资-(个人)小计
			BigDecimal specialOldSubsidies=employeesSalaryDetailVO.getSpecialOldSubsidies()
											.add(employeesSalaryDetailVO.getSpecialUnemploymentSubsidies())
											.add(employeesSalaryDetailVO.getSpecialHealthCareSubsidies())
											.add(employeesSalaryDetailVO.getSpecialAccumulationFundSubsidies()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
											
			beforeSalary = shouldPayTotal.subtract(personalSubtotal).add(specialOldSubsidies);
			
			
			employeesSalaryDetailVO.setBeforeSalary(beforeSalary.setScale(2,BigDecimal.ROUND_HALF_DOWN));

			// 合计(企业应付)=应发工资+(企业)小计+服务费+意外险+特殊补贴
			aggregate = shouldPayTotal.add(employeesSalaryDetail.getServiceCharge() == null ? 
						new BigDecimal("0.00"): employeesSalaryDetail.getServiceCharge())
						.add(enterpriseSubtotal)
					    .add(employeesSalaryDetailVO.getAccident()==null?
					    new BigDecimal("0.00"):employeesSalaryDetailVO.getAccident())
					    .add(specialOldSubsidies);
						
						
			
			employeesSalaryDetailVO.setAggregate(aggregate.setScale(2, BigDecimal.ROUND_HALF_DOWN));


			personalTax = personalTax(Double.valueOf(beforeSalary.doubleValue()));
			// 个税
			employeesSalaryDetailVO.setPersonalTax(personalTax == null ? new BigDecimal(0.00): personalTax.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			// 企业个税
			employeesSalaryDetailVO.setEnterpriseTax(new BigDecimal(0.00));

			// 到卡金额=税前工资-个人税
			BigDecimal moneyToCards = beforeSalary.subtract(personalTax);
			employeesSalaryDetailVO.setMoneyToCards(moneyToCards.setScale(2,BigDecimal.ROUND_HALF_DOWN));

		} catch (Exception e) {
			e.printStackTrace();
		}

		return employeesSalaryDetailVO;
	}

	/**
	 * 企业小计 计算方法
	 * 
	 * @param employeesSalaryDetailInsurances
	 * @return enterpriseSubtotal
	 *         //企业小计=(企业)养老保险+(企业)失业保险+(企业)工伤+(企业)生育+(企业)医疗保险+大病统筹+(企业)住房公积金+(企业)养老失业补贴+(企业)工伤生育补贴+(企业)医保补贴)
	 */
	public BigDecimal enterpriseSubtotal(EmployeesSalaryDetail employeesSalaryDetailInsurances,	BigDecimal morbidityStatistics) {

		BigDecimal enterpriseSubtotal =((employeesSalaryDetailInsurances.getEnterprisePensionInsurance() == null ?new BigDecimal("0.00"): 
					employeesSalaryDetailInsurances.getEnterprisePensionInsurance()))
					.add(employeesSalaryDetailInsurances.getEnterpriseUnemploymentInsurance() == null ?new BigDecimal("0.00")
						: employeesSalaryDetailInsurances.getEnterpriseUnemploymentInsurance())
					.add(employeesSalaryDetailInsurances.getEnterpriseBirthInsurance() == null ? new BigDecimal("0.00")
							: employeesSalaryDetailInsurances.getEnterpriseBirthInsurance())
					.add(employeesSalaryDetailInsurances.getEnterpriseInductrialInjuryBase() == null ?new BigDecimal("0.00"): 
						employeesSalaryDetailInsurances.getEnterpriseInductrialInjuryBase())
					.add(employeesSalaryDetailInsurances.getEnterpriseReserveBase() == null ? new BigDecimal("0.00")
							: employeesSalaryDetailInsurances.getEnterpriseReserveBase())
					.add(employeesSalaryDetailInsurances.getEnterpriseMedicalBase() == null ? new BigDecimal("0.00")
							: employeesSalaryDetailInsurances.getEnterpriseMedicalBase())
					.add(morbidityStatistics == null ? new BigDecimal("0.00") : morbidityStatistics)
					.add(employeesSalaryDetailInsurances.getSpecialOldSubsidies() == null ? new BigDecimal("0.00")
							: employeesSalaryDetailInsurances.getSpecialOldSubsidies())
					.add(employeesSalaryDetailInsurances.getSpecialUnemploymentSubsidies() == null ? new BigDecimal("0.00")
							: employeesSalaryDetailInsurances.getSpecialUnemploymentSubsidies())
					.add(employeesSalaryDetailInsurances.getSpecialHealthCareSubsidies() == null ? new BigDecimal("0.00")
							: employeesSalaryDetailInsurances.getSpecialHealthCareSubsidies())
					.add(employeesSalaryDetailInsurances.getSpecialAccumulationFundSubsidies() == null ? new BigDecimal("0.00")
							: employeesSalaryDetailInsurances.getSpecialAccumulationFundSubsidies());

		return enterpriseSubtotal;
	}

	/**
	 * 个人小计
	 * 
	 * @param employeesSalaryDetailInsurances
	 * @return personalSubtotal 个人小计=(个人)养老保险+(个人)失业保险+(个人)医疗保险+(个人)住房公积金
	 */
	public BigDecimal personalSubtotal(EmployeesSalaryDetail employeesSalaryDetailInsurances) {

		BigDecimal personalSubtotal =(employeesSalaryDetailInsurances.getPersonalPensionInsurance() == null ? new BigDecimal("0.00"): 
									 employeesSalaryDetailInsurances.getPersonalPensionInsurance())
									 .add(employeesSalaryDetailInsurances.getPersonalUnemploymentInsurance() == null ? 
										  new BigDecimal("0.00"): employeesSalaryDetailInsurances.getPersonalUnemploymentInsurance())
									.add( employeesSalaryDetailInsurances.getPersonalMedicalBase() == null ? new BigDecimal("0.00")
											: employeesSalaryDetailInsurances.getPersonalMedicalBase())
									.add(employeesSalaryDetailInsurances.getPersonalReserveBase() == null ? new BigDecimal("0.00")
											: employeesSalaryDetailInsurances.getPersonalReserveBase());

		return personalSubtotal;
	}

	/**
	 * @date 2013-10-11
	 * @param wage
	 * @param fiveBastTotal
	 * @return personalTax 个人计税规则
	 */
	public BigDecimal personalTax(Double beforeSalary) {
		BigDecimal personalTax = new BigDecimal("0.00");
		TaxOfPerson taxOfPerson = getTaxOfPerson();
		if (taxOfPerson.getTaxThreshold() != null) {
			personalTax = PersonalTaxUtil.getPersonalTaxResults(beforeSalary, Double
					.valueOf(taxOfPerson.getTaxThreshold().doubleValue()));

		}
		return personalTax;

	}

	/**
	 * 计算五险一金规则或者个性基数计算
	 * 
	 * @param wage
	 * @param tax
	 */
	public EmployeesSalaryDetail toCalculateFiveInsurances(
			EnterpriseEmployees enterpriseEmployees) {
		EmployeesSalaryDetail employeesSalaryDetail = new EmployeesSalaryDetail();
		InsurancesTax tax = getCustomBonus();
		InsurancesBaseSettings base = getInsurancesBaseSettings();

		if (enterpriseEmployees != null&& enterpriseEmployees.getBase() != null && enterpriseEmployees.getBase() == 1) {
			employeesSalaryDetail = personalityCalculationRules(enterpriseEmployees, tax);

		} else {

			// *社会保险基数*/
			employeesSalaryDetail.setSocialInsuranceBase(base
					.getSocialInsurance());

			// 生育保险基数
			employeesSalaryDetail.setBirthInsuranceBase(base
					.getBirthInsurance());

			// 工伤基数
			employeesSalaryDetail.setInductrialInjuryBase(base
					.getInductrialInjury());

			// 基本医疗保险 缴费基数
			employeesSalaryDetail.setMedicalPaymentBase(base.getBasicMedical());

			// 住房公积金-缴费基数
			employeesSalaryDetail.setHousingReserveBase(base.getHousingMPF());

			// 是否社保
			if (!StringUtil.isEmpty(enterpriseEmployees.getHealthCare())
					&& enterpriseEmployees.getHealthCare().equals(
							Constant.WMS_YES)) {

				// 养老保险 企业
				employeesSalaryDetail
						.setEnterprisePensionInsurance(proportionToCalculate(
								base.getSocialInsurance(), tax
										.getEndowmentInsurance()));

				// 计算个人养老保险
				employeesSalaryDetail.setPersonalPensionInsurance(proportionToCalculate(base.getSocialInsurance(), tax.getPersonalEndowmentInsurance()));

				// 计算失业保险 (个人)
				employeesSalaryDetail
						.setPersonalUnemploymentInsurance(proportionToCalculate(
								base.getSocialInsurance(), tax
										.getUnemploymentInsurance()));

				// 计算失业保险 (企业)
				employeesSalaryDetail
						.setEnterpriseUnemploymentInsurance(proportionToCalculate(
								base.getSocialInsurance(), tax
										.getPersonalUnemploymentInsurance()));

				// 计算生育（企业)
				employeesSalaryDetail
						.setEnterpriseBirthInsurance(proportionToCalculate(base
								.getBirthInsurance(), tax.getBirthEnterprise()));

				// 计算工伤（企业）
				employeesSalaryDetail
						.setEnterpriseInductrialInjuryBase(proportionToCalculate(
								base.getInductrialInjury(), tax
										.getInjuriesEnterprise()));

			}
		if (enterpriseEmployees.getWhetherGinseng() != null && enterpriseEmployees.getWhetherGinseng() == 2) {
					
					// 特殊养老失业保险补贴
					
					BigDecimal specialOldSubsidies = proportionToCalculate(base.getSocialInsurance(), tax.getEndowmentInsurance());
					BigDecimal unemploymentInsurance = proportionToCalculate(base.getSocialInsurance(), tax.getUnemploymentInsurance());// 失业保险
					BigDecimal total_subsid = specialOldSubsidies.add(unemploymentInsurance).setScale(2,BigDecimal.ROUND_HALF_DOWN);
					
					employeesSalaryDetail.setSpecialOldSubsidies(total_subsid);
	
					/* 特殊工伤生育补贴 */
					BigDecimal birthInsurance = proportionToCalculate(base.getBirthInsurance(), tax.getBirthEnterprise());// 生育
					BigDecimal inductrialInjury = proportionToCalculate(base.getInductrialInjury(), tax.getInjuriesEnterprise());// 工伤
					BigDecimal total_birth = birthInsurance.add(inductrialInjury).setScale(2, BigDecimal.ROUND_HALF_DOWN);
					employeesSalaryDetail.setSpecialUnemploymentSubsidies(total_birth);
	
					/* 特殊医保补贴 */
					BigDecimal basicMedical = proportionToCalculate(base.getBasicMedical(), tax.getMedicalEnterprise());// 医疗保险
					employeesSalaryDetail.setSpecialHealthCareSubsidies(basicMedical.setScale(2,BigDecimal.ROUND_HALF_DOWN));
	
					/* 特殊公积金补贴 */
					BigDecimal housingMPF = proportionToCalculate(base.getHousingMPF(), tax.getHousingFundEnterprise());// 公积金
					employeesSalaryDetail.setSpecialAccumulationFundSubsidies(housingMPF.setScale(2, BigDecimal.ROUND_HALF_DOWN));
			}else{
				
				// 是否医保
				if (!StringUtil.isEmpty(enterpriseEmployees.getSociaSecurity())&& enterpriseEmployees.getSociaSecurity().equals(Constant.WMS_YES)) {
					
					// 计算基本医疗保险 (企业)
					employeesSalaryDetail.setEnterpriseMedicalBase(proportionToCalculate(base.getBasicMedical(), tax.getMedicalEnterprise()));

					// 计算基本医疗保险 (个人)
					employeesSalaryDetail.setPersonalMedicalBase(proportionToCalculate(base.getBasicMedical(), tax.getPersonalEnterprise()));
				}

				 // 是否公积金
				if (!StringUtil.isEmpty(enterpriseEmployees.getAccumulationFund())&& enterpriseEmployees.getAccumulationFund().equals(Constant.WMS_YES)) {

					// 计算住房公积金-(企业)
					employeesSalaryDetail.setEnterpriseReserveBase(proportionToCalculate(base.getHousingMPF(), tax.getHousingFundEnterprise()));

					// 住房公积金 - 个人
					employeesSalaryDetail.setPersonalReserveBase(proportionToCalculate(base.getHousingMPF(), tax.getPersonalHousingFund()));

				}
				
			}

		}

		return employeesSalaryDetail;
	}

	/**
	 * 个性计算规则
	 * 
	 * @return
	 */
	public EmployeesSalaryDetail personalityCalculationRules(EnterpriseEmployees enterpriseEmployees, InsurancesTax tax) {
		
		EmployeesSalaryDetail employeesSalaryDetail = new EmployeesSalaryDetail();
		if (enterpriseEmployees != null && enterpriseEmployees.getBase() == 1) {
			
			// *社会保险基数*/
			employeesSalaryDetail.setSocialInsuranceBase(new BigDecimal(
					enterpriseEmployees.getSocialInsurance() == null ? 0.00: enterpriseEmployees.getSocialInsurance())
					.setScale(2, BigDecimal.ROUND_HALF_DOWN));

			// 生育保险基数
			employeesSalaryDetail.setBirthInsuranceBase(new BigDecimal(
					enterpriseEmployees.getFertilityInsurance() == null ? 0.00
							: enterpriseEmployees.getFertilityInsurance())
					.setScale(2, BigDecimal.ROUND_HALF_DOWN));

			// 工伤基数
			employeesSalaryDetail.setInductrialInjuryBase(new BigDecimal(
					enterpriseEmployees.getInductrialBase() == null ? 0.00: enterpriseEmployees.getInductrialBase())
					.setScale(2, BigDecimal.ROUND_HALF_DOWN));

			// 基本医疗保险 缴费基数
			employeesSalaryDetail.setMedicalPaymentBase(new BigDecimal(
					enterpriseEmployees.getBasicMedical() == null ? 0.00
							: enterpriseEmployees.getBasicMedical()).setScale(
					2, BigDecimal.ROUND_HALF_DOWN));

			// 住房公积金-缴费基数
			employeesSalaryDetail.setHousingReserveBase(new BigDecimal(
					enterpriseEmployees.getHousingFund() == null ? 0.00: enterpriseEmployees.getHousingFund()).setScale(2,
					BigDecimal.ROUND_HALF_DOWN));

			// 选择特殊补贴的计算方式
		if (enterpriseEmployees.getWhetherGinseng() != null && enterpriseEmployees.getWhetherGinseng() == 2) {

				// 特殊养老失业保险补贴
				BigDecimal specialOldSubsidies = proportionToCalculate(
						new BigDecimal(enterpriseEmployees.getSocialInsurance() == null ? 0.00: enterpriseEmployees
												.getSocialInsurance()), tax.getEndowmentInsurance());
				BigDecimal unemploymentInsurance = proportionToCalculate(new BigDecimal(enterpriseEmployees.getSocialInsurance() == null ? 0.00
										: enterpriseEmployees.getSocialInsurance()), tax.getUnemploymentInsurance());// 失业保险
				BigDecimal total_subsid = specialOldSubsidies.add(
						unemploymentInsurance).setScale(2,
						BigDecimal.ROUND_HALF_DOWN);
				employeesSalaryDetail.setSpecialOldSubsidies(total_subsid);

				/* 特殊工伤生育补贴 */
				BigDecimal birthInsurance = proportionToCalculate(
						new BigDecimal(
								enterpriseEmployees.getInductrialBase() == null ? 0.00
										: enterpriseEmployees
												.getInductrialBase()), tax
								.getBirthEnterprise());// 生育
				BigDecimal inductrialInjury = proportionToCalculate(
						new BigDecimal(enterpriseEmployees
								.getFertilityInsurance() == null ? 0.00
								: enterpriseEmployees.getFertilityInsurance()),
						tax.getInjuriesEnterprise());// 工伤
				BigDecimal total_birth = birthInsurance.add(inductrialInjury)
						.setScale(2, BigDecimal.ROUND_HALF_DOWN);
				employeesSalaryDetail
						.setSpecialUnemploymentSubsidies(total_birth);

				/* 特殊医保补贴 */
				BigDecimal basicMedical = proportionToCalculate(new BigDecimal(
						enterpriseEmployees.getBasicMedical() == null ? 0.00
								: enterpriseEmployees.getBasicMedical()), tax
						.getMedicalEnterprise());// 医疗保险
				employeesSalaryDetail
						.setSpecialHealthCareSubsidies(basicMedical.setScale(2,
								BigDecimal.ROUND_HALF_DOWN));

				/* 特殊公积金补贴 */
				BigDecimal housingMPF = proportionToCalculate(new BigDecimal(enterpriseEmployees.getHousingFund() == null ? 0.00
								: enterpriseEmployees.getHousingFund()), tax
						.getHousingFundEnterprise());// 公积金
				employeesSalaryDetail
						.setSpecialAccumulationFundSubsidies(housingMPF
								.setScale(2, BigDecimal.ROUND_HALF_DOWN));
			}else{
				
				// 是否社保
				if (!StringUtil.isEmpty(enterpriseEmployees.getHealthCare())
						&& enterpriseEmployees.getHealthCare().equals(Constant.WMS_YES)) {

					// 养老保险 企业
					employeesSalaryDetail.setEnterprisePensionInsurance(proportionToCalculate(new BigDecimal(enterpriseEmployees
											.getSocialInsurance() == null ? 0.00: enterpriseEmployees
											.getSocialInsurance()), tax.getEndowmentInsurance()));

					// 计算个人养老保险
					employeesSalaryDetail
							.setPersonalPensionInsurance(proportionToCalculate(
									new BigDecimal(enterpriseEmployees.getSocialInsurance() == null ? 0.00
									: enterpriseEmployees.getSocialInsurance()), tax.getPersonalEndowmentInsurance()));

					// 计算失业保险 (个人)
					employeesSalaryDetail.setPersonalUnemploymentInsurance(proportionToCalculate(
									new BigDecimal(enterpriseEmployees.getSocialInsurance() == null ? 0.00
									: enterpriseEmployees.getSocialInsurance()), tax.getUnemploymentInsurance()));

					// 计算失业保险 (企业)
					employeesSalaryDetail
							.setEnterpriseUnemploymentInsurance(proportionToCalculate(
									new BigDecimal(enterpriseEmployees.getSocialInsurance() == null ? 0.00
											: enterpriseEmployees.getSocialInsurance()), tax
											.getPersonalUnemploymentInsurance()));

					// 计算生育（企业)
					employeesSalaryDetail
							.setEnterpriseBirthInsurance(proportionToCalculate(
									new BigDecimal(enterpriseEmployees.getFertilityInsurance() == null ? 0.00
											: enterpriseEmployees.getFertilityInsurance()), tax
											.getBirthEnterprise()));

					// 计算工伤（企业）
					employeesSalaryDetail.setEnterpriseInductrialInjuryBase(proportionToCalculate(
									new BigDecimal(enterpriseEmployees.getInductrialBase() == null ? 0.00
									: enterpriseEmployees.getInductrialBase()), tax.getInjuriesEnterprise()));

				}

				// 是否医保
				if (!StringUtil.isEmpty(enterpriseEmployees.getSociaSecurity())&& enterpriseEmployees.getSociaSecurity().equals(Constant.WMS_YES)) {

					// 计算基本医疗保险 (企业)
					employeesSalaryDetail.setEnterpriseMedicalBase(proportionToCalculate(new BigDecimal(enterpriseEmployees.getBasicMedical() == null ? 
							0.00: enterpriseEmployees.getBasicMedical()), tax.getMedicalEnterprise()));

					// 计算基本医疗保险 (个人)
					employeesSalaryDetail.setPersonalMedicalBase(proportionToCalculate(new BigDecimal(enterpriseEmployees
										.getBasicMedical() == null ? 0.00: enterpriseEmployees.getBasicMedical()),
										tax.getPersonalEnterprise()));
				}

				// 是否公积金
				if (!StringUtil.isEmpty(enterpriseEmployees.getAccumulationFund())
						&& enterpriseEmployees.getAccumulationFund().equals(
								Constant.WMS_YES)) {

					// 计算住房公积金-(企业)
					employeesSalaryDetail
							.setEnterpriseReserveBase(proportionToCalculate(
									new BigDecimal(enterpriseEmployees.getHousingFund() == null ? 0.00: enterpriseEmployees.getHousingFund()),
									tax.getHousingFundEnterprise()));

					// 住房公积金 - 个人
					employeesSalaryDetail
							.setPersonalReserveBase(proportionToCalculate(
									new BigDecimal(enterpriseEmployees
											.getHousingFund() == null ? 0.00
											: enterpriseEmployees.getHousingFund()),
									tax.getPersonalHousingFund()));

				}
			}
		}

		return employeesSalaryDetail;
	}

	/**
	 * 计税规则除法%
	 * 
	 * @param tax
	 * @return
	 */
	public BigDecimal proportionToCalculate(BigDecimal base, BigDecimal tax) {
		BigDecimal proportion = new BigDecimal(100);
		BigDecimal resultsTax = tax.divide(proportion, 3,
				BigDecimal.ROUND_HALF_UP);
		BigDecimal baseTax = base.multiply(resultsTax);
		BigDecimal resultsBase = baseTax.setScale(2, BigDecimal.ROUND_HALF_DOWN);

		return resultsBase;
	}

	/**
	 * 查找是否包含五险
	 * 
	 * @return
	 */
	public SalaryTemplate getIsFiveBase(Integer templateId) {
		return (SalaryTemplate) em.createQuery(
				"select s from SalaryTemplate s where templateId=?")
				.setParameter(1, templateId).getSingleResult();

	}

	/**
	 * 获取个税起征点
	 * 
	 * @return
	 */
	public TaxOfPerson getTaxOfPerson() {
		try {
			Query query = em.createQuery("select t from TaxOfPerson t ");
			return (TaxOfPerson) query.getSingleResult();
		} catch (Exception e) {

			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param employeesName
	 * @param enterpriseId
	 * @return EnterpriseEmployees实体类
	 * @date 2013-09-01 上传的员工进行匹配
	 */

	public List<String> isExitUploadEnterpriseEmployees(
			List<EnterpriseEmployees> enterpriseEmployeesListPO,
			EmployeesSalaryDetail employeesSalaryDetailVO) {

		List<String> employeesName = new ArrayList<String>();
		int count = 0; // 记录存在几个相同名字
		int cardnumber = 0; // 记录存在几个相同身份证
		boolean falg = false; // 记录是否匹配
		int templCar = 0; // 记录总共多少个相同身份证号码
		boolean isCar = false;

		String detailName = employeesSalaryDetailVO.getEmployeesName();
		String detailCar = employeesSalaryDetailVO.getCardNumber();

		for (EnterpriseEmployees enterpriseEmployees : enterpriseEmployeesListPO) {
			String empCard = enterpriseEmployees.getCardNumber() == null ? ""
					: enterpriseEmployees.getCardNumber();
			String employeName = enterpriseEmployees.getEmployeesName() == null ? "": enterpriseEmployees.getEmployeesName();
			if (detailName.equals(employeName)) {
				falg = true;
				count++;
				if (count > 0) {
					if (empCard.equals(detailCar)) {
						cardnumber++;
						if (cardnumber > 1) {
							templCar++;
							isCar = true;
						}
					}
				}
			}
		}
		if (falg == false) {
			employeesName.add("数据库无法匹配不存在此员工: " + detailName);
		}
		if (isCar == true) {
			employeesName.add("数据库存在：" + count + "个: " + detailName + " 身份证号:"
					+ detailCar + " 相同 : " + templCar + " 个");
		}
		return employeesName;
	}

	/**
	 * 记录上传的员工与数据库的员工匹配成功；设置每个字段数据
	 * 
	 * @param enterpriseEmployeesListPO
	 * @param employeesSalaryDetailVO
	 * @return
	 */
	public EmployeesSalaryDetail recordEnterpriseEmployeesBySalaryDetail(
			List<EnterpriseEmployees> enterpriseEmployeesListPO,
			String[] fileDate, int number) {

		Double bonusesTotal = 0.0;
		String carNumber = null; // 身份证

		EmployeesSalaryDetail employeesSalaryDetail = new EmployeesSalaryDetail();
		carNumber = fileDate[2] == null ? "" : fileDate[2].toString();
		String note = fileDate[4] == null ? "" : fileDate[4].toString();
		if (!StringUtil.isEmpty(fileDate[1].toString())) {
			for (EnterpriseEmployees emp : enterpriseEmployeesListPO) {
				if (fileDate[1].equals(emp.getEmployeesName())) {
					if (StringUtil.isEmpty(emp.getCardNumber())) {

						if (StringUtil.isEmpty(carNumber))
							return null;
						if (!StringUtil.isEmpty(carNumber)) {
							EnterpriseEmployees tempEmployees = new EnterpriseEmployees();
							tempEmployees.setCardNumber(carNumber);
							tempEmployees.setEmployeesId(emp.getEmployeesId());
							// 更新员工的身份证号码
							updateEmployeesCarNumber(tempEmployees);
						}

					}
					employeesSalaryDetail.setWage(new BigDecimal(fileDate[3].equals("")?"0.00":fileDate[3].toString()));
					employeesSalaryDetail.setCardNumber(carNumber.trim());
					employeesSalaryDetail.setEnterpriseEmployees(emp);
					employeesSalaryDetail.setEmployeesName(emp.getEmployeesName().trim());
					employeesSalaryDetail.setBankCardNumber(emp.getBankCardNumber().trim());
					employeesSalaryDetail.setBank(emp.getBank().trim());
					employeesSalaryDetail.setNote(note.trim());
					employeesSalaryDetail.setAccident(emp.getAccident());

					// 服务费
					employeesSalaryDetail.setServiceCharge(new BigDecimal(emp
							.getServiceCost() == null ? "0.0" : emp
							.getServiceCost().toString()));

					// 大病统筹
					employeesSalaryDetail
							.setMorbidityStatistics(emp.getSeriousDisease() == null ? new BigDecimal(
									"0.0"): emp.getSeriousDisease());

					// 备注
					employeesSalaryDetail
							.setNote(fileDate[4].toString() == null ? "0.0"
									: fileDate[4].toString());

					// 统计excel导入各种奖金
					if (number > 5) {
						for (int i = 5; i < number; i++) {
							Double bonuses = Double.valueOf(fileDate[i].equals("") ? "0.0"
											: fileDate[i].toString());
							if (bonuses == null)
								continue;
							bonusesTotal += bonuses;

						}
					}
					employeesSalaryDetail.setBonus(new BigDecimal(
							(bonusesTotal == null ? 0.00 : bonusesTotal
									.doubleValue())));

					// 电脑补贴
					employeesSalaryDetail.setSubsidies(new BigDecimal(0.00));
				}
			}

		}

		return employeesSalaryDetail;
	}

	/**
	 * 更新员工的身份证号码
	 */
	public void updateEmployeesCarNumber(EnterpriseEmployees enterpriseEmployees) {
		try {
			em.createQuery(
							"update EnterpriseEmployees set cardNumber=?1 where employeesId=?2")
					.setParameter(1, enterpriseEmployees.getCardNumber())
					.setParameter(2, enterpriseEmployees.getEmployeesId())
					.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取当前企业员工工资
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeesSalaryDetail> getAllEmployeesSalaryDetail(
			Integer enterpriseId, Integer budgetId) {
		Query query = em
				.createQuery("select e from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2 order by e.salaryId ");

		return query.setParameter(1, enterpriseId).setParameter(2, budgetId)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	/**
	 * 统计预算工资
	 */
	public List<EmployeesSalaryDetail> findImportEmployeesSalaryDetailStatistics(
			Integer budgetId, Integer enterpriseId) {

		Query query = em
				.createQuery("select e from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2 order by e.salaryId ");

		return query.setParameter(1, enterpriseId).setParameter(2, budgetId)
				.getResultList();

	}


	/**
	 * 统计工资总额（元）
	 */
	public BigDecimal getWageTotal(Integer enterpriseId, Integer budgettableId) {
		Query query = null;
		try {

			query = em.createQuery("select sum(e.shouldPay) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (BigDecimal) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}
	/**
	 * 工伤生育补贴总额（元）
	 */
	public BigDecimal getspecialUnemploymentSubsidiesTotal(Integer enterpriseId, Integer budgettableId) {
		Query query = null;
		try {

			query = em.createQuery("select sum(e.specialUnemploymentSubsidies) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (BigDecimal) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}
	/**
	 * /*特殊养老保险补贴总额（元）
	 */
	public BigDecimal getspecialOldSubsidiesTotal(Integer enterpriseId, Integer budgettableId) {
		Query query = null;
		try {

			query = em.createQuery("select sum(e.specialOldSubsidies) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (BigDecimal) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}
	/**
	 * /*特殊医保补贴补贴总额（元）
	 */
	public BigDecimal getSpecialHealthCareSubsidiesTotal(Integer enterpriseId, Integer budgettableId) {
		Query query = null;
		try {

			query = em.createQuery("select sum(e.specialHealthCareSubsidies) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (BigDecimal) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}
	/**
	 * /*特殊公积金补贴补贴总额（元）
	 */
	public BigDecimal getSpecialAccumulationFundSubsidiesTotal(Integer enterpriseId, Integer budgettableId) {
		Query query = null;
		try {

			query = em.createQuery("select sum(e.specialAccumulationFundSubsidies) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (BigDecimal) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}
	
	/**
	 * /*统计企业小计总额（元）
	 */
	public BigDecimal getEnterpriseSubtotalTotal(Integer enterpriseId, Integer budgettableId) {
		Query query = null;
		try {

			query = em.createQuery("select sum(e.enterpriseSubtotal) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (BigDecimal) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}
	/**
	 * 统计特殊补贴五险一金
	 * @param enterpriseId
	 * @param budgettableId
	 * @return
	 */
	public BigDecimal  getfiveServiceTotal(Integer enterpriseId, Integer budgettableId)
	{
		
		Query query=em.createQuery("select sum(e.specialOldSubsidies+specialUnemploymentSubsidies+specialHealthCareSubsidies+specialAccumulationFundSubsidies)" +
				" from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");

		return (BigDecimal) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}
	
	
	
	

	/**
	 * 统计发放人数
	 */
	public long getNumberPersonlTotal(Integer enterpriseId,
			Integer budgettableId) {
		Query query = null;
		try {

			query = em
					.createQuery("select count(e.salaryId) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (Long) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}

	/**
	 * 统计民生人数
	 */
	public long getSumMingShengBank(Integer enterpriseId, Integer budgettableId) {
		Query query = null;
		try {

			query = em
					.createQuery("select count(e.salaryId) from EmployeesSalaryDetail e "
							+ " where e.enterprise.enterpriseId=?1 "
							+ " and e.budgettableId=?2"
							+ " and e.bank like '%民生%'");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return (Long) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}

	/**
	 * 统计现金人数
	 */
	public long getSumCashNumber(Integer enterpriseId, Integer budgettableId) {
		Query query = null;
		try {

			query = em
					.createQuery("select count(e.salaryId) from EmployeesSalaryDetail e "
							+ "where e.enterprise.enterpriseId=?1 "
							+ " and  e.budgettableId=?2"
							+ " and ( e.bank is null  or e.bank like ' %现金% ' or e.bank='' )");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return (Long) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}

	/**
	 * 统计他行人数
	 */
	public long getSumHeLineBank(Integer enterpriseId, Integer budgettableId) {
		Query query = null;
		try {

			query = em
					.createQuery("select count(e.salaryId) from EmployeesSalaryDetail e "
							+ "where e.enterprise.enterpriseId=?1 "
							+ " and e.budgettableId=?2"
							+ " and e.bank not like '%民生%'  "
							+ " and e.bank is not null and e.bank!='' ");
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return (Long) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}

	/**
	 * 统计服务费总额
	 */
	public BigDecimal getServiceTotal(Integer enterpriseId,
			Integer budgettableId) {
		Query query = null;
		try {

			query = em
					.createQuery("select sum(e.serviceCharge) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");

		} catch (Exception e) {
			e.printStackTrace();
			return new BigDecimal("0.00");
		}

		return (BigDecimal) query.setParameter(1, enterpriseId).setParameter(2,
				budgettableId).getSingleResult();
	}

	/**
	 * 修改工资，奖金
	 */
	public void updateEmployeesSalaryDetail(
			EmployeesSalaryDetail employeesSalaryDetail) {

		try {
			if (employeesSalaryDetail != null
					&& employeesSalaryDetail.getSalaryId() != null) {

				EmployeesSalaryDetail employeesSalaryDetailPO = computationalSalary(employeesSalaryDetail);
				super.clear();
				em.createQuery(
						"update EmployeesSalaryDetail e " 
								+ "set e.wage=?1,"
								+ " e.bonus=?2," 
								+ " e.subsidies=?3, "
								+ " e.shouldPay=?4, " 
								+ " e.beforeSalary=?5, "
								+ " e.personalTax=?6, " 
								+ " e.aggregate=?7,"
								+ " e.moneyToCards=?8, "
								+ " e.accident=?9 "
								+ " where e.salaryId=?10")
						.setParameter(1,employeesSalaryDetailPO.getWage())
						.setParameter(2,employeesSalaryDetailPO.getBonus())
						.setParameter(3,employeesSalaryDetailPO.getSubsidies())
						.setParameter(4,employeesSalaryDetailPO.getShouldPay())
						.setParameter(5,employeesSalaryDetailPO.getBeforeSalary())
						.setParameter(6,employeesSalaryDetailPO.getPersonalTax())
						.setParameter(7,employeesSalaryDetailPO.getAggregate())
						.setParameter(8,employeesSalaryDetailPO.getMoneyToCards())
						.setParameter(9,employeesSalaryDetailPO.getAccident())
						.setParameter(10,employeesSalaryDetailPO.getSalaryId())
						.executeUpdate();

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 计算修改后的工资
	 * @param employeesSalaryDetail
	 * @return
	 */
	public EmployeesSalaryDetail computationalSalary(EmployeesSalaryDetail employeesSalaryDetail) {
		
		
		/* 工资 */
		BigDecimal wage = new BigDecimal("0.00");
		/* 奖金 */
		BigDecimal bonus = new BigDecimal("0.00");
		/* 电脑补贴 */
		BigDecimal subsidies = new BigDecimal("0.00");
		/* 应发工资 */
		BigDecimal shouldPay = new BigDecimal("0.00");
		/* 小计- 企业 */
		BigDecimal enterpriseSubtotal = new BigDecimal("0.00");
		/* 税前工资 */
		BigDecimal beforeSalary = new BigDecimal("0.00");
		/* 个人 个税 */
		BigDecimal personalTax = new BigDecimal("0.00");
		/* 合计（企业应付） */
		BigDecimal aggregate = new BigDecimal("0.00");
		/* 到卡金额 */
		BigDecimal moneyToCards = new BigDecimal("0.00");
		
		/* 意外险 */
		BigDecimal accident = new BigDecimal("0.00");
		

		EmployeesSalaryDetail employeesSalaryDetailPO = super.find(employeesSalaryDetail.getSalaryId());

		wage = employeesSalaryDetail.getWage() == null ? new BigDecimal("0.00")
				: employeesSalaryDetail.getWage();

		bonus = employeesSalaryDetail.getBonus() == null ? new BigDecimal("0.00") : 
				employeesSalaryDetail.getBonus().setScale(2,BigDecimal.ROUND_HALF_DOWN);

		subsidies = employeesSalaryDetail.getSubsidies() == null ? new BigDecimal("0.00"):employeesSalaryDetail.getSubsidies();

		shouldPay = wage.add(bonus).add(subsidies).setScale(2,BigDecimal.ROUND_HALF_DOWN);
		
		accident=employeesSalaryDetail.getAccident() == null ? new BigDecimal("0.00"):employeesSalaryDetail.getAccident();
		

		if (wage != null && wage != new BigDecimal("0.00")) {
			employeesSalaryDetailPO.setWage(wage);
		}
		if (bonus != null && bonus != new BigDecimal("0.00")) {
			employeesSalaryDetailPO.setBonus(bonus);
		}
		if (subsidies != null && subsidies != new BigDecimal("0.00")) {
			employeesSalaryDetailPO.setSubsidies(subsidies);
		}
		if (shouldPay != null && shouldPay != new BigDecimal("0.00")) {
			// 应发工资
			employeesSalaryDetailPO.setShouldPay(shouldPay);
		}
		if (accident != null && accident != new BigDecimal("0.00")) {
			// 应发工资
			employeesSalaryDetailPO.setAccident(accident);
		}

		// 税前工资
		beforeSalary = employeesSalaryDetailPO.getShouldPay().subtract(
				employeesSalaryDetailPO.getPersonalSubtotal()).setScale(2,BigDecimal.ROUND_HALF_DOWN);
		employeesSalaryDetailPO.setBeforeSalary(beforeSalary);

		// 企业合计
		aggregate = employeesSalaryDetailPO.getShouldPay()
					.add( employeesSalaryDetailPO.getEnterpriseSubtotal())
				    .add( employeesSalaryDetailPO.getServiceCharge())
				    .add( employeesSalaryDetailPO.getAccident())
				    .setScale(2,BigDecimal.ROUND_HALF_DOWN);

		// 个税
		personalTax = personalTax(Double.valueOf(employeesSalaryDetailPO.getShouldPay().doubleValue()));
		
			
		// 到卡金额
		moneyToCards = employeesSalaryDetailPO.getBeforeSalary().subtract(
				employeesSalaryDetailPO.getPersonalTax()).setScale(2,BigDecimal.ROUND_HALF_DOWN);

		
		
		employeesSalaryDetailPO.setPersonalTax(personalTax);
		employeesSalaryDetailPO.setMoneyToCards(moneyToCards);
		employeesSalaryDetailPO.setAggregate(aggregate);
		employeesSalaryDetailPO.setEnterpriseSubtotal(enterpriseSubtotal);
		return employeesSalaryDetailPO;
	}

	/**
	 *删除工资
	 */

	public void deleteEmployeesSalaryDetail(Integer budgetId) {
		try {
			em.createQuery(
					"delete EmployeesSalaryDetail e where e.budgettableId=?1")
					.setParameter(1, budgetId).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取计税规则
	 * 
	 * @return
	 */
	public InsurancesTax getCustomBonus() {
		try {
			return (InsurancesTax) em.createQuery(
					"select t from InsurancesTax t ").getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 获取五险一金基数设置
	 * 
	 * @return
	 */
	public InsurancesBaseSettings getInsurancesBaseSettings() {

		return (InsurancesBaseSettings) em.createQuery("select t from InsurancesBaseSettings t ").getSingleResult();

	}

	/**
	 * 获取当前企业所有在职人员
	 * 
	 * @param enterpriseId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> getAllEnterpriseEmployees(
			Integer enterpriseId) {
		try {
			Query query = em
					.createQuery("select e from EnterpriseEmployees e where e.enterprise.enterpriseId=?1  and e.departure=0 and e.pseudoDelete=0 ");
			return query.setParameter(1, enterpriseId).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<EmployeesSalaryDetail> getPayrollStaff(Integer budgetId) {
		String defaultHql = "select e from EmployeesSalaryDetail e where e.budgettableId=?1";
		return em.createQuery(defaultHql).setParameter(1, budgetId)
				.getResultList();
	}

	/**
	 * 查看发放人员情况
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeesSalaryDetail> getBankEmployeesSalaryDetail(
			Integer budgetId) {
		return em.createQuery("select e from EmployeesSalaryDetail e where e.budgettableId=?1 order by e.salaryId desc ")
				.setParameter(1, budgetId).getResultList();
	}
	

	public EmployeesSalaryDetail getSumDateSalaryDeatil(CreateSalaryBudgetTable createSalaryBudgetTable)
	{
		EmployeesSalaryDetail employeesSalaryDetailVO=new EmployeesSalaryDetail();
		if(createSalaryBudgetTable==null)return null;
		 BigDecimal  bonus=new BigDecimal("0.00");
		 BigDecimal  wage=new BigDecimal("0.00");
		 BigDecimal  subsidies=new BigDecimal("0.00");
		 BigDecimal  shouldPay=new BigDecimal("0.00");
		 BigDecimal  enterprisePensionInsurance=new BigDecimal("0.00");
		 BigDecimal  personalPensionInsurance=new BigDecimal("0.00");
		 BigDecimal  enterpriseUnemploymentInsurance=new BigDecimal("0.00");
		 BigDecimal  personalUnemploymentInsurance=new BigDecimal("0.00");
		 BigDecimal  enterpriseBirthInsurance=new BigDecimal("0.00");
		 BigDecimal  enterpriseInductrialInjuryBase=new BigDecimal("0.00");
		 BigDecimal  enterpriseMedicalBase=new BigDecimal("0.00");
		 BigDecimal  personalMedicalBase=new BigDecimal("0.00");
		 BigDecimal  enterpriseReserveBase=new BigDecimal("0.00");
		 BigDecimal  personalReserveBase=new BigDecimal("0.00");
		 BigDecimal  morbidityStatistics=new BigDecimal("0.00");
		 BigDecimal  enterpriseSubtotal=new BigDecimal("0.00");
		 BigDecimal  personalSubtotal=new BigDecimal("0.00");
		 BigDecimal  beforeSalary=new BigDecimal("0.00");
		 BigDecimal  enterpriseTax=new BigDecimal("0.00");
		 BigDecimal  personalTax=new BigDecimal("0.00");
		 BigDecimal  serviceCharge=new BigDecimal("0.00");
		 BigDecimal  aggregate=new BigDecimal("0.00");
		 BigDecimal  moneyToCards=new BigDecimal("0.00");
		 BigDecimal  specialOldSubsidies=new BigDecimal("0.00");
		 BigDecimal  specialUnemploymentSubsidies=new BigDecimal("0.00");
		 BigDecimal  specialHealthCareSubsidies=new BigDecimal("0.00");
		 BigDecimal  specialAccumulationFundSubsidies=new BigDecimal("0.00");
		 BigDecimal  accident=new BigDecimal("0.00");
		 
		
		
		
		if( createSalaryBudgetTable.getEmployeesSalaryDetail()!=null){
			
			for (EmployeesSalaryDetail employeesSalaryDetailPO : createSalaryBudgetTable.getEmployeesSalaryDetail()){
				
				bonus=bonus.add(employeesSalaryDetailPO.getBonus());
				wage=wage.add(employeesSalaryDetailPO.getWage());
				subsidies=subsidies.add(employeesSalaryDetailPO.getSubsidies());
				shouldPay=shouldPay.add(employeesSalaryDetailPO.getShouldPay());
				enterprisePensionInsurance=enterprisePensionInsurance.add(employeesSalaryDetailPO.getEnterprisePensionInsurance());
				personalUnemploymentInsurance=personalUnemploymentInsurance.add(employeesSalaryDetailPO.getPersonalUnemploymentInsurance());
				enterpriseBirthInsurance=enterpriseBirthInsurance.add(employeesSalaryDetailPO.getEnterpriseBirthInsurance());
				enterpriseInductrialInjuryBase=enterpriseInductrialInjuryBase.add(employeesSalaryDetailPO.getEnterpriseInductrialInjuryBase());
				enterpriseMedicalBase=enterpriseMedicalBase.add(employeesSalaryDetailPO.getEnterpriseMedicalBase());
				personalMedicalBase=personalMedicalBase.add(employeesSalaryDetailPO.getPersonalMedicalBase());
				enterpriseReserveBase=enterpriseReserveBase.add(employeesSalaryDetailPO.getEnterpriseReserveBase());
				personalReserveBase=personalReserveBase.add(employeesSalaryDetailPO.getPersonalReserveBase());
				morbidityStatistics=morbidityStatistics.add(employeesSalaryDetailPO.getMorbidityStatistics());
				enterpriseSubtotal=enterpriseSubtotal.add(employeesSalaryDetailPO.getEnterpriseSubtotal());
				personalSubtotal=personalSubtotal.add(employeesSalaryDetailPO.getPersonalSubtotal());
				beforeSalary=beforeSalary.add(employeesSalaryDetailPO.getBeforeSalary());
				enterpriseTax=enterpriseTax.add(employeesSalaryDetailPO.getEnterpriseTax());
				personalTax=personalTax.add(employeesSalaryDetailPO.getPersonalTax());
				serviceCharge=serviceCharge.add(employeesSalaryDetailPO.getServiceCharge());
				aggregate=aggregate.add(employeesSalaryDetailPO.getAggregate());
				moneyToCards=moneyToCards.add(employeesSalaryDetailPO.getMoneyToCards());
				personalPensionInsurance=personalPensionInsurance.add(employeesSalaryDetailPO.getPersonalPensionInsurance());
				enterpriseUnemploymentInsurance=enterpriseUnemploymentInsurance.add(employeesSalaryDetailPO.getEnterpriseUnemploymentInsurance());
				
				specialOldSubsidies=specialOldSubsidies.add(employeesSalaryDetailPO.getSpecialOldSubsidies());
				specialUnemploymentSubsidies=specialUnemploymentSubsidies.add(employeesSalaryDetailPO.getSpecialUnemploymentSubsidies());
				specialHealthCareSubsidies=specialHealthCareSubsidies.add(employeesSalaryDetailPO.getSpecialHealthCareSubsidies());
				specialAccumulationFundSubsidies=specialAccumulationFundSubsidies.add(employeesSalaryDetailPO.getSpecialAccumulationFundSubsidies());
				
				accident=accident.add(employeesSalaryDetailPO.getAccident()==null?new BigDecimal("0.00"):employeesSalaryDetailPO.getAccident());
				
				
				
				
			}
			
			employeesSalaryDetailVO.setWage(wage.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setBonus(bonus.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setSubsidies(subsidies.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setShouldPay(shouldPay.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setEnterprisePensionInsurance(enterprisePensionInsurance.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setPersonalUnemploymentInsurance(personalUnemploymentInsurance.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setEnterpriseBirthInsurance(enterpriseBirthInsurance.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setEnterpriseInductrialInjuryBase(enterpriseInductrialInjuryBase.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setEnterpriseMedicalBase(enterpriseMedicalBase.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setPersonalMedicalBase(personalMedicalBase.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setEnterpriseReserveBase(enterpriseReserveBase.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setPersonalReserveBase(personalReserveBase.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setMorbidityStatistics(morbidityStatistics.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setEnterpriseSubtotal(enterpriseSubtotal.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setPersonalSubtotal(personalSubtotal.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setEnterpriseTax(enterpriseTax.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setPersonalTax(personalTax.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setServiceCharge(serviceCharge.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setAggregate(aggregate.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setMoneyToCards(moneyToCards.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setPersonalPensionInsurance(personalPensionInsurance.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setEnterpriseUnemploymentInsurance(enterpriseUnemploymentInsurance.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setBeforeSalary(beforeSalary.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setSpecialOldSubsidies(specialOldSubsidies.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setSpecialUnemploymentSubsidies(specialUnemploymentSubsidies.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setSpecialHealthCareSubsidies(specialHealthCareSubsidies.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setSpecialAccumulationFundSubsidies(specialAccumulationFundSubsidies.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			employeesSalaryDetailVO.setAccident(accident.setScale(2,BigDecimal.ROUND_HALF_DOWN));
			
			
			
		}
		
		return employeesSalaryDetailVO;
	}
	
	

}
