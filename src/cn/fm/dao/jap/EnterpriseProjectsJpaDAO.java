package cn.fm.dao.jap;

import org.springframework.stereotype.Repository;

import cn.fm.bean.company.EnterpriseProjects;
import cn.fm.dao.EnterpriseProjectsDAO;

@Repository
public class EnterpriseProjectsJpaDAO extends JpaDAO<EnterpriseProjects> implements EnterpriseProjectsDAO{

}
