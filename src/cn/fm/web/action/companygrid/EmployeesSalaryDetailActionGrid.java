package cn.fm.web.action.companygrid;

import javax.annotation.Resource;

import cn.fm.service.base.BaseGrid;
import cn.fm.service.salary.EmployeesSalaryDetailService;
import cn.fm.web.BaseGridAction;


public class EmployeesSalaryDetailActionGrid extends BaseGridAction{
	
	@Resource
	private EmployeesSalaryDetailService 	employeesSalaryDetailService;
	
	
	
	
	private String  viewSalaryWithBankPersonalNumber1(){
		
		
		//baseGrid=employeesSalaryDetailService.getPayrollStaff(baseGrid);
		
		return "SUCCESS";
		
	}




	@Override
	public BaseGrid getBaseGrid() {
		// TODO Auto-generated method stub
		return super.baseGrid;
	}




	@Override
	public boolean getIsJsonSessionExpired() {
		// TODO Auto-generated method stub
		return this.isJsonSessionExpired;
	}




	@Override
	public boolean getIsJsonUserLogin() {
		// TODO Auto-generated method stub
		return this.isJsonUserLogin;
	}
	
	
	
	
	
	
	
	
}
