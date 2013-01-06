package com.jin.tpdb.repositories;

import java.util.ArrayList;
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

import com.jin.YouTubeUtil;
import com.jin.YouTubeVideo;
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
	@EJB
	private ArtistRepository artistRepo;
	
	public static final int NEXT_ALBUM = 0;
	public static final int PREVIOUS_ALBUM = 1;
	
	public void addComment(AlbumComment c, int albumId) {
		Album album = findById(albumId);
		c.setAlbum(album);
		em.persist(c);
		em.refresh( album);
	}

	public Album findById(int id) {
		return em.find(Album.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Album> getAlbums() {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.addOrder(Order.desc("uploadDate"));
		c.setCacheable(true);
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
	
	public Album getSibling(Album a, int position) {
		Criteria c = hbs.createCriteria(Album.class);
		c.add(Restrictions.ne("id", a.getId()));
		c.add(Restrictions.eq("artist", a.getArtist()));
		if(position == PREVIOUS_ALBUM) {
			c.add(Restrictions.le("releaseDate", a.getReleaseDate()));
			c.addOrder(Order.desc("releaseDate"));
		} else if(position == NEXT_ALBUM) {
			c.add(Restrictions.ge("releaseDate", a.getReleaseDate()));
			c.addOrder(Order.asc("releaseDate"));
		}
		c.setMaxResults(1);
		c.setCacheable(true);
		return (Album) c.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Album> getRelatedAlbums(Album a) {
		List<Album> results = new ArrayList<Album>();
		hbs = (Session) em.getDelegate();
		//get previous album
		Album previous = getSibling(a, PREVIOUS_ALBUM);
		int[] selected = new int[3];
		selected[0] = a.getId();
		if(previous != null) {
			results.add(previous);
			selected[1] = previous.getId();
		} else {
			Album random = getRandomAlbums(1, selected).get(0);
			selected[1] = random.getId();
			results.add(random);
		}
		//get next album
		Album next = getSibling(a, NEXT_ALBUM);
		if(next != null) {
			selected[2] = next.getId();
			results.add(next);
		} else {
			Album random = getRandomAlbums(1, selected).get(0);
			selected[2] = random.getId();
			results.add(random);
		}
		Album random = getRandomAlbums(1, selected).get(0);
		results.add(random);
		return results;
	}
	
	@SuppressWarnings("unchecked")
	public List<Album> getRandomAlbums(int limit, int[] excludes) {
		hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		for(int id : excludes) {
			c.add(Restrictions.ne("id", id));
		}
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
		song.setYoutubeUrl(getYoutubeUrl(song));
		em.persist(song);		
	}
	
	public void updateSongs() {
		List<Artist> artists = artistRepo.getAllArtists();
		for(Artist a : artists) {
			List<Album> albums = artistRepo.getArtistAlbums(a.getId());
			for(Album ab : albums) {
				for(Song s: ab.getSongs()) {
					s.setYoutubeUrl(getYoutubeUrl(s));
					em.merge(s);
				}
				em.refresh(ab);
			}
			em.refresh(a);
		}
	}
	
	public void merge(Album album) {
		em.merge(album);
	}
	public void refresh(int albumId) {
		em.refresh(em.find(Album.class, albumId));
	}
	
	
	public String getYoutubeUrl(Song song)  {
		YouTubeUtil u = new YouTubeUtil();
		System.out.println("#######################");
		String artistName = song.getAlbum().getArtist().getName().toLowerCase();
		String result = null;
		String query = song.getAlbum().getArtist().getName() + " - " + song.getName();
		System.out.println("Query = " + query);
		System.out.println("Loading videos...");
		List<YouTubeVideo> videos = u.search(query, 15);
		System.out.println("Found [" + videos.size() + "] videos.");
		for (YouTubeVideo video : videos) {
			String name = video.getName().toLowerCase();
			if(name.contains(artistName) && name.contains(song.getName().toLowerCase())) {
				System.out.println("This one matches: " + name);
				result = video.getId();
				break;
			} else {
				System.out.println("Rejecting this video:" + name);
			}
		}
		System.out.println("Returning result as: " + result);
		System.out.println("#######################");
		return result;
	}


}
