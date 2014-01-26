package cn.fm.bean.salary;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseProjects;


@Entity
public class SalaryTemplate {

	private Integer  templateId;
	
	/*名称*/
	private String templateName;
	
	/*各种补贴*/
	private String subsidyList;

	/*是否包含五险一金* 1 是 0 否*/
	private  Integer  fiveInsurances;

	/*是否包含个税* 1 是 0 否*/
	private  Integer  tax;

	/*是否启用 1 启用 0 停用*/
	private  Integer  status;
	 
	private   Date    createDate=new Date();
	
	private List<String>  subsidys=new ArrayList<String>();
	
	private Set<CreateSalaryBudgetTable>   createSalaryBudgetTable=new HashSet<CreateSalaryBudgetTable>();
	
	private Enterprise   enterprise;
	
	//private Set<CustomBonus>  customBonus=new HashSet<CustomBonus>();
	
	private EnterpriseProjects enterpriseProjects;
	
	@ManyToOne(cascade=CascadeType.REFRESH,optional=true)
	@JoinColumn(name="enterpriseId")
	@NotFound(action=NotFoundAction.IGNORE)
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}
	
	
	@Id @GeneratedValue
	@Column(length=36)
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
	@Column(length=100)
	public String getTemplateName() {
		return templateName;
	}
	
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	@Column(length=80)
	public String getSubsidyList() {
		return subsidyList;
	}

	public void setSubsidyList(String subsidyList) {
		this.subsidyList = subsidyList;
	}
	@Column(length=1)
	public Integer getFiveInsurances() {
		return fiveInsurances;
	}
	
	public void setFiveInsurances(Integer fiveInsurances) {
		this.fiveInsurances = fiveInsurances;
	}
	@Column(length=1)
	public Integer getTax() {
		return tax;
	}

	public void setTax(Integer tax) {
		this.tax = tax;
	}
	@Column(length=1)
	public Integer getStatus(){
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Transient
	public List<String> getSubsidys() {
		return subsidys;
	}

	public void setSubsidys(List<String> subsidys) {
		this.subsidys = subsidys;
	}
	@OneToMany(cascade={CascadeType.REFRESH},fetch=FetchType.EAGER,mappedBy="salaryTemplate")
	@NotFound(action=NotFoundAction.IGNORE)
	public Set<CreateSalaryBudgetTable> getCreateSalaryBudgetTable() {
		return createSalaryBudgetTable;
	}
	public void setCreateSalaryBudgetTable(
			Set<CreateSalaryBudgetTable> createSalaryBudgetTable) {
		this.createSalaryBudgetTable = createSalaryBudgetTable;
	}
	
	public void addCreateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable){
		this.createSalaryBudgetTable.add(createSalaryBudgetTable);
	}
	public void romveCreateSalaryBudgetTable(CreateSalaryBudgetTable createSalaryBudgetTable){
		if(this.createSalaryBudgetTable.contains(createSalaryBudgetTable)){
			this.romveCreateSalaryBudgetTable(createSalaryBudgetTable);
		}
	}
//	@OneToMany(cascade={CascadeType.REFRESH},fetch=FetchType.LAZY,mappedBy="salaryTemplate")
//	@NotFound(action=NotFoundAction.IGNORE)
//	public Set<CustomBonus> getCustomBonus(){
//		return customBonus;
//	}
//	public void setCustomBonus(Set<CustomBonus> customBonus) {
//		this.customBonus = customBonus;
//	}
//	
//	public void addCustomBonus(CustomBonus customBonus){
//		this.customBonus.add(customBonus);	
//	}
//	
//	public void rovmeCustomBonus(CustomBonus customBonus){
//		if(this.customBonus.contains(customBonus)){
//			this.customBonus.remove(customBonus);
//		}
//	}
	@OneToOne(cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@JoinColumn(name="custom_id")
	public EnterpriseProjects getEnterpriseProjects() {
		return enterpriseProjects;
	}
	public void setEnterpriseProjects(EnterpriseProjects enterpriseProjects) {
		this.enterpriseProjects = enterpriseProjects;
	}
	
	
}
