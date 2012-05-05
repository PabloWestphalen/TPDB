package com.jin.tpdb.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.Song;

public class DAO {
	protected static EntityManagerFactory factory;
	protected EntityManager em;

	public DAO() {
		if (DAO.getManagerFactory() == null) {
			factory = Persistence.createEntityManagerFactory("jin");
			DAO.setManagerFactory(factory);
		} else {
			factory = DAO.getManagerFactory();
		}
	}

	protected static void setManagerFactory(EntityManagerFactory f) {
		factory = f;
	}

	protected static EntityManagerFactory getManagerFactory() {
		return factory;
	}

	public void open() {
		try {
			em = factory.createEntityManager();
			em.getTransaction().begin();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			em.getTransaction().commit();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void save(Object o) {
		em.persist(o);
	}

	public void rollback() {
		em.getTransaction().rollback();
	}

	public <T> T get(Class c, int i) {
		return (T) em.find(c, i);
	}

	protected <T> List<T> list(Class entity) {
		/*
		 * CriteriaBuilder cb = em.getCriteriaBuilder(); CriteriaQuery<T> query
		 * = cb.createQuery(entity);
		 * 
		 * TypedQuery<T> typedQuery = em.createQuery(query.select(query
		 * .from(entity)));
		 * 
		 * return typedQuery.getResultList();
		 */
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		List<T> results = c.list();
		return results;

	}

	protected <T> List<T> listSongs(Class entity, int id) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		c.add(Restrictions.eq("album.id", id));
		List<T> results = c.list();
		return results;
	}

	protected Long getAlbumTotalComments(int id) {

		/*
		 * $albums_query = "SELECT users.username, albums.album_id,
		 * albums.uploader_user_id, albums.album_name, albums.upload_date,
		 * albums.cover, albums.description, artists.artist_name, (SELECT COUNT(
		 * comment_id ) FROM comments WHERE comments.album_id = albums.album_id)
		 * AS total_comments FROM albums, artists, users WHERE artists.artist_id
		 * = albums.artist_id AND users.user_id
		 */
		String qs = "SELECT COUNT(id) FROM AlbumComment WHERE album.id = " + id;
		TypedQuery<Long> query = em.createQuery(qs, Long.class);
		return query.getSingleResult();
		/*
		 * Root<AlbumComment> root = cq.from(AlbumComment.class);
		 * cq.select(qb.count(root)); Predicate predicate =
		 * qb.equal(root.get("album_id"), id); cq.where(predicate); return
		 * em.createQuery(cq).getSingleResult();
		 */
	}

	protected List<Album> listAlbums(int id) {

		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(Album.class);
		c.add(Restrictions.eq("artist.id", id));
		List<Album> albums = c.list();
		return albums;
	}

	public static List<Album> getAlbums(int id) {
		DAO dao = new DAO();
		dao.open();
		List<Album> albums = dao.listAlbums(id);
		dao.close();
		return albums;
	}

	public static <T> T load(Class c, int i) {
		DAO dao = new DAO();
		dao.open();
		T result = dao.get(c, i);
		dao.close();
		return result;
	}

	public static <T> List<T> getList(Class c) {
		DAO dao = new DAO();
		dao.open();
		List<T> results = dao.list(c);
		dao.close();
		return results;
	}

	public static Long countAlbumComments(int id) {
		DAO dao = new DAO();
		dao.open();
		Long count = dao.getAlbumTotalComments(id);
		dao.close();
		return count;
	}

	public static <T> List<T> getSongs(int id) {
		DAO dao = new DAO();
		dao.open();
		List<T> songsList = dao.listSongs(Song.class, id);
		dao.close();
		return songsList;
	}

}
