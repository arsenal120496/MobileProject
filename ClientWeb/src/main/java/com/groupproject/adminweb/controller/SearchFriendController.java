package com.groupproject.adminweb.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import com.object.Account;
import com.object.Location;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.stylesheets.LinkStyle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class SearchFriendController {
	@RequestMapping(value = "/searchFriend", method = RequestMethod.GET)
	public String getListFriend(Model model,@RequestParam("value") String value,HttpServletRequest request){
		String username = (String)request.getSession().getAttribute("username");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		final String uri = "http://692f117b.ngrok.io/findListFriendByEmail";
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("email", value);
		params.add("username", username);
		HttpEntity<MultiValueMap<String, String>> requestAPI = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.postForObject(uri, requestAPI, String.class);
		
		System.out.println(result);
		
		Type type = new TypeToken<ArrayList<Account>>() {}.getType();
		List<Account> listFriend = new Gson().fromJson(result, type);
		
		for(int i=0;i<listFriend.size();i++){
			System.out.println(listFriend.get(i).getFirstName());
		}
		model.addAttribute("username", username);
		model.addAttribute("listFriend", listFriend);
		/*HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		final String uri = "http://692f117b.ngrok.io/findListFriendByEmail";
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("email", value);
		params.add("username", username);
		HttpEntity<MultiValueMap<String, String>> requestAPI = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.postForObject(uri, requestAPI, String.class);
		
		System.out.println(result);*/
		

		return "ListFriend";
	}
}
