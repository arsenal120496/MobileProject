package com.gst.service;


import java.util.List;

import org.springframework.data.domain.Pageable;

import com.gst.domain.Location;


public interface LocationService {
	List<Location> findByUserId(int userID);    
    
    List<Location> findAll();

    Location save(Location userLocation);
    
    Location edit(Location userLocation);
    
    Location editLocationById(int userId);
    
    void delete (int locationid);
    
    List<Location> getLastLocation(int userid , Pageable pageable);
    
    Location getLocationById(int locationId);
    
}

