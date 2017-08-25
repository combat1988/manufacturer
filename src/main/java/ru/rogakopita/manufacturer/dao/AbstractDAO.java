package ru.rogakopita.manufacturer.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import ru.rogakopita.manufacturer.domain.ifc.DTOIfc;

@Repository
@Transactional
public abstract class AbstractDAO<T> {

	protected Class<?> entityClass;

	@Autowired
	private SessionFactory sessionFactory;

	public Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected AbstractDAO(Class<T> entityClass) {
		this.setEntityClass(entityClass);
	}

	public AbstractDAO() {
	}

	public Class<?> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	public T get(long id) throws HibernateException, DataAccessException {
		T entity = null;
		entity = (T) currentSession().get(entityClass, id);
		return entity;

	}

	abstract public DTOIfc getByDTO(T data);

	@SuppressWarnings("unchecked")
	public List<T> list()  {
			return (List<T>) currentSession().createCriteria(entityClass).list();

	}
	
	@SuppressWarnings("unchecked")
	public List<T> listOrderedBy(String propertyName)  {
			return (List<T>) currentSession().createCriteria(entityClass).addOrder(Order.asc(propertyName)).list();

	}

	public List<T> list(int start, int limit) {
		return this.list(start, limit, Order.asc("id"));
	}

	@SuppressWarnings("unchecked")
	public List<T> list(int start, int limit, Order order) {
			Criteria criteria = currentSession().createCriteria(entityClass);
			if (start != 0) {
				criteria.setFirstResult(start);
			}
			if (limit != 0) {
				criteria.setMaxResults(limit);
			}
			if (order != null) {
				criteria.addOrder(order);
			}

			return (List<T>) criteria.list();
	}

	public void delete(long id) {
		
			delete(get(id));
			currentSession().flush();
	}

	public void delete(T entity) {
			
			currentSession().delete(entity);
			currentSession().flush();
		
	}

	public T update(T entity) {
			currentSession().update(entity);
			currentSession().flush();
			return entity;
		
	}

	@SuppressWarnings("unchecked")
	public T merge(T entity) {
		return (T) currentSession().merge(entity);
	}

	public T save(T entity) {
			currentSession().save(entity);
			currentSession().flush();
			return entity;
		
	}
}
