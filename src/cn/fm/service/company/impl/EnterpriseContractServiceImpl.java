package cn.fm.service.company.impl;

import java.util.Date;
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
		enterpriseContractPO.setUpdateDate(new Date());
		enterpriseContractDAO.update(enterpriseContractPO);
		
		
		
	}

	@Override
	public EnterpriseContract getByIdEnterpriseContract(String id) {
		return enterpriseContractDAO.findById(id);
	}

	@Override
	public void delete(String id) {
		
		EnterpriseContract	enterpriseContract=enterpriseContractDAO.findById(id);
		enterpriseContractDAO.delete(enterpriseContract);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
