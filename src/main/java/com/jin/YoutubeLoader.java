package com.jin;

import java.net.URL;
import java.util.List;

public class YoutubeLoader {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
		Long before = System.currentTimeMillis();
		System.out.println("Loading...");
		
		YouTubeManager ym = new YouTubeManager();
		List<YouTubeVideo> videos;
		String query = "Portishead - Roads";
		System.out.println("Query is... " + query);
		videos = ym.retrieveVideos("\"" + query + "\"", 5, true, 2000);
		System.out.println("Loaded in " + (System.currentTimeMillis() - before) + "ms");

		for (YouTubeVideo video : videos) {
			System.out.println(video.getName());
		}
		System.out.println("##Done loading##");
		for (YouTubeVideo video : videos) {
			if(video.getName().equals(query)) {
				System.out.println("This one matches: " + video.getWebPlayerUrl());
				break;
			}
			System.out.println("This one doesn't " + video.getWebPlayerUrl());
		}
		System.out.println("###Done looking for matches");
	}
}
