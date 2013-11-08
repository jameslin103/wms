package cn.fm.bean.company;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import cn.fm.bean.salary.BalanceDetail;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.bean.user.WmsUser;

@SuppressWarnings("serial")
@Entity
/**
 * 企业实体类
 */
public class Enterprise implements Serializable{
	
	private  Integer  enterpriseId;
	/*简称*/
	private  String   rferred;
	/*全称*/
	private  String   fullName;
	/*法人代表*/
	private  String   legalRepresentative;
	/*开户行*/
	private  String   accountLine;
	/*企业银行账号*/
	private  String   enterpriseBankAccount;
	/*地址*/
	private  String   address;
	/*联系人*/
	private  String   contact;
	/*电话*/
	private  String   phone;
	/*QQ号*/
	private  String   qq;
	/*传真*/
	private  String   fax;
	/*电子邮件*/
	private  String   email;
	/*状态	0.合约  1.暂停*/
	private  Integer   status;
	
	private  long      count;
	
	private Date       createDate=new Date();
	
	private Date       updateDate;
	
	/*统计企业资金往来*/
	private  BigDecimal	   balanceDetailTotal ;
	

	/**增员**/
	private  long addCount=0;
	
	/**续保**/
	private  long renewalCount=0;
	
	/**参保**/
	private  long whetherGinsengCount=0;
	
	/**统计减员**/
	private long  reductionTotal=0;
	     
	
	private Set<EnterpriseEmployees> enterpriseEmployees=new HashSet<EnterpriseEmployees>();
	
	private Set<WmsUser> user=new HashSet<WmsUser>();
	
	//工资预算表
	private Set<CreateSalaryBudgetTable> createSalaryBugetTables=new HashSet<CreateSalaryBudgetTable>();
	
	private Set<BalanceDetail>           balanceDetail=new HashSet<BalanceDetail>();
	
	//工资模板
	private Set<SalaryTemplate>  salaryTemplates=new HashSet<SalaryTemplate>();
	
	private Set<CustomBonus>   customBonus=new HashSet<CustomBonus>();

    
	 @ManyToMany(fetch=FetchType.EAGER,cascade={CascadeType.REFRESH,CascadeType.MERGE,CascadeType.PERSIST})  
	  @JoinTable(name = "user_enterprise",
			   inverseJoinColumns =@JoinColumn (name ="user_id" ),//被维护端外键
              joinColumns =  @JoinColumn (name ="enterprise_id" ))//维护端外键
	public Set<WmsUser> getUser() {
		return user;
	}
	public void setUser(Set<WmsUser> user) {
		this.user = user;
	}

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER ,mappedBy="enterprise")
	public Set<SalaryTemplate> getSalaryTemplates() {
		return salaryTemplates;
	}
	public void setSalaryTemplates(Set<SalaryTemplate> salaryTemplates) {
		this.salaryTemplates = salaryTemplates;
	}
	
	@Id @GeneratedValue @Column(length=36)
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	@Column(length=20)
	public String getRferred() {
		return rferred;
	}
	public void setRferred(String rferred) {
		this.rferred = rferred;
	}
	@Column(length=30)
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Column(length=15)
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	@Column(length=20)
	public String getAccountLine() {
		return accountLine;
	}
	public void setAccountLine(String accountLine) {
		this.accountLine = accountLine;
	}
	@Column(length=30)
	public String getEnterpriseBankAccount() {
		return enterpriseBankAccount;
	}
	public void setEnterpriseBankAccount(String enterpriseBankAccount) {
		this.enterpriseBankAccount = enterpriseBankAccount;
	}
	@Column(length=80)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length=20)
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(length=20)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(length=20)
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	@Column(length=20)
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	@Column(length=25)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(length=2)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	@Transient
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}

	@OneToMany(cascade ={CascadeType.REFRESH, CascadeType.REMOVE,CascadeType.PERSIST},fetch=FetchType.EAGER , mappedBy = "enterprise")
	@OrderBy("budgetId asc")
	public Set<CreateSalaryBudgetTable> getCreateSalaryBugetTables() {
		return createSalaryBugetTables;
	}
	
	public void setCreateSalaryBugetTables(Set<CreateSalaryBudgetTable> createSalaryBugetTables) {
		this.createSalaryBugetTables = createSalaryBugetTables;
	}
	
	 public void addCreateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable) {  
	    	createSalaryBudgetTable.setEnterprise(this);  
	        this.createSalaryBugetTables.add(createSalaryBudgetTable);  
	 } 
	
	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER, mappedBy = "enterprise") 
	@NotFound(action=NotFoundAction.IGNORE)
	public Set<EnterpriseEmployees> getEnterpriseEmployees(){
		return enterpriseEmployees;
	}
	
	public void setEnterpriseEmployees(Set<EnterpriseEmployees> enterpriseEmployees) {
		this.enterpriseEmployees = enterpriseEmployees;
	}
	public void addEnterpriseEmployees(EnterpriseEmployees enterpriseEmployees)
	{
		enterpriseEmployees.setEnterprise(this);
		this.enterpriseEmployees.add(enterpriseEmployees);
	}
	
	
	
	/**
	 * 添加企业负责人
	 * @param wmsUser
	 */
	public void addWmsUser(WmsUser  wmsUser){
	        this.user.add(wmsUser);  
	}  
	      
	public void removeWmsUser(WmsUser wmsUser)
	{  
	   if(this.user.contains(wmsUser))
	   {
	         this.user.remove(wmsUser);  
	   }  
	}  
	
	
	@Transient
	public BigDecimal getBalanceDetailTotal() {
		return balanceDetailTotal;
	}
	public void setBalanceDetailTotal(BigDecimal balanceDetailTotal) {
		this.balanceDetailTotal = balanceDetailTotal;
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
	@Transient
	public long getReductionTotal() {
		return reductionTotal;
	}
	public void setReductionTotal(long reductionTotal) {
		this.reductionTotal = reductionTotal;
	}
	
	@OneToMany(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE},
			fetch=FetchType.EAGER,mappedBy="enterprise")
	public Set<CustomBonus> getCustomBonus() {
		return customBonus;
	}
	public void setCustomBonus(Set<CustomBonus> customBonus) {
		this.customBonus = customBonus;
	}
	
	
	
	@OneToMany(cascade = {CascadeType.ALL},  
                       fetch = FetchType.EAGER, mappedBy = "enterprise")
	public Set<BalanceDetail> getBalanceDetail() {
		return balanceDetail;
	}
	public void setBalanceDetail(Set<BalanceDetail> balanceDetail) {
		this.balanceDetail = balanceDetail;
	}
	
	public void addBalanceDetail(BalanceDetail balanceDetail)
	{
		this.balanceDetail.add(balanceDetail);
		
	}
	public void romveBalanceDetail(BalanceDetail balanceDetail)
	{
		if(this.balanceDetail.contains(balanceDetail))
			this.balanceDetail.remove(balanceDetail);

	}
	
}
