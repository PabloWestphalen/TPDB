package com.jin.tpdb.controller.contribute;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
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

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		Enumeration parms = request.getParameterNames();
		String parmname;
		String parmval;
		out.println("<ul>");
		while (parms.hasMoreElements()) {
			parmname = (String) parms.nextElement();
			parmval = request.getParameter(parmname);
			out.print("<li><code>");
			out.print(parmname);
			out.print("</code> = ");
			if (parmval == null)
				out.print("&lt;null&gt;");
			else {
				out.print("<code>");
				out.print(parmval);
				out.print("</code>");
			}
			out.println();

			String[] tracks = request.getParameterValues("tracks[]");
			for (String t : tracks) {
				out.print("tracks[] = " + t);
			}

			/*
			 * String[] lengths = request.getParameterValues("tracks_length");
			 * for (String l : lengths) { out.print("tracks_length = " + l); }
			 */

			String hiddenField = request.getParameter("temp_cover_name");
			out.print("yoyo, hidden field's value is = " + hiddenField);

		}
		out.println("</ul>");
		out.println("</body>");
		out.println("</html>");
	}
}