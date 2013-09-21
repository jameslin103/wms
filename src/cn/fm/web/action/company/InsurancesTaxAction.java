package cn.fm.web.action.company;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import cn.fm.bean.company.InsurancesTax;
import cn.fm.service.company.InsurancesTaxService;
import cn.fm.utils.DateUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class InsurancesTaxAction extends BaseAction{

	@Resource
	private InsurancesTaxService insurancesTaxService;
	private InsurancesTax        insurancesTax;
	private String                 startDate;
	private Integer 			 taxId;
	private InsurancesTax        insuranceTaxJson;
	
	public Integer getTaxId() {
		return taxId;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public InsurancesTax getInsurancesTax() {
		return insurancesTax;
	}

	public void setInsurancesTax(InsurancesTax insurancesTax) {
		this.insurancesTax = insurancesTax;
	}
	


	public InsurancesTax getInsuranceTaxJson() {
		return insuranceTaxJson;
	}

	public void setInsuranceTaxJson(InsurancesTax insuranceTaxJson) {
		this.insuranceTaxJson = insuranceTaxJson;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String addInsurancesTax(){
		
		if(insurancesTax==null)return INPUT;
		insurancesTax.setStartDate(DateUtil.StringToDate(this.startDate, DateUtil.FORMAT_DATE));
		insurancesTaxService.save(insurancesTax);
			
		return SUCCESS;
		
	}
	public String toViewTaxRules()
	{
		
		List<InsurancesTax> insurancesTax=insurancesTaxService.getAllInsurancesTax();
		if(insurancesTax.size()==0)
			insurancesTax=new ArrayList<InsurancesTax>();
		request.setAttribute("insurancesTax", insurancesTax);
		return SUCCESS;
		
	}
	public String findInsurancesTax()
	{
		
		insuranceTaxJson=insurancesTaxService.find(taxId);
		
		return "insuranceTaxJson";
	}
	public String  updateInsurancesTax()
	{
		insurancesTaxService.updateInsurancesTax(insurancesTax);
		return SUCCESS;
	}
	
}
