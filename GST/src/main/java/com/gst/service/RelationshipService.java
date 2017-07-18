package com.gst.service;

import java.util.List;

import com.gst.domain.Account;
import com.gst.domain.Relationship;

public interface RelationshipService {
    
	Relationship addFriend(Relationship s);
	
	boolean acceptFriend(Relationship s);
	
	boolean shareLocation(Relationship s, boolean shareLocation);
	
	boolean unFriend(Relationship s, Relationship ss);
	
	Relationship checkRelationship(int requestuserid, int responseuserid);
	
	List<Account> getListFriendByUserID(int userid);	
	
	List<Relationship> getListRelationshipByUserID(int userid);
	
	Relationship getRelationshipByRelationId(int id);
}

