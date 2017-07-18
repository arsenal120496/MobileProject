package com.gst.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gst.domain.Account;
import com.gst.domain.Accrelate;
import com.gst.domain.Relationship;
import com.gst.repository.AccountRepository;
import com.gst.repository.RelationshipRepository;



@Service
public class AccountServiceImp implements AccountService {

	@Autowired
	private AccountRepository ar;
	
	@Autowired
	private RelationshipRepository rr;
	
	@Override
	public Account checkLogin(String username, String password) {
		return ar.login(username, password);
	}

	@Override
	public Account register(Account acc) {
		// TODO Auto-generated method stub
		return ar.save(acc);
	}

	@Override
	public Account checkAccountExist(String username) {	
		return ar.checkExistAccount(username);
	}

	@Override
	public Account update(Account acc) {
		// TODO Auto-generated method stub
		return ar.save(acc);
	}

	@Override
	public void delete(Account acc) {
		// TODO Auto-generated method stub
		ar.delete(acc);
	}

	@Override
	public List<Account> getAllAccount() {
		List<Account> res = (List<Account>) ar.findAll();
		return res;
	}

	@Override
	public List<Accrelate> findListAccountByWord(String email, String username) {
		List<Account> res = ar.findListAccountByWord(email, username);
		List<Accrelate> accre = new ArrayList<>();
		for (Account acc : res) {
			Accrelate s = new Accrelate();
			s.setFriendid(acc.getId());
			s.setUsername(acc.getUsername());
			s.setFirstname(acc.getFirstName());
			s.setLastname(acc.getLastName());
			s.setDateofbirth(acc.getDateOfBirth());
			accre.add(s);
		}	
		Account acc = ar.checkExistAccount(username);
		for (Accrelate accrelate : accre) {
			Relationship r = rr.checkRelation(acc.getId(), accrelate.getFriendid());
			if(r!=null){
				if(r.isCheckaccept() == true){
					accrelate.setCheckaccept(true);
					accrelate.setSharelocation(r.isSharelocation());			
				} else {
					accrelate.setCheckaccept(false);
					accrelate.setSharelocation(false);
				}
			}
		}
		return accre;
	}

	@Override
	public Account checkExistAccountById(int userid) {
		
		return ar.findOne(userid);
	}


	
}
