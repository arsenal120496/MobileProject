package com.groupproject.adminweb.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groupproject.adminweb.dto.Account;
import com.groupproject.adminweb.dto.Location;
import com.groupproject.adminweb.dto.Relationship;
import com.groupproject.adminweb.restclient.AccountRestClient;

@Controller
public class UserController {
	
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String showUserList( Model model, final RedirectAttributes redirectAttributes){
		List<Account> listAccount = AccountRestClient.getListAccount();
		model.addAttribute("listAccount", listAccount);
		return "userList";
	}
	
	@RequestMapping(value = "/viewProfile", method = RequestMethod.GET)
	public String viewProfile(HttpServletRequest request, Model model) {
		String username = (String) request.getSession().getAttribute("username");
		Account account = AccountRestClient.getAccountDetailByUsername(username);
		model.addAttribute("accountProfile", account);
		return "profile";
	}
	
	@RequestMapping(value = "/viewProfile/save", method = RequestMethod.POST)
	public String saveProfile(HttpServletRequest request, 
			@RequestParam("email") String email,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("gender") String gender,
			@RequestParam("dateofbirth") String dateofbirth,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam(value = "role", required = false, defaultValue = "false") String role,
			Model model, final RedirectAttributes redirectAttributes) {
		String username = (String) request.getSession().getAttribute("username");
		if (!role.equalsIgnoreCase("true")) {
			role = "false";
		}
		
		//parse Date
		DateFormat dfSource = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dfTarget = new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			dateofbirth = dfTarget.format(dfSource.parse(dateofbirth));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AccountRestClient.saveProfile(username, email,
				lastname, firstname, gender, dateofbirth, address, phone, role);
		return "redirect:/userList";
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String viewChangePasswordForm(HttpServletRequest request, Model model) {
		return "changePassword";
	}
	
	@RequestMapping(value = "/changePassword/change", method = RequestMethod.POST)
	public String changePassword(HttpServletRequest request, @RequestParam("password") String password, Model model) {
		String username = (String )request.getSession().getAttribute("username");
		AccountRestClient.changePasswordByUsername(username, password);
		return "changePassword";
	}
	
	@RequestMapping(value = "/user/addNew", method = RequestMethod.GET)
	public String viewAddNewForm(HttpServletRequest request, Model model) {
		return "userAddNew";
	}
	
	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	public String addNewUser(@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam("email") String email,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("gender") String gender,
			@RequestParam("dateofbirth") String dateofbirth,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam(value = "role", required = false, defaultValue = "false") String role, 
			Model model, final RedirectAttributes redirectAttributes) {
		
		if (!role.equalsIgnoreCase("true")) {
			role = "false";
		}
		
		//parse Date
		DateFormat dfSource = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dfTarget = new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			dateofbirth = dfTarget.format(dfSource.parse(dateofbirth));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AccountRestClient.addNewUser(username, password, email, lastname, 
				firstname, gender, dateofbirth, address, phone, role);
		return "redirect:/userList";
	}
	
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public String viewDetail(@PathVariable("id") String id, Model model) {
		Account account = AccountRestClient.getAccountDetailById(id);
		model.addAttribute("accountDetail", account);
		System.out.println(account.getAddress());
		model.addAttribute("userid", id);
		return "userInformation";
	}
	
	@RequestMapping(value = "/user/{id}/save", method = RequestMethod.POST)
	public String saveUserProfile(HttpServletRequest request, 
			@RequestParam("username") String username,
			@RequestParam("email") String email,
			@RequestParam("firstname") String firstname,
			@RequestParam("lastname") String lastname,
			@RequestParam("gender") String gender,
			@RequestParam("dateofbirth") String dateofbirth,
			@RequestParam("address") String address,
			@RequestParam("phone") String phone,
			@RequestParam(value = "role", required = false, defaultValue = "false") String role,
			Model model, final RedirectAttributes redirectAttributes) {
		if (!role.equalsIgnoreCase("true")) {
			role = "false";
		}
		
		//parse Date
		DateFormat dfSource = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat dfTarget = new SimpleDateFormat("MM/dd/yyyy");
		
		try {
			dateofbirth = dfTarget.format(dfSource.parse(dateofbirth));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AccountRestClient.saveProfile(username, email,
				lastname, firstname, gender, dateofbirth, address, phone, role);
		return "redirect:/userList";
	}
	
	@RequestMapping(value = "/user/{id}/changePassword", method = RequestMethod.GET)
	public String viewChangePasswordForm(@PathVariable("id") String id, Model model){
		model.addAttribute("userid", id);
		return "userChangePassword";
	}
	
	@RequestMapping(value = "/user/{id}/changePassword/change", method = RequestMethod.POST)
	public String changeUserPassword(@PathVariable("id") String id,
			@RequestParam("password") String password, Model model,
			final RedirectAttributes redirectAttributes){
		AccountRestClient.changePasswordById(id, password);
		return "redirect:/userList";
	}
	
	@RequestMapping(value = "/user/{username}/delete", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("username") String username, Model model,
			final RedirectAttributes redirectAttributes){
		AccountRestClient.deleteUser(username);
		return "redirect:/userList";
	}
	
	@RequestMapping(value = "/user/{id}/friend", method = RequestMethod.GET)
	public String viewListFriend(@PathVariable("id") String id, Model model){
		List<Relationship> listFriend = new ArrayList<Relationship>();
		try {
			listFriend = AccountRestClient.getListFriends(id);
		} catch (Exception e) {
		}
		
		model.addAttribute("userid", id);
		model.addAttribute("listFriend", listFriend);
		
		return "userFriends";
	}
	
	@RequestMapping(value = "/user/{id}/friend/{relationshipId}", method = RequestMethod.GET)
	public String viewRelationship(@PathVariable("relationshipId") String relationshipId,
			@PathVariable("id") String id, Model model){
		
		Relationship relationship = AccountRestClient.getRelationship(relationshipId);
		
		model.addAttribute("userid", id);
		model.addAttribute("relationship", relationship);
		
		return "userFriendEdit";
	}
	
	@RequestMapping(value = "/user/{id}/friend/{relationshipId}/edit", method = RequestMethod.GET)
	public String editRelationship(@PathVariable("relationshipId") String relationshipId,
			@PathVariable("id") String id, Model model){
		
		Relationship relationship = AccountRestClient.getRelationship(relationshipId);
		
		model.addAttribute("userid", id);
		model.addAttribute("relationship", relationship);
		
		return "userFriendEdit";
	}
	
	@RequestMapping(value = "/user/{id}/newFriend", method = RequestMethod.GET)
	public String viewAddNewFriendForm(@PathVariable("id") String id, Model model){
		model.addAttribute("userid", id);
		return "userFriendAddNew";
	}
	
	@RequestMapping(value = "/user/{id}/newFriend/add", method = RequestMethod.POST)
	public String addNewFriend(@PathVariable("id") String id,
			@RequestParam("responseId") String responseId,
			@RequestParam("shareLocation") String shareLocation, Model model,
			final RedirectAttributes redirectAttributes){
		AccountRestClient.addNewRelationship(id, responseId, shareLocation);
		AccountRestClient.acceptRelationship(id, responseId);
		return "redirect:/user/" + id + "/friend";
	}
	
	@RequestMapping(value = "/user/{id}/unFriend/{responseUserId}", method = RequestMethod.GET)
	public String unFriend(@PathVariable("id") String id,
			@PathVariable("responseUserId") String responseUserId, Model model,
			final RedirectAttributes redirectAttributes){
		AccountRestClient.unFriend(id, responseUserId);
		return "redirect:/user/" + id + "/friend";
	}
	
	@RequestMapping(value = "/user/{id}/location", method = RequestMethod.GET)
	public String viewListLocation(@PathVariable("id") String id, Model model){
		List<Location> listLocation = new ArrayList<>();
		try {
			listLocation = AccountRestClient.getListLocation(id);
		} catch (Exception e) {
			
		}
		
		model.addAttribute("userid", id);
		model.addAttribute("listLocation", listLocation);
		
		return "userLocations";
	}
	
	@RequestMapping(value = "/user/{id}/location/{locationId}", method = RequestMethod.GET)
	public String viewLocation(@PathVariable("id") String id, @PathVariable("locationId") String locationId, Model model){
		Location location = AccountRestClient.getLocation(locationId);
		model.addAttribute("location", location);
		model.addAttribute("userid", id);
		return "userLocationEdit";
	}
	
	@RequestMapping(value = "/user/{id}/location/{locationId}/edit", method = RequestMethod.POST)
	public String editLocation(@PathVariable("id") String id, 
			@PathVariable("locationId") String locationId, 
			@RequestParam("longitude") String longitude,
			@RequestParam("latitude") String latitude,
			@RequestParam("time") String time,
			Model model, final RedirectAttributes redirectAttributes){
		
		return "redirect:/user/" + id + "/location";
	}
	
	@RequestMapping(value = "/user/{id}/location/addNew", method = RequestMethod.GET)
	public String viewAddNewLocationForm(@PathVariable("id") String id, Model model){
		model.addAttribute("userid", id);
		return "userLocationAddNew";
	}
	
	@RequestMapping(value = "/user/{id}/location/addNew/add", method = RequestMethod.POST)
	public String addNewLocation(@PathVariable("id") String id,
			@RequestParam("longitude") String longitude,
			@RequestParam("latitude") String latitude,
			@RequestParam("time") String time,
			Model model, final RedirectAttributes redirectAttributes){
		AccountRestClient.addNewLocation(longitude, latitude, id, time);
		return "redirect:/user/" + id + "/location";
	}
	
	@RequestMapping(value = "/user/{id}/location/delete/{locationId}", method = RequestMethod.GET)
	public String deleteLocation(@PathVariable("id") String id,
			@PathVariable("locationId") String locationId, Model model,
			final RedirectAttributes redirectAttributes){
		AccountRestClient.deleteLocation(locationId);
		return "redirect:/user/" + id + "/location";
	}
	
}
