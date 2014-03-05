package cn.fm.service.salary;

import java.util.List;

import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.salary.BalanceDetail;
import cn.fm.service.base.DAO;

public interface BalanceDetailService extends DAO<BalanceDetail> {

	
	public void save(BalanceDetail balanceDetail);
	/**
	 * 查询目前这个企业的自己往来情况
	 */
	public List<BalanceDetail>  getAllBalanceDetail(Integer enterpriseId);
	
	public boolean  updateBalanceDetail(BalanceDetail balanceDetail);
	
	
	public void deleteBalanceDetail(Integer budgetId);
	
	
	public PageView<BalanceDetail>  getAllEnterpriseBalanceDetail(int maxresult, int page,Enterprise enterprsie);
	
}
