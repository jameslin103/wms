package cn.fm.bean.company;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
/***
 * jameslin 2013-08-15
 */
public class InsurancesTax implements Serializable{

	
	private  Integer id;
	/*医保类型*/
	private  String     InsurancesType;
	/*养老保险（公司）%*/
	private  BigDecimal endowmentInsurance;
	/*养老保险（个人）*/
	private  BigDecimal personalEndowmentInsurance;
	/*失业保险（公司）*/
	private  BigDecimal unemploymentInsurance;
	/*失业保险（个人）*/
	private  BigDecimal personalUnemploymentInsurance;
	/*开始执行年月份*/
	private Date	startDate;
	

	
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=8,nullable=false)
	public String getInsurancesType() {
		return InsurancesType;
	}
	public void setInsurancesType(String insurancesType) {
		InsurancesType = insurancesType;
	}
	@Column(length=5,nullable=false)
	public BigDecimal getEndowmentInsurance() {
		return endowmentInsurance;
	}
	public void setEndowmentInsurance(BigDecimal endowmentInsurance) {
		this.endowmentInsurance = endowmentInsurance;
	}
	@Column(length=5,nullable=false)
	public BigDecimal getPersonalEndowmentInsurance() {
		return personalEndowmentInsurance;
	}
	public void setPersonalEndowmentInsurance(BigDecimal personalEndowmentInsurance) {
		this.personalEndowmentInsurance = personalEndowmentInsurance;
	}
	@Column(length=5,nullable=false)
	public BigDecimal getUnemploymentInsurance() {
		return unemploymentInsurance;
	}
	public void setUnemploymentInsurance(BigDecimal unemploymentInsurance) {
		this.unemploymentInsurance = unemploymentInsurance;
	}
	@Column(length=5,nullable=false)
	public BigDecimal getPersonalUnemploymentInsurance() {
		return personalUnemploymentInsurance;
	}
	public void setPersonalUnemploymentInsurance(
			BigDecimal personalUnemploymentInsurance) {
		this.personalUnemploymentInsurance = personalUnemploymentInsurance;
	}
	@Column(length=20,nullable=false)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
}
