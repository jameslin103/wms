//package cn.fm.bean.company;
//import java.math.BigDecimal;
//import java.util.Date;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.Id;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//
//import org.hibernate.annotations.GenericGenerator;
//
//@Entity
//public class EnterpriseContract {
//	
//	
//	
//	private String id;
//	
//	/*按人头服务费*/
//	private BigDecimal serviceHead;
//	
//	/*按比例服务费*/
//	private BigDecimal proportion;
//	
//	/*服务性质*/
//	private String   serviceType;
//	
//	private String   customType;
//	/*合同时间*/
//	private Date startContractDate;
//	
//	private Date endContractDate;
//	/*合作项目*/
//	private String projects;
//
//	/*备注*/
//	private  String   note;
//	
//	
//	
//	
//	
//	
//	@Id
//	@GenericGenerator(name = "idGenerator", strategy = "uuid")
//	@GeneratedValue(generator = "idGenerator")
//	@Column(length=32)
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	@Column(length=20,scale=2)
//	public BigDecimal getServiceHead() {
//		return serviceHead;
//	}
//	public void setServiceHead(BigDecimal serviceHead) {
//		this.serviceHead = serviceHead;
//	}
//	
//	public BigDecimal getProportion() {
//		return proportion;
//	}
//	public void setProportion(BigDecimal proportion) {
//		this.proportion = proportion;
//	}
//	@Column(length=20)
//	public String getServiceType() {
//		return serviceType;
//	}
//	public void setServiceType(String serviceType) {
//		this.serviceType = serviceType;
//	}
//	@Column(length=20)
//	public String getCustomType() {
//		return customType;
//	}
//	public void setCustomType(String customType) {
//		this.customType = customType;
//	}
//	@Temporal(TemporalType.DATE)
//	public Date getStartContractDate() {
//		return startContractDate;
//	}
//	public void setStartContractDate(Date startContractDate) {
//		this.startContractDate = startContractDate;
//	}
//	@Temporal(TemporalType.DATE)
//	public Date getEndContractDate() {
//		return endContractDate;
//	}
//	public void setEndContractDate(Date endContractDate) {
//		this.endContractDate = endContractDate;
//	}
//	@Column(length=80)
//	public String getProjects() {
//		return projects;
//	}
//	public void setProjects(String projects) {
//		this.projects = projects;
//	}
//	
//	@Column(length=100)
//	public String getNote() {
//		return note;
//	}
//	public void setNote(String note) {
//		this.note = note;
//	}
//
//	
//}
