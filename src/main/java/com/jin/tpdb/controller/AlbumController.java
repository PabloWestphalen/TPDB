package com.jin.tpdb.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.Song;
import com.jin.tpdb.persistence.DAO;

public class AlbumController extends HttpServlet {

	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));

		Album album = DAO.load(Album.class, id);
		List<Song> songs = DAO.getSongs(id);
		
		DAO dao = new DAO();
		ArrayList<Song> songsCollection = new ArrayList<Song>();
		
		dao.open();
		
		for(Song s : songs) {
			songsCollection.add(s);
			
		}
		
		album.setSongs(songsCollection);
		dao.save(album);
		dao.close();
		
		request.setAttribute("songs", songs);
		request.setAttribute("album", album);
		request.setCharacterEncoding("UTF-8");
		RequestDispatcher jsp = request.getRequestDispatcher("/album.jsp");
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
