package cn.fm.bean.company;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
public class CreateSalaryBudgetTable implements Serializable {

	private Integer id;
	/*预算表名称*/
	private String  name;
	/*选择模板*/
	private String  temple;
	/*生成哪月工资？*/
	private Date    salaryDate;
	/*选择与其他工资表合并计税*/
	private String  chooseTax;
	/*补充说明*/
	private String   note;
	/*创建日期*/
	private Date    createDate=new Date();
	
	
	
	//private SalaryTemplate  salaryTemplate;

	@Id @GeneratedValue
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	@Column(length=30)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(length=30)
	public String getTemple() {
		return temple;
	}

	public void setTemple(String temple) {
		this.temple = temple;
	}
	@Temporal(TemporalType.DATE)
	public Date getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(Date salaryDate) {
		this.salaryDate = salaryDate;
	}
	@Temporal(TemporalType.DATE)
	public Date getCreateDate() {
		return createDate;
	}
	
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	@Column(length=10)
	public String getChooseTax() {
		return chooseTax;
	}
	
	public void setChooseTax(String chooseTax) {
		this.chooseTax = chooseTax;
	}
	@Column(length=100)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
/*
	@OneToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},mappedBy="createSalaryBudgetTable")
	public SalaryTemplate getSalaryTemplate() {
		return salaryTemplate;
	}

	public void setSalaryTemplate(SalaryTemplate salaryTemplate) {
		this.salaryTemplate = salaryTemplate;
	}
	*/
	
}
