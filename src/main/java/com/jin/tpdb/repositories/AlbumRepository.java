package com.jin.tpdb.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.AlbumComment;
import com.jin.tpdb.entities.AlbumRating;
import com.jin.tpdb.entities.Song;

@Stateless
public class AlbumRepository {
	@PersistenceContext(unitName = "jin")
	private EntityManager em;
	private Session hbs;
	

	public void save(Album album) {
		em.persist(album);
	}

	public Album getAlbumById(int id) {
		return em.find(Album.class, id);
	}
	
	public Album getFullAlbumById(int id) {
		Album album = getAlbumById(id);
		album.setAverageRating(getAverageRating(album.getId()));
		album.setSongs(getSongs(id));
		album.setComments(getComments(id));
		return album;
		
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
	public List<Album> getArtistAlbums(int id) {
		System.out.println("getting albums from artist" + id);
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.add(Restrictions.eq("artist.id", id));
		c.addOrder(Order.desc("uploadDate"));
		// c.setMaxResults(5);
		return (List<Album>) c.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Album> getFullArtistAlbums(int id) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.add(Restrictions.eq("artist.id", id));
		c.addOrder(Order.desc("uploadDate"));
		List<Album> albums = c.list();

		for(Album album : albums) {
			album.setAverageRating(getAverageRating(album.getId()));
			album.setTotalSongs(getTotalSongs(album.getId()));
		}
		return albums;
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAlbums(int limit) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.addOrder(Order.desc("uploadDate"));
		c.setMaxResults(limit);
		List<Album> albums =  c.list();
		for(Album a : albums) {
			a.getComments().size();
		}
		return albums;
	}

	@SuppressWarnings("unchecked")
	public List<Album> getFeaturedAlbums() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.setMaxResults(3);
		return (List<Album>) c.list();
	}
	
	@SuppressWarnings("unchecked")
	public Set<Song> getSongs(int id) {
		System.out.println("getting songs from album " + id);
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Song.class);
		c.add(Restrictions.eq("album.id", id));
		Set<Song> results = new HashSet<Song>();
		results.addAll(c.list());
		return results;
	}
	
	public int getTotalSongs(int id) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Song.class);
		c.add(Restrictions.eq("album.id", id));
		c.setProjection(Projections.countDistinct("id"));
		Long result = (Long) c.uniqueResult();
		if(result != null && result > 0) {
			return result.intValue();
		} else {
			return 0;
		}
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
	
	public int getTotalComments(int id) {
		System.out.println("######### getting total comments");
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(AlbumComment.class);
		c.add(Restrictions.eq("album.id", id));
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
	public Set<AlbumComment> getComments(int id) {
		System.out.println("######### getting comments");
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(AlbumComment.class);
		c.add(Restrictions.eq("album.id", id));
		Set<AlbumComment> results = new HashSet<AlbumComment>();
		results.addAll(c.list());
		return results;
	}
	
	public void remove(Album a) {
		Object deleting = em.find(Album.class, a.getId());
		em.remove(deleting);
	}

}
