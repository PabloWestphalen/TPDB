package com.jin.tpdb.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@Column(nullable = false)
	String name;

	@Column(length = 500)
	String site;

	//@OneToMany(fetch = FetchType.EAGER, mappedBy = "artist", orphanRemoval = true)
	//@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="artist")
	private List<Album> albums;

	public Artist() {

	}

	public Artist(String name, String site) {

		if (!name.isEmpty() && !site.isEmpty()) {
			this.name = name;
			this.site = site;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public List<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(List<Album> albums) {
		this.albums = albums;
	}
	
	
}
