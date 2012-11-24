package com.jin.tpdb.entities;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
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
	@OrderBy("releaseDate ASC")
	private Set<Album> albums;

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

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}
	
	
}
