package com.gst.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.gst.domain.Location;
import com.gst.repository.AccountRepository;
import com.gst.repository.LocationRespository;




@Service
public class LocationServiceImp implements LocationService {

	
	@Autowired
	private LocationRespository lr;
	
	
	@Override
	public List<Location> findByUserId(int userID) {
		
		List<Location> res = lr.findByUsername(userID);
		if(!res.isEmpty()){
			return res;
		}
		return null;
	}


	@Override
	public List<Location> findAll() {
		// TODO Auto-generated method stub
		return (List<Location>)lr.findAll();
	}

	@Override
	public Location save(Location userLocation) {		
		return lr.save(userLocation);
	}


	@Override
	public Location edit(Location userLocation) {

		return lr.save(userLocation);
	}


	@Override
	public void delete(int locationid) {
		lr.delete(locationid);		
	}


	@Override
	public List<Location> getLastLocation(int userid, Pageable pageable) {	
		List<Location> res = lr.getLastLocation(userid, pageable);
		if(!res.isEmpty()){
			return res;
		}
		return null;
	}


	@Override
	public Location getLocationById(int locationId) {
		return lr.findOne(locationId);
	}


	@Override
	public Location editLocationById(int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
}
