package com.gst.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="Relationship")
public class Relationship implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int Id;
	int Requestuserid;
	int Responseuserid;
	boolean Sharelocation;
	boolean Checkaccept;
	
	
	public Relationship() {
		// TODO Auto-generated constructor stub
	}

	


	public Relationship(int requestuserid, int responseuserid, boolean sharelocation, boolean checkaccept) {
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
