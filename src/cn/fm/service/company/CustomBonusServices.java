package cn.fm.service.company;

import java.util.List;

import cn.fm.bean.company.CustomBonus;
import cn.fm.service.base.DAO;

public interface CustomBonusServices extends DAO<CustomBonus> {
	public void save(CustomBonus customBonus);
	
	public void update(CustomBonus customBonus,Integer id);
	
	public List<CustomBonus> getAllCustomBonus();
}
