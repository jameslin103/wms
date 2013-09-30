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

/**
 * 五险一金基数实体类
 * @author jameslin
 * @date   2013-09-23
 */
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
	@Column(length=5)
	public String getInsurancesType() {
		return insurancesType;
	}
	public void setInsurancesType(String insurancesType) {
		this.insurancesType = insurancesType;
	}
	@Column(length=60)
	public BigDecimal getSocialInsurance() {
		return socialInsurance;
	}
	public void setSocialInsurance(BigDecimal socialInsurance) {
		this.socialInsurance = socialInsurance;
	}
	@Column(precision=60 ,scale=2)
	public BigDecimal getBirthInsurance() {
		return birthInsurance;
	}
	public void setBirthInsurance(BigDecimal birthInsurance) {
		this.birthInsurance = birthInsurance;
	}
	@Column(precision=60 ,scale=2)
	public BigDecimal getInductrialInjury() {
		return inductrialInjury;
	}
	public void setInductrialInjury(BigDecimal inductrialInjury) {
		this.inductrialInjury = inductrialInjury;
	}
	@Column(precision=60 ,scale=2)
	public BigDecimal getHousingMPF() {
		return housingMPF;
	}
	public void setHousingMPF(BigDecimal housingMPF) {
		this.housingMPF = housingMPF;
	}
	@Column(precision=60 ,scale=2)
	public BigDecimal getBasicMedical() {
		return basicMedical;
	}
	public void setBasicMedical(BigDecimal basicMedical) {
		this.basicMedical = basicMedical;
	}
	@Column(precision=60 ,scale=2)
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
