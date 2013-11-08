package cn.fm.service.company;

import java.util.List;

import cn.fm.bean.company.CustomBonus;
import cn.fm.service.base.DAO;

public interface CustomBonusServices extends DAO<CustomBonus> {
	public void save(CustomBonus customBonus);
	
	public void update(CustomBonus customBonus,Integer id);
	
	public List<CustomBonus> getAllCustomBonus(Integer enterpriseId);

	public void updateCustomBonus(CustomBonus customBonus);
	/**
	 * 获取启用的奖金
	 * @return
	 */
	public List<CustomBonus> getStatusEnableCustomBonus(Integer enterpriseId);
}
