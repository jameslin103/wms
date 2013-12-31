package cn.fm.web.action.salary;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.Column;

import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.BalanceDetail;
import cn.fm.bean.user.User;
import cn.fm.service.company.EnterpriseService;
import cn.fm.service.salary.BalanceDetailService;
import cn.fm.utils.WebUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class BalanceDetailAction extends BaseAction {
	
	@Resource
	private BalanceDetailService balanceDetailService;
	@Resource 
	private EnterpriseService    enterpriseService;
	
	
	private BalanceDetail		  balanceDetail;
	
	private Integer				 enterpriseId;
	
	private Integer				 employeeId;
	private Integer				 detailId;

	private int page;
	
	public int getPage() {
		return page<1?1:page;
	}
	public void setPage(int page) {
		this.page = page;
	}
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
	
				   
	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String  viewBalanceDetail()
	{
		Enterprise enterprise=null;
		if(enterpriseId==null){
			enterprise=WebUtil.getEnterprise(request);
		}else{
			enterprise=enterpriseService.find(this.getEnterpriseId());
			request.getSession().setAttribute("enterprise", enterprise);
		}
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("createDate", "asc");
		StringBuffer jpql = new StringBuffer("");
		List<Object> params = new ArrayList<Object>();
		if(enterprise.getEnterpriseId()!=null)
		{
			jpql.append(" o.enterprise.enterpriseId=?").append(params.size()+1);
			params.add(enterprise.getEnterpriseId());
			PageView<BalanceDetail> pageView = new PageView<BalanceDetail>(10,  this.getPage());
			pageView.setQueryResult(balanceDetailService.getScrollData(pageView.getFirstResult(), 
					pageView.getMaxresult(),jpql.toString(),params.toArray(), orderby));
			request.setAttribute("pageView", pageView);
		}
		
		return SUCCESS;
	}
	public String findToIdBalanceDetail()
	{
		balanceDetail=balanceDetailService.find(detailId);
		return SUCCESS;
	}
	public String   addBalanceDetail()
	{
		if(balanceDetail==null)return INPUT;
		User user=WebUtil.getUser(request);
		balanceDetail.setUserIusse(user.getEmployee().getName());
		Double endblan=(balanceDetail.getWages()==null?0.00:balanceDetail.getWages().doubleValue()+
				(balanceDetail.getServiceWith()==null?0.00:balanceDetail.getServiceWith().doubleValue())+
				(balanceDetail.getFiveFund()==null?0.00:balanceDetail.getFiveFund().doubleValue()));
			
		balanceDetail.setEndingBalance(new BigDecimal(endblan).setScale(2,BigDecimal.ROUND_HALF_UP));
		balanceDetail.setBalance(balanceDetail.getEndingBalance());
		balanceDetailService.updateBalanceDetail(balanceDetail);
		return SUCCESS;
		
	}
	
	
	
	

}
