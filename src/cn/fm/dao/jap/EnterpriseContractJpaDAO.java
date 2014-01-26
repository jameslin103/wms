package cn.fm.dao.jap;

import org.springframework.stereotype.Repository;

import cn.fm.bean.company.EnterpriseContract;
import cn.fm.dao.EnterpriseContractDAO;

@Repository
public class EnterpriseContractJpaDAO extends JpaDAO<EnterpriseContract> implements EnterpriseContractDAO{

}
