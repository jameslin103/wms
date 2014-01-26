package cn.fm.bean.company;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import cn.fm.bean.salary.SalaryTemplate;


@Entity
/**
 * 定制各种奖金
 */
public class CustomBonus{

	private Integer id;
	private String  bonusName;
	private Integer state;
	private Date     createDate=new Date();
	
	private Enterprise   enterprise;
	
	
	
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
	
	@ManyToOne(cascade={CascadeType.REFRESH},optional=true)
	@JoinColumn(name="enterprise_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		CustomBonus other = (CustomBonus) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
