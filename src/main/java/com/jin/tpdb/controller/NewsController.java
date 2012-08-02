package com.jin.tpdb.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;

import com.jin.tpdb.entities.News;
import com.jin.tpdb.entities.Tag;
import com.jin.tpdb.persistence.DAO;

public class NewsController extends HttpServlet {

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		if (request.getParameter("id") != null) {

			int id = Integer.parseInt(request.getParameter("id"));
			News news = DAO.load(News.class, id, FetchMode.JOIN, "tags");
			Collection<Tag> tags = news.getTags();
			request.setAttribute("news", news);

		} else {
			List<News> newsList = DAO.getList(News.class, Order.desc("date"));
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
