package com.object;

import java.io.Serializable;
import java.util.Date;






public class Location implements Serializable {

	int id;
	Double longitude;
    Double latitude;
    int user_id;
	Long time;
	
    

    public Location() {
    }

    

	public Location(int id, Double longitude, Double latitude, int user_id, Long time) {
		super();
		this.id = id;
		this.longitude = longitude;
		this.latitude = latitude;
		this.user_id = user_id;
		this.time = time;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Double getLongitude() {
		return longitude;
	}



	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}



	public Double getLatitude() {
		return latitude;
	}



	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}



	public int getUser_id() {
		return user_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	public Long getTime() {
		return time;
	}



	public void setTime(Long time) {
		this.time = time;
	}



	
    
}
