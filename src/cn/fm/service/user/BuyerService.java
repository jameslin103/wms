package cn.fm.service.user;

import java.io.Serializable;
import java.util.List;
import cn.fm.bean.user.Buyer;
import cn.fm.service.base.DAO;


public interface BuyerService extends DAO<Buyer>{

	
	public void enable(Serializable ... usernames);

	public boolean exsit(String username);
	
	public boolean checkUser(String username, String password);
	

	public void updatePassword(String username, String newpassword);
	
	
	public List getBuyerInfo(Serializable ... usernames);

	
	public List getAllByuer();
	
}