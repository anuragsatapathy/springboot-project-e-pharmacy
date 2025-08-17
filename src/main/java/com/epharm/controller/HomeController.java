package com.epharm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/contactus")
	public String contactUs() {
		
		return "contact";
		
	}
	
@GetMapping("/aboutus")
public String AboutUs() {
		
		return "About";
		
	}
}
