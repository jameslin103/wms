package cn.fm.bean.company;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private  Integer   state;
	
	
	
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=20,nullable=false)
	public String getRferred() {
		return rferred;
	}
	public void setRferred(String rferred) {
		this.rferred = rferred;
	}
	@Column(length=30,nullable=false)
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	@Column(length=15,nullable=false)
	public String getLegalRepresentative() {
		return legalRepresentative;
	}
	public void setLegalRepresentative(String legalRepresentative) {
		this.legalRepresentative = legalRepresentative;
	}
	@Column(length=20,nullable=false)
	public String getAccountLine() {
		return accountLine;
	}
	public void setAccountLine(String accountLine) {
		this.accountLine = accountLine;
	}
	@Column(length=20,nullable=false)
	public String getEnterpriseBankAccount() {
		return enterpriseBankAccount;
	}
	public void setEnterpriseBankAccount(String enterpriseBankAccount) {
		this.enterpriseBankAccount = enterpriseBankAccount;
	}
	@Column(length=80,nullable=false)
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Column(length=20,nullable=false)
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	@Column(length=20,nullable=false)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(length=20,nullable=false)
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	@Column(length=20,nullable=false)
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	@Column(length=25,nullable=false)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(length=2,nullable=false)
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}

}
