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
 * @version 1.0
 * @date 2013-09-24
 * @author jameslin
 *  工资预算表实体类
 */
@SuppressWarnings("serial")
@Entity
public class CreateSalaryBudgetTable implements Serializable {

	
	private Integer budgetId;
	
	/**预算表名称*/
	private String  name;

	/**生成哪月工资？*/
	private Date    salaryDate;
	
	/**选择与其他工资表合并计税**/
	private String  chooseTax;
	
	 /**模板名称**/
	 private String templateName;
	
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
	private Integer     issueNumber;

	/**民生银行（人）**/
	private   Integer     cmbc;

	/**他行（人）**/
	private   Integer     heLines;

	/**现金（人数）**/
	 private Integer      cashnumber;

	/**状态 **/
	private String       status;
	
	/**补充说明*/
	private String   note;
	
	/**创建日期*/
	private Date    createDate=new Date();
	
	private Date    updateDate=new Date();
	
	/**选择模板*/
	private SalaryTemplate  salaryTemplate;
	
	/*是否已经合并 0未合并，1合并*/
	private Integer    isTax=0;   
	
	/**跟哪个预算表合并**/
	private Integer    mergeId;
	
	private Enterprise enterprise;
	

	@Id @GeneratedValue
	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
	}
	@Column(length=100)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}
	@Temporal(TemporalType.DATE)
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

	@Column(length=40)
	public String getChooseTax() {
		return chooseTax;
	}
	
	public void setChooseTax(String chooseTax) {
		this.chooseTax = chooseTax;
	}
	
	@Column(length=100)
	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	@Column(length=20)
	public String getNture() {
		return nture;
	}

	public void setNture(String nture) {
		this.nture = nture;
	}
	@Column(length=50)
	public BigDecimal getMakeTotal() {
		return makeTotal;
	}
	
	public void setMakeTotal(BigDecimal makeTotal) {
		this.makeTotal = makeTotal;
	}
	@Column(length=50)
	public BigDecimal getWageTotal() {
		return wageTotal;
	}

	public void setWageTotal(BigDecimal wageTotal) {
		this.wageTotal = wageTotal;
	}
	@Column(length=50)
	public BigDecimal getServiceTotal() {
		return serviceTotal;
	}

	public void setServiceTotal(BigDecimal serviceTotal) {
		this.serviceTotal = serviceTotal;
	}
	@Column(length=50)
	public BigDecimal getFiveInsurancesTotal() {
		return fiveInsurancesTotal;
	}

	public void setFiveInsurancesTotal(BigDecimal fiveInsurancesTotal) {
		this.fiveInsurancesTotal = fiveInsurancesTotal;
	}
	@Column(length=50)
	public Integer getIssueNumber() {
		return issueNumber;
	}

	public void setIssueNumber(Integer issueNumber) {
		this.issueNumber = issueNumber;
	}
	
	@Column(length=50)
	public Integer getHeLines() {
		return heLines;
	}

	public void setHeLines(Integer heLines) {
		this.heLines = heLines;
	}

	@Column(length=50)
	public Integer getCashnumber() {
		return cashnumber;
	}

	public void setCashnumber(Integer cashnumber) {
		this.cashnumber = cashnumber;
	}

	@Column(length=50)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(length=100)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	@Column(length=50)
	public Integer getCmbc() {
		return cmbc;
	}

	public void setCmbc(Integer cmbc) {
		this.cmbc = cmbc;
	}
	@Column(length=1)
	public Integer getIsTax() {
		return isTax;
	}

	public void setIsTax(Integer isTax) {
		this.isTax = isTax;
	}
	
	@Column(length=20)
	public Integer getMergeId() {
		return mergeId;
	}

	public void setMergeId(Integer mergeId) {
		this.mergeId = mergeId;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH},optional=true)
	@JoinColumn(name = "enterpriseId")
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST},optional=true)
	@JoinColumn(name="templateId")
	public SalaryTemplate getSalaryTemplate() {
		return salaryTemplate;
	}

	public void setSalaryTemplate(SalaryTemplate salaryTemplate) {
		this.salaryTemplate = salaryTemplate;
	}
	
	
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((budgetId == null) ? 0 : budgetId.hashCode());
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
		CreateSalaryBudgetTable other = (CreateSalaryBudgetTable) obj;
		if (budgetId == null) {
			if (other.budgetId != null)
				return false;
		} else if (!budgetId.equals(other.budgetId))
			return false;
		return true;
	}

}
