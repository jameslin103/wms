package cn.fm.service.user;

import java.util.List;

import cn.fm.bean.user.User;


public interface UserService {
	
	public void saveUser(User user);

	public void updateUser(User user);

	public List<User> getUsers();

	public void delUsers(String... ids);

	public boolean isExist(String account);

	public User login(String name, String password);

	public User getById(String id);
}

