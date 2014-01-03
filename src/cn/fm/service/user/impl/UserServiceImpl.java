package cn.fm.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.fm.bean.user.User;
import cn.fm.dao.UserDAO;
import cn.fm.service.user.UserService;



@Service @Transactional
public class UserServiceImpl  implements UserService{

	@Resource
	private UserDAO userDAO;
	
	public void saveUser(User user) {
		if(user==null)return;
		user.setStatus(User.NORMAL);
		if(StringUtils.isBlank(user.getEmployee().getId())){
			user.setEmployee(null);
		}
		
		userDAO.save(user);
		
	}
	public List<User> getUsers() {
		return userDAO.findAll();
	}
	
	public void delUsers(String... ids){
		for (String id : ids) {
			userDAO.delete(userDAO.findById(id));
		}
	}
	
	public boolean isExist(String account){
		return userDAO.isExist("account", account);
	}

	@Override
	public User login(String account, String password) {
		return userDAO.findUserByAccountAndPwd(account, password);
	}

	@Override
	public User getById(String id) {
		return userDAO.findById(id);
	}

	@Override
	public void updateUser(User user) {
		
		User userPO=userDAO.findById(user.getId());
		userPO.setAccount(user.getAccount());
		userPO.setPassword(user.getPassword());
		userPO.setEmployee(user.getEmployee());
		userDAO.update(user);
		
	}

}
