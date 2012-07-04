package com.jin.tpdb.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.jin.tpdb.persistence.DAO;

@Entity
public class Artist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(nullable = false)
	String name;
	@Column(length = 500)
	String site;

	public Artist(String name, String site) {

		if (!name.isEmpty() && !site.isEmpty()) {
			this.name = name;
			this.site = site;
		}
	}

	public int getId() {
		return id;
	}

	private void setId(int id) {
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
		List<Album> albums = DAO.getAlbums(this.id);
		return albums;
	}
}
