package cn.fm.dao;

import java.util.List;

public interface DAO<E> {
	/**
	 * 保存实体
	 * 
	 * @param e
	 */
	public void save(E e);

	/**
	 * 更新实体
	 * 
	 * @param e
	 */
	public void update(E e);

	/**
	 * 删除实体
	 */
	public void delete(E e);

	/**
	 * 根据主键查询实体
	 * 
	 * @param id
	 * @return
	 */
	public E findById(String id);
	
	public E findById(Integer id);

	/**
	 * 查询所有的数据
	 * 
	 * @return
	 */
	public List<E> findAll();

	public boolean isExist(String prop, String value);
}
