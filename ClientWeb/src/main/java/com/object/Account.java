package com.object;

import java.io.Serializable;
import java.util.Date;


public class Account implements Serializable {


	int id;
	String username;
	String password;
	String email;
	String lastName;
	String firstName;
	Long dateOfBirth;
	String gender;
	String address;
	String phone;
	boolean role;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(String username, String password, String email, String lastname, String firstnam, Long dateofbi,
			String gender, String address, String phone, boolean role) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.lastName = lastname;
		this.firstName = firstnam;
		this.dateOfBirth = dateofbi;
		this.gender = gender;
		this.address = address;
		this.phone = phone;
		this.role = role;
	}

	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public Long getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Long dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isRole() {
		return role;
	}

	public void setRole(boolean role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}
	
	
	
}
