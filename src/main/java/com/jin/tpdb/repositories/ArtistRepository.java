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
import org.hibernate.criterion.Restrictions;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.Artist;

@Singleton
public class ArtistRepository {
	@PersistenceContext(unitName = "jin", type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	private Session hbs;

	public void save(Artist artist) {
		em.merge(artist);
	}

	public Artist findById(int id) {
		Artist artist = em.find(Artist.class, id);
		return artist;
	}

	@SuppressWarnings("unchecked")
	public List<Artist> getAllArtists() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Artist.class);
		c.addOrder(Order.asc("name"));
		c.setCacheable(true);
		return (List<Artist>) c.list();
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

	public List<Artist> getFullArtistsListing() {
		List<Artist> artists = getAllArtists();

		for (Artist a : artists) {
			a.getAlbums();
			for (Album ab : a.getAlbums()) {
				ab.getSongs().size();
			}
		}
		return artists;
	}

	public void refresh(int artistId) {
		em.refresh(em.find(Artist.class, artistId));
		//em.flush();

	}

}
