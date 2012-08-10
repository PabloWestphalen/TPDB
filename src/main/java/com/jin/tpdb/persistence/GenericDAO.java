package com.jin.tpdb.persistence;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

@Stateless
public class GenericDAO {
	@PersistenceContext(unitName = "jin")
	private EntityManager em;
	private Session hbs;
	
	public void save(Object entity) {
		em.persist(entity);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T load(Class<?> entity, int i) {
		return (T) em.find(entity, i);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T load(Class<?> entity, String field, Object value) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		c.add(Restrictions.like(field, value));
		return (T) c.uniqueResult();
		
	}
	

}
