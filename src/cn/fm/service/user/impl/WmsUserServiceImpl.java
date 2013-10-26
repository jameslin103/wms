package cn.fm.service.user.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.company.Enterprise;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.user.WmsUserService;



@Service @Transactional
public class WmsUserServiceImpl extends DaoSupport<WmsUser> implements WmsUserService {

	public void updatePassword(String phone, String newpassword){
		em.createQuery("update WmsUser o set o.password=?1 where o.phone=?2")
		.setParameter(1, newpassword).setParameter(2, phone).executeUpdate();
	}

	public void save(WmsUser entity) {
		//entity.setPassword(MD5.MD5Encode(entity.getPassword()));
		super.save(entity);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean exsit(String phone){
		long count = (Long)em.createQuery("select count(o) from WmsUser o where o.phone=?1").setParameter(1, phone)
			.getSingleResult();
		return count>0;
	}
	
	public boolean checkUser(String phone, String password){
		long count = (Long)em.createQuery("select count(o) from WmsUser o where o.phone=?1 and o.password=?2")
		.setParameter(1, phone).setParameter(2,password).getSingleResult();
		return count>0;
	}
	

	public void delete(Serializable ... entityIds){
		visible(false, entityIds);
	}


	public long getCount() {
		return (Long)em.createQuery("select count(o) from WmsUser o where o.visible=?1")
		.setParameter(1, true).getSingleResult();
	}

	private void visible(boolean visible, Serializable ... usernames){
		if(usernames!=null && usernames.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0; i<usernames.length;i++){
				jpql.append('?').append(i+2).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("update WmsUser b set b.visible=?1 where b.username in("+ jpql.toString() +")");
			query.setParameter(1, visible);
			for(int i=0; i<usernames.length;i++){
				query.setParameter(i+2, usernames[i]);
			}
			query.executeUpdate();
		}
	}
	
	public void enable(Serializable ... usernames) {
		visible(true, usernames);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List getWmsUserInfo(Serializable ... usernames){
		List<WmsUser> buyerList=new ArrayList<WmsUser>();
		if(usernames!=null && usernames.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0; i<usernames.length;i++){
				jpql.append('?').append(i+1).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("select b from WmsUser b where b.username in("+ jpql.toString() +")");
			for(int i=0; i<usernames.length;i++){
				query.setParameter(i+1, usernames[i]);
			}
			buyerList=query.getResultList();
		}
		return 	buyerList;	
	}
	@SuppressWarnings("unchecked")
	public List<WmsUser> getAllWmsUser()
	{
		List<WmsUser> wmsUserList=new ArrayList<WmsUser>();
		Query query = em.createQuery("select b from WmsUser b ");
		wmsUserList=query.getResultList();
		return wmsUserList;
	}

	public WmsUser find(String phone) {
		Query query=em.createQuery("select w from WmsUser w where w.phone=?");
		return (WmsUser)query.setParameter(1, phone).getSingleResult();
	}
	
	/**
	 * 查询这个企业的所有负责人
	 * @param enterprise
	 * @return
	 */
	public List<WmsUser>  getEnterpriseToBoWmsUser(List<Enterprise> enterprise)
	{
		
		List<WmsUser>  wmsUserListVO=new ArrayList<WmsUser>();
		if(enterprise.size()==0)return null;
		for(Enterprise enter : enterprise){
			Enterprise en=em.find(Enterprise.class, enter.getEnterpriseId());
			for(WmsUser us :en.getUser()){
				wmsUserListVO.add(us);
			}
		}
		return wmsUserListVO;
	}

	public void assignRoles(String[] roleIds, WmsUser wmsUser) {
		try {
			em.createQuery("update WmsUser u set u.roleIds=?1 where u.userId =?2")
			.setParameter(1,mergeRoleIds(roleIds)).setParameter(2,wmsUser.getUserId()).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String mergeRoleIds(String[] roleIds){
		String ids = "";
		for (int i = 0; i < roleIds.length; i++) {
			ids+=roleIds[i]+",";
		}
		return ids;
	}

	public void assignRoles(String[] roleIds, String[] userIds) {
		String roleIdsStr = mergeRoleIds(roleIds);
		for (int i = 0; i < userIds.length; i++) {
			em.createQuery("update WmsUser u set u.roleIds=?1 where u.userId =?2").setParameter(1, roleIdsStr).setParameter(2, Integer.valueOf(userIds[i])).executeUpdate();
		}
	}

}
