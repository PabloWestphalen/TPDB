package com.jin.tpdb.repositories;

import java.util.List;

import javax.ejb.Singleton;
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

@Singleton
public class NewsRepository {
	//@PersistenceContext(unitName = "jin")
	@PersistenceContext(unitName = "jin", type=PersistenceContextType.EXTENDED)
	
	
	private EntityManager em;	
	private Session hbs;
	
	public News getNewsById(int id) {
		/*hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(News.class);
		c.add(Restrictions.eq("id", id));
		c.setCacheable(true);
		News news = (News)c.uniqueResult();
		news.getComments().size();
		news.getTags().size();
		news.setComments(news.getComments());
		news.setTags(news.getTags());
		return news;*/
		News n = em.find(News.class, id);
		return n; 
	}
	
	@SuppressWarnings("unchecked")
	public List<News> getAllNews() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(News.class);
		c.addOrder(Order.desc("date"));
		c.setCacheable(true);
		System.out.println();
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

	public void addComment(NewsComment c, int id) {
		News n = getNewsById(id);
		c.setNews(n);
		em.persist(c);
		em.refresh(n);
	}
	

}
