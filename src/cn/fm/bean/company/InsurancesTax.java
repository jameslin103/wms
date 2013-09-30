package cn.fm.bean.company;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
/***
 * 计税规则实体类
 * jameslin 2013-08-15
 */
public class InsurancesTax implements Serializable{

	
	private  Integer id;
	/*医保类型*/
	private  Integer     insurancesType;
	/*养老保险（公司）%*/
	private  BigDecimal endowmentInsurance;
	/*养老保险（个人）*/
	private  BigDecimal personalEndowmentInsurance;
	/*失业保险（公司）*/
	private  BigDecimal unemploymentInsurance;
	/*失业保险（个人）*/
	private  BigDecimal personalUnemploymentInsurance;
	
	/*生育（企业）*/
	private BigDecimal birthEnterprise;
	
	/*工伤（企业）*/
	private BigDecimal injuriesEnterprise;
	
	/*基本医疗保险（企业）*/
	private BigDecimal medicalEnterprise;
	
	/*基本医疗保险（个人）*/	
	private BigDecimal personalEnterprise;
	
	/*住房公积金（企业）*/
	private BigDecimal housingFundEnterprise;
	
	/*住房公积金（个人）*/
	private BigDecimal personalHousingFund;
	
	/*开始执行年月份*/
	private Date	startDate;
	

	
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=1)
	public Integer getInsurancesType() {
		return insurancesType;
	}
	public void setInsurancesType(Integer insurancesType) {
		this.insurancesType = insurancesType;
	}
	@Column(precision=5,scale=2)
	public BigDecimal getEndowmentInsurance() {
		return endowmentInsurance;
	}
	public void setEndowmentInsurance(BigDecimal endowmentInsurance) {
		this.endowmentInsurance = endowmentInsurance;
	}
	@Column(precision=5,scale=2)
	public BigDecimal getPersonalEndowmentInsurance() {
		return personalEndowmentInsurance;
	}
	public void setPersonalEndowmentInsurance(BigDecimal personalEndowmentInsurance) {
		this.personalEndowmentInsurance = personalEndowmentInsurance;
	}
	@Column(precision=5,scale=2)
	public BigDecimal getUnemploymentInsurance() {
		return unemploymentInsurance;
	}
	public void setUnemploymentInsurance(BigDecimal unemploymentInsurance) {
		this.unemploymentInsurance = unemploymentInsurance;
	}
	@Column(precision=5,scale=2)
	public BigDecimal getPersonalUnemploymentInsurance() {
		return personalUnemploymentInsurance;
	}
	public void setPersonalUnemploymentInsurance(
			BigDecimal personalUnemploymentInsurance) {
		this.personalUnemploymentInsurance = personalUnemploymentInsurance;
	}
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	@Column(precision=5,scale=2)
	public BigDecimal getBirthEnterprise() {
		return birthEnterprise;
	}
	public void setBirthEnterprise(BigDecimal birthEnterprise) {
		this.birthEnterprise = birthEnterprise;
	}
	@Column(precision=5,scale=2)
	public BigDecimal getInjuriesEnterprise() {
		return injuriesEnterprise;
	}
	public void setInjuriesEnterprise(BigDecimal injuriesEnterprise) {
		this.injuriesEnterprise = injuriesEnterprise;
	}
	@Column(precision=5,scale=2)
	public BigDecimal getMedicalEnterprise() {
		return medicalEnterprise;
	}
	public void setMedicalEnterprise(BigDecimal medicalEnterprise) {
		this.medicalEnterprise = medicalEnterprise;
	}
	@Column(precision=5,scale=2)
	public BigDecimal getPersonalEnterprise() {
		return personalEnterprise;
	}
	public void setPersonalEnterprise(BigDecimal personalEnterprise) {
		this.personalEnterprise = personalEnterprise;
	}
	@Column(precision=5,scale=2)
	public BigDecimal getHousingFundEnterprise() {
		return housingFundEnterprise;
	}
	public void setHousingFundEnterprise(BigDecimal housingFundEnterprise) {
		this.housingFundEnterprise = housingFundEnterprise;
	}
	@Column(precision=5,scale=2)
	public BigDecimal getPersonalHousingFund() {
		return personalHousingFund;
	}
	public void setPersonalHousingFund(BigDecimal personalHousingFund) {
		this.personalHousingFund = personalHousingFund;
	}

	
}
