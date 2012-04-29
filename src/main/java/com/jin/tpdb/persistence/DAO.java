package com.jin.tpdb.persistence;

import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.*;

public class DAO {
	protected EntityManagerFactory factory;
	protected static EntityManager em;
	
	public DAO() {
		if(DAO.getManager() == null) {
		factory = Persistence.createEntityManagerFactory("jin");
		em = factory.createEntityManager();
		DAO.setManager(em);
		} else {
			em = DAO.getManager();
		}
	}
	
	protected static void setManager(EntityManager manager){
        em = manager;
    }
	
	protected static EntityManager getManager() {
		return em;
	}
	
	public void open() {
		try {
			em.getTransaction().begin();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			em.getTransaction().commit();
			em.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(Object o) {
		em.persist(o);
	}
	
	public void rollback() {
		em.getTransaction().rollback();
	}
	
	public static <T> T load(Class c, int i) {
		DAO dao = new DAO();
		dao.open();
		return em.find(c, i);
		dao.close();
	}
	
	public static <T> List<T> getList(Class entity) {		
		DAO dao = new DAO();
		dao.open();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(entity); 

		TypedQuery<T> typedQuery = em.createQuery(
			query.select(
				query.from(entity)
			)
		);
				
		dao.close();
		return typedQuery.getResultList();
	}	
}

