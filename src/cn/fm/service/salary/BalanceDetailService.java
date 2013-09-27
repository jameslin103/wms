package cn.fm.service.salary;

import java.math.BigDecimal;
import java.util.List;

import cn.fm.bean.salary.BalanceDetail;
import cn.fm.service.base.DAO;

public interface BalanceDetailService extends DAO<BalanceDetail> {

	
	public void save(BalanceDetail balanceDetail);
	/**
	 * 查询目前这个企业的自己往来情况
	 */
	public List<BalanceDetail>  getAllBalanceDetail(Integer enterpriseId);
	public boolean  updateBalanceDetail(BalanceDetail balanceDetail);
	/**
	 * 查询企业资金往来
	 * @param enterpriseId
	 * @return
	 */
	public BigDecimal findBalanceDetail(Integer enterpriseId);
	
	
	
	
	
	
	
}
