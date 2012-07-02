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
		DAO dao = new DAO();
		dao.open();
		Album album = new Album();

		Artist a = DAO.load(Artist.class,
				Integer.parseInt(request.getParameter("artist")));

		album.setArtist(a);
		album.setName(request.getParameter("name"));
		album.setDescription(request.getParameter("description"));
		album.setLabel(request.getParameter("label"));
		String path = System.getenv("OPENSHIFT_DATA_DIR") + "/uploads/";
		File cover = new File(path + request.getParameter("temp_cover_name"));
		if (cover.exists()) {
			String coverName = album.getArtist().getName() + "-"
					+ album.getName() + ".jpg".toLowerCase().replace(" ", "_");
			System.out.println("###### renaming to: " + coverName);
			cover.renameTo(new File(path + coverName));
		}

		String[] tracks = request.getParameterValues("tracks[]");
		String[] lengths = request.getParameterValues("tracks_length[]");

		for (int i = 0; i <= tracks.length - 1; i++) {
			Song s = new Song();
			s.setAlbum(album);
			s.setName(tracks[i]);
			s.setLength(lengths[i]);
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

		/*
		 * if (!request.getParameter("name").isEmpty()) { Artist artist = new
		 * Artist(); artist.setName(request.getParameter("name"));
		 * 
		 * if (!request.getParameter("site").isEmpty()) {
		 * artist.setSite(request.getParameter("site")); }
		 * 
		 * DAO dao = new DAO(); dao.open(); dao.save(artist); dao.close();
		 * dispatch(request, response); }
		 * 
		 * 
		 * PrintWriter out = response.getWriter(); out.println("<html>");
		 * out.println("<head>"); out.println("</head>"); out.println("<body>");
		 * Enumeration parms = request.getParameterNames(); String parmname;
		 * String parmval; out.println("<ul>"); while (parms.hasMoreElements())
		 * { parmname = (String) parms.nextElement(); parmval =
		 * request.getParameter(parmname); out.print("<li><code>");
		 * out.print(parmname); out.print("</code> = "); if (parmval == null)
		 * out.print("&lt;null&gt;"); else { out.print("<code>");
		 * out.print(parmval); out.print("</code>"); } out.println();
		 * 
		 * String[] tracks = request.getParameterValues("tracks[]"); for (String
		 * t : tracks) { out.print("tracks[] = " + t); }
		 * 
		 * String hiddenField = request.getParameter("temp_cover_name");
		 * out.print("yoyo, hidden field's value is = " + hiddenField);
		 * 
		 * } out.println("</ul>"); out.println("</body>");
		 * out.println("</html>");
		 */
	}
}