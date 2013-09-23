package cn.fm.service.salary;

import java.util.List;

import cn.fm.bean.salary.BalanceDetail;
import cn.fm.service.base.DAO;

public interface BalanceDetailService extends DAO<BalanceDetail> {

	
	public void save(BalanceDetail balanceDetail);
	/**
	 * 查询目前这个企业的自己往来情况
	 */
	public List<BalanceDetail>  getAllBalanceDetail(Integer enterpriseId,Integer employeeId);
	public void  update(BalanceDetail balanceDetail,Integer detailId,Integer enterpriseId);
	
	
	
	
	
	
	
}
