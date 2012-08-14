package com.jin.tpdb.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.tpdb.entities.News;
import com.jin.tpdb.repositories.NewsRepository;

public class NewsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@EJB
	private NewsRepository newsRepo;
	
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (request.getParameter("id") != null) {
			int id = Integer.parseInt(request.getParameter("id"));
			Long after, before;
			before = System.currentTimeMillis();
			News news = newsRepo.findById(id);
			after = System.currentTimeMillis();
			System.out.println("Time to get this news from db: " + (after - before) + "ms");
			request.setAttribute("news", news);
		} else {
			List<News> newsList = newsRepo.getAllNews();
			request.setAttribute("newsList", newsList);
		}

		RequestDispatcher jsp = request.getRequestDispatcher("/news.jsp");
		jsp.forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
