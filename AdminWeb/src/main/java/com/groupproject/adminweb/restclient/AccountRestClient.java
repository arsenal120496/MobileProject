package com.groupproject.adminweb.restclient;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.groupproject.adminweb.dto.Account;
import com.groupproject.adminweb.dto.Location;
import com.groupproject.adminweb.dto.Relationship;

public class AccountRestClient implements Serializable{
	
	public static boolean checkLogin(String username, String password) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/login";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("username", username);
		params.add("password", password);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
		
		JsonObject obj = (JsonObject) new JsonParser().parse(result.getBody().toString());
		
		if (obj.get("checkLogin").getAsString().equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}
	
	public static List<Account> getListAccount(){
		
		final String uri = "http://d81bbbc5.ngrok.io/getListAllAccount";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity result = restTemplate.getForEntity(uri, String.class);
		
		Type type = new TypeToken<ArrayList<Account>>() {}.getType();
		List<Account> listAccount = new Gson().fromJson(result.getBody().toString(), type);
		
		return listAccount;
	}
	
	public static Account getAccountDetailByUsername(String username){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/getAccountDetail";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("username", username);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
		
		//parse to Account
		Gson gson = new Gson();
		return gson.fromJson(result.getBody().toString(), Account.class);
	}
	
	public static void changePasswordByUsername (String username, String password){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/changePasswordByUsername";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("username", username);
		params.add("password", password);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
		
	}
	
	public static void saveProfile (String username, String email, String lastname, 
			String firstname, String gender, String dateofbirth, String address, 
			String phone, String role){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/editAccount";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("username", username);
		params.add("email", email);
		params.add("lastname", lastname);
		params.add("firstname", firstname);
		params.add("gender", gender);
		params.add("dateofbirth", dateofbirth);
		params.add("address", address);
		params.add("phone", phone);
		params.add("role", role);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
	}
	
	public static void addNewUser (String username, String password, String email, String lastname, 
			String firstname, String gender, String dateofbirth, String address, 
			String phone, String role){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/addAccount";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("username", username);
		params.add("password", password);
		params.add("email", email);
		params.add("lastname", lastname);
		params.add("firstname", firstname);
		params.add("gender", gender);
		params.add("dateofbirth", dateofbirth);
		params.add("address", address);
		params.add("phone", phone);
		params.add("role", role);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
	}
	
	public static void deleteUser (String username){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/deleteAccount";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("username", username);


		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
	}
	
	public static Account getAccountDetailById(String userid){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/getAccountDetailById";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("userid", userid);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
		
		//parse to Account
		Gson gson = new Gson();
		return gson.fromJson(result.getBody().toString(), Account.class);
	}
	
	public static void changePasswordById (String userid, String password){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/changePasswordById";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("userid", userid);
		params.add("password", password);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
		
	}
	
	public static List<Relationship> getListFriends (String userid){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/getListRelationship";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("Requestuserid", userid);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
		
		Type type = new TypeToken<ArrayList<Relationship>>() {}.getType();
		return new Gson().fromJson(result.getBody().toString(), type);
		
	}
	
	public static List<Location> getListLocation (String userid){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/getListLocationById";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("userid", userid);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
		
		Type type = new TypeToken<ArrayList<Location>>() {}.getType();
		return new Gson().fromJson(result.getBody().toString(), type);
		
	}
	
	
	
	public static void addNewRelationship (String requestUserId, String responseUserId, String shareLocation){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/addRelationship";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("Requestuserid", requestUserId);
		params.add("Responseuserid", responseUserId);
		params.add("Sharelocation", shareLocation);
		params.add("Checkaccept", "0");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
	}
	
	public static void acceptRelationship (String requestUserId, String responseUserId){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/acceptFriend";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("Requestuserid", requestUserId);
		params.add("Responseuserid", responseUserId);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
	}
	
	public static Relationship getRelationship (String relationshipId){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/acceptFriend";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("Requestuserid", relationshipId);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
		
		return new Gson().fromJson(result.getBody().toString(), Relationship.class);
	}
	
	public static void unFriend (String requestUserId, String responseUserId){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/unFriend";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("Requestuserid", requestUserId);
		params.add("Responseuserid", responseUserId);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
	}
	
	public static void addNewLocation(String longitude, String latitude, String userid, String date) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/addLocationByUserId";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("longitude", longitude);
		params.add("latitude", latitude);
		params.add("userid", userid);
		params.add("date", date);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
	}
	
	public static void deleteLocation(String locationId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/deleteLocation";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("locationid", locationId);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
	}
	
	public static Location getLocation(String locationId) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://d81bbbc5.ngrok.io/getLocationById";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("locationid", locationId);

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, request, String.class);
		
		return new Gson().fromJson(result.getBody().toString(), Location.class);
	}

}
