package com.jin.tpdb.controller.contribute;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jin.Sanitizer;
import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.Artist;
import com.jin.tpdb.entities.Song;
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
		String coversPath = System.getenv("OPENSHIFT_DATA_DIR") + "/uploads/";

		DAO dao = new DAO();
		dao.open();
		Album album = new Album();

		try {
			int artist_id = Integer.parseInt(request.getParameter("artist"));
			Artist a = DAO.load(Artist.class, artist_id);
			album.setArtist(a);

		} catch (Exception e) {
			// artist is not a number
		}

		String albumName = Sanitizer.clean(request.getParameter("name"));
		String description = Sanitizer.clean(request
				.getParameter("description"));
		String label = Sanitizer.clean(request.getParameter("label"));

		album.setName(albumName);
		album.setDescription(description);
		album.setLabel(label);

		File tempCover = new File(coversPath
				+ request.getParameter("temp_cover_name"));
		if (tempCover.exists()) {
			album.setCover(tempCover);
		}

		String[] tracks = request.getParameterValues("tracks[]");
		String[] lengths = request.getParameterValues("tracks_length[]");

		for (int i = 0; i <= tracks.length - 2; i++) {
			Song s = new Song();
			s.setAlbum(album);
			s.setName(Sanitizer.clean(tracks[i]));
			s.setLength(Sanitizer.clean(lengths[i]));
			s.setNumber(i + 1);
			dao.save(s);
		}

		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));

		cal.set(year, month, 1);

		album.setReleaseDate(cal.getTime());
		album.setUploadDate(new Date());

		dao.save(album);
		dao.close();
		dispatch(request, response);
	}
}