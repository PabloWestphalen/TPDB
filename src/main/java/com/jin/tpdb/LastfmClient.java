package com.jin.tpdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.jin.tpdb.repositories.AlbumRepository;

import de.umass.lastfm.Album;
import de.umass.lastfm.Artist;
import de.umass.lastfm.Caller;
import de.umass.lastfm.ImageSize;
import de.umass.lastfm.Track;

public class LastfmClient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	// protected void doPost(HttpServletRequest request,
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String artistName = request.getParameter("artist");
		String albumName = request.getParameter("album");
		String ajaxRequest = request.getHeader("X-Requested-With");

		if (artistName != null && albumName != null) {
			// if (ajaxRequest != null && ajaxRequest.equals("XMLHttpRequest"))
			// {
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
			out.print(getData(artistName, albumName));

			// }
		}
	}

	@SuppressWarnings("unchecked")
	public static JSONObject getData(String artistName, String albumName) {
		Caller.getInstance().setCache(null);
		JSONObject r = new JSONObject();
		String key = "c360ec64b75bdc863f5109da6bf7e3ca"; // this is the key used
		Album album = null;
		for (Album ab : Artist.getTopAlbums(artistName, key)) {
			if (ab.getName().equals(albumName)) {
				album = Album.getInfo(artistName, ab.getMbid(), key);
				break;
			}
		}
		// image
		r.put("image", album.getImageURL(ImageSize.LARGE));
		// date
		Date releaseDate = album.getReleaseDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(releaseDate);
		r.put("month", cal.get(Calendar.MONTH));
		r.put("year", cal.get(Calendar.YEAR));
		// tracks
		ArrayList<HashMap<String, String>> tracks = new ArrayList<HashMap<String, String>>();
		for (Track t : album.getTracks()) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("title", t.getName());
			map.put("length", formatLength(t.getDuration()));
			tracks.add(map);
		}
		r.put("tracks", tracks);
		return r;
	}

	public static String formatLength(int length) {
		int minutes = length / 60;
		int seconds = length % 60;
		return ((minutes < 1 ? "0" : "") + minutes + ":"
				+ (seconds < 10 ? "0" : "") + seconds);

	}
}