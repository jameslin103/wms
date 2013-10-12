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
import cn.fm.bean.salary.EmployeesSalaryDetail;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.bean.salary.TaxOfPerson;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.utils.GenerateSqlFromExcel;
import cn.fm.utils.PersonalTaxUtil;
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
	public List<String> saveEmployeesSalaryDetail(File file , String fileName,int number,int readRows,EmployeesSalaryDetail employeesSalaryDetail,Integer templateId)
	{
		List<String>  employeesName=new ArrayList<String>();
	    if(employeesSalaryDetail==null || employeesSalaryDetail.getEnterpriseId()==null)return null;
		GenerateSqlFromExcel excel =new GenerateSqlFromExcel();
		try {
			List<String[]> arrayList=excel.generateStationBugSql(file,fileName,number,readRows);
			if(arrayList.size()==0)return null;
			for (int i = 0; i < arrayList.size(); i++){
				String[] data = arrayList.get(i);
				
				
				List<EnterpriseEmployees> enterpriseEmployeesListPO=getAllEnterpriseEmployees(employeesSalaryDetail.getEnterpriseId());
				
				//匹配姓名是否重复
				EmployeesSalaryDetail detail=refactoringEmployeesSalaryDetailDate(data);
				employeesName=isExitUploadEnterpriseEmployees(enterpriseEmployeesListPO,detail);
				
				
				if(employeesName.size()==0){
					//记录id
					EmployeesSalaryDetail employeesSalaryDetailVO=structureEmployeesSalaryDetail(data,templateId);
					
					employeesSalaryDetailVO.setEmpolyessId(recordEnterpriseEmployeesToId(enterpriseEmployeesListPO,data).getEmpolyessId());
					employeesSalaryDetailVO.setCardNumber(recordEnterpriseEmployeesToId(enterpriseEmployeesListPO,data).getCardNumber());
					
					
					employeesSalaryDetailVO.setSalaryDate(employeesSalaryDetail.getSalaryDate());
					employeesSalaryDetailVO.setEnterpriseId(employeesSalaryDetail.getEnterpriseId());
					employeesSalaryDetailVO.setBudgettableId(employeesSalaryDetail.getBudgettableId());
					super.save(employeesSalaryDetailVO);
				}
				
			}	
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return employeesName;
		
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
	public EmployeesSalaryDetail structureEmployeesSalaryDetail(String[] fileDate ,Integer templateId)
	{
			int rows=10;
			Double  bonusesTotal=0.0; 			 //统计各种奖金
			Double  wage;						 //基本工资
			Double  enterpriseSubtotal;		     //企业小计
			Double  personalSubtotal;			 //个人小计
			Double  beforeSalary;				 //税前工资
			Double  shouldPayTotal;			     //应发工资
			Double  aggregate;					//合计(企业应付)
			Double  fiveBaseTotal = 0.00;		//五险一金总基数
			BigDecimal  personalTax = null;		//个税
		
		
		
		
		EmployeesSalaryDetail   employeesSalaryDetailVO=new EmployeesSalaryDetail();
		try {
			employeesSalaryDetailVO.setEmployeesName(fileDate[1].toString());
			
			/*基本工资*/ 
		    wage=fileDate[2].toString()==null?null:Double.valueOf(fileDate[2].toString());
			employeesSalaryDetailVO.setWage(new BigDecimal(wage));
			
			
			
			//统计excel导入各种奖金
			for(int i=4;i<rows;i++){
				Double bonuses=Double.valueOf(fileDate[i].toString());
					bonusesTotal+=bonuses;

			}
			employeesSalaryDetailVO.setBonus(new BigDecimal(bonusesTotal));
			
			//应发工资=奖金+补贴+工资
			int shouldPay=fileDate[2].toString()==null?null:Integer.parseInt(fileDate[2]);
			    shouldPayTotal=wage+bonusesTotal+shouldPay;
			
			employeesSalaryDetailVO.setShouldPay(new BigDecimal(shouldPayTotal));
			
			employeesSalaryDetailVO.setSubsidies(fileDate[4].toString()==null?null:new BigDecimal(fileDate[4]));
			
			
			
			//计算五险一金规则
			SalaryTemplate salaryTemplate=getIsFiveBase(templateId);
			if(salaryTemplate!=null && salaryTemplate.getStatus()==1)
			{
					EmployeesSalaryDetail employeesSalaryDetailInsurances=toCalculateFiveInsurances();
					employeesSalaryDetailVO.setSocialInsuranceBase(employeesSalaryDetailInsurances.getSocialInsuranceBase());
					employeesSalaryDetailVO.setEnterprisePensionInsurance(employeesSalaryDetailInsurances.getEnterprisePensionInsurance());
					employeesSalaryDetailVO.setPersonalPensionInsurance(employeesSalaryDetailInsurances.getPersonalPensionInsurance());
					employeesSalaryDetailVO.setEnterpriseUnemploymentInsurance(employeesSalaryDetailInsurances.getEnterpriseUnemploymentInsurance());
					employeesSalaryDetailVO.setPersonalUnemploymentInsurance(employeesSalaryDetailInsurances.getPersonalUnemploymentInsurance());
					employeesSalaryDetailVO.setBirthInsuranceBase(employeesSalaryDetailInsurances.getBirthInsuranceBase());
					employeesSalaryDetailVO.setEnterpriseBirthInsurance(employeesSalaryDetailInsurances.getInductrialInjuryBase());
					employeesSalaryDetailVO.setInductrialInjuryBase(employeesSalaryDetailInsurances.getInductrialInjuryBase());
					employeesSalaryDetailVO.setEnterpriseInductrialInjuryBase(employeesSalaryDetailInsurances.getEnterpriseInductrialInjuryBase());
					employeesSalaryDetailVO.setMedicalPaymentBase(employeesSalaryDetailInsurances.getMedicalPaymentBase());
					employeesSalaryDetailVO.setEnterpriseMedicalBase(employeesSalaryDetailInsurances.getEnterpriseMedicalBase());
					employeesSalaryDetailVO.setPersonalMedicalBase(employeesSalaryDetailInsurances.getPersonalMedicalBase());
					employeesSalaryDetailVO.setHousingReserveBase(employeesSalaryDetailInsurances.getHousingReserveBase());
					employeesSalaryDetailVO.setEnterpriseReserveBase(employeesSalaryDetailInsurances.getEnterpriseReserveBase());
					employeesSalaryDetailVO.setPersonalReserveBase(employeesSalaryDetailInsurances.getPersonalReserveBase());
					
					
					//企业小计=(企业)养老保险+(企业)失业保险+生育（企业）+工伤(企业)+住房公积金(企业)+住房公积金(个人)+大病统筹
					 enterpriseSubtotal=enterpriseSubtotal(employeesSalaryDetailInsurances);
					 employeesSalaryDetailVO.setEnterpriseSubtotal(new BigDecimal(enterpriseSubtotal).setScale(2,BigDecimal.ROUND_HALF_DOWN));
					
					//个人小计=(个人)养老保险+(个人)失业保险+(个人)医疗保险+(个人)住房公积金
					personalSubtotal=personalSubtotal(employeesSalaryDetailInsurances);	 
					employeesSalaryDetailVO.setPersonalSubtotal(new BigDecimal(personalSubtotal).setScale(2,BigDecimal.ROUND_HALF_DOWN));
					
					//税前工资=应发工资-(个人)小计
					beforeSalary=shouldPayTotal-personalSubtotal;
					employeesSalaryDetailVO.setBeforeSalary(new BigDecimal(beforeSalary).setScale(2,BigDecimal.ROUND_HALF_DOWN));			
					
					//合计(企业应付)=应发工资+(企业)小计+服务费
					aggregate=shouldPayTotal+enterpriseSubtotal+Double.valueOf(employeesSalaryDetailInsurances.getServiceCharge().toString());
					employeesSalaryDetailVO.setAggregate(new BigDecimal(aggregate).setScale(2,BigDecimal.ROUND_HALF_DOWN));
					
					//到卡金额=税前工资-个人税
					Double moneyToCards=beforeSalary-Double.valueOf(employeesSalaryDetailInsurances.getPersonalTax().toString());
					employeesSalaryDetailVO.setMoneyToCards(new BigDecimal(moneyToCards).setScale(2,BigDecimal.ROUND_HALF_DOWN));

			}
			//个税起征点计算
			personalTax=personalTax(Double.valueOf(wage),Double.valueOf(fiveBaseTotal)).setScale(2,BigDecimal.ROUND_HALF_DOWN);
			//个税
			employeesSalaryDetailVO.setPersonalTax(personalTax);
			//企业个税
			employeesSalaryDetailVO.setEnterpriseTax(new BigDecimal("0.00"));
			//备注
			employeesSalaryDetailVO.setNote(fileDate[4].toString()==null?null:fileDate[4].toString());
			
			
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
	public Double   enterpriseSubtotal(EmployeesSalaryDetail employeesSalaryDetailInsurances)
	{
		
	  Double enterpriseSubtotal=Double.valueOf(employeesSalaryDetailInsurances.getEnterprisePensionInsurance().toString())+
							    Double.valueOf(employeesSalaryDetailInsurances.getEnterpriseUnemploymentInsurance().toString())+
								Double.valueOf(employeesSalaryDetailInsurances.getEnterpriseBirthInsurance().toString())+
								Double.valueOf(employeesSalaryDetailInsurances.getEnterpriseInductrialInjuryBase().toString())+
								Double.valueOf(employeesSalaryDetailInsurances.getEnterpriseReserveBase().toString())+
								Double.valueOf(employeesSalaryDetailInsurances.getPersonalReserveBase().toString())+
								Double.valueOf(employeesSalaryDetailInsurances.getMorbidityStatistics().toString());

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
		BigDecimal personalTax=null;
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
	 * 记录上传的员工与数据库的员工匹配成功；记录id
	 * @param enterpriseEmployeesListPO
	 * @param employeesSalaryDetailVO
	 * @return
	 */
	public EmployeesSalaryDetail recordEnterpriseEmployeesToId(List<EnterpriseEmployees> enterpriseEmployeesListPO,String[] excelDate)
	{
	
		EmployeesSalaryDetail employeesSalaryDetail=new EmployeesSalaryDetail();
		if(!StringUtil.isEmpty(excelDate.toString()))
		{
			for (EnterpriseEmployees emp : enterpriseEmployeesListPO) 
			{
				    if(excelDate[2].equals(emp.getEmployeesName()))
				    employeesSalaryDetail.setEmpolyessId(emp.getEmployeesId());
				    employeesSalaryDetail.setServiceCharge(new BigDecimal(emp.getServiceCost()));
				    if(!StringUtil.isEmpty(emp.getCardNumber())){
				    	
						employeesSalaryDetail.setCardNumber(emp.getCardNumber());
						
						
				    }else{
				    	
				    	
				    	
				    }			
			}
		
		}
		
		return employeesSalaryDetail;
	}

	
	/**
	 * 获取当前企业员工工资
	 */
	@SuppressWarnings("unchecked")
	public List<EmployeesSalaryDetail>  getAllEmployeesSalaryDetail(Integer enterpriseId,Integer budgetId)
	{
		Query query=em.createQuery("select e from EmployeesSalaryDetail e where e.enterpriseId=?1 and e.budgettableId=?2");
		
		return query.setParameter(1, enterpriseId).setParameter(2, budgetId).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * 统计预算工资
	 */
	public List<EmployeesSalaryDetail> findImportEmployeesSalaryDetailStatistics(Integer budgetId,Integer enterpriseId) {
		
		Query query=em.createQuery("select e from EmployeesSalaryDetail e where e.enterpriseId=?1 and e.budgettableId=?2");
		
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
          "e.personalUnemploymentInsurance) from EmployeesSalaryDetail e where e.enterpriseId=?1 and e.budgettableId=?2");
		
		return (BigDecimal)query.setParameter(1, enterpriseId).setParameter(2, budgettableId).getSingleResult();
		
	}
	/**
	 * 统计工资总额（元）
	 */
	public BigDecimal getWageTotal(Integer enterpriseId,Integer budgettableId)
	{
		Query query=null;
		try {
			
			query=em.createQuery("select sum(e.wage) from EmployeesSalaryDetail e where e.enterpriseId=?1 and e.budgettableId=?2");		
				
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
			
			query=em.createQuery("select count(e.salaryId) from EmployeesSalaryDetail e where e.enterpriseId=?1 and e.budgettableId=?2");		
				
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
			
			query=em.createQuery("select sum(e.serviceCharge) from EmployeesSalaryDetail e where e.enterpriseId=?1 and e.budgettableId=?2");		
				
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
					"from EmployeesSalaryDetail e where e.enterpriseId=?1 and e.budgettableId=?2");		
				
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
		
	  return (InsurancesTax)em.createQuery("select t from InsurancesTax t ").getSingleResult();
		
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
		Query query = em.createQuery("select e from EnterpriseEmployees e where e.enterprise.enterpriseId=?1  and e.departure=0 and e.pseudoDelete!=0 ");
		return query.setParameter(1, enterpriseId).getResultList();
	}
	
	
}
