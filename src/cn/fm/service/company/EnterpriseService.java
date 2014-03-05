package cn.fm.service.company;

import java.util.List;

import cn.fm.bean.PageView;
import cn.fm.bean.company.Enterprise;
import cn.fm.bean.company.EnterpriseContract;
import cn.fm.bean.user.User;
import cn.fm.service.base.DAO;


public interface EnterpriseService extends DAO<Enterprise> {

	
	/**
	 * 统计多少员工
	 * @param enterpriseId
	 * @return
	 */
	public long getCountEmployees(Integer enterpriseId);
	
	
	
	public PageView<Enterprise> getAllEnterprise(int maxresult, int page,User user,Enterprise enterprise);
	
	
	/**
	 * 查询这个企业的所有负责人
	 * @param enterprise
	 * @return
	 */
	public List<User>  getEnterpriseToBoWmsUser(List<Enterprise> enterprise);
	
	/**
	 * 更新企业
	 * @param enterprise
	 * @return
	 */
	public boolean updateEnterprise(Enterprise enterprise);
	
	public boolean updateEnterStatus(Enterprise enterprise);
	
	/**
	 * 修改企业联系人
	 * @param enterprise
	 * @return
	 */
	public boolean updateEnterpriseContact(Enterprise enterprise);
	
	
	/**
	 * 增加企业负责人
	 * @param enterprise
	 * @param user
	 */
	public void saveEnterpriseToBeResponsible(Integer enterpriseId,String userId);
	
	/**
	 * 解除企业负责人
	 * @return
	 */
	public void removeToEnterpriseHeadUser(Integer enterpriseId,String userId);
	
	public List<Enterprise> getUserToAllEnterprise(User user);

	public Long findByFullName(String fullName);
	
	/**
	 * 每个用户负责的企业
	 */
	public PageView<Enterprise> getUserToByEnterprise(int maxresult, int page,User user,Enterprise enterprise);



	public void deteleEnterprise(Integer enterpriseId);
	
	
	public EnterpriseContract   getEndContractDateDay(Integer id);
	
}
