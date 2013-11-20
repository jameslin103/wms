package cn.fm.service.salary.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.GridParameter;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.company.InsurancesBaseSettings;
import cn.fm.bean.company.InsurancesTax;
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.bean.salary.TaxOfPerson;
import cn.fm.service.base.BaseGrid;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.base.SearchImpl;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.utils.GenerateSqlFromExcel;
import cn.fm.utils.PersonalTaxUtil;
import cn.fm.utils.StringUtil;

@Service @Transactional
public class EmployeesSalaryDetailServiceImpl extends DaoSupport<EmployeesSalaryDetail> implements	EmployeesSalaryDetailService {
	
	private SearchImpl searchImpl;
	
	
	
	
	
	
	public void setSearchImpl(SearchImpl searchImpl) {
		this.searchImpl = searchImpl;
	}
	/**
	 * @version 1.0
	 * 批量上传员工工资基本工资信息
	 * @return enterpriseEmployeesIsExistVO
	 * @author jameslin
	 * @date   2013-09-01
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public List<String> saveEmployeesSalaryDetail(File file , String fileName,int number,int readRows,EmployeesSalaryDetail employeesSalaryDetail,Integer templateId,Integer enterpriseId)
	{
		List<String>  employeesName=new ArrayList<String>();
		List<EmployeesSalaryDetail> detailList=new ArrayList<EmployeesSalaryDetail>();
		List<EnterpriseEmployees> enterpriseEmployeesListPO=getAllEnterpriseEmployees(enterpriseId);
	    if(employeesSalaryDetail==null || employeesSalaryDetail.getEnterprise()==null || employeesSalaryDetail.getEnterprise().getEnterpriseId()==null)return null;
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		try {
			List<String[]> arrayList=excel.generateStationBugSql(file,fileName,number,readRows);
			if(arrayList.size()==0)return null;
			for (int i = 0; i < arrayList.size(); i++)
			{
				String[] data = arrayList.get(i);
				
				
			
				
				//匹配姓名是否重复
				EmployeesSalaryDetail detail=refactoringEmployeesSalaryDetailDate(data);
				employeesName=isExitUploadEnterpriseEmployees(enterpriseEmployeesListPO,detail);
				
				
				if(employeesName.size()==0){
					//记录封装每个字段
					EmployeesSalaryDetail employeesSalaryDetailBySalaryDetail= recordEnterpriseEmployeesBySalaryDetail(enterpriseEmployeesListPO,data,number);
					EmployeesSalaryDetail employeesSalaryDetailVO=structureEmployeesSalaryDetail(employeesSalaryDetailBySalaryDetail,templateId);
					employeesSalaryDetailVO.setSalaryDate(employeesSalaryDetail.getSalaryDate());
					employeesSalaryDetailVO.setEnterprise(employeesSalaryDetail.getEnterprise());
					employeesSalaryDetailVO.setBudgettableId(employeesSalaryDetail.getBudgettableId());
					detailList.add(employeesSalaryDetailVO);
				}
				
			}
			if(employeesName.size()==0){
				saveEmployeesSalaryDetail(detailList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			employeesName.add("文件出错!!");
			
		}
		return employeesName;
		
	}
	/**
	 * 保存数据到数据库
	 * @param employeesSalaryDetailList
	 */
	public void saveEmployeesSalaryDetail(List<EmployeesSalaryDetail> employeesSalaryDetailList)
	{
			if(employeesSalaryDetailList==null)return;
			     EmployeesSalaryDetail  employeesSalaryDetailVO=null;
			for (EmployeesSalaryDetail detail : employeesSalaryDetailList) 
			{
				 if(StringUtil.isEmpty(detail.getCardNumber()) || StringUtil.isEmpty(detail.getEmployeesName()))continue;
				employeesSalaryDetailVO=new EmployeesSalaryDetail();
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
				 
			   super.save(employeesSalaryDetailVO);
			}
			
			
	}
	
	
	
	
	
	/**
	 * 重构导入的数据进行封装EmployeesSalaryDetail类
	 * @return EmployeesSalaryDetail
	 * 2013-10-13
	 */
	public EmployeesSalaryDetail   refactoringEmployeesSalaryDetailDate(String[] fileDate)
	{
		EmployeesSalaryDetail  salaryDetailExcelDate=new EmployeesSalaryDetail();
		
		salaryDetailExcelDate.setEmployeesName(fileDate[1]==null?"":fileDate[1].toString());
		salaryDetailExcelDate.setCardNumber(fileDate[3]==null?"":fileDate[3].toString());
		
		return salaryDetailExcelDate;
	}
	/**
	 * @author jameslin
	 * @date 2013-09-01
	 * @return  EmployeesSalaryDetail
	 * 从excel导入的数据重新构造数据
	 */
	public EmployeesSalaryDetail structureEmployeesSalaryDetail(EmployeesSalaryDetail employeesSalaryDetail ,Integer templateId)
	{
			Double  subsidies=0.0; 			     //补贴
			Double  wage;						 //基本工资
			Double  enterpriseSubtotal;		     //企业小计
			Double  personalSubtotal;			 //个人小计
			Double  beforeSalary;				 //税前工资
			Double  shouldPayTotal;			     //应发工资
			Double  aggregate;					 //合计(企业应付)
			Double  fiveBaseTotal = 0.00;		 //五险一金总基数
			BigDecimal  personalTax = new BigDecimal("0.00");		 //个税
		
		
		
		
		EmployeesSalaryDetail   employeesSalaryDetailVO=new EmployeesSalaryDetail();
		try {
			
			
			/*基本工资*/ 
		    wage=Double.valueOf(employeesSalaryDetail.getWage()==null?"0.00":employeesSalaryDetail.getWage().toString());
		    employeesSalaryDetailVO.setWage(new BigDecimal(wage));
			//应发工资=奖金+补贴+工资
			Double bonus=Double.valueOf(employeesSalaryDetail.getBonus()==null?"0.00":employeesSalaryDetail.getBonus().toString());
			subsidies=Double.valueOf(employeesSalaryDetail.getSubsidies()==null?"0.00":employeesSalaryDetail.getSubsidies().toString());
			
			shouldPayTotal=wage+subsidies+bonus;
			
			employeesSalaryDetailVO.setSubsidies(new BigDecimal(subsidies));
			employeesSalaryDetailVO.setShouldPay(new BigDecimal(shouldPayTotal));
			employeesSalaryDetailVO.setBonus(new BigDecimal(bonus));
			employeesSalaryDetailVO.setMorbidityStatistics(employeesSalaryDetail.getMorbidityStatistics());
			employeesSalaryDetailVO.setEmployeesName(employeesSalaryDetail.getEmployeesName());
			employeesSalaryDetailVO.setCardNumber(employeesSalaryDetail.getCardNumber());
			employeesSalaryDetailVO.setEnterpriseEmployees(employeesSalaryDetail.getEnterpriseEmployees());
			employeesSalaryDetailVO.setNote(employeesSalaryDetail.getNote());
			employeesSalaryDetailVO.setServiceCharge(employeesSalaryDetail.getServiceCharge());
			employeesSalaryDetailVO.setBankCardNumber(employeesSalaryDetail.getBankCardNumber());
			
			//计算五险一金规则
			SalaryTemplate salaryTemplate=getIsFiveBase(templateId);
			EmployeesSalaryDetail employeesSalaryDetailInsurances=new EmployeesSalaryDetail();
			if(salaryTemplate!=null && salaryTemplate.getStatus()==1)
			{
				    employeesSalaryDetailInsurances=toCalculateFiveInsurances();
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
					
					
					
			}
			
			// TODO: handle exception 
			
			//企业小计=(企业)养老保险+(企业)失业保险+生育（企业）+工伤(企业)+住房公积金(企业)+住房公积金(个人)+大病统筹
			 enterpriseSubtotal=enterpriseSubtotal(employeesSalaryDetailInsurances,(employeesSalaryDetail.getMorbidityStatistics()==null?new BigDecimal(0.00):employeesSalaryDetail.getMorbidityStatistics()));
			 employeesSalaryDetailVO.setEnterpriseSubtotal(new BigDecimal(enterpriseSubtotal).setScale(2,BigDecimal.ROUND_HALF_DOWN));
			
			//个人小计=(个人)养老保险+(个人)失业保险+(个人)医疗保险+(个人)住房公积金
			personalSubtotal=personalSubtotal(employeesSalaryDetailInsurances);	 
			employeesSalaryDetailVO.setPersonalSubtotal(new BigDecimal(personalSubtotal).setScale(2,BigDecimal.ROUND_HALF_DOWN));
			
			//税前工资=应发工资-(个人)小计
			beforeSalary=shouldPayTotal-personalSubtotal;
			employeesSalaryDetailVO.setBeforeSalary(new BigDecimal(beforeSalary).setScale(2,BigDecimal.ROUND_HALF_DOWN));			
			
			//合计(企业应付)=应发工资+(企业)小计+服务费
			aggregate=shouldPayTotal+enterpriseSubtotal+Double.valueOf(employeesSalaryDetail.getServiceCharge()==null?"0.00":employeesSalaryDetail.getServiceCharge().toString());
			employeesSalaryDetailVO.setAggregate(new BigDecimal(aggregate).setScale(2,BigDecimal.ROUND_HALF_DOWN));
			

			//五险一金统计
			fiveBaseTotal=Double.valueOf(employeesSalaryDetailVO.getPersonalPensionInsurance()==null?"0.00":employeesSalaryDetailVO.getPersonalPensionInsurance().toString())+Double.valueOf(employeesSalaryDetailVO.getPersonalUnemploymentInsurance()==null?"0.00":employeesSalaryDetailVO.getPersonalUnemploymentInsurance().toString())+
			              Double.valueOf(employeesSalaryDetailVO.getEnterpriseBirthInsurance()==null?"0.00":employeesSalaryDetailVO.getEnterpriseBirthInsurance().toString())+Double.valueOf(employeesSalaryDetailVO.getEnterpriseInductrialInjuryBase()==null?"0.00":employeesSalaryDetailVO.getEnterpriseInductrialInjuryBase().toString())+
			              Double.valueOf(employeesSalaryDetailVO.getPersonalMedicalBase()==null?"0.00":employeesSalaryDetailVO.getPersonalMedicalBase().toString())+Double.valueOf(employeesSalaryDetailVO.getPersonalReserveBase()==null?"0.00":employeesSalaryDetailVO.getPersonalReserveBase().toString());
			
			personalTax=personalTax(Double.valueOf(wage),Double.valueOf(fiveBaseTotal==null?"0.00":fiveBaseTotal.toString())).setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//个税
			employeesSalaryDetailVO.setPersonalTax(personalTax==null?new BigDecimal(0.00):personalTax);
			//企业个税
			employeesSalaryDetailVO.setEnterpriseTax(new BigDecimal(0.00));
			
			//到卡金额=税前工资-个人税
			Double moneyToCards=beforeSalary-Double.valueOf(personalTax.toString());
			employeesSalaryDetailVO.setMoneyToCards(new BigDecimal(moneyToCards).setScale(2,BigDecimal.ROUND_HALF_DOWN));
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return employeesSalaryDetailVO;
	}
	
	/**
	 * 企业小计 计算方法
	 * @param employeesSalaryDetailInsurances
	 * @return enterpriseSubtotal
	 * //企业小计=(企业)养老保险+(企业)失业保险+生育（企业）+工伤(企业)+住房公积金(企业)+住房公积金(个人)+大病统筹
	 */
	public Double   enterpriseSubtotal(EmployeesSalaryDetail employeesSalaryDetailInsurances,BigDecimal morbidityStatistics)
	{
		
	  Double enterpriseSubtotal=Double.valueOf(employeesSalaryDetailInsurances.getEnterprisePensionInsurance().toString())+
							    Double.valueOf(employeesSalaryDetailInsurances.getEnterpriseUnemploymentInsurance().toString())+
								Double.valueOf(employeesSalaryDetailInsurances.getEnterpriseBirthInsurance().toString())+
								Double.valueOf(employeesSalaryDetailInsurances.getEnterpriseInductrialInjuryBase().toString())+
								Double.valueOf(employeesSalaryDetailInsurances.getEnterpriseReserveBase().toString())+
								Double.valueOf(employeesSalaryDetailInsurances.getPersonalReserveBase().toString())+
								Double.valueOf(morbidityStatistics.toString());

		return enterpriseSubtotal;
	}
	/**
	 * 个人小计
	 * @param   employeesSalaryDetailInsurances
	 * @return  personalSubtotal
	 * 个人小计=(个人)养老保险+(个人)失业保险+(个人)医疗保险+(个人)住房公积金
	 */
	public Double  personalSubtotal(EmployeesSalaryDetail employeesSalaryDetailInsurances){
		
		 Double personalSubtotal=Double.valueOf(employeesSalaryDetailInsurances.getPersonalPensionInsurance().toString())+
								 Double.valueOf(employeesSalaryDetailInsurances.getPersonalUnemploymentInsurance().toString())+
								 Double.valueOf(employeesSalaryDetailInsurances.getPersonalMedicalBase().toString())+
								 Double.valueOf(employeesSalaryDetailInsurances.getPersonalReserveBase().toString());
		
		return personalSubtotal;
	}
	
	/**
	 * @date 2013-10-11
	 * @param wage
	 * @param fiveBastTotal
	 * @return personalTax
	 * 个人计税规则
	 */
	public BigDecimal  personalTax(Double wage,Double fiveBastTotal)
	{
		BigDecimal personalTax=new BigDecimal("0.00");
		TaxOfPerson taxOfPerson=getTaxOfPerson();
		if(taxOfPerson.getTaxThreshold()!=null)
		{
			personalTax=PersonalTaxUtil.getPersonalTaxResults(wage, Double.valueOf(taxOfPerson.getTaxThreshold().toString()), fiveBastTotal);
			
		}
		return personalTax;
		
	}
	
	/**
	 * 计算五险一金规则
	 * @param wage
	 * @param tax
	 */
	public EmployeesSalaryDetail toCalculateFiveInsurances()
	{
		InsurancesTax tax=getCustomBonus();
		InsurancesBaseSettings base=getInsurancesBaseSettings();
		EmployeesSalaryDetail employeesSalaryDetail=new EmployeesSalaryDetail();
		
		//*社会保险基数*/
		employeesSalaryDetail.setSocialInsuranceBase(base.getSocialInsurance());
		
		//生育保险基数
		employeesSalaryDetail.setBirthInsuranceBase(base.getBirthInsurance());
		
		//工伤基数
		employeesSalaryDetail.setInductrialInjuryBase(base.getInductrialInjury());
		
		//基本医疗保险   缴费基数
		employeesSalaryDetail.setMedicalPaymentBase(base.getBasicMedical());
		
		//住房公积金-缴费基数
		employeesSalaryDetail.setHousingReserveBase(base.getHousingMPF());
		
		
		//养老保险  企业
		employeesSalaryDetail.setEnterprisePensionInsurance(proportionToCalculate(base.getSocialInsurance(),tax.getEndowmentInsurance()));
		
		//计算个人养老保险
		employeesSalaryDetail.setPersonalPensionInsurance(proportionToCalculate(base.getSocialInsurance(),tax.getPersonalEndowmentInsurance()));
		
		//计算失业保险  (个人)
		employeesSalaryDetail.setPersonalUnemploymentInsurance(proportionToCalculate(base.getSocialInsurance(),tax.getUnemploymentInsurance()));
		
		//计算失业保险  (企业)
		employeesSalaryDetail.setEnterpriseUnemploymentInsurance(proportionToCalculate(base.getSocialInsurance(),tax.getPersonalUnemploymentInsurance()));
		
		//计算生育（企业)
		employeesSalaryDetail.setEnterpriseBirthInsurance(proportionToCalculate(base.getBirthInsurance(),tax.getBirthEnterprise()));
		
		//计算工伤（企业）
		employeesSalaryDetail.setEnterpriseInductrialInjuryBase(proportionToCalculate(base.getInductrialInjury(),tax.getInjuriesEnterprise()));
		
		//计算基本医疗保险  (企业)
		employeesSalaryDetail.setEnterpriseMedicalBase(proportionToCalculate(base.getBasicMedical(),tax.getMedicalEnterprise()));
		
		//计算基本医疗保险 (个人)
		employeesSalaryDetail.setPersonalMedicalBase(proportionToCalculate(base.getBasicMedical(),tax.getPersonalEnterprise()));
		
		//计算住房公积金-(企业)
		employeesSalaryDetail.setEnterpriseReserveBase(proportionToCalculate(base.getHousingMPF(),tax.getHousingFundEnterprise()));
		
		//住房公积金 - 个人
		employeesSalaryDetail.setPersonalReserveBase(proportionToCalculate(base.getHousingMPF(),tax.getPersonalHousingFund()));
		
		return employeesSalaryDetail;
	}
	/**
	 * 计税规则除法%
	 * @param tax
	 * @return
	 */
	public BigDecimal proportionToCalculate(BigDecimal base,BigDecimal tax)
	{
		BigDecimal proportion=new BigDecimal(100);
		BigDecimal resultsTax=tax.divide(proportion, 3, BigDecimal.ROUND_HALF_UP);
		BigDecimal baseTax=base.multiply(resultsTax);
		BigDecimal resultsBase= baseTax.setScale(2, BigDecimal.ROUND_HALF_UP);  

		return resultsBase;
	}
	/**
	 * 查找是否包含五险
	 * @return
	 */
	public SalaryTemplate  getIsFiveBase(Integer templateId)
	{
		return (SalaryTemplate)em.createQuery("select s from SalaryTemplate s where templateId=?")
			   .setParameter(1, templateId).getSingleResult();
		
	}
	
	/**
	 * 获取个税起征点
	 * @return
	 */
	public TaxOfPerson getTaxOfPerson()
	{
		try {
			Query query=em.createQuery("select t from TaxOfPerson t ");
			return (TaxOfPerson)query.getSingleResult();
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
	 * @date   2013-09-01
	 * 上传的员工进行匹配
	 */

	public List<String> isExitUploadEnterpriseEmployees(List<EnterpriseEmployees> enterpriseEmployeesListPO,EmployeesSalaryDetail employeesSalaryDetailVO) 
	{
		
		
				List<String>   employeesName=new ArrayList<String>();
				int count=0;        //记录存在几个相同名字
				int cardnumber=0;   //记录存在几个相同身份证
				boolean falg=false; //记录是否匹配
				int  templCar=0;    //记录总共多少个相同身份证号码
				boolean isCar=false;
			   
			   String detailName=employeesSalaryDetailVO.getEmployeesName();
			   String detailCar=employeesSalaryDetailVO.getCardNumber();
			   
			   for ( EnterpriseEmployees enterpriseEmployees : enterpriseEmployeesListPO)
			   {           
				           String empCard=enterpriseEmployees.getCardNumber()==null?"":enterpriseEmployees.getCardNumber();
				           String employeName=enterpriseEmployees.getEmployeesName()==null?"":enterpriseEmployees.getEmployeesName();
						   if(detailName.equals(employeName))
						   {       falg=true;
						   		   count++;
								   if(count>0)
								   {
										 if(empCard.equals(detailCar))
										  {
											     cardnumber++;
											     if(cardnumber>1)
											      {
											    	 templCar++;
											    	 isCar=true;
											      }   										     
										   } 
								   }
						   } 
				    }
			   		if(falg==false)
			   		{
			   			employeesName.add("数据库无法匹配不存在此员工: "+detailName);
			   		}
			   		if(isCar==true)
			   		{
			   			employeesName.add("数据库存在："+count+"个: "+detailName+" 身份证号:"+detailCar+" 相同 : "+templCar+" 个");
			   		}
		return employeesName;
	}
	
	/**
	 * 记录上传的员工与数据库的员工匹配成功；设置每个字段数据
	 * @param enterpriseEmployeesListPO
	 * @param employeesSalaryDetailVO
	 * @return
	 */
	public EmployeesSalaryDetail recordEnterpriseEmployeesBySalaryDetail(List<EnterpriseEmployees> enterpriseEmployeesListPO,String[] fileDate,int number)
	{
	
		
		Double bonusesTotal=0.0;
		String carNumber = null;     //身份证
		
		EmployeesSalaryDetail employeesSalaryDetail=new EmployeesSalaryDetail();
		carNumber=fileDate[3]==null?"":fileDate[3].toString();
		if(!StringUtil.isEmpty(fileDate[1].toString()))
		{
			for (EnterpriseEmployees emp : enterpriseEmployeesListPO) 
			{
				    if(fileDate[1].equals(emp.getEmployeesName()))
				    {
						    if(StringUtil.isEmpty(emp.getCardNumber()))
						    {
						    	  
								    if(StringUtil.isEmpty(carNumber))return null;
							    	if(!StringUtil.isEmpty(carNumber))
							    	{
							    		EnterpriseEmployees  tempEmployees=new EnterpriseEmployees();
							    		tempEmployees.setCardNumber(carNumber);
							    		tempEmployees.setEmployeesId(emp.getEmployeesId());
							    		//更新员工的身份证号码
							    		updateEmployeesCarNumber(tempEmployees);
							    	}
	
						     }
					    	    employeesSalaryDetail.setWage(new BigDecimal(fileDate[2].toString()==null?null:fileDate[2].toString()));
					    	    employeesSalaryDetail.setCardNumber(carNumber);
					    	    employeesSalaryDetail.setEnterpriseEmployees(emp);
					    	    employeesSalaryDetail.setEmployeesName(emp.getEmployeesName());
					    	    employeesSalaryDetail.setBankCardNumber(emp.getBankCardNumber());
					    	    
							    
							    //服务费
							    employeesSalaryDetail.setServiceCharge(new BigDecimal(emp.getServiceCost()==null?"0.0":emp.getServiceCost().toString()));
							    
							    //大病统筹
							    employeesSalaryDetail.setMorbidityStatistics(new BigDecimal(emp.getSeriousDiseaseBase()==null?"0.0".toString():emp.getSeriousDiseaseBase().toString()));
						    	
							  
						    	 //备注
						    	 employeesSalaryDetail.setNote(fileDate[4].toString()==null?"0.0":fileDate[4].toString());
		
								//统计excel导入各种奖金
								for(int i=5;i<number;i++)
								{
									Double bonuses=Double.valueOf(fileDate[i]==null?"0.0":fileDate[i].toString());
									if(bonuses==null)continue;
										bonusesTotal+=bonuses;
		
								}
								employeesSalaryDetail.setBonus(new BigDecimal(bonusesTotal));
						    
								//电脑补贴
								employeesSalaryDetail.setSubsidies(new BigDecimal(0.00));
				    }
			  }
		
		}
		
		return employeesSalaryDetail;
	}
	/**
	 * 更新员工的身份证号码
	 */
	public void updateEmployeesCarNumber(EnterpriseEmployees enterpriseEmployees){
		try {
			em.createQuery("update EnterpriseEmployees set cardNumber=?1 where employeesId=?2").
			setParameter(1, enterpriseEmployees.getCardNumber()).setParameter(2, enterpriseEmployees.getEmployeesId())
			.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 获取当前企业员工工资
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeesSalaryDetail>  getAllEmployeesSalaryDetail(Integer enterpriseId,Integer budgetId)
	{
		Query query=em.createQuery("select e from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");
		
		return query.setParameter(1, enterpriseId).setParameter(2, budgetId).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 统计预算工资
	 */
	public List<EmployeesSalaryDetail> findImportEmployeesSalaryDetailStatistics(Integer budgetId,Integer enterpriseId) {
		
		Query query=em.createQuery("select e from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");
		
		return query.setParameter(1, enterpriseId).setParameter(2, budgetId).getResultList();

	}
	/**
	 * 统计开票总额
	 * 
	 */
	public BigDecimal   getInvoiceTotal(Integer enterpriseId,Integer budgettableId)
	{
		
		Query query=em.createQuery("select  sum(e.wage+e.serviceCharge+"+
          "e.moneyToCards+e.enterpriseTax+"+
          "e.personalTax+e.socialInsuranceBase+"+
          "e.enterprisePensionInsurance+"+
          "e.personalPensionInsurance+"+
          "e.enterpriseUnemploymentInsurance+"+
          "e.personalUnemploymentInsurance) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");
		
		return (BigDecimal)query.setParameter(1, enterpriseId).setParameter(2, budgettableId).getSingleResult();
		
	}
	/**
	 * 统计工资总额（元）
	 */
	public BigDecimal getWageTotal(Integer enterpriseId,Integer budgettableId)
	{
		Query query=null;
		try {
			
			query=em.createQuery("select sum(e.wage) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");		
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (BigDecimal)query.setParameter(1, enterpriseId).setParameter(2, budgettableId).getSingleResult();
	}
	/**
	 * 统计发放人数
	 */
	public long getNumberPersonlTotal(Integer enterpriseId,Integer budgettableId)
	{
		Query query=null;
		try {
			
			query=em.createQuery("select count(e.salaryId) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");		
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (Long)query.setParameter(1, enterpriseId).setParameter(2, budgettableId).getSingleResult();
	}
	
	/**
	 * 统计服务费总额
	 */
	public BigDecimal getServiceTotal(Integer enterpriseId,Integer budgettableId)
	{
		Query query=null;
		try {
			
			query=em.createQuery("select sum(e.serviceCharge) from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");		
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (BigDecimal)query.setParameter(1, enterpriseId).setParameter(2, budgettableId).getSingleResult();
	}
	/**
	 * 统计统计五险总额
	 */
	public BigDecimal getFiveInsuranceTotal(Integer enterpriseId,Integer budgettableId)
	{
		Query query=null;
		try {
			
			query=em.createQuery("select sum("+
					"e.enterprisePensionInsurance+" +
					"e.personalPensionInsurance+" +
					"e.enterpriseUnemploymentInsurance+" +
					"e.personalUnemploymentInsurance+" +
					"e.enterpriseBirthInsurance+" +
					"e.enterpriseInductrialInjuryBase+" +
					"e.enterpriseMedicalBase+" +
					"e.personalMedicalBase) " +
					"from EmployeesSalaryDetail e where e.enterprise.enterpriseId=?1 and e.budgettableId=?2");		
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return (BigDecimal)query.setParameter(1, enterpriseId).setParameter(2, budgettableId).getSingleResult();
	}
	
	public void updateEmployeesSalaryDetail(EmployeesSalaryDetail employeesSalaryDetail) {
		 try {
			 em.createQuery("update EmployeesSalaryDetail e set e.wage=?1,e.bonus=?2,e.subsidies=?3 where e.salaryId=?4")
			 .setParameter(1, employeesSalaryDetail.getWage()).setParameter(2, employeesSalaryDetail.getBonus())
			 .setParameter(3, employeesSalaryDetail.getSubsidies()).setParameter(4, employeesSalaryDetail.getSalaryId())
			 .executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
		
		
	}
	public void deleteEmployeesSalaryDetail(Integer budgetId){
		try {
			em.createQuery("delete EmployeesSalaryDetail e where e.budgettableId=?1")
			.setParameter(1, budgetId).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		
		
	}

	/**
	 * 获取计税规则
	 * @return
	 */
	public InsurancesTax getCustomBonus()
	{
		try {
			return 	(InsurancesTax)em.createQuery("select t from InsurancesTax t ").getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	  
		
	}
	/**
	 * 获取五险一金基数设置
	 * @return
	 */
	public InsurancesBaseSettings getInsurancesBaseSettings()
	{
		
		return (InsurancesBaseSettings)em.createQuery("select t from InsurancesBaseSettings t ").getSingleResult();
		
	}
	
	/**
	 * 获取当前企业所有在职人员
	 * @param enterpriseId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<EnterpriseEmployees> getAllEnterpriseEmployees(Integer enterpriseId)
	{
		try {
			Query query = em.createQuery("select e from EnterpriseEmployees e where e.enterprise.enterpriseId=?1  and e.departure=0 and e.pseudoDelete=0 ");
			return query.setParameter(1, enterpriseId).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	
	}
	public BaseGrid getPayrollStaff(BaseGrid baseGrid) {
		
		 String defaultHql="select e from EmployeesSalaryDetail e where e.budgettableId=50";
		 String defaultValue="";
		 
		 GridParameter gridParameter = GridParameter.getInstance()
		 .setModuleNameVal("employeesSalaryDetail").setObjectAliasVal("employeesSalaryDetail")
         .setDefaultHQLVal(defaultHql).setDefaultValueVal(defaultValue);
		 
        return searchImpl.findPage(gridParameter, baseGrid);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<EmployeesSalaryDetail> getPayrollStaff(Integer budgetId) {
		String defaultHql="select e from EmployeesSalaryDetail e where e.budgettableId=?1";
		return em.createQuery(defaultHql).setParameter(1, budgetId).getResultList();
	}
	
	
}
