package cn.fm.bean.company;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@SuppressWarnings("serial")
@Entity
/**
 * 定制各种奖金
 */
public class CustomBonus implements Serializable{

	private Integer id;
	private String  bonusName;
	private Integer state;
	private Date     createDate=new Date();
	
	
	
	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}	
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=10)
	public String getBonusName() {
		return bonusName;
	}
	public void setBonusName(String bonusName) {
		this.bonusName = bonusName;
	}
	@Column(length=2)
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Date getCreateDate() {
		return createDate;
	}
	@Temporal(TemporalType.DATE)
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
}
