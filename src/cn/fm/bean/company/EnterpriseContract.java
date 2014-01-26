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
import org.hibernate.annotations.GenericGenerator;

@Entity
public class EnterpriseContract {
	
	
	
	private String id;
	
	/*合同时间*/
	private Date startContractDate;
	
	private Date endContractDate;
	
	private Integer status;

	private Date  createDate=new Date();
	
	private Date updateDate;
	
	/*备注*/
	private  String   note;
	
	
	
	private Enterprise enterprise=new Enterprise();
	
	
	
	@ManyToOne(cascade={CascadeType.REFRESH,CascadeType.MERGE})
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
	
	@Temporal(TemporalType.DATE)
	public Date getStartContractDate() {
		return startContractDate;
	}
	public void setStartContractDate(Date startContractDate) {
		this.startContractDate = startContractDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getEndContractDate() {
		return endContractDate;
	}
	public void setEndContractDate(Date endContractDate) {
		this.endContractDate = endContractDate;
	}
	
	@Column(length=100)
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Column(length=1)
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	@Temporal(TemporalType.TIMESTAMP)
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
	
}
