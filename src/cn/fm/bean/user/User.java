package cn.fm.bean.user;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import cn.fm.bean.permissions.Privilege;
import cn.fm.bean.permissions.WmsRole;
	

@JsonIgnoreProperties({ "employee", "roles", "privileges" })
@Entity
@Table(name ="wms_user")
	public class User {
		
		private String id;
		private String account;
		private String password;
		private Employee employee;
		private String status;
		private Date lastLoginTime;
		public final static String NORMAL = "正常";
		public final static String ABSNORMAL = "禁用";
		
		private Set<WmsRole> roles = new HashSet<WmsRole>();
		private Set<Privilege> privileges = new HashSet<Privilege>();

		
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
		
		@OneToOne(fetch = FetchType.LAZY)
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
		public Set<WmsRole> getRoles() {
			return roles;
		}

		public void setRoles(Set<WmsRole> roles) {
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

