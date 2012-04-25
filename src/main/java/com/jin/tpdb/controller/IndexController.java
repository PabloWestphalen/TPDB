package com.jin.tpdb.controller;

import com.jin.tpdb.entities.News;
import com.jin.tpdb.entities.Album;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Order;



public class IndexController extends HttpServlet {
	
	protected SessionFactory sessionFactory;
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		/*EntityManagerFactory factory = Persistence.createEntityManagerFactory("jin");
		EntityManager em = factory.createEntityManager();		
		Session hbs = (Session) em.getDelegate();
		
		List<News> newsList = hbs.createCriteria(News.class).list();
		List<Album> albumsList = hbs.createCriteria(Album.class).list();
		
		request.setAttribute("news", newsList);
		request.setAttribute("albums", albumsList);*/
		
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher jsp = request.getRequestDispatcher("index.jsp");
		jsp.forward(request, response);
		
	}	
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
