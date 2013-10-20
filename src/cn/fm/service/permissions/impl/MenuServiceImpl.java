package cn.fm.service.permissions.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fm.bean.permissions.Menu;
import cn.fm.service.base.DaoSupport;
import cn.fm.service.permissions.MenuService;

@Service
@Transactional
public class MenuServiceImpl extends DaoSupport<Menu> implements MenuService{

	@Override
	public List<Menu> getAll() {
		List<Menu> list = em.createQuery("from Menu").getResultList();
		return list;
	}

	@Override
	public List<Menu> getByIds(String menuIds) {
		List<Menu> menuList = new ArrayList<Menu>();
		String[] ids = menuIds.split(",");
		List<Long> idsL = new ArrayList<Long>(); 
		for (int i = 0; i < ids.length; i++) {
			if(!idsL.contains(Long.valueOf(ids[i]))){
				idsL.add(Long.valueOf(ids[i]));
			}
		}
		for (Iterator<Long> iterator = idsL.iterator(); iterator.hasNext();) {
			Long id = iterator.next();
			menuList.add(find(id));
		}
		return menuList;
	}
	
}
