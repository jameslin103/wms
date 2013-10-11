package cn.fm.web.action.salary;

import javax.annotation.Resource;

import cn.fm.bean.salary.TaxOfPerson;
import cn.fm.service.salary.TaxOfPersonService;
import cn.fm.utils.StringUtil;
import cn.fm.web.action.BaseAction;

@SuppressWarnings("serial")
public class TaxOfPersonAction extends BaseAction {
	
	@Resource
	private TaxOfPersonService     taxOfPersonService;
	
	
	private TaxOfPerson			   taxOfPerson;
	
	
	
	
	
	
	
	
	public TaxOfPerson getTaxOfPerson() {
		return taxOfPerson;
	}
	public void setTaxOfPerson(TaxOfPerson taxOfPerson) {
		this.taxOfPerson = taxOfPerson;
	}
	public void setTaxOfPersonService(TaxOfPersonService taxOfPersonService) {
		this.taxOfPersonService = taxOfPersonService;
	}
	public String viewTaxOfPerson()
	{
		
		TaxOfPerson taxOfPerson=taxOfPersonService.getTaxOfPerson();
		if(taxOfPerson==null)taxOfPerson=new TaxOfPerson();
		request.setAttribute("taxOfPerson", taxOfPerson);
		return SUCCESS;
	}
	public String updateTaxOfPerson()
	{
		if(taxOfPerson.getTaxThreshold()==null || taxOfPerson.getStatrDate()==null)return INPUT;
		taxOfPersonService.updateTaxOfPerson(taxOfPerson);
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	

}
