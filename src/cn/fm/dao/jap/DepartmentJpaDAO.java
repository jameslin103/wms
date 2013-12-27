package cn.fm.dao.jap;

import org.springframework.stereotype.Repository;

import cn.fm.bean.user.Department;
import cn.fm.dao.DepartmentDAO;


@Repository
public class DepartmentJpaDAO extends JpaDAO<Department> implements DepartmentDAO {

}
