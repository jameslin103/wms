package cn.fm.service.user.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.user.Buyer;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.user.BuyerService;



@Service @Transactional
public class BuyerServiceImpl extends DaoSupport<Buyer> implements BuyerService {

	public void updatePassword(String username, String newpassword){
		em.createQuery("update Buyer o set o.password=?1 where o.username=?2")
		.setParameter(1, newpassword).setParameter(2, username).executeUpdate();
	}

	public void save(Buyer entity) {
		//entity.setPassword(MD5.MD5Encode(entity.getPassword()));
		super.save(entity);
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean exsit(String username){
		long count = (Long)em.createQuery("select count(o) from Buyer o where o.username=?1").setParameter(1, username)
			.getSingleResult();
		return count>0;
	}
	
	public boolean checkUser(String username, String password){
		long count = (Long)em.createQuery("select count(o) from Buyer o where o.username=?1 and o.password=?2")
		.setParameter(1, username).setParameter(2,password).getSingleResult();
		return count>0;
	}
	

	public void delete(Serializable ... entityIds){
		visible(false, entityIds);
	}


	public long getCount() {
		return (Long)em.createQuery("select count(o) from Buyer o where o.visible=?1")
		.setParameter(1, true).getSingleResult();
	}

	private void visible(boolean visible, Serializable ... usernames){
		if(usernames!=null && usernames.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0; i<usernames.length;i++){
				jpql.append('?').append(i+2).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("update Buyer b set b.visible=?1 where b.username in("+ jpql.toString() +")");
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
	public List getBuyerInfo(Serializable ... usernames){
		List<Buyer> buyerList=new ArrayList<Buyer>();
		if(usernames!=null && usernames.length>0){
			StringBuffer jpql = new StringBuffer();
			for(int i=0; i<usernames.length;i++){
				jpql.append('?').append(i+1).append(',');
			}
			jpql.deleteCharAt(jpql.length()-1);
			Query query = em.createQuery("select b from Buyer b where b.username in("+ jpql.toString() +")");
			for(int i=0; i<usernames.length;i++){
				query.setParameter(i+1, usernames[i]);
			}
			buyerList=query.getResultList();
		}
		return 	buyerList;	
	}
	public List getAllByuer()
	{
		List<Buyer> buyerList=new ArrayList<Buyer>();
		Query query = em.createQuery("select b from Buyer b ");
		buyerList=query.getResultList();
		return buyerList;
	}

}
