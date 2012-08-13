package com.jin.tpdb.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.AlbumRating;
import com.jin.tpdb.entities.User;
import com.jin.tpdb.repositories.AlbumRepository;

public class RatingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AlbumRepository albumRepo;
	
	
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String type = request.getParameter("type");
			int albumId = Integer.parseInt(request.getParameter("id"));
			int value = Integer.parseInt(request.getParameter("value"));

			if (type.equals("album") && albumId > 0) {

				//User user = dao.load(User.class, 1);

				AlbumRating rating = new AlbumRating();
				rating.setRating(value);
				albumRepo.setRating(albumId, rating);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
