package cn.fm.service.company;

import java.util.List;

import cn.fm.bean.company.EnterpriseProjects;

public interface EnterpriseProjectsService {
	
	
public void save(EnterpriseProjects enterpriseProjects);
	
	public List<EnterpriseProjects> getAllEnterpriseProjects();
	
	public void updateEnterpriseContract(EnterpriseProjects enterpriseProjects);
	
	public EnterpriseProjects  getByIdEnterpriseProjects(String id);

	public void deleteEnterpriseProjects(String id);
	
	
	
	
}
