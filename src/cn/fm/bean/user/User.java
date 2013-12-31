package cn.fm.bean.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.Role;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.company.Enterprise;
	

@JsonIgnoreProperties({ "employee", "roles", "privileges" })
@Entity
@Table(name ="wms_user")
	public class User {
		
		private String id;
		private String account;
		private String password;
		private Employee employee=new Employee();
		private String status;
		private Date lastLoginTime;
		public final static String NORMAL = "正常";
		public final static String ABSNORMAL = "禁用";
		
		
		private Set<Role> roles = new HashSet<Role>();
		
		private Set<Privilege> privileges = new HashSet<Privilege>();
		
		private Set<Enterprise> enterprise=new HashSet<Enterprise>();
		
		private Set<CreateSalaryBudgetTable> createSalaryBudgetTable=new HashSet<CreateSalaryBudgetTable>();
		
		
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
		
		
		
		@Id
		@GenericGenerator(name = "idGenerator", strategy = "uuid")
		@GeneratedValue(generator = "idGenerator")
		@Column(length=32)
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
		@Column(length=32)
		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		@Column(length=12)
		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}
		
		@OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.REFRESH,optional=false)
		@JoinColumn(name ="employee_id")
		public Employee getEmployee() {
			return employee;
		}

		public void setEmployee(Employee employee) {
			this.employee = employee;
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
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name ="wms_user_role", 
				joinColumns = @JoinColumn(name ="user_id"), 
				inverseJoinColumns = @JoinColumn(name ="role_id"))
		public Set<Role> getRoles() {
			return roles;
		}

		public void setRoles(Set<Role> roles) {
			this.roles = roles;
		}
		
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(name = "wms_user_priv", joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "priv_id"))
		@OrderBy(value = "orderNum")
		public Set<Privilege> getPrivileges() {
			return privileges;
		}

		public void setPrivileges(Set<Privilege> privileges) {
			this.privileges = privileges;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof User) {
				User u = (User) obj;
				return getId().equals(u.getId());
			}
			return false;
		}

		@Override
		public int hashCode() {
			return getId().hashCode();
		}

	}

