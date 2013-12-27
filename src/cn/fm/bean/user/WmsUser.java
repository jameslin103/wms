package cn.fm.bean.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.Role;
import cn.fm.bean.permissions.WmsRole;
import cn.fm.bean.salary.CreateSalaryBudgetTable;


@JsonIgnoreProperties({ "employee", "roles", "privileges", "enterprise", "createSalaryBudgetTable", "roless" })
@Entity
public class WmsUser implements Serializable{
	
	private static final long serialVersionUID = 8394979715028899027L;
	
	private Integer userId;
	
	private String username;//
	
	private String password;//密码MD5加密
	
	private String phone;
	
	private String email;

	private Gender gender=Gender.MAN;

	private ContactInfo contactInfo;

	private Boolean visible=true;

	private Date regTime = new Date();
	
	private String status;
	
	private Date lastLoginTime;
	
	public final static String NORMAL = "正常";
	public final static String ABSNORMAL = "禁用";
	
	private Set<Enterprise> enterprise=new HashSet<Enterprise>();
	
	private String roleIds;
	
	private List<Role> roles = new ArrayList<Role>();
	
	private Set<CreateSalaryBudgetTable> createSalaryBudgetTable=new HashSet<CreateSalaryBudgetTable>();
	
	private Set<WmsRole> roless= new HashSet<WmsRole>();
	
	private Set<Privilege> privileges = new HashSet<Privilege>();
	
	private Employee employee;
	 
    @ManyToMany(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,mappedBy="user")          
	public Set<Enterprise> getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Set<Enterprise> enterprise) {
		this.enterprise = enterprise;
	}
	
	@OneToMany(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,mappedBy="user")
	public Set<CreateSalaryBudgetTable> getCreateSalaryBudgetTable() {
		return createSalaryBudgetTable;
	}

	public void setCreateSalaryBudgetTable(
			Set<CreateSalaryBudgetTable> createSalaryBudgetTable) {
		this.createSalaryBudgetTable = createSalaryBudgetTable;
	}
	
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="employee_id")
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Id @GeneratedValue
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	@Column(length=2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	
	
	public WmsUser(){}
	
	public WmsUser(String username){
		this.username = username;
	}
	
	public WmsUser(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public WmsUser(String username, String password, String email) {
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	@Column(length=18)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Column(length=32)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Column(length=13)
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Column(length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Enumerated(EnumType.STRING) @Column(length=5)
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="contactid")
	public ContactInfo getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(ContactInfo contactInfo) {
		this.contactInfo = contactInfo;
	}

	public Boolean getVisible() {
		return visible;
	}
	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	public String getRoleIds() {
		return roleIds;
	}

	@Column(length=20)
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	@Transient
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", 
			joinColumns = @JoinColumn(name="user_id"), 
			inverseJoinColumns = @JoinColumn(name="role_id"))
	public Set<WmsRole> getRoless() {
		return roless;
	}

	public void setRoless(Set<WmsRole> roless) {
		this.roless = roless;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_priv", 
			joinColumns = @JoinColumn(name="user_id"), 
			inverseJoinColumns =@JoinColumn(name="priv_id"))
	@OrderBy(value = "orderNum")
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		WmsUser other = (WmsUser) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	
	
}
