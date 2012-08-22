package com.jin.tpdb.repositories;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.jin.tpdb.entities.News;
import com.jin.tpdb.entities.NewsComment;
import com.jin.tpdb.entities.Tag;
import com.jin.tpdb.entities.User;

@Singleton
public class NewsRepository {
	@PersistenceContext(unitName = "jin", type=PersistenceContextType.EXTENDED)
	private EntityManager em;	
	private Session hbs;
	
	public void addComment(NewsComment c, int id) {
		News n = findById(id);
		c.setNews(n);
		em.persist(c);
		em.refresh(n);
	}
	
	public void addTag(Tag tag) {
		em.persist(tag);
	}
	
	public News findById(int id) {
		return em.find(News.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<News> getAllNews() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(News.class);
		c.addOrder(Order.desc("date"));
		c.setCacheable(true);
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<News> getLatestNews() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(News.class);
		c.addOrder(Order.desc("date"));
		c.setMaxResults(4);
		c.setCacheable(true);
		List<News> newsList = c.list();
		return newsList;
	}
	
	public Tag getTagByName(String name) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Tag.class);
		c.add(Restrictions.eq("name", name));
		c.setCacheable(true);
		return (Tag) c.uniqueResult();
	}
	
	public User getUser() {
		return em.find(User.class, 1);
	}

	public void save(News news) {
		em.persist(news);
	}
}
