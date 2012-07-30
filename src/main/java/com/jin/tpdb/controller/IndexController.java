package com.jin.tpdb.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.FetchMode;
import org.hibernate.criterion.Order;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.News;
import com.jin.tpdb.persistence.DAO;

public class IndexController extends HttpServlet {

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<News> newsList = DAO.getList(News.class, Order.desc("date"));

		List<Album> albumsList = DAO.getList(Album.class,
				Order.desc("uploadDate"), 4);
		List<Album> featuredAlbumsList = DAO.getList(Album.class, Order.desc("name"), 3);

		request.setAttribute("newsList", newsList);
		request.setAttribute("albums", albumsList);
		request.setAttribute("featuredAlbums", featuredAlbumsList);

		request.setCharacterEncoding("UTF-8");
		RequestDispatcher jsp = request.getRequestDispatcher("index.jsp");
		jsp.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}
