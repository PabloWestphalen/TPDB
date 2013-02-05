package com.jin.tpdb.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.tpdb.entities.Album;
import com.jin.tpdb.repositories.AlbumRepository;
import com.cedarsoftware.util.io.*;

public class AlbumController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@EJB
	private AlbumRepository albumRepo;
	
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));

		Album album = albumRepo.findById(id);
		System.out.println("###################################################################################");
		//System.out.println("Serializing begin");
		System.out.println("JSON-IO Serializing begin");
		//System.out.println(JsonMaker.serialize(album));
		System.out.println(JsonWriter.objectToJson(album));
		System.out.println("Serializing end");
		System.out.println("###################################################################################");
		List<Album> relatedAlbums = albumRepo.getRelatedAlbums(album);
		/*if(relatedAlbums == null || relatedAlbums.size() == 0) {
			relatedAlbums = albumRepo.getRandomAlbums(3, album.getId());
		}*/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		request.setAttribute("year", sdf.format(album.getReleaseDate()));
		request.setAttribute("relatedAlbums", relatedAlbums);
		request.setAttribute("album", album);
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
