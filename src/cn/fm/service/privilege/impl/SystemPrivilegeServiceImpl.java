package cn.fm.service.privilege.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.fm.bean.permissions.SystemPrivilege;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.privilege.SystemPrivilegeService;


@Service
public class SystemPrivilegeServiceImpl extends DaoSupport<SystemPrivilege> implements SystemPrivilegeService {
	
	public void batchSave(List<SystemPrivilege> privileges){
		for(SystemPrivilege p : privileges){
			save(p);
		}
	}
}
