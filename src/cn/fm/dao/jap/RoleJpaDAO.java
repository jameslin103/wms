package cn.fm.dao.jap;

import org.springframework.stereotype.Repository;

import cn.fm.bean.permissions.WmsRole;
import cn.fm.dao.RoleDAO;


@Repository
public class RoleJpaDAO extends JpaDAO<WmsRole> implements RoleDAO {

}
