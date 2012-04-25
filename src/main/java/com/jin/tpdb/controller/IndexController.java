package com.jin.tpdb.controller;

import com.jin.tpdb.entities.News;
import com.jin.tpdb.entities.Album;
//import com.jin.tpdb.DAO;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.persistence.*;

public class IndexController extends HttpServlet {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("jin");
	EntityManager em = factory.createEntityManager();	
	
	
	public <T> List<T> getList(Class entity) {
		return em.createQuery("SELECT n FROM " + entity.getName() + " n").getResultList();
	}
	
	public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		List<News> newsList = getList(News.class);
		List<Album> albumsList = getList(Album.class);
		
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
