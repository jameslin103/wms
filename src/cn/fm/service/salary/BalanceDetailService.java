package cn.fm.service.salary;

import java.util.List;

import cn.fm.bean.salary.BalanceDetail;
import cn.fm.service.base.DAO;

public interface BalanceDetailService extends DAO<BalanceDetail> {

	
	public void save(BalanceDetail balanceDetail);
	public List<BalanceDetail>  getAllBalanceDetail();
	public void  update(BalanceDetail balanceDetail,Integer detailId,Integer enterpriseId);
	
	
	
	
	
	
	
}
