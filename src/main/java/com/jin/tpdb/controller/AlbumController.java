package com.jin.tpdb.controller;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.jin.util.JsonMaker;
import com.jin.util.JsonObject;
import com.jin.util.JsonReader;

public class AlbumController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	@EJB
	private AlbumRepository albumRepo;
	
	public void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		Album album = albumRepo.findById(id);
		if (request.getParameter("json") != null
				&& request.getParameter("json").equals("true")) {
			PrintWriter out = response.getWriter();
			String jSonString = JsonMaker.serialize(album);
			JsonObject obj = JsonReader.toJava(jSonString);
			out.write(obj.get("name").toString());
		} else {
			List<Album> relatedAlbums = albumRepo.getRelatedAlbums(album);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			request.setAttribute("year", sdf.format(album.getReleaseDate()));
			request.setAttribute("relatedAlbums", relatedAlbums);
			request.setAttribute("album", album);
			RequestDispatcher jsp = request.getRequestDispatcher("/album.jsp");
			jsp.forward(request, response);
		}
		
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
