package com.jin.tpdb.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;

@Stateless
@Named
public class GenericRepository {
	@PersistenceContext(unitName = "jin")
	private EntityManager em;
	private Session hbs;

	public void load(Class<?> c, int id) {
		em.find(c, id);
	}

	public void persist(Object entity) {
		em.persist(entity);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> list(Class<?> entity) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		return (List<T>) c.list();
	}

	/*@SuppressWarnings("unchecked")
	@Named
	public List<Person> getPersons() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Person.class);
		return (List<Person>) c.list();
	}*/
	
	/*public Pet getPet(int petId) {
		return em.find(Pet.class, petId);
	}*/
	



	

}
