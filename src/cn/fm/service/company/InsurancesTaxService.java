package cn.fm.service.company;

import cn.fm.bean.company.InsurancesTax;
import cn.fm.service.base.DAO;

public interface InsurancesTaxService extends DAO<InsurancesTax> {

		public void save(InsurancesTax insurancesTax);
}
