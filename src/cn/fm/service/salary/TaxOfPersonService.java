package cn.fm.service.salary;

import cn.fm.bean.salary.TaxOfPerson;
import cn.fm.service.base.DAO;

public interface TaxOfPersonService extends DAO<TaxOfPerson> {

	
	//查询起征点个税
	public TaxOfPerson getTaxOfPerson();
	
	//修改征税起点
	public void updateTaxOfPerson(TaxOfPerson taxOfPerson );
	
	
}
