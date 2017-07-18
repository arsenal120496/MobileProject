package com.gst.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gst.config.TokenAuthenticationService;
import com.gst.domain.Account;
import com.gst.domain.Accrelate;
import com.gst.domain.UserTokenRespone;
import com.gst.service.AccountService;



@RestController
public class AccountController {
	@Autowired
	AccountService as;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity login(@RequestParam(value = "username", required = true) String username,
            @RequestParam(value = "password", required = true) String password, HttpServletResponse res) throws JsonProcessingException
                      
	{
		Account acc = as.checkLogin(username, password);
		boolean result = false;
		if (acc != null) {
			result = true;
			TokenAuthenticationService.addAuthentication(res, username);
			ObjectMapper obj = new ObjectMapper();		
			String token = TokenAuthenticationService.gettoken(res, username);
			UserTokenRespone userTokenRespone = new UserTokenRespone(result, TokenAuthenticationService.HEADER_STRING, token);
				
			return new ResponseEntity(userTokenRespone, HttpStatus.OK);
		}
		else	
		{
			return new ResponseEntity(new UserTokenRespone(result),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/loginMobile", method = RequestMethod.POST)
    public ResponseEntity<Account> loginMobile(@RequestParam(value = "username", required = true) String  username, 
    											@RequestParam(value = "password", required = true) String password, HttpServletResponse res){
    	Account accTest = as.checkAccountExist(username);
    	Account acc = as.checkLogin(username, password);

    	if(acc != null && accTest !=null){
    		String token = TokenAuthenticationService.gettoken(res, username);
            UserTokenRespone userTokenRespone = new UserTokenRespone(acc);
            return new ResponseEntity(acc,HttpStatus.OK);
    	}else if (acc == null && acc != null){
    		return new ResponseEntity("1",HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity("2", HttpStatus.NOT_FOUND);
        }
    }
	
	@RequestMapping(value = "/deleteAccount", method = RequestMethod.POST)
	public ResponseEntity deleteAccount(@RequestParam(value = "username", required = true) String  username){
		Account acc = as.checkAccountExist(username);
		if(acc !=null){
			as.delete(acc);
			return new ResponseEntity("Delete Sucessfull !!!",HttpStatus.OK);
		}
		
		return new ResponseEntity("Delete Fail !!!",HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/editAccount", method = RequestMethod.POST)
	public ResponseEntity editAccount(@RequestParam(value = "username", required = true) String  username,
			@RequestParam(value = "email", required = true) String  email,
			@RequestParam(value = "lastname") String  lastname,
			@RequestParam(value = "firstname") String  firstname,
			@RequestParam(value = "gender") String  gender,
			@RequestParam(value = "dateofbirth", required = true) String  dateofbirth,
			@RequestParam(value = "address") String  address,
			@RequestParam(value = "phone") String  phone,
			@RequestParam(value = "role",required = true) String  role) throws ParseException{
		Account accTest = as.checkAccountExist(username);
		if(accTest !=null){
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			accTest.setAddress(address);
			accTest.setDateOfBirth(df.parse(dateofbirth));
			accTest.setEmail(email);
			accTest.setGender(gender);
			accTest.setFirstName(firstname);
			accTest.setLastName(lastname);
			accTest.setPhone(phone);
			accTest.setRole(Boolean.parseBoolean(role));
			Account acc = as.update(accTest);
			return new ResponseEntity("Edit Sucessfull !!!",HttpStatus.OK);
		}
		
		return new ResponseEntity("Edit Fail !!!",HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/getListAllAccount", method = RequestMethod.GET)
	public ResponseEntity getAllUser(){
		List<Account> res = as.getAllAccount();
		if(res.size()>0){
			return new ResponseEntity(res,HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping (value = "/getAccountDetail", method = RequestMethod.POST)
	public ResponseEntity getAllAccount(@RequestParam(value = "username", required = true) String  username){
		Account acc = as.checkAccountExist(username);
		if(acc != null){
			return new ResponseEntity(acc,HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/addAccount", method = RequestMethod.POST)
	public ResponseEntity addAccount(@RequestParam(value = "username", required = true) String  username,
			@RequestParam(value = "password", required = true) String  password,
			@RequestParam(value = "email", required = true) String  email,
			@RequestParam(value = "lastname") String  lastname,
			@RequestParam(value = "firstname") String  firstname,
			@RequestParam(value = "gender") String  gender,
			@RequestParam(value = "dateofbirth", required = true) String  dateofbirth,
			@RequestParam(value = "address") String  address,
			@RequestParam(value = "phone") String  phone,
			@RequestParam(value = "role",required = true) String  role) throws ParseException{
		Account accTest = as.checkAccountExist(username);
		if(accTest == null){
			Account accTest1 = new Account();
			accTest1.setUsername(username);
			SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			accTest1.setAddress(address);
			accTest1.setDateOfBirth(df.parse(dateofbirth));
			accTest1.setEmail(email);
			accTest1.setFirstName(firstname);
			accTest1.setGender(gender);
			accTest1.setLastName(lastname);
			accTest1.setPassword(password);
			accTest1.setPhone(phone);
			accTest1.setRole(Boolean.parseBoolean(role));
			Account acc = as.register(accTest1);
			return new ResponseEntity("Add Sucessfull !!!",HttpStatus.OK);
		}
		
		return new ResponseEntity("Add Fail !!!",HttpStatus.OK);
	}
	
	
	@RequestMapping (value = "/changePasswordById", method = RequestMethod.POST)
	public ResponseEntity changePasswordById(@RequestParam(value = "userid", required = true) String  userid,
			@RequestParam(value = "password", required = true) String  password){
		Account acc = as.checkExistAccountById(Integer.parseInt(userid));
		if(acc != null){
			acc.setPassword(password);
			as.update(acc);
			return new ResponseEntity("Edit Sucessfull !!!",HttpStatus.OK);
		}
		return new ResponseEntity("Edit Fail !!!",HttpStatus.NOT_ACCEPTABLE);
	}
	
	@RequestMapping (value = "/changePasswordByUsername", method = RequestMethod.POST)
	public ResponseEntity changePasswordByUsername(@RequestParam(value = "username", required = true) String  username,
			@RequestParam(value = "password", required = true) String  password){
		Account acc = as.checkAccountExist(username);
		if(acc != null){
			acc.setPassword(password);
			as.update(acc);
			return new ResponseEntity("Edit Sucessfull !!!",HttpStatus.OK);
		}
		return new ResponseEntity("Edit Fail !!!",HttpStatus.NOT_ACCEPTABLE);
	}
	
	
	@RequestMapping (value = "/findListFriendByEmail", method = RequestMethod.POST)
	public ResponseEntity findFriendByEmail(@RequestParam(value = "email", required = true) String  email,
			@RequestParam(value = "username", required = true) String  username){
		List<Accrelate> res = as.findListAccountByWord(email,username);
		if(!res.isEmpty()){
			return new ResponseEntity(res,HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping (value = "/getAccountDetailById", method = RequestMethod.POST)
	public ResponseEntity getAccountDetailById(@RequestParam(value = "userid", required = true) String  userid){
		Account acc = as.checkExistAccountById(Integer.parseInt(userid));
		if(acc != null){
			return new ResponseEntity(acc,HttpStatus.OK);
		}
		return new ResponseEntity(null,HttpStatus.NOT_FOUND);
	}
	
}
