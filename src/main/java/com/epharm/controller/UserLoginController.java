package com.epharm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epharm.entity.User;
import com.epharm.service.UserService;

@Controller
public class UserLoginController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/home")
	public String doHome() {
		return "home";
		
	}

	@GetMapping("/login")
	public String login(Model m) {
		User user = new User();
		m.addAttribute("user", user);
		return "login";

	}

	@PostMapping("/login")
	public String loginForm(User user) {
		boolean isValid = userService.loginUser(user.getEmail(), user.getPassword());
		if (isValid) {
			return "redirect:/productlist";
		} else {
			return "redirect:/login";
		}

	}

	@GetMapping("/register")
	public String signup(Model m) {
		User users = new User();
		m.addAttribute("users", users);
		return "register";

	}

	@PostMapping("/register")
	public String signupForm(User user) {
		boolean isValid = userService.RegisterUser(user.getEmail(),user.getPassword());
		if (isValid) {
			return "redirect:/login";
		} else {
			return "redirect:/register";
		}
	}
}
