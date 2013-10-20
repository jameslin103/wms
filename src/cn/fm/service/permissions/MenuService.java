package cn.fm.service.permissions;

import java.util.List;

import cn.fm.bean.permissions.Menu;
import cn.fm.service.base.DAO;

public interface MenuService extends DAO<Menu>{
	public List<Menu> getAll();
	public List<Menu> getByIds(String ids);
}
