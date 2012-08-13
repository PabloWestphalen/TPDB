package com.jin.tpdb.controller.contribute;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.Sanitizer;
import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.Artist;
import com.jin.tpdb.entities.Song;
import com.jin.tpdb.persistence.GenericDAO;
import com.jin.tpdb.repositories.AlbumRepository;
import com.jin.tpdb.repositories.ArtistRepository;

public class EditAlbumController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private GenericDAO dao;

	@EJB
	private ArtistRepository artistRepo;
	
	@EJB
	private AlbumRepository albumRepo;
	
	

	protected void dispatch(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher jsp = request
				.getRequestDispatcher("/contribute/album.jsp");
		jsp.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		List<Artist> artists = artistRepo.getAllArtists();
		request.setAttribute("artists", artists);
		dispatch(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String coversPath = System.getenv("OPENSHIFT_DATA_DIR") + "/uploads/";

		Album album = new Album();

		int artistId = Integer.parseInt(request.getParameter("artist"));


		String albumName = Sanitizer.clean(request.getParameter("name"));
		String description = Sanitizer.clean(request
				.getParameter("description"));
		String label = Sanitizer.clean(request.getParameter("label"));
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		cal.set(year, month, 1);

		album.setName(albumName);
		album.setDescription(description);
		album.setLabel(label);
		album.setReleaseDate(cal.getTime());
		album.setUploadDate(new Date());

		File tempCover = new File(coversPath
				+ request.getParameter("temp_cover_name"));
		if (tempCover.exists()) {
			album.setCover(tempCover);
		}

		String[] tracks = request.getParameterValues("tracks[]");
		String[] lengths = request.getParameterValues("tracks_length[]");
		albumRepo.save(album, artistId);
		artistRepo.refresh(artistId);

		for (int i = 0; i <= tracks.length - 2; i++) {
			Song s = new Song();
			s.setAlbum(album);
			s.setName(Sanitizer.clean(tracks[i]));
			s.setLength(Sanitizer.clean(lengths[i]));
			s.setNumber(i + 1);
			dao.save(s);
		}

		dispatch(request, response);
	}
}