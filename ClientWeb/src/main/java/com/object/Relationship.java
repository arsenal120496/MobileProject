package com.object;

import java.io.Serializable;
import java.util.Date;


public class Relationship implements Serializable {

	int Id;
	int Requestuserid;
	int Responseuserid;
	int Sharelocation;
	boolean Checkaccept;
	
	
	public Relationship() {
		// TODO Auto-generated constructor stub
	}

	


	public Relationship(int requestuserid, int responseuserid, int sharelocation, boolean checkaccept) {
		super();
		Requestuserid = requestuserid;
		Responseuserid = responseuserid;
		Sharelocation = sharelocation;
		Checkaccept = checkaccept;
	}



	public int getId() {
		return Id;
	}


	public int getRequestUserId() {
		return Requestuserid;
	}


	public void setRequestUserId(int requestUserId) {
		Requestuserid = requestUserId;
	}


	public int getResponseUserId() {
		return Responseuserid;
	}


	public void setResponseUserId(int responseUserId) {
		Responseuserid = responseUserId;
	}


	public int getShareLocation() {
		return Sharelocation;
	}


	public void setShareLocation(int shareLocation) {
		Sharelocation = shareLocation;
	}


	public boolean isCheckaccept() {
		return Checkaccept;
	}


	public void setCheckaccept(boolean checkaccept) {
		Checkaccept = checkaccept;
	}
	
}
