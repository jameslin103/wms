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



import cn.fm.bean.company.Enterprise;

/**
 * 资金往来实体类
 * @author jameslin
 * @version 1.0
 * @date    2013-08-29
 */
@SuppressWarnings("serial")
@Entity
public class BalanceDetail implements Serializable{

	
	@Id @GeneratedValue
	private Integer  detailId;

	/**月份**/
	@Temporal(TemporalType.DATE)
	private Date yearMonth; 

	/**期初余额（元）**/
	@Column(length=80)
	private BigDecimal	balance;


	/**开票总额（元）**/
	@Column(length=80)
	private BigDecimal	 ballotsToal;

	/**工资总额（元）**/
	@Column(length=80)
	private BigDecimal    wagesToal;

	/**服务费总额（元）**/
	@Column(length=80)
	private BigDecimal	  serviceToal;

	/**实收款项（元）**/
	@Column(length=80)
	private BigDecimal     receivedFunds;

	/**期末余额(元)**/
	@Column(length=80)
	private    BigDecimal    endingBalance;

	/**工资（元）**/
	@Column(length=80)
	private BigDecimal     wages;

	/**服务费（元）**/
	@Column(length=80)
	private BigDecimal     serviceWith;

	/**五险一金**/
	@Column(length=80)
	private BigDecimal     fiveFund;
	
	@Temporal(TemporalType.DATE)
	private  Date		   createDate=new Date();
	
	@Column(length=100)
	private String       note;


	@Column(length=80)
	private Integer      employeesId;
	
	private Enterprise   enterprise;
	
	
	
	public Integer getEmployeesId() {
		return employeesId;
	}


	public void setEmployeesId(Integer employeesId) {
		this.employeesId = employeesId;
	}


	public Integer getDetailId() {
		return detailId;
	}


	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}


	public Date getYearMonth() {
		return yearMonth;
	}


	public void setYearMonth(Date yearMonth) {
		this.yearMonth = yearMonth;
	}


	public BigDecimal getBalance() {
		return balance;
	}


	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}


	public BigDecimal getBallotsToal() {
		return ballotsToal;
	}


	public void setBallotsToal(BigDecimal ballotsToal) {
		this.ballotsToal = ballotsToal;
	}


	public BigDecimal getWagesToal() {
		return wagesToal;
	}


	public void setWagesToal(BigDecimal wagesToal) {
		this.wagesToal = wagesToal;
	}


	public BigDecimal getServiceToal() {
		return serviceToal;
	}


	public void setServiceToal(BigDecimal serviceToal) {
		this.serviceToal = serviceToal;
	}


	public BigDecimal getReceivedFunds() {
		return receivedFunds;
	}


	public void setReceivedFunds(BigDecimal receivedFunds) {
		this.receivedFunds = receivedFunds;
	}


	public BigDecimal getWages() {
		return wages;
	}


	public void setWages(BigDecimal wages) {
		this.wages = wages;
	}


	public BigDecimal getServiceWith() {
		return serviceWith;
	}


	public void setServiceWith(BigDecimal serviceWith) {
		this.serviceWith = serviceWith;
	}


	public BigDecimal getFiveFund() {
		return fiveFund;
	}


	public void setFiveFund(BigDecimal fiveFund) {
		this.fiveFund = fiveFund;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getEndingBalance() {
		return endingBalance;
	}


	public void setEndingBalance(BigDecimal endingBalance) {
		this.endingBalance = endingBalance;
	}

	@ManyToOne(cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinColumn(name="enterpriseId",referencedColumnName = "enterpriseId")  
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
}