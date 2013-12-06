package cn.fm.bean.salary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;


@SuppressWarnings("serial")
@Entity
public class EmployeesSalaryDetail implements Serializable
{
	
	private Integer  salaryId;
	
	private String   employeesName;
	
	/*工资*/ 
	private BigDecimal  wage;
	
	 /*奖金 */
	private BigDecimal  bonus;
	
	/*电脑补贴 */
	private BigDecimal  subsidies;
	
	/*应发工资 */
	private BigDecimal  shouldPay;
	  
	/*社会保险基数*/
	private BigDecimal  socialInsuranceBase;
	 
	/* 养老保险  企业 */
	private BigDecimal  enterprisePensionInsurance;
	 
	/*个人养老保险 */
	private BigDecimal  personalPensionInsurance;

	/*失业保险  企业 */
	private BigDecimal  enterpriseUnemploymentInsurance;
	  
	/*失业保险  个人*/
	private BigDecimal  personalUnemploymentInsurance;
	
	/*生育保险基数  */
	private BigDecimal  birthInsuranceBase;
	
	/*生育（企业) */
	private BigDecimal  enterpriseBirthInsurance;
	
	/*工伤基数 */
	private BigDecimal  inductrialInjuryBase;
	
	/*工伤（企业）*/
	private BigDecimal  enterpriseInductrialInjuryBase;
 
	/*基本医疗保险   缴费基数*/
	private BigDecimal  medicalPaymentBase;
	  
	/*基本医疗保险  企业*/
	private BigDecimal  enterpriseMedicalBase;
	  
	/*基本医疗保险  个人 */
	private BigDecimal  personalMedicalBase;
	    
	/*住房公积金-缴费基数*/
	private BigDecimal  housingReserveBase;
	 
	/*住房公积金-企业 */
	private BigDecimal  enterpriseReserveBase;
	
	/*住房公积金 - 个人 */
	private BigDecimal  personalReserveBase;
	
	/*疾病统计 */
	private BigDecimal  morbidityStatistics;

	/*小计- 企业  */
	private BigDecimal  enterpriseSubtotal;
	
	/*小计- 个人 */
	private BigDecimal  personalSubtotal;

	/*税前工资 */
	private BigDecimal  beforeSalary;

	/*企业 个税*/
	private BigDecimal  enterpriseTax;
	/*个人 个税*/
	private BigDecimal  personalTax;
	/*服务费  */
	private BigDecimal  serviceCharge;
	
	/*合计（企业应付）*/
	private BigDecimal  aggregate;

	/*到卡金额 */
	private BigDecimal  moneyToCards;
	
	/*备注**/
	private String      note;
	
	/*生成哪月工资？*/
	private Date    salaryDate;

	private Date        createDate=new Date();
	
	private Integer     budgettableId;
	
	/*特殊养老保险补贴*/
	private  BigDecimal  specialOldSubsidies;
	
	/*特殊工伤生育补贴*/
	private  BigDecimal   specialUnemploymentSubsidies;
	
	/*特殊医保补贴*/
	private  BigDecimal   specialHealthCareSubsidies;
	
	/*特殊公积金补贴*/
	private  BigDecimal   specialAccumulationFundSubsidies;
	

	private CreateSalaryBudgetTable   createSalaryBudgetTable;
	
	/*身份证号*/
	private String  cardNumber;
	
	
	private String  bankCardNumber;
	
	/*开户银行*/
	private String  bank;
	
	private EnterpriseEmployees    enterpriseEmployees;
	
	private Enterprise			   enterprise;
	
	
	@Column(length=20)
	public String getBankCardNumber() {
		return bankCardNumber;
	}

	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}

	@Column(length=20)
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Id @GeneratedValue
	public Integer getSalaryId() {
		return salaryId;
	}

	public void setSalaryId(Integer salaryId) {
		this.salaryId = salaryId;
	}
	
	@Column(length=50)
	public Integer getBudgettableId() {
		return budgettableId;
	}

	public void setBudgettableId(Integer budgettableId) {
		this.budgettableId = budgettableId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(length=80,scale=2)
	public BigDecimal getWage() {
		return wage;
	}

	public void setWage(BigDecimal wage) {
		this.wage = wage;
	}
	@Column(length=80,scale=2)
	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}
	@Column(length=80,scale=2)
	public BigDecimal getSubsidies() {
		return subsidies;
	}

	public void setSubsidies(BigDecimal subsidies) {
		this.subsidies = subsidies;
	}
	@Column(length=80,scale=2)
	public BigDecimal getShouldPay() {
		return shouldPay;
	}

	public void setShouldPay(BigDecimal shouldPay) {
		this.shouldPay = shouldPay;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getSocialInsuranceBase() {
		return socialInsuranceBase;
	}

	public void setSocialInsuranceBase(BigDecimal socialInsuranceBase) {
		this.socialInsuranceBase = socialInsuranceBase;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getEnterprisePensionInsurance() {
		return enterprisePensionInsurance;
	}

	public void setEnterprisePensionInsurance(BigDecimal enterprisePensionInsurance) {
		this.enterprisePensionInsurance = enterprisePensionInsurance;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getPersonalPensionInsurance() {
		return personalPensionInsurance;
	}

	public void setPersonalPensionInsurance(BigDecimal personalPensionInsurance) {
		this.personalPensionInsurance = personalPensionInsurance;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getEnterpriseUnemploymentInsurance() {
		return enterpriseUnemploymentInsurance;
	}

	public void setEnterpriseUnemploymentInsurance(
			BigDecimal enterpriseUnemploymentInsurance) {
		this.enterpriseUnemploymentInsurance = enterpriseUnemploymentInsurance;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getPersonalUnemploymentInsurance() {
		return personalUnemploymentInsurance;
	}

	public void setPersonalUnemploymentInsurance(
			BigDecimal personalUnemploymentInsurance) {
		this.personalUnemploymentInsurance = personalUnemploymentInsurance;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getBirthInsuranceBase() {
		return birthInsuranceBase;
	}

	public void setBirthInsuranceBase(BigDecimal birthInsuranceBase) {
		this.birthInsuranceBase = birthInsuranceBase;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getEnterpriseBirthInsurance() {
		return enterpriseBirthInsurance;
	}

	public void setEnterpriseBirthInsurance(BigDecimal enterpriseBirthInsurance) {
		this.enterpriseBirthInsurance = enterpriseBirthInsurance;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getInductrialInjuryBase() {
		return inductrialInjuryBase;
	}

	public void setInductrialInjuryBase(BigDecimal inductrialInjuryBase) {
		this.inductrialInjuryBase = inductrialInjuryBase;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getEnterpriseInductrialInjuryBase() {
		return enterpriseInductrialInjuryBase;
	}

	public void setEnterpriseInductrialInjuryBase(
			BigDecimal enterpriseInductrialInjuryBase) {
		this.enterpriseInductrialInjuryBase = enterpriseInductrialInjuryBase;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getMedicalPaymentBase() {
		return medicalPaymentBase;
	}

	public void setMedicalPaymentBase(BigDecimal medicalPaymentBase) {
		this.medicalPaymentBase = medicalPaymentBase;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getEnterpriseMedicalBase() {
		return enterpriseMedicalBase;
	}

	public void setEnterpriseMedicalBase(BigDecimal enterpriseMedicalBase) {
		this.enterpriseMedicalBase = enterpriseMedicalBase;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getPersonalMedicalBase() {
		return personalMedicalBase;
	}

	public void setPersonalMedicalBase(BigDecimal personalMedicalBase) {
		this.personalMedicalBase = personalMedicalBase;
	}
	
	@Column(length=80 ,scale=2)
	public BigDecimal getHousingReserveBase() {
		return housingReserveBase;
	}

	public void setHousingReserveBase(BigDecimal housingReserveBase) {
		this.housingReserveBase = housingReserveBase;
	}

	@Column(length=80 ,scale=2)
	public BigDecimal getEnterpriseReserveBase() {
		return enterpriseReserveBase;
	}

	public void setEnterpriseReserveBase(BigDecimal enterpriseReserveBase) {
		this.enterpriseReserveBase = enterpriseReserveBase;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getPersonalReserveBase() {
		return personalReserveBase;
	}

	public void setPersonalReserveBase(BigDecimal personalReserveBase) {
		this.personalReserveBase = personalReserveBase;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getMorbidityStatistics() {
		return morbidityStatistics;
	}

	public void setMorbidityStatistics(BigDecimal morbidityStatistics) {
		this.morbidityStatistics = morbidityStatistics;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getEnterpriseSubtotal() {
		return enterpriseSubtotal;
	}

	public void setEnterpriseSubtotal(BigDecimal enterpriseSubtotal) {
		this.enterpriseSubtotal = enterpriseSubtotal;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getPersonalSubtotal() {
		return personalSubtotal;
	}
	
	public void setPersonalSubtotal(BigDecimal personalSubtotal) {
		this.personalSubtotal = personalSubtotal;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getBeforeSalary() {
		return beforeSalary;
	}

	public void setBeforeSalary(BigDecimal beforeSalary) {
		this.beforeSalary = beforeSalary;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getEnterpriseTax() {
		return enterpriseTax;
	}

	public void setEnterpriseTax(BigDecimal enterpriseTax) {
		this.enterpriseTax = enterpriseTax;
	}
	
	@Column(length=20)
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	@Column(length=80 ,scale=2)
	public BigDecimal getPersonalTax() {
		return personalTax;
	}

	public void setPersonalTax(BigDecimal personalTax) {
		this.personalTax = personalTax;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getAggregate() {
		return aggregate;
	}

	public void setAggregate(BigDecimal aggregate) {
		this.aggregate = aggregate;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getMoneyToCards() {
		return moneyToCards;
	}

	public void setMoneyToCards(BigDecimal moneyToCards) {
		this.moneyToCards = moneyToCards;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getEmployeesName() {
		return employeesName;
	}

	public void setEmployeesName(String employeesName) {
		this.employeesName = employeesName;
	}
	@Temporal(TemporalType.DATE)
	public Date getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}

	@ManyToOne(cascade={CascadeType.REFRESH})
	@JoinColumn(name="employees_id")
	public EnterpriseEmployees getEnterpriseEmployees() {
		return enterpriseEmployees;
	}

	public void setEnterpriseEmployees(EnterpriseEmployees enterpriseEmployees) {
		this.enterpriseEmployees = enterpriseEmployees;
	}
	
	@Column(length=80 ,scale=2)
	public BigDecimal getSpecialOldSubsidies() {
		return specialOldSubsidies;
	}

	public void setSpecialOldSubsidies(BigDecimal specialOldSubsidies) {
		this.specialOldSubsidies = specialOldSubsidies;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getSpecialUnemploymentSubsidies() {
		return specialUnemploymentSubsidies;
	}
	
	public void setSpecialUnemploymentSubsidies(
			BigDecimal specialUnemploymentSubsidies) {
		this.specialUnemploymentSubsidies = specialUnemploymentSubsidies;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getSpecialHealthCareSubsidies() {
		return specialHealthCareSubsidies;
	}

	public void setSpecialHealthCareSubsidies(BigDecimal specialHealthCareSubsidies) {
		this.specialHealthCareSubsidies = specialHealthCareSubsidies;
	}
	@Column(length=80 ,scale=2)
	public BigDecimal getSpecialAccumulationFundSubsidies() {
		return specialAccumulationFundSubsidies;
	}

	public void setSpecialAccumulationFundSubsidies(
			BigDecimal specialAccumulationFundSubsidies) {
		this.specialAccumulationFundSubsidies = specialAccumulationFundSubsidies;
	}
	
	
	@ManyToOne(cascade={CascadeType.REFRESH},optional=true)
	@JoinColumn(name="budget_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public CreateSalaryBudgetTable getCreateSalaryBudgetTable() {
		return createSalaryBudgetTable;
	}

	public void setCreateSalaryBudgetTable(
			CreateSalaryBudgetTable createSalaryBudgetTable) {
		this.createSalaryBudgetTable = createSalaryBudgetTable;
	}
	
	@ManyToOne(cascade={CascadeType.REFRESH},optional=false)
	@JoinColumn(name="enterprise_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Enterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
}
