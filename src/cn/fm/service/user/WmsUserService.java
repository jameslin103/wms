package cn.fm.service.user;

import java.io.Serializable;
import java.util.List;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.base.DAO;


public interface WmsUserService extends DAO<WmsUser>{

	
	public void enable(Serializable ... usernames);

	public boolean exsit(String phone);
	
	public boolean checkUser(String phone, String password);
	

	public void updatePassword(String username, String newpassword);
	
	
	public List getWmsUserInfo(Serializable ... usernames);

	
	public List<WmsUser> getAllWmsUser();
	
	
	public WmsUser find(String phone);
	
	/**
	 * 查询这个企业的所有负责人
	 * @param enterprise
	 * @return
	 */
	public List<WmsUser>  getEnterpriseToBoWmsUser(List<Enterprise> enterprise);
	
	public void assignRoles(String[] roleIds,WmsUser wmsUser);
	
	public void assignRoles(String[] roleIds, String[] userIds);
	
	
	
}