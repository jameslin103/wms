package cn.fm.service.company;

import java.util.List;

import cn.fm.bean.company.InsurancesTax;
import cn.fm.service.base.DAO;

public interface InsurancesTaxService extends DAO<InsurancesTax> {

		public void save(InsurancesTax insurancesTax);
		
		public List<InsurancesTax> getAllInsurancesTax();
		
		/**
		 * 更新实体
		 * @param insurancesTax
		 * @return
		 */
		public boolean updateInsurancesTax(InsurancesTax insurancesTax);
		
		
		public InsurancesTax  getInsurancesTax();
		
}
