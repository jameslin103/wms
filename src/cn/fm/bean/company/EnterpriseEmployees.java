package cn.fm.bean.company;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SuppressWarnings("serial")
@Entity
public class EnterpriseEmployees implements Serializable{
	
	private Integer employeesId;
	/*员工姓名 */
	private String  employeesName;
	/*员工性别*/
	private Integer employeesSex;
	/*户口性质  0非农  1 农村*/
	private Integer householdRegister;
	/*籍贯*/
	private String  nativePlace;
	/*照片  0.没有 1.有*/
	private Integer photo;
	/*身份证号*/
	private String  cardNumber;
	/*电话号码*/
	private String  phone;
	/*服务费用*/
	private Double  serviceCost;
	/*社会保险*/
	private Integer  socialInsurance;
	/*生育保险*/
	private Integer  fertilityInsurance;
	/*合同编号*/
	private String  contractNo;
	/*家庭住址*/
	private String  homeAddress;
	/*银行卡号*/
	private String  bankCardNumber;
	/*开户银行*/
	private String  bank;
	/*行业*/
	private String  industry;
	/*岗位*/
	private String  jobs;
	/*婚姻状况*/
	private Integer  maritalStatus;
	/*文化程度*/
	private Integer  levelEducation;
	/*合同期限 开始*/
	private Date  startContractDeadline;
	/*合同期限 终止*/
	private Date  endContractDeadline;
	/*是否参保*/
	private  Integer whetherGinseng;
	/*参保类型  0.医保   1.  社保 2.  公积金*/
	private Integer  ginsengProtectType;
	/*参保性质  0新增  1续保*/
	private Integer ginsengProtectNature;
	/*参保日期*/
	private Date  cinsengDate;
	/*参保基数 0.默认基数 1.个性设置*/
	private Integer  base;
	/*个税缴纳方式  0个人缴纳，  1企业缴纳*/
	private Integer  paymentWay;
	/*工伤基数*/
	private Integer  inductrialBase;
	/*住房公积金基数*/
	private Integer  housingFund;
	/*基本医疗保险基数*/
	private Integer  basicMedical;
	/*伪删除  0 隐藏    1显示*/
	private Integer  pseudoDelete;
	
	
	
	
	
	@Id @GeneratedValue
	public Integer getEmployeesId() {
		return employeesId;
	}
	public void setEmployeesId(Integer employeesId) {
		this.employeesId = employeesId;
	}
	@Column(length=30,nullable=false)
	public String getEmployeesName() {
		return employeesName;
	}
	public void setEmployeesName(String employeesName) {
		this.employeesName = employeesName;
	}
	@Column(length=2,nullable=false)
	public Integer getEmployeesSex() {
		return employeesSex;
	}
	public void setEmployeesSex(Integer employeesSex) {
		this.employeesSex = employeesSex;
	}
	@Column(length=2,nullable=false)
	public Integer getHouseholdRegister() {
		return householdRegister;
	}
	public void setHouseholdRegister(Integer householdRegister) {
		this.householdRegister = householdRegister;
	}
	@Column(length=20,nullable=false)
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	@Column(length=1,nullable=false)
	public Integer getPhoto() {
		return photo;
	}
	public void setPhoto(Integer photo) {
		this.photo = photo;
	}
	@Column(length=19,nullable=false,unique=true)
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	@Column(length=15,nullable=false)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(length=30,nullable=false)
	public Double getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(Double serviceCost) {
		this.serviceCost = serviceCost;
	}
	@Column(length=1,nullable=false)
	public Integer getSocialInsurance() {
		return socialInsurance;
	}
	public void setSocialInsurance(Integer socialInsurance) {
		this.socialInsurance = socialInsurance;
	}
	@Column(length=1,nullable=false)
	public Integer getFertilityInsurance() {
		return fertilityInsurance;
	}
	public void setFertilityInsurance(Integer fertilityInsurance) {
		this.fertilityInsurance = fertilityInsurance;
	}
	@Column(length=20,nullable=false)
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	@Column(length=30,nullable=false)
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	@Column(length=20,nullable=false)
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	@Column(length=20,nullable=false)
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	@Column(length=20,nullable=false)
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	@Column(length=20,nullable=false)
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	@Column(length=1,nullable=false)
	public Integer getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	@Column(length=20,nullable=false)
	public Integer getLevelEducation() {
		return levelEducation;
	}
	public void setLevelEducation(Integer levelEducation) {
		this.levelEducation = levelEducation;
	}
	@Temporal(TemporalType.TIMESTAMP) @Column(nullable=false)
	public Date getStartContractDeadline() {
		return startContractDeadline;
	}
	public void setStartContractDeadline(Date startContractDeadline) {
		this.startContractDeadline = startContractDeadline;
	}
	@Temporal(TemporalType.DATE) @Column(nullable=false)
	public Date getEndContractDeadline() {
		return endContractDeadline;
	}
	public void setEndContractDeadline(Date endContractDeadline) {
		this.endContractDeadline = endContractDeadline;
	}
	@Column(length=1,nullable=false)
	public Integer getGinsengProtectType() {
		return ginsengProtectType;
	}
	public void setGinsengProtectType(Integer ginsengProtectType) {
		this.ginsengProtectType = ginsengProtectType;
	}
	@Column(length=1,nullable=false)
	public Integer getGinsengProtectNature() {
		return ginsengProtectNature;
	}
	public void setGinsengProtectNature(Integer ginsengProtectNature) {
		this.ginsengProtectNature = ginsengProtectNature;
	}
	@Temporal(TemporalType.DATE) @Column(nullable=false)
	public Date getCinsengDate() {
		return cinsengDate;
	}
	public void setCinsengDate(Date cinsengDate) {
		this.cinsengDate = cinsengDate;
	}
	@Column(length=1,nullable=false)
	public Integer getBase() {
		return base;
	}
	public void setBase(Integer base) {
		this.base = base;
	}
	@Column(length=1,nullable=false)
	public Integer getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(Integer paymentWay) {
		this.paymentWay = paymentWay;
	}
	@Column(length=1,nullable=false)
	public Integer getInductrialBase() {
		return inductrialBase;
	}
	public void setInductrialBase(Integer inductrialBase) {
		this.inductrialBase = inductrialBase;
	}
	@Column(length=1,nullable=false)
	public Integer getHousingFund() {
		return housingFund;
	}
	public void setHousingFund(Integer housingFund) {
		this.housingFund = housingFund;
	}
	@Column(length=1,nullable=false)
	public Integer getBasicMedical() {
		return basicMedical;
	}
	public void setBasicMedical(Integer basicMedical) {
		this.basicMedical = basicMedical;
	}
	@Column(length=1,nullable=false)
	public Integer getPseudoDelete() {
		if(pseudoDelete==null)
			 pseudoDelete=0;
		return pseudoDelete;
	}
	public void setPseudoDelete(Integer pseudoDelete) {
		this.pseudoDelete = pseudoDelete;
	}
	@Column(length=2,nullable=false)
	public Integer getWhetherGinseng() {
		return whetherGinseng;
	}
	public void setWhetherGinseng(Integer whetherGinseng) {
		this.whetherGinseng = whetherGinseng;
	}
	
		
	
}
