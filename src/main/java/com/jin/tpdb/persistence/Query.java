package com.jin.tpdb.persistence;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.jin.tpdb.entities.Artist;

public class Query extends BaseDAO {

	public <T> List<T> getList(Class entity, Order order) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		c.addOrder(order);
		List<T> results = c.list();
		return results;
	}

	public <T> List<T> getList(Class entity, Criterion... restrictions) {
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

	public double getAverage(Class entity, String field,
			Criterion... restrictions) {
		Session hbs = (Session) em.getDelegate();
		Criteria c = hbs.createCriteria(entity);
		if (restrictions != null) {
			for (Criterion restriction : restrictions) {
				c.add(restriction);
			}
		}
		c.setProjection(Projections.avg(field));
		Double uniqueResult = (Double) c.uniqueResult();
		if (uniqueResult != null) {
			return uniqueResult;
		} else {
			return 0;
		}
	}

}