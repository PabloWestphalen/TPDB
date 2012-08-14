package com.jin.tpdb.entities;

import java.util.Date;

public interface Comment {

	public abstract String getUserIP();

	public abstract void setUserIP(String userIP);

	public abstract String getUserName();

	public abstract void setUserName(String userName);

	public abstract String getUserEmail();

	public abstract void setUserEmail(String userEmail);

	public abstract int getId();

	public abstract void setId(int id);

	public abstract User getUser();

	public abstract void setUser(User user);

	public abstract Date getDate();

	public abstract void setDate(Date date);

	public abstract String getComment();

	public abstract void setComment(String comment);

}