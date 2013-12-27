package cn.fm.dao.jap;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.fm.bean.user.User;
import cn.fm.dao.UserDAO;

@Repository
public class UserJpaDAO extends JpaDAO<User> implements UserDAO {
	@Override
	public User findUserByAccountAndPwd(String account, String password) {
		String jpql = "from User where account=? and password=?";
		List<User> users = findByJPQL(jpql, account, password);
		if (users != null && users.size() != 0) {
			return users.get(0);
		}
		return null;
	}

}
