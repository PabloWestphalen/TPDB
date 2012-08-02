package com.jin.tpdb.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.hibernate.criterion.Restrictions;

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

	//@Transient
	//@Inject
	//private Query query;
	
	@Transient
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
		if(albums == null) {
			System.out.println("########getting albums from artist " + name + "###########");
			this.albums = DAO.getList(Album.class,
					Restrictions.eq("artist.id", this.id));
			return albums;	
		} else {
			return albums;
		}		
	}
}
