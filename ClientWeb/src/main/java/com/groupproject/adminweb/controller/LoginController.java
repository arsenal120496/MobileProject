package com.groupproject.adminweb.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.groupproject.adminweb.services.HelloWorldService;

@Controller
public class LoginController {
	private final HelloWorldService helloWorldService;

	@Autowired
	public LoginController(HelloWorldService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Map<String, Object> model) {
		//call api
		

		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password,
			HttpServletRequest request, final RedirectAttributes redirectAttributes) {
		String resultPage = "loginfail";
		//goi api
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		final String uri = "http://692f117b.ngrok.io/login";
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap();
		params.add("username", username);
		params.add("password", password);
		
		HttpEntity<MultiValueMap<String, String>> requestAPI = new HttpEntity<MultiValueMap<String, String>>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity result = restTemplate.postForEntity(uri, requestAPI, String.class);
		
		JsonObject obj = (JsonObject) new JsonParser().parse(result.getBody().toString());
		if((obj.get("checkLogin").getAsString().equalsIgnoreCase("true"))){
			request.getSession().setAttribute("username", username);
			resultPage = "redirect:getlocation";
		}
		return resultPage;	
	}
	
	

}
