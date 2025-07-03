package com.epharm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String login(Model m, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		User user = new User();
		m.addAttribute("user", user);
		if (error != null) {
			m.addAttribute("error", "Invalid email or password");
		}
		if (logout != null) {
			m.addAttribute("logout", "You have been loggedout");
		}
		return "login";

	}

	@GetMapping("/register")
	public String signup(Model m) {
		User users = new User();
		m.addAttribute("users", users);
		return "register";

	}

	@PostMapping("/register")
	public String signupForm(User user) {
		boolean isValid = userService.RegisterUser(user.getEmail(), user.getPassword(), user.getRole());
		if (isValid) {
			return "redirect:/login";
		} else {
			return "redirect:/register";
		}
	}
}
