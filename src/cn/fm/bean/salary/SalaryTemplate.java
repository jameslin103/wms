package cn.fm.bean.salary;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import cn.fm.bean.company.Enterprise;


@SuppressWarnings("serial")
@Entity
public class SalaryTemplate implements Serializable {

	private Integer  templateId;
	
	/*名称*/
	private String templateName;
	
	/*各种补贴*/
	private String subsidyList;

	/*是否包含五险一金* 0 是 1 否*/
	private  Integer  fiveInsurances;

	/*是否包含个税* 0 是 1 否*/
	private  Integer  tax;

	/*是否启用 0 启用 1 停用*/
	private  Integer  status;
	 
	private   Date    createDate=new Date();
	
	private List<String>  subsidys=new ArrayList<String>();
	
	private CreateSalaryBudgetTable   createSalaryBudgetTable;
	
	private Enterprise   enterprise;
	
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name="enterpriseId")
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	
	@Id @GeneratedValue
	@Column(length=36)
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	@Column(length=20)
	public String getTemplateName() {
		return templateName;
	}
	
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	@Column(length=80)
	public String getSubsidyList() {
		return subsidyList;
	}

	public void setSubsidyList(String subsidyList) {
		this.subsidyList = subsidyList;
	}
	@Column(length=1)
	public Integer getFiveInsurances() {
		return fiveInsurances;
	}
	
	public void setFiveInsurances(Integer fiveInsurances) {
		this.fiveInsurances = fiveInsurances;
	}
	@Column(length=1)
	public Integer getTax() {
		return tax;
	}

	public void setTax(Integer tax) {
		this.tax = tax;
	}
	@Column(length=1)
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Transient
	public List<String> getSubsidys() {
		return subsidys;
	}

	public void setSubsidys(List<String> subsidys) {
		this.subsidys = subsidys;
	}
	@OneToOne(cascade={CascadeType.ALL}) 
	    @JoinColumn(name="budget_id") 
	public CreateSalaryBudgetTable getCreateSalaryBudgetTable() {
		return createSalaryBudgetTable;
	}

	public void setCreateSalaryBudgetTable(
			CreateSalaryBudgetTable createSalaryBudgetTable) {
		this.createSalaryBudgetTable = createSalaryBudgetTable;
	}
	

	
	
}
