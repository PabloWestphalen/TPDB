package com.jin.tpdb.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.jin.tpdb.entities.News;

@Stateless
public class NewsRepository {
	@PersistenceContext(unitName = "jin")
	private EntityManager em;	
	private Session hbs;
	
	public News getNewsById(int id) {
		return em.find(News.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<News> getAllNews() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(News.class);
		c.addOrder(Order.desc("date"));
		return (List<News>) c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<News> getLatestNews() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(News.class);
		c.addOrder(Order.desc("date"));
		c.setMaxResults(4);
		return (List<News>) c.list();
	}
}
