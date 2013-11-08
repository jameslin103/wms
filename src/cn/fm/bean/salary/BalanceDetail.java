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
	
	/**应收款项 五险一金**/
	@Column(length=80)
	private BigDecimal     receivableFiveFund;
	
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
	
	//创建日期
	
	private  Date		   createDate=new Date();
	
	//修改日期
	private   Date         updteDate=new Date();
	
	//备注
	@Column(length=100)
	private String       note;


	@Column(length=80)
	private Integer      employeesId;
	
	@Column(length=80)
	private Integer      budgetId;
	
	
	private Enterprise      enterprise;
	
	
	
	public Integer getBudgetId() {
		return budgetId;
	}
	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}
	public BigDecimal getReceivableFiveFund() {
		return receivableFiveFund;
	}
	public void setReceivableFiveFund(BigDecimal receivableFiveFund) {
		this.receivableFiveFund = receivableFiveFund;
	}
	public Integer getEmployeesId() {
		return employeesId;
	}
	public void setEmployeesId(Integer employeesId) {
		this.employeesId = employeesId;
	}

	@Id @GeneratedValue
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

	@Temporal(TemporalType.DATE)
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
	@Temporal(TemporalType.DATE)
	public Date getUpdteDate() {
		return updteDate;
	}
	public void setUpdteDate(Date updteDate) {
		this.updteDate = updteDate;
	}
	
	@ManyToOne(cascade={CascadeType.REFRESH }, optional= true)  
	@JoinColumn(name="enterprise_id")
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((detailId == null) ? 0 : detailId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BalanceDetail other = (BalanceDetail) obj;
		if (detailId == null) {
			if (other.detailId != null)
				return false;
		} else if (!detailId.equals(other.detailId))
			return false;
		return true;
	}
	
	
}
