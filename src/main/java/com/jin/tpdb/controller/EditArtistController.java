package com.jin.tpdb.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.tpdb.entities.Artist;
import com.jin.tpdb.persistence.DAO;

public class EditArtistController extends HttpServlet {

	protected void dispatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher jsp = request
				.getRequestDispatcher("contribute_artist.jsp");
		jsp.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		dispatch(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (!request.getParameter("name").isEmpty()) {
			Artist artist = new Artist();
			artist.setName(request.getParameter("name"));

			if (!request.getParameter("site").isEmpty()) {
				artist.setSite(request.getParameter("site"));
			}

			DAO dao = new DAO();
			dao.open();
			dao.save(artist);
			dao.close();
			dispatch(request, response);
		}
	}

}