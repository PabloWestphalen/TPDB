package com.jin.tpdb.persistence;

import java.io.IOException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

//@Named
//@RequestScoped
@Stateless
//@ApplicationScoped
public class BaseDAO {
	@Inject
	protected EntityManager em;
	
	public void open() {
		try {
			em.getTransaction().begin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}