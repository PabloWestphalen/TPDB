package com.jin;
public class YouTubeVideo {

	private String name;
	private String id;
	private String webPlayerUrl;
	
	public YouTubeVideo() {
		
	}
	
	public YouTubeVideo(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getWebPlayerUrl() {
		return webPlayerUrl;
	}

	public void setWebPlayerUrl(String webPlayerUrl) {
		this.webPlayerUrl = webPlayerUrl;
	}
	
}