package com.gst.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gst.domain.Account;
import com.gst.domain.Relationship;
import com.gst.repository.AccountRepository;
import com.gst.repository.RelationshipRepository;


@Service
public class RelationshipServiceImp implements RelationshipService{

	
	@Autowired
	RelationshipRepository rr;
	
	@Autowired
	AccountRepository ar;

	@Override
	public Relationship addFriend(Relationship s) {
		return rr.save(s);
	}

	@Override
	public boolean acceptFriend(Relationship s) {
		s.setCheckaccept(true);
		rr.save(s);
		Relationship generate = new Relationship(s.getResponseUserId(), s.getRequestUserId(), true, true);
		rr.save(generate);	
		return true;
	}

	@Override
	public boolean shareLocation(Relationship s, boolean shareLocation) {
		s.setSharelocation(shareLocation);
		rr.save(s);
		return true;
	}


	@Override
	public Relationship checkRelationship(int requestuserid, int responseuserid) {		
		return rr.checkRelation(requestuserid,responseuserid);
	}

	@Override
	public boolean unFriend(Relationship s, Relationship ss) {
		rr.delete(s);
		rr.delete(ss);
		return true;
	}

	@Override
	public List<Account> getListFriendByUserID(int userid) {
		List<Relationship> res = rr.getListRelation(userid);
		if(!res.isEmpty()){
			List<Account> listAcc = new ArrayList<Account>();
			for (Relationship relationship : res) {
				if(relationship.isCheckaccept() == true){
					Account a = ar.findOne(relationship.getResponseUserId());
					listAcc.add(a);
				}
			}
			return listAcc;
		}
		return null;
	}

	@Override
	public List<Relationship> getListRelationshipByUserID(int userid) {
		return rr.getListRelation(userid);
	}

	@Override
	public Relationship getRelationshipByRelationId(int id) {
		return rr.findOne(id);
	}


}
