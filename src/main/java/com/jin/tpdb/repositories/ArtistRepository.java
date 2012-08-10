package com.jin.tpdb.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.jin.tpdb.entities.Artist;
import com.jin.tpdb.entities.News;

@Stateless
public class ArtistRepository {
	@PersistenceContext(unitName = "jin", type=PersistenceContextType.EXTENDED)
	private EntityManager em;	
	private Session hbs;
	
	public void save(Artist artist) {
		em.merge(artist);
	}
	
	public News getNewsById(int id) {
		return em.find(News.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Artist> getAllArtists() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Artist.class);
		c.addOrder(Order.asc("name"));
		return (List<Artist>) c.list();
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
