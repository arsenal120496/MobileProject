package com.gst.domain;

import java.util.Date;

public class Accrelate {
	int friendid;
	String Username;
	String Lastname;
	String Firstname;
	Date Dateofbirth;
	boolean Sharelocation;
	boolean Checkaccept;
	
	public Accrelate() {
		// TODO Auto-generated constructor stub
	}

	public Accrelate( int friendid, String username, String lastname, String firstname, Date dateofbirth,
			boolean sharelocation, boolean checkaccept) {
		super();
		this.friendid = friendid;
		Username = username;
		Lastname = lastname;
		Firstname = firstname;
		Dateofbirth = dateofbirth;
		Sharelocation = sharelocation;
		Checkaccept = checkaccept;
	}


	public int getFriendid() {
		return friendid;
	}

	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public Date getDateofbirth() {
		return Dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		Dateofbirth = dateofbirth;
	}

	public boolean isSharelocation() {
		return Sharelocation;
	}

	public void setSharelocation(boolean sharelocation) {
		Sharelocation = sharelocation;
	}

	public boolean isCheckaccept() {
		return Checkaccept;
	}

	public void setCheckaccept(boolean checkaccept) {
		Checkaccept = checkaccept;
	}
	
}
