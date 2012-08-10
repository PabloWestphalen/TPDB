// cubo, k19, caelum-web, caelum-java+oo
package com.jin.tpdb.entities;

import java.io.File;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

//lol

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "album", orphanRemoval = true)
	//@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="album")
	private Set<AlbumComment> comments;

	// @Transient
	// private Query query = new Query();

	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "album", orphanRemoval = true)
	//@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="album")
	private Set<Song> songs;

	// @Column(nullable = false) @ManyToOne
	@ManyToOne
	private Artist artist;

	// @Column(nullable = false) @ManyToOne
	@ManyToOne
	private User user;

	@Column(nullable = false)
	private Date uploadDate;

	@Column(nullable = false)
	private String name;

	// private String cover;

	@Column(length = 65535, columnDefinition = "Text")
	private String description;

	private Date releaseDate;

	@Column(length = 6)
	private String length;

	private String label;

	private String downloadLink;

	@Column(length = 4)
	private int bitrate;

	@Column(length = 5)
	private int downloadSize;

	private int downloadCount;
	
	@Transient
	private int totalSongs;
	
	@Transient
	private int averageRating;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDownloadLink() {
		return downloadLink;
	}

	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}

	public int getBitrate() {
		return bitrate;
	}

	public void setBitrate(int bitrate) {
		this.bitrate = bitrate;
	}

	public int getDownloadSize() {
		return downloadSize;
	}

	public void setDownloadSize(int downloadSize) {
		this.downloadSize = downloadSize;
	}

	public int getDownloadCount() {
		return downloadCount;
	}

	public void setDownloadCount(int downloadCount) {
		this.downloadCount = downloadCount;
	}

	public Set<AlbumComment> getComments() {
		return comments;
	}

	public void setComments(Set<AlbumComment> comments) {
		this.comments = comments;
	}

	public String getCover() {
		String coverPath = ("images/albums/" + artist.getName() + "-" + name + ".jpg")
				.replace(" ", "_").toLowerCase();

		return coverPath;
	}

	public void setCover(File cover) {
		String path = System.getenv("OPENSHIFT_DATA_DIR") + "/uploads/";
		String coverName = (artist.getName() + "-" + name + ".jpg")
				.toLowerCase().replace(" ", "_");

		cover.renameTo(new File(path + coverName));
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}
	
	public int getTotalSongs() {
		return this.totalSongs;
	}
	
	public void setTotalSongs(int totalSongs) {
		this.totalSongs = totalSongs;
	}

	public int getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(int averageRating) {
		this.averageRating = averageRating;
	}

	
	
}