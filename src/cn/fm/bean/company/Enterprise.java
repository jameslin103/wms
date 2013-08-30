package cn.fm.bean.company;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.user.WmsUser;

@SuppressWarnings("serial")
@Entity
public class Enterprise implements Serializable{
	
	private  Integer  id;
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
	
	private  Integer   userId;
	
	private  long      count;
	
	private Set<EnterpriseEmployees> enterpriseEmployess=new HashSet<EnterpriseEmployees>();
	
	private Set<WmsUser> user=new HashSet<WmsUser>();
	
	private Set<CreateSalaryBudgetTable> createSalaryBugetTables=new HashSet<CreateSalaryBudgetTable>();
	
	
	public Enterprise(){}
    public Enterprise(String rferred, String fullName,
			String legalRepresentative, String accountLine,
			String enterpriseBankAccount, String address, String contact,
			String phone, String qq, String fax, String email, Integer status) {
			this.rferred = rferred;
			this.fullName = fullName;
			this.legalRepresentative = legalRepresentative;
			this.accountLine = accountLine;
			this.enterpriseBankAccount = enterpriseBankAccount;
			this.address = address;
			this.contact = contact;
			this.phone = phone;
			this.qq = qq;
			this.fax = fax;
			this.email = email;
			this.status = status;
	}
    
	@ManyToMany(cascade=CascadeType.REFRESH,fetch=FetchType.EAGER,mappedBy="enterprise")//这里说明了关系维护端是student，teacher是关系被维护端  
	public Set<WmsUser> getUser() {
		return user;
	}
	public void setUser(Set<WmsUser> user) {
		this.user = user;
	}
	
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	@Column(length=50)
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	@OneToMany( cascade =CascadeType.ALL,fetch=FetchType.EAGER , mappedBy = "enterprise")  
	public Set<CreateSalaryBudgetTable> getCreateSalaryBugetTables() {
		return createSalaryBugetTables;
	}
	public void setCreateSalaryBugetTables(
			Set<CreateSalaryBudgetTable> createSalaryBugetTables) {
		this.createSalaryBugetTables = createSalaryBugetTables;
	}
	
	  public void setCreateSalaryBudgetTable(Set<CreateSalaryBudgetTable> createSalaryBudgetTable) {  
	        this.createSalaryBugetTables = createSalaryBudgetTable;  
	    }  
	  
	    public void addCreateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable) {  
	    	createSalaryBudgetTable.setEnterprise(this);  
	        this.createSalaryBugetTables.add(createSalaryBudgetTable);  
	    } 
	
	/*@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "enterprise")  
	public Set<EnterpriseEmployees> getEnterpriseEmployess() {
		return enterpriseEmployess;
	}
	public void setEnterpriseEmployess(Set<EnterpriseEmployees> enterpriseEmployess) {
		this.enterpriseEmployess = enterpriseEmployess;
	}
	public void addEnterpriseEmployess(EnterpriseEmployees enterpriseEmployees)
	{
		enterpriseEmployees.setEnterprise(this);
		this.enterpriseEmployess.add(enterpriseEmployees);
	}*/
}
