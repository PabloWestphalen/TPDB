package com.jin.tpdb.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.News;
import com.jin.tpdb.repositories.AlbumRepository;
import com.jin.tpdb.repositories.NewsRepository;

public class RssController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private NewsRepository newsRepo;

	@EJB
	private AlbumRepository albumRepo;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<News> newsList = newsRepo.getAllNews();
		List<Album> albums = albumRepo.getAlbums();

		request.setAttribute("newsList", newsList);
		request.setAttribute("albums", albums);
		RequestDispatcher jsp = request.getRequestDispatcher("rss.jsp");
		jsp.forward(request, response);
	}
}
