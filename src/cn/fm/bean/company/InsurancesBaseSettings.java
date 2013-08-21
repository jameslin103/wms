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
public class InsurancesBaseSettings implements Serializable{

	
	private Integer      id;
	/*医保类型*/
	private String       insurancesType;
	/*社会保险基数*/
	private BigDecimal   socialInsurance;
	/*生育保险基数*/
	private BigDecimal   birthInsurance;
	/*工伤基数*/
	private BigDecimal   inductrialInjury;
	/*住房公积金基数*/
	private BigDecimal   housingMPF;
	/*基本医疗基数*/
	private BigDecimal   basicMedical;
	/*大病统筹基数*/
	private BigDecimal   povertyStricken;
	/*开始执行年月份*/
	private Date   startDate;
	
	
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=5,nullable=false)
	public String getInsurancesType() {
		return insurancesType;
	}
	public void setInsurancesType(String insurancesType) {
		this.insurancesType = insurancesType;
	}
	@Column(length=100,nullable=false)
	public BigDecimal getSocialInsurance() {
		return socialInsurance;
	}
	public void setSocialInsurance(BigDecimal socialInsurance) {
		this.socialInsurance = socialInsurance;
	}
	@Column(length=100,nullable=false)
	public BigDecimal getBirthInsurance() {
		return birthInsurance;
	}
	public void setBirthInsurance(BigDecimal birthInsurance) {
		this.birthInsurance = birthInsurance;
	}
	@Column(length=100,nullable=false)
	public BigDecimal getInductrialInjury() {
		return inductrialInjury;
	}
	public void setInductrialInjury(BigDecimal inductrialInjury) {
		this.inductrialInjury = inductrialInjury;
	}
	@Column(length=100,nullable=false)
	public BigDecimal getHousingMPF() {
		return housingMPF;
	}
	public void setHousingMPF(BigDecimal housingMPF) {
		this.housingMPF = housingMPF;
	}
	@Column(length=100,nullable=false)
	public BigDecimal getBasicMedical() {
		return basicMedical;
	}
	public void setBasicMedical(BigDecimal basicMedical) {
		this.basicMedical = basicMedical;
	}
	@Column(length=100,nullable=false)
	public BigDecimal getPovertyStricken() {
		return povertyStricken;
	}
	public void setPovertyStricken(BigDecimal povertyStricken) {
		this.povertyStricken = povertyStricken;
	}
	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	
}
