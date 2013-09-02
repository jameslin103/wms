package cn.fm.web.action.company;



import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.struts2.ServletActionContext;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.CreateSalaryBudgetTable;
import cn.fm.bean.salary.SalaryTemplate;
import cn.fm.service.salary.CreateSalaryBudgetTableService;
import cn.fm.service.salary.SalaryTemplateService;
import cn.fm.utils.DateUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class CreateSalaryBudgetTableAction extends BaseAction {
	
	@Resource
	private CreateSalaryBudgetTableService createSalaryBudgetTableService;
	@Resource
	private SalaryTemplateService           salaryTemplateService;
	
	private CreateSalaryBudgetTable      createSalaryBudgetTable;
	
	List<CreateSalaryBudgetTable>  createSalaryBudgetTableList;
	
	private String salaryFileName ="工资预算表.xls";
	
	private String excelName;

	private String salaryDate;
	
	private String error;
	
	private String message; 
	private File   file;
	
	
	
	
	 public String getExcelName() {
		return excelName;
	}
	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}
	public String getMessage() {   
	        return message;   
	  }   
	 public void setMessage(String message) {   
	        this.message = message;   
	 }   
	public String getSalaryDate() {
		return salaryDate;
	}

	public void setSalaryDate(String salaryDate) {
		this.salaryDate = salaryDate;
	}
	
	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public List<CreateSalaryBudgetTable> getCreateSalaryBudgetTableList() {
		return createSalaryBudgetTableList;
	}

	public void setCreateSalaryBudgetTableList(
			List<CreateSalaryBudgetTable> createSalaryBudgetTableList) {
		this.createSalaryBudgetTableList = createSalaryBudgetTableList;
	}

	public CreateSalaryBudgetTable getCreateSalaryBudgetTable() {
		return createSalaryBudgetTable;
	}

	public void setCreateSalaryBudgetTable(
			CreateSalaryBudgetTable createSalaryBudgetTable) {
		this.createSalaryBudgetTable = createSalaryBudgetTable;
	}
	
	public void setCreateSalaryBudgetTableService(
			CreateSalaryBudgetTableService createSalaryBudgetTableService) {
		this.createSalaryBudgetTableService = createSalaryBudgetTableService;
	}

	public String viewSalaryBudgetTable()
	{
		
		List<CreateSalaryBudgetTable> createSalaryBudgetTableList=createSalaryBudgetTableService.getAllCreateSalaryBudgetTable();
		
		if(createSalaryBudgetTableList.size()==0)
			createSalaryBudgetTableList=new ArrayList<CreateSalaryBudgetTable>();
		
		request.setAttribute("createSalaryBudgetTable", createSalaryBudgetTableList);
		
		
		return SUCCESS;
	}
	
	public String  addSalaryBudgetTable()
	{
		Enterprise enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		if(createSalaryBudgetTable==null || createSalaryBudgetTable.getName()==null)return INPUT;
		if(createSalaryBudgetTable!=null && createSalaryBudgetTable.getBudgetId()!=null){
			if(enterprise==null || enterprise.getId()==null)return INPUT;
			createSalaryBudgetTable.setEnterprise(enterprise);
			CreateSalaryBudgetTable CreateSalaryBudgetTablePO=new CreateSalaryBudgetTable();
			CreateSalaryBudgetTablePO=createSalaryBudgetTable;
			createSalaryBudgetTableService.update(CreateSalaryBudgetTablePO);
			return SUCCESS;
		}else{
			createSalaryBudgetTable.setEnterprise(enterprise);
			createSalaryBudgetTable.setSalaryDate(DateUtil.StringToDate(this.salaryDate, DateUtil.FORMAT_DATE));
			createSalaryBudgetTableService.save(createSalaryBudgetTable);
			request.setAttribute("createSalaryBudgetTable",createSalaryBudgetTable );
			return SUCCESS;
		}
	}
	
	public String newSalaryBudgetTable()
	{
		createSalaryBudgetTable=new CreateSalaryBudgetTable();
		getSalaryTemplate(createSalaryBudgetTable);
		return SUCCESS;
	}
	public void getSalaryTemplate(CreateSalaryBudgetTable createSalaryBudgetTable)
	{
		Enterprise enterprise=(Enterprise)request.getSession().getAttribute("enterprise");
		if(enterprise==null)return;
		createSalaryBudgetTable.setEnterprise(enterprise);
		List<SalaryTemplate> salaryTemplateList=salaryTemplateService.getAllSalaryTemplate(createSalaryBudgetTable.getEnterprise().getId());
		if(salaryTemplateList.size()==0)
			salaryTemplateList=new ArrayList<SalaryTemplate>();
		request.setAttribute("salaryTemplates", salaryTemplateList);
	}
	public String findBeforeCurrentDateTemplate()
	{
		createSalaryBudgetTableList=salaryTemplateService.findBeforeCurrentDateTemplate(DateUtil.StringToDate(salaryDate, DateUtil.FORMAT_DATE),3);
		if(createSalaryBudgetTableList.size()==0){
			createSalaryBudgetTableList=new ArrayList<CreateSalaryBudgetTable>();
			this.setError("暂无记录");
		}else{
			this.setError("true");
		}
	
		return "createSalaryBudgetTableList";
	
	}
	public String modfiySalaryBudgetTable()
	{
		if(createSalaryBudgetTable==null)return INPUT;
		getSalaryTemplate(createSalaryBudgetTable);
		request.setAttribute("createSalaryBudgetTable",createSalaryBudgetTable );
    	return SUCCESS;
		
	}
	
	public String downloadSalaryBudgetTable()
	{
		
		try {
			excelName = new String(salaryFileName.getBytes(), "iso8859-1");//解决中文 文件名问题
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return SUCCESS;
		
	}
	 public InputStream getDownloadFile()  
	 {  
	        return ServletActionContext.getServletContext().getResourceAsStream("/doc/"+salaryFileName);  
	        
	        
	 } 


}
