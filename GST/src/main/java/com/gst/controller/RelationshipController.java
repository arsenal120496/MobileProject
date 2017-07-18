package com.gst.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gst.domain.Account;
import com.gst.domain.Relationship;
import com.gst.service.RelationshipService;

@RestController
public class RelationshipController {
	
	@Autowired
	RelationshipService rs;
	
	@RequestMapping(value = "/addRelationship" , method = RequestMethod.POST)
	public ResponseEntity addRelationship(@RequestParam(value = "Requestuserid", required = true) String  requestuserid, 
							@RequestParam(value = "Responseuserid", required = true) String responseuserid,
							@RequestParam(value = "Sharelocation", required = true) String sharelocation,
							@RequestParam(value = "Checkaccept", required = true) String checkaccept) throws ParseException{
		Relationship relationship = rs.checkRelationship(Integer.parseInt(requestuserid), Integer.parseInt(responseuserid));
		if(relationship == null){
			Relationship relationship1 = new Relationship();
			relationship1.setRequestUserId(Integer.parseInt(requestuserid));
			relationship1.setResponseUserId(Integer.parseInt(responseuserid));
			relationship1.setSharelocation(Boolean.parseBoolean(sharelocation));
			relationship1.setCheckaccept(Boolean.parseBoolean(checkaccept));
			rs.addFriend(relationship1);
			return new ResponseEntity("Add friend sucessfull , waiting accept !!!", HttpStatus.OK);
		}

		return new ResponseEntity("Add friend fail !!!",HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/acceptFriend" , method = RequestMethod.POST)
	public ResponseEntity acceptFriend(@RequestParam(value = "Requestuserid", required = true) String  requestuserid, 
			@RequestParam(value = "Responseuserid", required = true) String responseuserid
							) throws ParseException{
		Relationship relationship = rs.checkRelationship(Integer.parseInt(requestuserid), Integer.parseInt(responseuserid));
		if(relationship != null){
			rs.acceptFriend(relationship);
			return new ResponseEntity("Accept friend sucessfull , waiting accept !!!", HttpStatus.OK);
		}

		return new ResponseEntity("Accept friend fail !!!",HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/shareLocation" , method = RequestMethod.POST)
	public ResponseEntity shareLocation(@RequestParam(value = "Requestuserid", required = true) String  requestuserid, 
			@RequestParam(value = "Responseuserid", required = true) String responseuserid,
			@RequestParam(value = "Sharelocation", required = true) String sharelocation
							) throws ParseException{
		Relationship relationship = rs.checkRelationship(Integer.parseInt(requestuserid), Integer.parseInt(responseuserid));
		if(relationship != null){
			rs.shareLocation(relationship, Boolean.parseBoolean(sharelocation));
			return new ResponseEntity("Accept friend sucessfull , waiting accept !!!", HttpStatus.OK);
		}

		return new ResponseEntity("Accept friend fail !!!",HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/unFriend" , method = RequestMethod.POST)
	public ResponseEntity unFriend(@RequestParam(value = "Requestuserid", required = true) String  requestuserid, 
			@RequestParam(value = "Responseuserid", required = true) String responseuserid
							) throws ParseException{
		Relationship relationship1 = rs.checkRelationship(Integer.parseInt(requestuserid), Integer.parseInt(responseuserid));
		Relationship relationship2 = rs.checkRelationship(Integer.parseInt(responseuserid), Integer.parseInt(requestuserid));
		if(relationship1 != null && relationship2 !=null){
			rs.unFriend(relationship1, relationship2);
			return new ResponseEntity("Unfriend sucessfull , waiting accept !!!", HttpStatus.OK);
		}

		return new ResponseEntity("Unfriend fail !!!",HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/getListFriend" , method = RequestMethod.POST)
	public ResponseEntity getListFriend(@RequestParam(value = "Requestuserid", required = true) String  requestuserid
							) throws ParseException{
		List<Account> res = rs.getListFriendByUserID(Integer.parseInt(requestuserid));
		if(!res.isEmpty()){
			return new ResponseEntity(res, HttpStatus.OK);
		}

		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/getListRelationship" , method = RequestMethod.POST)
	public ResponseEntity getListRelationship(@RequestParam(value = "Requestuserid", required = true) String  requestuserid
							) throws ParseException{
		List<Relationship> res = rs.getListRelationshipByUserID(Integer.parseInt(requestuserid));
		if(!res.isEmpty()){
			return new ResponseEntity(res, HttpStatus.OK);
		}

		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/getRelationshipByRelationshipId" , method = RequestMethod.POST)
	public ResponseEntity getRelationshipByRelationshipId(@RequestParam(value = "id", required = true) String  id
							) throws ParseException{
		Relationship res = rs.getRelationshipByRelationId(Integer.parseInt(id));
		if(res!=null){
			return new ResponseEntity(res, HttpStatus.OK);
		}

		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	
	
	
}
