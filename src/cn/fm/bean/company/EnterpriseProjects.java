package cn.fm.bean.company;

import java.math.BigDecimal;
import java.util.Date;

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

import org.hibernate.annotations.GenericGenerator;

import cn.fm.bean.salary.SalaryTemplate;

@Entity
public class EnterpriseProjects {
	
	
	private String id;
	
	/*按人头服务费*/
	private BigDecimal serviceHead=new BigDecimal("0.00");
	
	private Integer    fee;
	
	/*按比例服务费*/
	private BigDecimal proportion=new BigDecimal("0.00");
	
	/*服务性质*/
	private String   serviceType;
	
	private String   customType;
	
	/*合作项目*/
	private String projects;
	
	private Date createDate=new Date();
	
	private Date updateDate;
	
	private String note;
	
	private Enterprise enterprise=new Enterprise();
	
	private SalaryTemplate   salaryTemplate;
	
	
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false)
	@JoinColumn(name="enterprise_id")
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	@Id
	@GenericGenerator(name = "idGenerator", strategy = "uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(length=32)
	public String getId() {
		return id;
	}
	public void setId(String id){
		this.id = id;
	}
	@Column(length=20,scale=2)
	public BigDecimal getServiceHead() {
		return serviceHead;
	}
	public void setServiceHead(BigDecimal serviceHead) {
		this.serviceHead = serviceHead;
	}
	@Column(length=20,scale=2)
	public BigDecimal getProportion() {
		return proportion;
	}
	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}
	@Column(length=20)
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	@Column(length=20)
	public String getCustomType() {
		return customType;
	}
	public void setCustomType(String customType) {
		this.customType = customType;
	}
	
	@Column(length=80)
	public String getProjects() {
		return projects;
	}
	public void setProjects(String projects) {
		this.projects = projects;
	}
	@Temporal(TemporalType.TIMESTAMP)
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
	@Column(length=1)
	public Integer getFee() {
		return fee;
	}
	public void setFee(Integer fee) {
		this.fee = fee;
	}
	@Column(length=80)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@OneToOne(cascade=CascadeType.REFRESH,mappedBy="enterpriseProjects",optional=true)
	public SalaryTemplate getSalaryTemplate() {
		return salaryTemplate;
	}
	public void setSalaryTemplate(SalaryTemplate salaryTemplate) {
		this.salaryTemplate = salaryTemplate;
	}
	
	
	
	
	
	
}
