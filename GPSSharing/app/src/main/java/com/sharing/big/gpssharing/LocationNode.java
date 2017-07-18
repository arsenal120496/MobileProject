package com.sharing.big.gpssharing;

import java.io.Serializable;






public class LocationNode implements Serializable {

	String Longitude;
    String Latitude;
    String Userid;
    String Time;
	
    

    public LocationNode() {
    }

    
    public LocationNode(String longitude, String latitude, String user_id, String time ) {
		super();
		Longitude = longitude;
		Latitude = latitude;
		Time = time;
		Userid = user_id;
	}


    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
