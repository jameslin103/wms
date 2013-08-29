package cn.fm.web.action.salary;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;


import cn.fm.bean.salary.BalanceDetail;
import cn.fm.service.salary.BalanceDetailService;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class BalanceDetailAction extends BaseAction {
	
	@Resource
	private BalanceDetailService balanceDetailService;
	
	private BalanceDetail		  balanceDetail;
	
	private Integer				 enterpriseId;
	

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
				   
	public String  viewBalanceDetail()
	{
		List<BalanceDetail> balanceDetailList=balanceDetailService.getAllBalanceDetail();
		if(balanceDetailList.size()==0)
			balanceDetailList=new ArrayList<BalanceDetail>();
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
