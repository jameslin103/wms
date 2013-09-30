package cn.fm.bean.salary;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@Temporal(TemporalType.DATE)
	private  Date		   createDate=new Date();
	
	//修改日期
	private   Date         updteDate=new Date();
	
	//备注
	@Column(length=100)
	private String       note;


	@Column(length=80)
	private Integer      employeesId;
	
	@Column(length=80)
	private Integer      enterpriseId;
	
	@Column(length=80)
	private Integer      budgetId;
	
	
	
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
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
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
	public Date getUpdteDate() {
		return updteDate;
	}
	public void setUpdteDate(Date updteDate) {
		this.updteDate = updteDate;
	}
	

}
