package com.groupproject.adminweb.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.stylesheets.LinkStyle;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.object.Account;
import com.object.Location;

@Controller
public class GetLocationController {
	
	@RequestMapping(value = "/getlocation", method = RequestMethod.GET)
	public String getListLocation(Model model,HttpServletRequest request){
		String username = (String)request.getSession().getAttribute("username");
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://692f117b.ngrok.io/getListLocation";
		
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("username", username);

		HttpEntity<MultiValueMap<String, String>> requestAPI = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		String result = restTemplate.postForObject(uri, requestAPI, String.class);
		
		System.out.println(result);
		
		Type type = new TypeToken<ArrayList<Location>>() {}.getType();
		List<Location> listLocation = new Gson().fromJson(result, type);
		
		System.out.println(listLocation.get(0).getLatitude());
		model.addAttribute("username", username);
		model.addAttribute("listLocation", listLocation);
		
		
		
		return "mapclient";
	}

}
