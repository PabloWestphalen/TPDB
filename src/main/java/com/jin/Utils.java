package com.jin;

import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.jin.Sanitizer;
import com.jin.tpdb.entities.Album;
import com.jin.tpdb.entities.Song;
import com.jin.tpdb.persistence.DAO;

public class Utils {

	public static List<String> getMonths() {
		List<String> months = new ArrayList<String>();
		Collections.addAll(months, new DateFormatSymbols().getMonths());
		try {
			months.remove(12);
		} catch (IndexOutOfBoundsException e) {
		}
		return months;
	}

	public static int getYear() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		return year;
	}

	public static String cleanHtml(String text) {
		return Sanitizer.cleanHtml(text);
	}

	public static String trim(String msg, Integer limit) {
		if (msg.length() > limit) {
			msg = msg.substring(0, limit);
			int lastDot = msg.lastIndexOf(".") + 1;
			if (lastDot > 0) {
				msg = msg.substring(0, lastDot);
			}
			msg += " [...]";
		} else {
			int nl = msg.indexOf("\n\n");
			if (nl > 0) {
				msg = msg.substring(0, nl);
				msg += " [...]";
			}
		}
		return msg;
	}
	
	@Deprecated
	public static void assignTracksToAlbums() {
		List<Album> albums = DAO.getList(Album.class);
		
		DAO dao = new DAO();
		dao.open();		
		
		for(Album a : albums) {
			ArrayList<Song> songsCollection = new ArrayList<Song>();
			Collection<Song> songs = a.getSongs();
			for(Song s : songs) {
				songsCollection.add(s);
				
			}
			a.setSongs(songsCollection);
			dao.em.merge(a);
		}
		
		dao.close();
	}
}
