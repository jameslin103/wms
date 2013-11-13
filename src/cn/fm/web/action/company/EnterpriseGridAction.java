package cn.fm.web.action.company;


import javax.annotation.Resource;

import cn.fm.service.base.BaseGrid;
import cn.fm.service.company.EnterpriseService;
import cn.fm.web.BaseGridAction;


public class EnterpriseGridAction extends BaseGridAction{
	
	@Resource
	private EnterpriseService   enterpriseService;
	
	
	
	
	
	
	 public void setEnterpriseService(EnterpriseService enterpriseService) {
		this.enterpriseService = enterpriseService;
	}






	public BaseGrid getBaseGrid() {
	        return super.baseGrid;
	    }






	@Override
	public boolean getIsJsonSessionExpired() {
		// TODO Auto-generated method stub
		return false;
	}






	@Override
	public boolean getIsJsonUserLogin() {
		// TODO Auto-generated method stub
		return false;
	}

}
