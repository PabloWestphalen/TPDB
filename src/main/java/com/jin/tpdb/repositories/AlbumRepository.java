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

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.AlbumComment;
import com.jin.tpdb.entities.AlbumRating;
import com.jin.tpdb.entities.Artist;
import com.jin.tpdb.entities.Song;

@Singleton
public class AlbumRepository {
	@PersistenceContext(unitName = "jin", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	private Session hbs;

	public void addComment(AlbumComment c, int albumId) {
		Album album = findById(albumId);
		c.setAlbum(album);
		em.persist(c);
		em.refresh(album);
	}

	public Album findById(int id) {
		return em.find(Album.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAlbums() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.addOrder(Order.desc("uploadDate"));
		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAlbums(int limit) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.addOrder(Order.desc("uploadDate"));
		c.setMaxResults(limit);
		c.setCacheable(true);
		return c.list();
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

	@SuppressWarnings("unchecked")
	public List<Album> getFeaturedAlbums() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.setMaxResults(3);
		c.setCacheable(true);
		return c.list();
	}

	@SuppressWarnings("unchecked")
	public List<Album> getRelatedAlbums(Album a) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.add(Restrictions.ne("id", a.getId()));
		c.add(Restrictions.eq("artist", a.getArtist()));
		c.addOrder(Order.asc("releaseDate"));
		c.setMaxResults(3);
		c.setCacheable(true);
		return c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Album> getRandomAlbums(int limit, int albumId) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.add(Restrictions.ne("id", albumId));
		c.add(Restrictions.sqlRestriction("1=1 order by rand()"));
		c.setMaxResults(limit);
		return c.list();		
	}

	public void remove(Album a) {
		Album deleting = em.find(Album.class, a.getId());
		em.remove(deleting);
	}

	public void save(Album album, int id) {
		Artist artist = em.find(Artist.class, id);
		album.setArtist(artist);
		em.persist(album);
	}

	public void setRating(int albumId, AlbumRating rating) {
		Album album = findById(albumId);
		rating.setAlbum(album);
		em.persist(rating);
		album.setAverageRating(getAverageRating(albumId));
		em.merge(album);
	}

	public void addSong(Song song) {
		em.persist(song);
	}
	
	public void refresh(int albumId) {
		em.refresh(em.find(Album.class, albumId));
	}

}
