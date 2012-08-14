package com.jin.tpdb.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AlbumComment implements Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;

	@ManyToOne
	private User user;

	@ManyToOne(fetch=FetchType.LAZY)
	// private Collection<Album> album;
	private Album album;

	@Column(nullable = false)
	private Date date;

	@Column(length = 65535, columnDefinition = "Text")
	private String comment;
	
	private String userIP;
	
	private String userName;
	
	private String userEmail;
	
	

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#getUserIP()
	 */
	@Override
	public String getUserIP() {
		return userIP;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#setUserIP(java.lang.String)
	 */
	@Override
	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#getUserName()
	 */
	@Override
	public String getUserName() {
		return userName;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#getUserEmail()
	 */
	@Override
	public String getUserEmail() {
		return userEmail;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#setUserEmail(java.lang.String)
	 */
	@Override
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#setId(int)
	 */
	@Override
	public void setId(int id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#getUser()
	 */
	@Override
	public User getUser() {
		return user;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#setUser(com.jin.tpdb.entities.User)
	 */
	@Override
	public void setUser(User user) {
		this.user = user;
	}

	public Album getAlbum() {
		return album;
	}

	public void setAlbum(Album album) {
		this.album = album;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#getDate()
	 */
	@Override
	public Date getDate() {
		return date;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#setDate(java.util.Date)
	 */
	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#getComment()
	 */
	@Override
	public String getComment() {
		return comment;
	}

	/* (non-Javadoc)
	 * @see com.jin.tpdb.entities.Comment#setComment(java.lang.String)
	 */
	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

}
