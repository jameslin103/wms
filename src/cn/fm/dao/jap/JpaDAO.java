package cn.fm.dao.jap;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import cn.fm.dao.DAO;


@SuppressWarnings("unchecked")
public class JpaDAO<E> implements DAO<E> {
	@PersistenceContext
	private EntityManager em;
	private Class<E> entityClass;

	
	public JpaDAO() {
		ParameterizedType pt = (ParameterizedType) getClass()
				.getGenericSuperclass();
		entityClass = (Class<E>) pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(E e) {
		em.persist(e);
	}

	@Override
	public void update(E e) {
		em.merge(e);
	}

	@Override
	public void delete(E e) {
		em.remove(e);
	}

	@Override
	public E findById(String id) {
		return em.find(entityClass, id);
	}

	@Override
	public List<E> findAll() {
		return em.createQuery("from " + entityClass.getSimpleName(),
				entityClass).getResultList();
	}

	protected List<E> findByJPQL(String jpql, Object... params) {
		TypedQuery<E> query = em.createQuery(jpql, entityClass);
		if (params.length != 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		return query.getResultList();
	}

	protected List<E> findByJPQLAndPage(int page, int pageSize, String jpql,
			Object... params) {
		TypedQuery<E> query = em.createQuery(jpql, entityClass);
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);
		if (params.length != 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		return query.getResultList();
	}

	protected int findTotalNums(String jpql) {
		return em.createQuery(jpql, Long.class).getSingleResult().intValue();
	}

	@Override
	public boolean isExist(String prop, String value) {
		String jpql = "select count(*) from " + entityClass.getSimpleName()
				+ " where " + prop + "='" + value + "'";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		return query.getSingleResult() >= 1;
	}
	
	protected void executeUpdate(String jpql,Object...params) {
		Query query = em.createQuery(jpql);
		if (params.length != 0) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i + 1, params[i]);
			}
		}
		query.executeUpdate();
	}
	
	public List getStatResult(String jpql){
		Query query=em.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public E findById(Integer id) {
		return em.find(entityClass, id);
	}
}
