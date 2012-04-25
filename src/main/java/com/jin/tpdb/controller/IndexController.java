package com.jin.tpdb.controller;

import com.jin.tpdb.entities.News;
import com.jin.tpdb.entities.Album;
//import com.jin.tpdb.DAO;
import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.persistence.*;
import javax.persistence.criteria.*;

public class IndexController extends HttpServlet {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("jin");
	EntityManager em = factory.createEntityManager();		
	CriteriaBuilder cb = em.getCriteriaBuilder();
	
	
	public <T> List<T> getList(Class et) {		
		
		CriteriaQuery<et> query = cb.createQuery(et); 
		
		/*TypedQuery<et.class> typedQuery = em.createQuery(
			query.select(
				query.from(et)
			)
		);*/
		
		
		
		
		
		
		
		
		List<T> list = em.createQuery("SELECT n FROM " + entity.getName() + " n").getResultList();		
		return list;
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
