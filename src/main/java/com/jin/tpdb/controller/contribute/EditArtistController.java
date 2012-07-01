package com.jin.tpdb.controller.contribute;

import java.io.IOException;
import java.io.PrintWriter;

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
				.getRequestDispatcher("/contribute/artist.jsp");
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

		if (!request.getParameter("artist_name").isEmpty()) {
			Artist artist = new Artist();
			artist.setName(request.getParameter("artist_name"));

			if (!request.getParameter("site").isEmpty()) {
				artist.setSite(request.getParameter("site"));
			}

			DAO dao = new DAO();
			dao.open();
			dao.save(artist);
			dao.close();
			if (request.getHeader("X-Requested-With").equals("XMLHttpRequest")) {
				PrintWriter out = response.getWriter();
				out.print("yo dawg, you got it. i'm an ajax request!");
			} else {
				dispatch(request, response);
			}
		}
	}

}