package cn.fm.service.company;

import java.util.List;

import cn.fm.bean.company.Enterprise;
import cn.fm.service.base.DAO;


public interface EnterpriseService extends DAO<Enterprise> {

	public List<Enterprise> getAllEnterprise();
	
}
