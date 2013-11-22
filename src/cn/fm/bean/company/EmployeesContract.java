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

@Entity
public class EmployeesContract {

	private  Integer  contractid;
	
	/*合同编号*/
	private String    contractNo;  
	
	private  Date     contractStatrDate;
	
	private  Date     contractEndDate;
	
	private  Date     actualTerminationDate;
	
	private  String   status;
	
	private  Date     createDate=new Date();
	
	private  EnterpriseEmployees  enterpriseEmployees;

	
	
	@Id @GeneratedValue
	public Integer getContractid() {
		return contractid;
	}

	public void setContractid(Integer contractid) {
		this.contractid = contractid;
	}
	
	@Column(length=20)
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getContractStatrDate() {
		return contractStatrDate;
	}

	public void setContractStatrDate(Date contractStatrDate) {
		this.contractStatrDate = contractStatrDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getActualTerminationDate() {
		return actualTerminationDate;
	}

	public void setActualTerminationDate(Date actualTerminationDate) {
		this.actualTerminationDate = actualTerminationDate;
	}
	@Column(length=5)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@ManyToOne(cascade=CascadeType.REFRESH,optional=false)
	@JoinColumn(name="employees_id")
	@NotFound(action=NotFoundAction.IGNORE)
	public EnterpriseEmployees getEnterpriseEmployees() {
		return enterpriseEmployees;
	}

	public void setEnterpriseEmployees(EnterpriseEmployees enterpriseEmployees) {
		this.enterpriseEmployees = enterpriseEmployees;
	}
	
	
	
}
