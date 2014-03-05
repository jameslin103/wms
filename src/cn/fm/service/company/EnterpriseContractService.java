package cn.fm.service.company;

import java.util.List;

import cn.fm.bean.company.EnterpriseContract;

public interface EnterpriseContractService{

	public void save(EnterpriseContract enterpriseContract);
	
	public List<EnterpriseContract> getAllEnterpriseContract();
	
	public void updateEnterpriseContract(EnterpriseContract enterpriseContract);
	
	public EnterpriseContract  getByIdEnterpriseContract(String id);
	
	
	public void delete(String id);
	
	
}
