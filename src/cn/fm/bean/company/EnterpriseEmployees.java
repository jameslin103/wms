package cn.fm.bean.company;
import java.io.Serializable;
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
import javax.persistence.Transient;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;


@SuppressWarnings("serial")
@Entity
public class EnterpriseEmployees implements Serializable{
	
	private Integer employeesId;
	/*员工姓名 */
	private String  employeesName;
	/*员工性别*/
	private String employeesSex;
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
	private Double  socialInsurance;
	/*生育保险*/
	private Double  fertilityInsurance;
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
	private String  levelEducation;
	/*合同期限 开始*/
	private Date  startContractDeadline;
	/*合同期限 终止*/
	private Date  endContractDeadline;
	/*是否参保*/
	private  Integer whetherGinseng;
	
	/*医保*/
	private String  sociaSecurity;
	
	/*社保 */
	private String  healthCare;
	
	/*公积金*/
	private String  accumulationFund;
	
	/*大病统筹*/
	private String  seriousDisease;
	
	/*大病统筹基数*/
	private Double  seriousDiseaseBase;
	
	/*参保性质  0新增  1续保 2 减员*/
	private String ginsengProtectNature;
	
	/*增员*/
	//private Integer  recruiting;
	
	/*续保*/
	//private Integer  renewal;
 
	/*参保日期*/
	private Date  cinsengDate;
	
	/*参保基数 0.默认基数 1.个性设置*/
	private Integer  base;
	
	/*个税缴纳方式  0个人缴纳，  1企业缴纳*/
	private String  paymentWay;
	
	/*工伤基数*/
	private Double  inductrialBase;
	
	/*住房公积金基数*/
	private Double  housingFund;
	
	/*基本医疗保险基数*/
	private Double  basicMedical;
	
	/*伪删除  1 隐藏    0显示*/
	private Integer  pseudoDelete=0;
	
	private String  note;
	/*离职员工*/
	private Integer departure;
	
	/**增员**/
	private  long addCount;
	
	/**续保**/
	private  long renewalCount;
	
	/**参保**/
	private  long whetherGinsengCount;
	
	/*减员*/
	private Integer  reduction=0;
	
	/*减员日期*/
	private Date reductionDate;
	
	private Date     createDate=new Date();
	
	private Enterprise  enterprise;
	
	
	
	@Temporal(TemporalType.DATE)
	public Date getReductionDate() {
		return reductionDate;
	}
	public void setReductionDate(Date reductionDate) {
		this.reductionDate = reductionDate;
	}
	@Column(length=2)
	public Integer getReduction() {
		return reduction;
	}
	public void setReduction(Integer reduction) {
		this.reduction = reduction;
	}
	@Column(length=1)
	public Integer getDeparture() {
		return departure;
	}
	public void setDeparture(Integer departure) {
		this.departure = departure;
	}
	@Column(length=50)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Id @GeneratedValue
	public Integer getEmployeesId() {
		return employeesId;
	}
	public void setEmployeesId(Integer employeesId) {
		this.employeesId = employeesId;
	}
	@Column(length=30)
	public String getEmployeesName() {
		return employeesName;
	}
	public void setEmployeesName(String employeesName) {
		this.employeesName = employeesName;
	}
	@Column(length=2)
	public String getEmployeesSex() {
		return employeesSex;
	}
	public void setEmployeesSex(String employeesSex) {
		this.employeesSex = employeesSex;
	}
	@Column(length=2)
	public Integer getHouseholdRegister() {
		return householdRegister;
	}
	public void setHouseholdRegister(Integer householdRegister) {
		this.householdRegister = householdRegister;
	}
	@Column(length=20)
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	@Column(length=1)
	public Integer getPhoto() {
		return photo;
	}
	public void setPhoto(Integer photo) {
		this.photo = photo;
	}
	@Column(length=20)
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	@Column(length=15)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Column(length=30)
	public Double getServiceCost() {
		return serviceCost;
	}
	public void setServiceCost(Double serviceCost) {
		this.serviceCost = serviceCost;
	}
	@Column(length=80)
	public Double getSocialInsurance() {
		return socialInsurance;
	}
	public void setSocialInsurance(Double socialInsurance) {
		this.socialInsurance = socialInsurance;
	}
	@Column(length=80)
	public Double getFertilityInsurance() {
		return fertilityInsurance;
	}
	public void setFertilityInsurance(Double fertilityInsurance) {
		this.fertilityInsurance = fertilityInsurance;
	}
	@Column(length=20)
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	@Column(length=30)
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	@Column(length=20)
	public String getBankCardNumber() {
		return bankCardNumber;
	}
	public void setBankCardNumber(String bankCardNumber) {
		this.bankCardNumber = bankCardNumber;
	}
	@Column(length=20)
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	@Column(length=20)
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	@Column(length=20)
	public String getJobs() {
		return jobs;
	}
	public void setJobs(String jobs) {
		this.jobs = jobs;
	}
	@Column(length=1)
	public Integer getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	@Column(length=30)
	public String getLevelEducation() {
		return levelEducation;
	}
	public void setLevelEducation(String levelEducation) {
		this.levelEducation = levelEducation;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartContractDeadline() {
		return startContractDeadline;
	}
	public void setStartContractDeadline(Date startContractDeadline) {
		this.startContractDeadline = startContractDeadline;
	}
	@Temporal(TemporalType.DATE)
	public Date getEndContractDeadline() {
		return endContractDeadline;
	}
	public void setEndContractDeadline(Date endContractDeadline) {
		this.endContractDeadline = endContractDeadline;
	}
	@Column(length=5)
	public String getGinsengProtectNature() {
		return ginsengProtectNature;
	}
	public void setGinsengProtectNature(String ginsengProtectNature) {
		this.ginsengProtectNature = ginsengProtectNature;
	}
	@Temporal(TemporalType.DATE)
	public Date getCinsengDate() {
		return cinsengDate;
	}
	public void setCinsengDate(Date cinsengDate) {
		this.cinsengDate = cinsengDate;
	}
	@Column(length=1)
	public Integer getBase() {
		return base;
	}
	public void setBase(Integer base) {
		this.base = base;
	}
	@Column(length=10)
	public String getPaymentWay() {
		return paymentWay;
	}
	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}
	@Column(length=1)
	public Double getInductrialBase() {
		return inductrialBase;
	}
	public void setInductrialBase(Double inductrialBase) {
		this.inductrialBase = inductrialBase;
	}
	@Column(length=1)
	public Double getHousingFund() {
		return housingFund;
	}
	public void setHousingFund(Double housingFund) {
		this.housingFund = housingFund;
	}
	@Column(length=1)
	public Double getBasicMedical() {
		return basicMedical;
	}
	public void setBasicMedical(Double basicMedical) {
		this.basicMedical = basicMedical;
	}
	@Column(length=1)
	public Integer getPseudoDelete() {
		return pseudoDelete;
	}
	public void setPseudoDelete(Integer pseudoDelete) {
		this.pseudoDelete = pseudoDelete;
	}
	@Column(length=10)
	public Integer getWhetherGinseng() {
		return whetherGinseng;
	}
	public void setWhetherGinseng(Integer whetherGinseng) {
		this.whetherGinseng = whetherGinseng;
	}
	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(length=15)
	public String getSociaSecurity() {
		return sociaSecurity;
	}
	
	public void setSociaSecurity(String sociaSecurity) {
		this.sociaSecurity = sociaSecurity;
	}
	@Column(length=15)
	public String getHealthCare() {
		return healthCare;
	}
	public void setHealthCare(String healthCare) {
		this.healthCare = healthCare;
	}
	@Column(length=15)
	public String getAccumulationFund() {
		return accumulationFund;
	}
	public void setAccumulationFund(String accumulationFund) {
		this.accumulationFund = accumulationFund;
	}
	@Column(length=10)
	public String getSeriousDisease() {
		return seriousDisease;
	}
	@Column(length=80)
	public void setSeriousDisease(String seriousDisease) {
		this.seriousDisease = seriousDisease;
	}
	@Column(length=80)
	public Double getSeriousDiseaseBase() {
		return seriousDiseaseBase;
	}
	public void setSeriousDiseaseBase(Double seriousDiseaseBase) {
		this.seriousDiseaseBase = seriousDiseaseBase;
	}
	
	@ManyToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name="enterpriseId")  
	@NotFound(action=NotFoundAction.IGNORE)
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	@Transient
	public long getAddCount() {
		return addCount;
	}
	public void setAddCount(long addCount) {
		this.addCount = addCount;
	}
	@Transient
	public long getRenewalCount() {
		return renewalCount;
	}
	public void setRenewalCount(long renewalCount) {
		this.renewalCount = renewalCount;
	}
	@Transient
	public long getWhetherGinsengCount() {
		return whetherGinsengCount;
	}
	public void setWhetherGinsengCount(long whetherGinsengCount) {
		this.whetherGinsengCount = whetherGinsengCount;
	}
	
	
	
}
