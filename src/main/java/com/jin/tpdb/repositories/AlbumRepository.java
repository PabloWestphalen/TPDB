package com.jin.tpdb.repositories;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.AlbumRating;

@Stateless
@Named
public class AlbumRepository {
	@PersistenceContext(unitName = "jin", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	private Session hbs;

	public void save(Album album) {
		em.persist(album);
	}

	public Album getAlbumById(int id) {
		return em.find(Album.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAlbums() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.addOrder(Order.desc("uploadDate"));
		// c.setMaxResults(5);
		return (List<Album>) c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAlbums(int limit) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.addOrder(Order.desc("uploadDate"));
		c.setMaxResults(limit);
		return (List<Album>) c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Album> getFeaturedAlbums() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.setMaxResults(3);
		return (List<Album>) c.list();
	}

	public int getAverageRating(int id) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(AlbumRating.class);
		c.add(Restrictions.eq("album.id", id));
		c.setProjection(Projections.avg("rating"));
		Double result = (Double) c.uniqueResult();
		if (result != null && result > 0) {
			return result.intValue();
		} else {
			return 0;
		}
	}

}
