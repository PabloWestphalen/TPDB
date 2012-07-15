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



public class RssController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		Collection newsList = new LinkedHashSet(DAO.getList(News.class,
				Order.desc("date"), FetchMode.JOIN, "tags"));
		// teste
		List<Album> albums = DAO.getList(Album.class, Order.desc("uploadDate"));
		
		request.setAttribute("newsList", newsList);
		request.setAttribute("albums", albums);		
		RequestDispatcher jsp = request.getRequestDispatcher("rss.jsp");
		jsp.forward(request, response);		
	}
}
