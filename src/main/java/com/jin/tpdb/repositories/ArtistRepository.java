package com.jin.tpdb.repositories;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.Artist;

@Stateless
public class ArtistRepository {
	@PersistenceContext(unitName = "jin")
	private EntityManager em;
	private Session hbs;

	@EJB
	private AlbumRepository albumRepo;
	
	private Long before, after;

	public void save(Artist artist) {
		em.merge(artist);
	}

	@SuppressWarnings("unchecked")
	public List<Artist> getAllArtists() {
		before = System.currentTimeMillis();
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Artist.class);
		c.addOrder(Order.asc("name"));
		after = System.currentTimeMillis();
		System.out.println("Getting all artists. " + "Time: "+ (after - before) + "ms");
		return (List<Artist>) c.list();
		
		
	}

	public List<Artist> getFullArtistsListing() {
		List<Artist> artists = getAllArtists();
		
		for(Artist a : artists) {
			a.getAlbums();
			for(Album ab : a.getAlbums()) {
				ab.getSongs().size();
			}
		}
		return artists;
	}

}
