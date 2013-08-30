package cn.fm.bean.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.fm.bean.company.Enterprise;


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
	
	private Set<Enterprise> enterprise=new HashSet<Enterprise>();
	
	
	
	
	
	
	  @ManyToMany(cascade = CascadeType.REFRESH,fetch=FetchType.EAGER)  
	  @JoinTable(name = "user_enterprise",
			  inverseJoinColumns = @JoinColumn(name = "enterpriseId"),
			  joinColumns = @JoinColumn(name = "userId")) //JoinTable就是定义中间表的名字以及关联字段名 
	public Set<Enterprise> getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(Set<Enterprise> enterprise) {
		this.enterprise = enterprise;
	}
	@Id @GeneratedValue
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Temporal(TemporalType.TIMESTAMP) @Column(nullable=false)
	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
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
	@Column(length=8)
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
	  public void addEterprise(Enterprise  enterprise){  
	        this.enterprise.add(enterprise);  
	    }  
	      
	    public void removeEnterprise(Enterprise enterprise){  
	         if(this.enterprise.contains(enterprise)){
	             this.enterprise.remove(enterprise);  
	         }  
	    }  
	
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		final WmsUser other = (WmsUser) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
}
