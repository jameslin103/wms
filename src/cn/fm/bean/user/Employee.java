package cn.fm.bean.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;



@NamedQueries({
@NamedQuery(name = "findAllEmps", query = "from Employee"),
@NamedQuery(name="findEmpByName",query="from Employee where name=?1")
})

@NamedNativeQuery(name="findAllEmpsSQL",
	query="select * from erp_employee",
	resultClass=Employee.class)

	
@JsonIgnoreProperties(value={"department"})	
@Entity
@Table(name = "wms_employee")
public class Employee {
	
	private String id;
	private String name;
	private String gender;
	private String empNo;
	private String marryState;
	private Date birthDate;
	private String degree;
	private String image;
	private String idCard;
	private String email;
	private String telPhone;
	private String phone;
	private String empState;
	
	private Department department=new Department();
	private WmsUser user;
	
	private User usern;

	@Id
	@GenericGenerator(name = "idGenerator", strategy ="uuid")
	@GeneratedValue(generator = "idGenerator")
	@Column(length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(length=10)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	@Column(length=1)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(length=4)
	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}
	
	@ManyToOne
	@JoinColumn(name="deptid")
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	@Column(length=2)
	public String getMarryState() {
		return marryState;
	}

	public void setMarryState(String marryState) {
		this.marryState = marryState;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	@Column(length=3)
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	@Column(length=100)
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	@Column(length=18)
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	@Column(length=50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(length=11)
	public String getTelPhone() {
		return telPhone;
	}

	public void setTelPhone(String telPhone) {
		this.telPhone = telPhone;
	}
	@Column(length=13)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(length=2)
	public String getEmpState() {
		return empState;
	}

	public void setEmpState(String empState) {
		this.empState = empState;
	}
	@OneToOne(mappedBy="employee")
	public WmsUser getUser() {
		return user;
	}

	public void setUser(WmsUser user) {
		this.user = user;
	}

	@OneToOne(mappedBy="employee")
	public User getUsern() {
		return usern;
	}

	public void setUsern(User usern) {
		this.usern = usern;
	}
	

}
