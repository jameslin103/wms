package cn.fm.service.salary.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.salary.BalanceDetail;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.salary.BalanceDetailService;

@Service @Transactional
public class BalanceDetailServiceImpl extends DaoSupport<BalanceDetail>	implements BalanceDetailService {
	
	@SuppressWarnings("unchecked")
	/**
	 * 查询目前这个企业的自己往来情况
	 */
	public List<BalanceDetail>  getAllBalanceDetail(Integer enterpriseId,Integer employeeId)
	{
		Query query=em.createQuery("select b from BalanceDetail b where b.enterpriseId=?1");
			  query.setParameter(1, enterpriseId);
		
		return query.getResultList();
		
	}
	public void save(BalanceDetail balanceDetail)
	{
		if(balanceDetail==null)return;
		super.save(balanceDetail);
		
	}
	public void  update(BalanceDetail balanceDetail,Integer detailId,Integer enterpriseId)
	{
		em.createQuery("update BalanceDetail o set o.receivedFunds=?1 , o.wages=?2 , o.serviceWith=?3 , o.fiveFund=?4 , o.note=?5 where o.detailId=?6 and o.enterpriseId=?7")
		.setParameter(1, balanceDetail.getReceivedFunds()).setParameter(2, balanceDetail.getWages())
		.setParameter(3, balanceDetail.getServiceWith()).setParameter(4,balanceDetail.getFiveFund())
		.setParameter(5,balanceDetail.getNote()).setParameter(6,detailId).setParameter(7,enterpriseId).executeUpdate();

	}
	
	
}
