package com.jin.tpdb.controller;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.Utils;
import com.jin.tpdb.entities.Artist;
import com.jin.tpdb.repositories.ArtistRepository;

public class ArtistsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	private ArtistRepository artistRepo;

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<Artist> artists = artistRepo.getAllArtists();

		if (request.getPathInfo() != null) {
			String artistName = Utils.urlDecode(request.getPathInfo()).replace(
					"/", "");
			request.setAttribute("defaultArtist", artistName);
		}

		request.setAttribute("artistsList", artists);

		RequestDispatcher jsp = request.getRequestDispatcher("/artists.jsp");
		jsp.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
}