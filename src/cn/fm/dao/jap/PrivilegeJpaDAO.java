package cn.fm.dao.jap;

import org.springframework.stereotype.Repository;

import cn.fm.bean.permissions.Privilege;
import cn.fm.dao.PrivilegeDAO;


@Repository
public class PrivilegeJpaDAO extends JpaDAO<Privilege> implements PrivilegeDAO {

}
