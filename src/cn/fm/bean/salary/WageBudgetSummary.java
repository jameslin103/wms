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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SuppressWarnings("serial")
@Entity
/**
 * 工资明细实体类
 */
public class WageBudgetSummary implements Serializable {
	
	
	 private Integer  wageId;
	
	 /**工资预算表名称**/
	 private String wageSheetName;
	 
	 /**工资所属月份**/
	 private  Date wageMonth ;
	 
	 /**合并计税工资表**/
	 private String   mergeTax;
	 
	 /**性质**/
	 private String nture;
	 
	 /**开票总额（元）**/
	 private   BigDecimal     makeTotal;

	/**工资总额（元）**/
	private    BigDecimal      wageTotal;

	/**服务费总额（元）**/
	private 	BigDecimal	  serviceTotal;

	/**五险一金总额（元）**/
	private      BigDecimal      fiveInsurancesTotal;

	/**发放人数（人）**/
	private long     issueNumber;

	/**民生银行（人）**/
	private   long     CMBC;

	/**他行（人）**/
	private   long     heLines;

	/**现金（人数）**/
	 private long      cash;

	/**状态**/
	private String       status;

	/**创建时间**/
	private Date     createDate=new Date();
	
	private String   note;
	
	private Integer budgetId;
	
	private Integer enterpriseId;
	
	private CreateSalaryBudgetTable createSalaryBudgerTable;
	
	
	@Column(length=100)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Id @GeneratedValue
	public Integer getWageId() {
		return wageId;
	}

	public void setWageId(Integer wageId) {
		this.wageId = wageId;
	}
	
	@Column(length=80)
	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}

	@Column(length=30)
	public String getWageSheetName() {
		return wageSheetName;
	}

	public void setWageSheetName(String wageSheetName) {
		this.wageSheetName = wageSheetName;
	}
	@Temporal(TemporalType.DATE)
	public Date getWageMonth() {
		return wageMonth;
	}

	public void setWageMonth(Date wageMonth) {
		this.wageMonth = wageMonth;
	}
	@Column(length=30)
	public String getMergeTax() {
		return mergeTax;
	}

	public void setMergeTax(String mergeTax) {
		this.mergeTax = mergeTax;
	}
    @Column(length=10)
	public String getNture() {
		return nture;
	}

	public void setNture(String nture) {
		this.nture = nture;
	}
	@Column(length=60,scale=2)
	public BigDecimal getMakeTotal() {
		return makeTotal;
	}

	public void setMakeTotal(BigDecimal makeTotal) {
		this.makeTotal = makeTotal;
	}
	@Column(length=100,scale=2)
	public BigDecimal getWageTotal() {
		return wageTotal;
	}

	public void setWageTotal(BigDecimal wageTotal) {
		this.wageTotal = wageTotal;
	}
	@Column(length=30,scale=2)
	public BigDecimal getServiceTotal() {
		return serviceTotal;
	}

	public void setServiceTotal(BigDecimal serviceTotal) {
		this.serviceTotal = serviceTotal;
	}
	@Column(length=80,scale=2)
	public BigDecimal getFiveInsurancesTotal() {
		return fiveInsurancesTotal;
	}

	public void setFiveInsurancesTotal(BigDecimal fiveInsurancesTotal) {
		this.fiveInsurancesTotal = fiveInsurancesTotal;
	}
	@Column(length=20)
	public long getIssueNumber() {
		return issueNumber;
	}
	
	public void setIssueNumber(long issueNumber) {
		this.issueNumber = issueNumber;
	}
	@Column(length=20)
	public long getCMBC() {
		return CMBC;
	}

	public void setCMBC(long cMBC) {
		CMBC = cMBC;
	}
	@Column(length=20)
	public long getHeLines() {
		return heLines;
	}

	public void setHeLines(long heLines) {
		this.heLines = heLines;
	}
	@Column(length=20)
	public long getCash() {
		return cash;
	}

	public void setCash(long cash) {
		this.cash = cash;
	}
	@Column(length=80)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(length=36)
	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	@OneToOne(cascade=CascadeType.REFRESH,mappedBy="wageBudgetSummary")
	@JoinColumn(name ="budgetId")
	public CreateSalaryBudgetTable getCreateSalaryBudgerTable() {
		return createSalaryBudgerTable;
	}

	public void setCreateSalaryBudgerTable(
			CreateSalaryBudgetTable createSalaryBudgerTable) {
		this.createSalaryBudgerTable = createSalaryBudgerTable;
	}
}
