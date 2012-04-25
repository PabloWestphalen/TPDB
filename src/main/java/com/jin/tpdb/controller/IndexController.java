package com.jin.tpdb.controller;

import com.jin.tpdb.entities.News;
import com.jin.tpdb.entities.Album;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.persistence.*;
import org.hibernate.Session;


public class IndexController extends HttpServlet {
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jin");
		EntityManager em = factory.createEntityManager();		
		Session hbs = (Session) em.getDelegate();
		
		List<News> newsList = hbs.createCriteria(News.class).list();
		List<Album> albumsList = hbs.createCriteria(Album.class).list();
		
		request.setAttribute("news", newsList);
		request.setAttribute("albums", albumsList);
		
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
