package cn.fm.dao;


import cn.fm.bean.user.User;

public interface UserDAO extends DAO<User> {
		
		
		public User findUserByAccountAndPwd(String account, String password);
		
		
		
		
	}

	
	
	
	
