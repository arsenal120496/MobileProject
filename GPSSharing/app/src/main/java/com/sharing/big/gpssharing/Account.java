package com.sharing.big.gpssharing;

import android.annotation.TargetApi;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;


public class Account implements Serializable {

	String Id;
	String Username;
	String Password;
	String Email;
	String Lastname;
	String Firstname;
	String Dateofbirth;
	String Gender;
	String Address;
	String Phone;
	String Role;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Account(String username, String password) {
		super();
		Username = username;
		Password = password;
	}	



	@RequiresApi(api = Build.VERSION_CODES.N)
	public Account(String Json) throws JSONException {
		JSONObject jsonObject = new JSONObject(Json);
		Id = jsonObject.getString("id");
		Username = jsonObject.getString("username");
		Password = jsonObject.getString("address");
		Email = jsonObject.getString("email");
		Lastname = jsonObject.getString("lastName");
		Firstname = jsonObject.getString("firstName");

		Long day = Long.parseLong(jsonObject.getString("dateOfBirth"));
		Date date=new Date(day);
		Dateofbirth = date.getDate()+"/"+(date.getMonth()+1)+"/"+date.getYear();

		Gender = jsonObject.getString("gender");
		Address = jsonObject.getString("address");
		Phone = jsonObject.getString("phone");
		Role = jsonObject.getString("role");
	}


	@Override
	public String toString() {
		return "Account{" +
				"Id='" + Id + '\'' +
				", Username='" + Username + '\'' +
				", Password='" + Password + '\'' +
				", Email='" + Email + '\'' +
				", Lastname='" + Lastname + '\'' +
				", Firstname='" + Firstname + '\'' +
				", Dateofbirth='" + Dateofbirth + '\'' +
				", Gender='" + Gender + '\'' +
				", Address='" + Address + '\'' +
				", Phone='" + Phone + '\'' +
				", Role='" + Role + '\'' +
				'}';
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getLastname() {
		return Lastname;
	}

	public void setLastname(String lastname) {
		Lastname = lastname;
	}

	public String getFirstname() {
		return Firstname;
	}

	public void setFirstname(String firstname) {
		Firstname = firstname;
	}

	public String getDateofbirth() {
		return Dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		Dateofbirth = dateofbirth;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}
}
