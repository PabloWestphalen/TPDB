package com.jin.tpdb.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Order;

import com.jin.tpdb.entities.Artist;
import com.jin.tpdb.persistence.DAO;

public class ArtistsController extends HttpServlet {
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Artist> artists = DAO.getList(Artist.class, Order.asc("name"));
		request.setAttribute("artistsList", artists);

		request.setCharacterEncoding("UTF-8");
		RequestDispatcher jsp = request.getRequestDispatcher("/artists.jsp");
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
