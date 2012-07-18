package com.jin.tpdb.persistence;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.Song;

@SuppressWarnings("unchecked")
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
	
	public double avg(Class entity, String field, Criterion... restrictions) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		if (restrictions != null) {
			for (Criterion restriction : restrictions) {
				c.add(restriction);
			}
		}
		c.setProjection(Projections.avg(field));
		if(c.uniqueResult() != null) {
			double uniqueResult = (Double) c.uniqueResult();
			return uniqueResult;
		} else {
			return 0;
		}
		
	}

	public <T> T get(Class entity, int i, Criterion... restrictions) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		if (restrictions != null) {
			for (Criterion restriction : restrictions) {
				c.add(restriction);
			}
		}
		T uniqueResult = (T) c.uniqueResult();
		return uniqueResult;
	}

	public <T> T get(Class entity, int i, FetchMode f, String joinField) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity).add(Restrictions.eq("id", i))
				.setFetchMode(joinField, f);
		T result = (T) c.uniqueResult();
		return result;
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
	
	protected <T> List<T> list(Class entity, Order order) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		c.addOrder(order);
		List<T> results = c.list();
		return results;
	}

	protected <T> List<T> list(Class entity, Criterion... restrictions) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		if (restrictions != null) {
			for (Criterion restriction : restrictions) {
				c.add(restriction);
			}
		}
		List<T> results = c.list();
		return results;
	}

	public <T> List<T> list(Class entity, Order order,
			Criterion... restrictions) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		c.addOrder(order);
		if (restrictions != null) {
			for (Criterion restriction : restrictions) {
				c.add(restriction);
			}
		}
		List<T> results = c.list();
		return results;
	}

	protected <T> Collection<T> list(Class entity, Order order, FetchMode f,
			String fieldName, Criterion... restrictions) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		c.addOrder(order);
		c.setFetchMode(fieldName, f);
		if (restrictions != null) {
			for (Criterion restriction : restrictions) {
				c.add(restriction);
			}
		}
		Collection<T> results = c.list();
		return results;
	}

	protected <T> List<T> list(Class entity, FetchMode f, String joinField) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity).setFetchMode(joinField, f);
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
		for(Album a : albums) {
			Hibernate.initialize(a.getSongs());
		}
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
	
	public static double getAverage(Class c, String field, Criterion... restrictions) {
		DAO dao = new DAO();
		dao.open();
		double result = dao.avg(c, field, restrictions);
		dao.close();
		return result;
	}

	public static <T> T load(Class c, int i, FetchMode f, String joinField) {
		DAO dao = new DAO();
		dao.open();
		T result = dao.get(c, i, f, joinField);
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
	
	public static <T> List<T> getList(Class entity, Order order) {
		DAO dao = new DAO();
		dao.open();
		List<T> results = dao.list(entity, order);
		dao.close();
		return results;
	}

	public static <T> List<T> getList(Class entity, Criterion... restrictions) {
		DAO dao = new DAO();
		dao.open();
		List<T> results = dao.list(entity, restrictions);
		dao.close();
		return results;
	}

	public static <T> List<T> getList(Class entity, Order order,
			Criterion... restrictions) {
		DAO dao = new DAO();
		dao.open();
		List<T> results = dao.list(entity, order, restrictions);
		dao.close();
		return results;
	}

	public static <T> Collection<T> getList(Class entity, Order order,
			FetchMode f, String fieldName, Criterion... restrictions) {
		DAO dao = new DAO();
		dao.open();
		Collection<T> results = dao.list(entity, order, f, fieldName,
				restrictions);
		dao.close();
		return results;
	}

	public static <T> List<T> getList(Class entity, FetchMode f,
			String joinField) {
		DAO dao = new DAO();
		dao.open();
		List<T> results = dao.list(entity, f, joinField);
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

	protected <T> T getUniqueResult(Class entity, Criterion... restrictions) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		if (restrictions != null) {
			for (Criterion restriction : restrictions) {
				c.add(restriction);
			}
		}
		T uniqueResult = (T) c.uniqueResult();
		return uniqueResult;
	}

	public static <T> T load(Class c, Criterion... restrictions) {
		DAO dao = new DAO();
		dao.open();
		T result = dao.getUniqueResult(c, restrictions);
		dao.close();
		return result;
	}

}
