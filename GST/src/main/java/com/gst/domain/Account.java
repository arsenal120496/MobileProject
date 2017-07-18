package com.gst.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account implements Serializable {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int Id;
	String Username;
	String Password;
	String Email;
	String Lastname;
	String Firstname;
	Date Dateofbirth;
	String Gender;
	String Address;
	String Phone;
	boolean Role;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Account(String username, String password) {
		super();
		Username = username;
		Password = password;
	}	


	public Account(String username) {
		super();
		Username = username;
	}



	public Account(String username, String password, String email, String lastName, String firstName, Date dateOfBirth,
			String gender, String address, String phone, boolean role) {
		super();
		Username = username;
		Password = password;
		Email = email;
		Lastname = lastName;
		Firstname = firstName;
		Dateofbirth = dateOfBirth;
		Gender = gender;
		Address = address;
		Phone = phone;
		Role = role;
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

	public String getLastName() {
		return Lastname;
	}

	public void setLastName(String lastName) {
		Lastname = lastName;
	}

	public String getFirstName() {
		return Firstname;
	}

	public void setFirstName(String firstName) {
		Firstname = firstName;
	}

	public Date getDateOfBirth() {
		return Dateofbirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		Dateofbirth = dateOfBirth;
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

	public boolean isRole() {
		return Role;
	}

	public void setRole(boolean role) {
		Role = role;
	}


	public int getId() {
		return Id;
	}
	
	
	
}
