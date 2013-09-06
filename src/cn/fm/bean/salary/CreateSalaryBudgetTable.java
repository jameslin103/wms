package cn.fm.bean.salary;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.fm.bean.company.Enterprise;

@SuppressWarnings("serial")
@Entity
public class CreateSalaryBudgetTable implements Serializable {

	private Integer budgetId;
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
	
	private SalaryTemplate  salaryTemplate;
	
	private WageBudgetSummary  wageBudgetSummary;
	 
	private Enterprise enterprise;
	

	@Id @GeneratedValue
	public Integer getBudgetId() {
		return budgetId;
	}

	public void setBudgetId(Integer budgetId) {
		this.budgetId = budgetId;
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
	@ManyToOne(cascade = { CascadeType.MERGE})
	@JoinColumn(name = "enterpriseId")
	public Enterprise getEnterprise() {
		return enterprise;
	}
	public void setEnterprise(Enterprise enterprise) {
		this.enterprise = enterprise;
	}

	
	 public WageBudgetSummary getWageBudgetSummary() {
			return wageBudgetSummary;
	 }

	  public void setWageBudgetSummary(WageBudgetSummary wageBudgetSummary) {
			this.wageBudgetSummary = wageBudgetSummary;
	  }
	
	

	@OneToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},mappedBy="createSalaryBudgetTable")
	public SalaryTemplate getSalaryTemplate() {
		return salaryTemplate;
	}

	public void setSalaryTemplate(SalaryTemplate salaryTemplate) {
		this.salaryTemplate = salaryTemplate;
	}
	
	
	


	@Override  
	    public int hashCode() {  
	        final int prime = 31;  
	        int result = 1;  
	        result = prime * result + ((budgetId == null) ? 0 : budgetId.hashCode());  
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
	        CreateSalaryBudgetTable table = (CreateSalaryBudgetTable) obj;  
	        if (budgetId== null) {  
	            if (table.budgetId != null)  
	                return false;  
	        } else if (!budgetId.equals(table.budgetId))  
	            return false;  
	        return true;  
	    }  
	  
	
}