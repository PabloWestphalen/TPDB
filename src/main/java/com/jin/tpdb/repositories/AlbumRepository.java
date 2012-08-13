package com.jin.tpdb.repositories;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
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
import com.jin.tpdb.entities.Artist;

@Singleton
public class AlbumRepository {
	@PersistenceContext(unitName = "jin", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	private Session hbs;
	
	@EJB
	private ArtistRepository artistRepo;
	
	private Album album;
	private List<Album> albums;

	
	public void save(Album album, int id) {
		Artist artist = em.find(Artist.class, id);
		album.setArtist(artist);
		em.persist(album);
	}	

	public Album findById(int id) {
		album = em.find(Album.class, id);
		return album;
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAlbums() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.addOrder(Order.desc("uploadDate"));
		albums = c.list();
		return albums;
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAlbums(int limit) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.addOrder(Order.desc("uploadDate"));
		c.setMaxResults(limit);
		c.setCacheable(true);
		albums =  c.list();
		return albums;
	}

	@SuppressWarnings("unchecked")
	public List<Album> getFeaturedAlbums() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.setMaxResults(3);
		c.setCacheable(true);
		albums = c.list();
		return albums;
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
	
	public void remove(Album a) {
		Object deleting = em.find(Album.class, a.getId());
		em.remove(deleting);
	}

	public void setRating(int albumId, AlbumRating rating) {
		Album album = findById(albumId);
		rating.setAlbum(album);
		em.persist(rating);
		album.setAverageRating(getAverageRating(albumId));
		em.merge(album);
	}

}
