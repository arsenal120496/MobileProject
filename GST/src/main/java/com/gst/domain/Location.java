package com.gst.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Location")
public class Location implements Serializable {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int Id;
	Double Longitude;
    Double Latitude;
    int Userid;
	Date Time;
	
    

    public Location() {
    }

    
    public Location( Double longitude, Double latitude,int user_id, Date time ) {
		super();
		Longitude = longitude;
		Latitude = latitude;
		Time = time;
		Userid = user_id;
	}

	public Location(int Id, Double longitude, Double latitude,int user_id, Date time ) {
		super();
		this.Id = Id;
		Longitude = longitude;
		Latitude = latitude;
		Time = time;
		Userid = user_id;
	}


	public Double getLongitude() {
		return Longitude;
	}


	public void setLongitude(Double longitude) {
		Longitude = longitude;
	}


	public Double getLatitude() {
		return Latitude;
	}


	public void setLatitude(Double latitude) {
		Latitude = latitude;
	}


	public Date getTime() {
		return Time;
	}


	public void setTime(Date time) {
		Time = time;
	}


	public int getUser_id() {
		return Userid;
	}


	public void setUser_id(int user_id) {
		Userid = user_id;
	}


	public int getId() {
		return Id;
	}

    
    
	
	
}
