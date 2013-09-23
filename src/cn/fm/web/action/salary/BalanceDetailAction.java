package cn.fm.web.action.salary;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseEmployees;
import cn.fm.bean.salary.BalanceDetail;
import cn.fm.service.salary.BalanceDetailService;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class BalanceDetailAction extends BaseAction {
	
	@Resource
	private BalanceDetailService balanceDetailService;
	
	private BalanceDetail		  balanceDetail;
	
	private Integer				 enterpriseId;
	
	private Integer				 employeeId;
	

	public BalanceDetail getBalanceDetail() {
		return balanceDetail;
	}

	public void setBalanceDetail(BalanceDetail balanceDetail) {
		this.balanceDetail = balanceDetail;
	}
	
	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
	
				   
	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String  viewBalanceDetail()
	{
		Enterprise enterprise=WebUtil.getEnterprise(request);
		List<BalanceDetail> balanceDetailList=balanceDetailService.getAllBalanceDetail(enterprise.getEnterpriseId(),employeeId);
		if(balanceDetailList.size()==0)
			balanceDetailList=new ArrayList<BalanceDetail>();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createDate", "desc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			jpql.append(" o.enterpriseId=?").append(params.size()+1);
			params.add(enterprise.getEnterpriseId());
			PageView<BalanceDetail> pageView = new PageView<BalanceDetail>(10,  this.getPage());
			pageView.setQueryResult(balanceDetailService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}

		request.setAttribute("balanceDetails", balanceDetailList);
		
		return SUCCESS;
	}
	
	public String   addBalanceDetail()
	{
		if(balanceDetail==null)return INPUT;
		balanceDetailService.update(balanceDetail,balanceDetail.getDetailId(),3);
		return SUCCESS;
	}
	
	
	
	

}
