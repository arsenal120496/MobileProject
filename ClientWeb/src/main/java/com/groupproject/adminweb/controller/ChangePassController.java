package com.groupproject.adminweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChangePassController {
	@RequestMapping(value = "/changePass", method = RequestMethod.POST)
	public String getListFriend(Model model,@RequestParam("cpass") String currentPass,
			@RequestParam("npass") String newPass,HttpServletRequest request){
		String username = (String)request.getSession().getAttribute("username");
		return "mapclient";
	}
}
