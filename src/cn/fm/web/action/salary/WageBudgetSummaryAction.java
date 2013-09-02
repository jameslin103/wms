package cn.fm.web.action.salary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.WageBudgetSummary;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.salary.WageBudgetSummaryService;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class WageBudgetSummaryAction extends BaseAction {
	
	@Resource
	private WageBudgetSummaryService  wageBudgetSummaryService;
	@Resource
	EnterpriseService           enterpriseService;
	private Integer             enterpriseId;
	private File                file;
	
	
	
	
	
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public void setWageBudgetSummaryService(
			WageBudgetSummaryService wageBudgetSummaryService) {
		this.wageBudgetSummaryService = wageBudgetSummaryService;
	}
	public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}
	public Integer getEnterpriseId() {
		return enterpriseId;
	}
	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}


	public String viewWageBudgetSummary()
	{	
		if(this.enterpriseId==null || this.enterpriseId==0)return SUCCESS;
		Enterprise enterprise=enterpriseService.find(this.enterpriseId);
		List<WageBudgetSummary> wageBudgetSummaryList=wageBudgetSummaryService.getAllWageBudgetSummary(1);
		if(wageBudgetSummaryList.size()==0)
			wageBudgetSummaryList=new ArrayList<WageBudgetSummary>();
		
		request.setAttribute("wageBudgetSummarys", wageBudgetSummaryList);
		request.getSession().setAttribute("enterprise", enterprise);

		return SUCCESS;
	}
	
	
	
	
	
	

}
