package com.gst.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gst.domain.Account;
import com.gst.domain.Location;
import com.gst.service.AccountService;
import com.gst.service.LocationService;




@RestController
public class LocationController {
	@Autowired
	LocationService ls;
	
	@Autowired
	AccountService as;
	

	@RequestMapping(value = "/addLocation" , method = RequestMethod.POST)
	public ResponseEntity addLocation(@RequestParam(value = "longitude", required = true) String  longitude, 
							@RequestParam(value = "latitude", required = true) String latitude,
							@RequestParam(value = "username", required = true) String username,
							@RequestParam(value = "date", required = true) String date) throws ParseException{
		Account s = as.checkAccountExist(username);
		if(s !=null){
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        Date fDate = df.parse(date);
			Location u = new Location(Double.parseDouble(longitude),Double.parseDouble(latitude),s.getId(),fDate);
			ls.save(u);
			return new ResponseEntity("Add Location Sucessfull !!!",HttpStatus.OK);
		}
		return new ResponseEntity("Add Location Fail",HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	@RequestMapping(value = "/addLocationByUserId" , method = RequestMethod.POST)
	public ResponseEntity addLocationByUserId(@RequestParam(value = "longitude", required = true) String  longitude, 
							@RequestParam(value = "latitude", required = true) String latitude,
							@RequestParam(value = "userid", required = true) String userid,
							@RequestParam(value = "date", required = true) String date) throws ParseException{
		Account s = as.checkExistAccountById(Integer.parseInt(userid));
		if(s !=null){
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        Date fDate = df.parse(date);
			Location u = new Location(Double.parseDouble(longitude),Double.parseDouble(latitude),Integer.parseInt(userid),fDate);
			ls.save(u);
			return new ResponseEntity("Add Location Sucessfull !!!",HttpStatus.OK);
		}
		return new ResponseEntity("Add Location Fail",HttpStatus.NOT_ACCEPTABLE);
		
	}
	
	
	@RequestMapping(value = "/editLocation" , method = RequestMethod.POST)
	public ResponseEntity editLocation(
							@RequestParam(value = "longitude", required = true) String  longitude, 
							@RequestParam(value = "latitude", required = true) String latitude,
							@RequestParam(value = "username", required = true) String username,
							@RequestParam(value = "date", required = true) String date
							) throws ParseException{
		Account s = as.checkAccountExist(username);
		if(s !=null){
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
	        Date fDate = df.parse(date);
			Location u = new Location(Double.parseDouble(longitude),Double.parseDouble(latitude),s.getId(),fDate);	
			ls.save(u);
			return new ResponseEntity("Edit Location Sucessfull !!!",HttpStatus.OK);
		}
		return new ResponseEntity("Edit Location Fail",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@RequestMapping(value = "/editLocationByLocationId" , method = RequestMethod.POST)
	public ResponseEntity editLocationByLocationId(
							@RequestParam(value = "locationid", required = true) String  locationid,
							@RequestParam(value = "longitude", required = true) String  longitude, 
							@RequestParam(value = "latitude", required = true) String latitude,
							@RequestParam(value = "userid", required = true) String userid,
							@RequestParam(value = "date", required = true) String date) throws ParseException{
		Location s = ls.getLocationById(Integer.parseInt(locationid));
		if(s !=null){
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
			df.setTimeZone(TimeZone.getTimeZone("UTC"));
		    Date fDate = df.parse(date);
		    Location u = new Location(Double.parseDouble(longitude),Double.parseDouble(latitude),Integer.parseInt(userid),fDate);	
			ls.save(u);
			return new ResponseEntity("Edit Location Sucessfull !!!",HttpStatus.OK);
		}
		return new ResponseEntity("Edit Location Fail",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@RequestMapping(value = "/deleteLocation" , method = RequestMethod.POST)
	public ResponseEntity deleteLocation(
							@RequestParam(value = "locationid", required = true) String  locationid 						
							) throws ParseException{
			ls.delete(Integer.parseInt(locationid));
			return new ResponseEntity("Delete Location Sucessfull !!!",HttpStatus.OK);
	}
				

	
	@RequestMapping(value = "/getListLocation" , method = RequestMethod.POST)
	public ResponseEntity getListLocation(
							@RequestParam(value = "username", required = true) String username							
							) throws ParseException{
		Account s = as.checkAccountExist(username);
		List<Location> res = ls.findByUserId(s.getId());
		if(!res.isEmpty()){
			return new ResponseEntity(res,HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/getLastLocation" , method = RequestMethod.POST)
	public ResponseEntity getLastLocation(
							@RequestParam(value = "userid", required = true) String userid							
							) throws ParseException{
		Pageable pageableTop = new PageRequest(0, 1, Direction.DESC, "Time");
		List<Location> res = ls.getLastLocation(Integer.parseInt(userid), pageableTop);
		if(!res.isEmpty()){
			return new ResponseEntity(res,HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/getListLocationById" , method = RequestMethod.POST)
	public ResponseEntity getListLocationById(
							@RequestParam(value = "userid", required = true) String userid							
							) throws ParseException{
		List<Location> res = ls.findByUserId(Integer.parseInt(userid));
		if(!res.isEmpty()){
			return new ResponseEntity(res,HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/getLocationById" , method = RequestMethod.POST)
	public ResponseEntity getLocationById(
							@RequestParam(value = "locationid", required = true) String locationid							
							) throws ParseException{
		Location res = ls.getLocationById(Integer.parseInt(locationid));
		if(res != null){
			return new ResponseEntity(res,HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	
	
}
