package cn.fm.service.company.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.fm.bean.company.EnterpriseProjects;
import cn.fm.dao.EnterpriseProjectsDAO;
import cn.fm.service.company.EnterpriseProjectsService;

@Transactional @Service
public class EnterpriseProjectsServiceImpl implements EnterpriseProjectsService{

	@Resource
	private EnterpriseProjectsDAO  enterpriseProjectsDAO;

	@Override
	public List<EnterpriseProjects> getAllEnterpriseProjects() {
		return enterpriseProjectsDAO.findAll();
	}

	@Override
	public EnterpriseProjects getByIdEnterpriseProjects(String id) {
		return enterpriseProjectsDAO.findById(id);
	}

	@Override
	public void save(EnterpriseProjects enterpriseProjects) {
		enterpriseProjectsDAO.save(enterpriseProjects);
		
	}

	@Override
	public void updateEnterpriseContract(EnterpriseProjects enterpriseProjects) {
		
		EnterpriseProjects enterpriseProjectsPO=enterpriseProjectsDAO.findById(enterpriseProjects.getId());
		
		enterpriseProjectsPO.setCustomType(enterpriseProjects.getCustomType());
		enterpriseProjectsPO.setProjects(enterpriseProjects.getProjects());
		enterpriseProjectsPO.setProportion(enterpriseProjects.getProportion());
		enterpriseProjectsPO.setServiceHead(enterpriseProjects.getServiceHead());
		enterpriseProjectsPO.setServiceType(enterpriseProjects.getServiceType());
		enterpriseProjectsPO.setNote(enterpriseProjects.getNote());
		enterpriseProjectsPO.setUpdateDate(new Date());
		
		enterpriseProjectsDAO.update(enterpriseProjectsPO);
	}

	@Override
	public void deleteEnterpriseProjects(String id) {
		
		EnterpriseProjects enterpriseProjects=enterpriseProjectsDAO.findById(id);
		enterpriseProjectsDAO.delete(enterpriseProjects);
		
	}
	


}
