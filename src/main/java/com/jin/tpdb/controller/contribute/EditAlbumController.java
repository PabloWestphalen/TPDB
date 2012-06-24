package com.jin.tpdb.controller.contribute;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.tpdb.entities.Artist;
import com.jin.tpdb.persistence.DAO;

public class EditAlbumController extends HttpServlet {

	protected void dispatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher jsp = request
				.getRequestDispatcher("/contribute/album.jsp");
		jsp.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Artist> artists = DAO.getList(Artist.class);
		request.setAttribute("artists", artists);
		dispatch(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * if (!request.getParameter("name").isEmpty()) { Artist artist = new
		 * Artist(); artist.setName(request.getParameter("name"));
		 * 
		 * if (!request.getParameter("site").isEmpty()) {
		 * artist.setSite(request.getParameter("site")); }
		 * 
		 * DAO dao = new DAO(); dao.open(); dao.save(artist); dao.close();
		 * dispatch(request, response); }
		 */
	}

}