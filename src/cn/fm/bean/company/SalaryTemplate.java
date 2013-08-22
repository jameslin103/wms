package cn.fm.bean.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@SuppressWarnings("serial")
@Entity
public class SalaryTemplate implements Serializable {

	private Integer  id;
	
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
	
	
	
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=20,nullable=false)
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
	
}
