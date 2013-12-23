package cn.fm.service.company;

import java.util.List;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.user.WmsUser;
import cn.fm.service.base.DAO;


public interface EnterpriseService extends DAO<Enterprise> {

	
	/**
	 * 统计多少员工
	 * @param enterpriseId
	 * @return
	 */
	public long getCountEmployees(Integer enterpriseId);
	
	
	
	public List<Enterprise> getAllEnterprise(WmsUser user);
	
	
	/**
	 * 查询这个企业的所有负责人
	 * @param enterprise
	 * @return
	 */
	public List<WmsUser>  getEnterpriseToBoWmsUser(List<Enterprise> enterprise);
	
	/**
	 * 更新企业
	 * @param enterprise
	 * @return
	 */
	public boolean updateEnterprise(Enterprise enterprise);
	
	/**
	 * 修改企业联系人
	 * @param enterprise
	 * @return
	 */
	public boolean updateEnterpriseContact(Enterprise enterprise);
	
	/**
	 * 查询所有企业
	 * @return
	 */
	public List<Enterprise>  getAllEnterprise();
	
	/**
	 * 增加企业负责人
	 * @param enterprise
	 * @param user
	 */
	public void saveEnterpriseToBeResponsible(Integer enterpriseId,Integer userId);
	
	
	
	/**
	 * 解除企业负责人
	 * @return
	 */
	public void removeToEnterpriseHeadUser(Integer enterpriseId,Integer userId);
	
	public List<Enterprise> getUserToAllEnterprise(WmsUser user);



	public Long findByFullName(String fullName);
}
