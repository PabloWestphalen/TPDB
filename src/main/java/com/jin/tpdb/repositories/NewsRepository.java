package com.jin.tpdb.repositories;

import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jin.tpdb.entities.News;
import com.jin.tpdb.entities.NewsComment;
import com.jin.tpdb.entities.Tag;

@Singleton
@Named
public class NewsRepository {
	@PersistenceContext(unitName = "jin", type=PersistenceContextType.EXTENDED)
	
	private EntityManager em;	
	private Session hbs;
	
	private News news;
	
	public News getNewsById(int id) {
		news = em.find(News.class, id);
		return news;
	}
	
	@SuppressWarnings("unchecked")
	public List<News> getAllNews() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(News.class);
		c.addOrder(Order.desc("date"));
		c.setCacheable(true);
		System.out.println("########Getting first... ");
		c.list();
		System.out.println("########Getting second... ");
		c.list();
		System.out.println("########Returning... ");
		return (List<News>) c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<News> getLatestNews() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(News.class);
		c.addOrder(Order.desc("date"));
		c.setMaxResults(4);
		List<News> newsList = c.list();
		for(News n : newsList) {
			n.getComments().size();
		}
		return newsList;
	}
	
	public int getTotalComments(int id) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(NewsComment.class);
		c.add(Restrictions.eq("news.id", id));
		c.setProjection(Projections.countDistinct("id"));
		c.addOrder(Order.desc("date"));
		Long result = (Long) c.uniqueResult();
		if(result != null && result > 0) {
			return result.intValue();
		} else {
			return 0;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<NewsComment> getComments(int id) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(NewsComment.class);
		c.add(Restrictions.eq("news.id", id));
		return (List<NewsComment>) c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Tag> getTags(int id) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Tag.class);
		c.add(Restrictions.eq("news.id", id));
		//return (List<Tag>) c.list();
		return null;
	}
}
