package com.groupproject.adminweb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groupproject.adminweb.dto.Account;
import com.groupproject.adminweb.restclient.AccountRestClient;

@Controller
public class WelcomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, @RequestParam String username,
						@RequestParam String password,
						Model model,
						final RedirectAttributes redirectAttributes) {
		boolean checkLogin = AccountRestClient.checkLogin(username, password);
		if (!checkLogin) {
			model.addAttribute("error", "Username or Password is not valid!");
			return "index";
		}
		request.getSession(true).setAttribute("username", username);
		return "redirect:userList";
	}
}
