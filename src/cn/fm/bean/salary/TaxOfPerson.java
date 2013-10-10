package cn.fm.bean.salary;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * 
 * @author jameslin 2013-10-10
 * @version 1.0
 */
@Entity
public class TaxOfPerson {
	
	private Integer taxid;
	
	private BigDecimal taxThreshold;
	
	private Date	   statrDate;
	
	private Date	   updateDate=new Date();

	
	@Id @GeneratedValue
	public Integer getTaxid() {
		return taxid;
	}

	public void setTaxid(Integer taxid) {
		this.taxid = taxid;
	}
	@Column(length=15)
	public BigDecimal getTaxThreshold() {
		return taxThreshold;
	}

	public void setTaxThreshold(BigDecimal taxThreshold) {
		this.taxThreshold = taxThreshold;
	}
	@Temporal(TemporalType.DATE)
	public Date getStatrDate() {
		return statrDate;
	}

	public void setStatrDate(Date statrDate) {
		this.statrDate = statrDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	
}
