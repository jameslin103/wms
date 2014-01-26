package cn.fm.service.company.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.EnterpriseContract;
import cn.fm.dao.EnterpriseContractDAO;
import cn.fm.service.company.EnterpriseContractService;


@Transactional @Service
public class EnterpriseContractServiceImpl implements EnterpriseContractService{

	@Resource
	private EnterpriseContractDAO enterpriseContractDAO;
	
	
	
	@Override
	public List<EnterpriseContract> getAllEnterpriseContract() {
		
		return enterpriseContractDAO.findAll();
		
	}

	@Override
	public void save(EnterpriseContract enterpriseContract) {
		if(enterpriseContract!=null)
			enterpriseContractDAO.save(enterpriseContract);
		
	}

	@Override
	public void updateEnterpriseContract(EnterpriseContract enterpriseContract) {
		
		EnterpriseContract enterpriseContractPO=enterpriseContractDAO.findById(enterpriseContract.getId());
		enterpriseContractPO.setEndContractDate(enterpriseContract.getEndContractDate());
		enterpriseContractPO.setStartContractDate(enterpriseContract.getStartContractDate());
		enterpriseContractPO.setNote(enterpriseContract.getNote());
		enterpriseContractPO.setUpdateDate(enterpriseContract.getUpdateDate());
		enterpriseContractDAO.save(enterpriseContractPO);
		
		
		
	}

	@Override
	public EnterpriseContract getByIdEnterpriseContract(String id) {
		return enterpriseContractDAO.findById(id);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
