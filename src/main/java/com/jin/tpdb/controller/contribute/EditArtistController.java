package com.jin.tpdb.controller.contribute;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jin.Sanitizer;
import com.jin.tpdb.entities.Artist;
import com.jin.tpdb.repositories.ArtistRepository;
import com.jin.util.JsonObject;

public class EditArtistController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ArtistRepository artistRepo;
	
	protected void dispatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher jsp = request
				.getRequestDispatcher("/contribute/artist.jsp");
		jsp.forward(request, response);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		dispatch(request, response);
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String name = Sanitizer.clean(request.getParameter("artist_name"));
		String site = Sanitizer.clean(request.getParameter("site"));
		
		if (!name.isEmpty() && !site.isEmpty()) {
			
			Artist artist = new Artist(name, site);
			
			artistRepo.save(artist);
			
			String ajaxRequest = request.getHeader("X-Requested-With");
			
			if (ajaxRequest != null && ajaxRequest.equals("XMLHttpRequest")) {
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				
				JsonObject jsonResponse = new JsonObject();
				jsonResponse.put("name", artist.getName());
				jsonResponse.put("id", artist.getId());
				out.print(jsonResponse);
				
			} else {
				dispatch(request, response);
			}
			
		}
	}
}