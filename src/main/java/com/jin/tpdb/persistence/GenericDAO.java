package com.jin.tpdb.persistence;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.jin.tpdb.entities.News;

@Singleton
public class GenericDAO {
	@PersistenceContext(unitName = "jin", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	private Session hbs;
	
	public void save(Object entity) {
		em.persist(entity);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T load(Class<?> entity, int i) {
		return (T) em.find(entity, i);
	}
	
	public void refresh(Class c, int id) {
		News o = em.find(c, id);
		o.getComments();
		//em.merge(o);
		em.refresh(o);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T load(Class<?> entity, String field, Object value) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		c.add(Restrictions.like(field, value));
		return (T) c.uniqueResult();
		
	}
	

}
